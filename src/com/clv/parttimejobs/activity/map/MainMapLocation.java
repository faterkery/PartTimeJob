package com.clv.parttimejobs.activity.map;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.clv.parttimejobs.activity.mainlayout.MainActivity;
import com.clv.parttimejobs.activity.message.personal.Message_person_activity;
import com.clv.parttimejobs.entity.map.InputTask;
import com.clv.parttimejobs.view.adapter.map.SearchAdapter;
import com.clv.homework.R;
import com.clv.homework.R.id;
import com.clv.homework.R.layout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainMapLocation extends Activity implements
		AMap.OnMapLongClickListener, AMap.OnMapScreenShotListener,
		LocationSource, AMapLocationListener, TextWatcher, OnItemClickListener {

	private String province = "";
	// 显示地图需要的变量
	private MapView mapView;// 地图控件
	private AMap aMap;// 地图对象
	private EditText text;
	private TextView text01;
	private TextView text02;
	private ListView listview;
	private Button map_button;
	SearchAdapter mAdapter;
	private ArrayAdapter<String> adapter;
	// 定位需要的声明
	private AMapLocationClient mLocationClient = null;// 定位发起端
	private AMapLocationClientOption mLocationOption = null;// 定位参数
	private OnLocationChangedListener mListener = null;// 定位监听器
	private List<String> list = new ArrayList<String>();
	// 标识，用于判断是否只显示一次定位信息和用户重新定位
	private boolean isFirstLoc = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_map_location);

		text = (EditText) findViewById(R.id.map_edittext);
		text01 = (TextView) findViewById(R.id.map_textview_address);
		text02 = (TextView) findViewById(R.id.map_textview_address_xx);
		map_button = (Button) findViewById(R.id.map_button);
		InnerOnClickListener i = new InnerOnClickListener();
		map_button.setOnClickListener(i);

//		Spinner spinner = (Spinner) findViewById(R.id.spinner2);
//		list.add("舟山");
//		list.add("北京");
//		list.add("上海");
//		list.add("深圳");
//		list.add("福州");
//		list.add("厦门");
//		adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_spinner_item, list);
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner.setAdapter(adapter);

		listview = (ListView) findViewById(R.id.listmap);
		text.addTextChangedListener(this);
		mAdapter = new SearchAdapter(this);
		listview.setAdapter(mAdapter);
		// 显示地图
		mapView = (MapView) findViewById(R.id.map);
		// 必须要写
		mapView.onCreate(savedInstanceState);
		// 获取地图对象
		aMap = mapView.getMap();

		// 设置显示定位按钮 并且可以点击
		UiSettings settings = aMap.getUiSettings();
		// 设置定位监听
		aMap.setLocationSource(this);
		// 是否显示定位按钮
		settings.setMyLocationButtonEnabled(true);
		// 是否可触发定位并显示定位层
		aMap.setMyLocationEnabled(true);
		aMap.moveCamera(CameraUpdateFactory.zoomTo(14));

		// //定位的小图标 默认是蓝点 这里自定义一团火，其实就是一张图片
		// MyLocationStyle myLocationStyle = new MyLocationStyle();
		// myLocationStyle.radiusFillColor(android.R.color.transparent);
		// myLocationStyle.strokeColor(android.R.color.transparent);
		// aMap.setMyLocationStyle(myLocationStyle);

		// 开始定位
		initLoc();
	}

	private class InnerOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.map_button: {
				Intent intent = new Intent();
				intent.putExtra("province", province);
				setResult(101, intent);
				MainMapLocation.this.finish();
				break;
			}
			}
		}

	}

	// 定位
	private void initLoc() {
		// 初始化定位
		mLocationClient = new AMapLocationClient(getApplicationContext());
		// 设置定位回调监听
		mLocationClient.setLocationListener(this);
		// 初始化定位参数
		mLocationOption = new AMapLocationClientOption();
		// 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
		mLocationOption
				.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
		// 设置是否返回地址信息（默认返回地址信息）
		mLocationOption.setNeedAddress(true);
		// 设置是否只定位一次,默认为false
		mLocationOption.setOnceLocation(false);
		// 设置是否强制刷新WIFI，默认为强制刷新
		mLocationOption.setWifiActiveScan(true);
		// 设置是否允许模拟位置,默认为false，不允许模拟位置
		mLocationOption.setMockEnable(false);
		// 设置定位间隔,单位毫秒,默认为2000ms
		mLocationOption.setInterval(2000);
		// 给定位客户端对象设置定位参数
		mLocationClient.setLocationOption(mLocationOption);
		// 启动定位
		mLocationClient.startLocation();
	}

	public void onLocationChanged(AMapLocation amapLocation) {
		if (amapLocation != null) {
			if (amapLocation.getErrorCode() == 0) {
				// 定位成功回调信息，设置相关消息
				amapLocation.getLocationType();// 获取当前定位结果来源，如网络定位结果，详见官方定位类型表
				amapLocation.getLatitude();// 获取纬度
				amapLocation.getLongitude();// 获取经度
				amapLocation.getAccuracy();// 获取精度信息
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = new Date(amapLocation.getTime());
				df.format(date);// 定位时间
				amapLocation.getAddress();// 地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
				amapLocation.getCountry();// 国家信息
				amapLocation.getProvince();// 省信息
				province = amapLocation.getCity();// 城市信息
				amapLocation.getDistrict();// 城区信息
				amapLocation.getStreet();// 街道信息
				amapLocation.getStreetNum();// 街道门牌号信息
				amapLocation.getCityCode();// 城市编码
				amapLocation.getAdCode();// 地区编码

				// 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
				if (isFirstLoc) {
					// 设置缩放级别
					// aMap.moveCamera(CameraUpdateFactory.zoomTo(22));
					// 将地图移动到定位点
					aMap.moveCamera(CameraUpdateFactory
							.changeLatLng(new LatLng(
									amapLocation.getLatitude(), amapLocation
											.getLongitude())));
					// 点击定位按钮 能够将地图的中心移动到定位点
					mListener.onLocationChanged(amapLocation);
					// 添加图钉
					aMap.addMarker(getMarkerOptions(amapLocation));
					// getMarkerOptions(amapLocation);
					// 获取定位信息
					StringBuffer buffer = new StringBuffer();
					text01.setText(amapLocation.getCountry() + " "
							+ amapLocation.getProvince() + " "
							+ amapLocation.getCity());
					text02.setText(amapLocation.getCity() + ""
							+ amapLocation.getProvince() + ""
							+ amapLocation.getDistrict() + ""
							+ amapLocation.getStreet() + ""
							+ amapLocation.getStreetNum());
					buffer.append(amapLocation.getCountry() + ""
							+ amapLocation.getProvince() + ""
							+ amapLocation.getCity() + ""
							+ amapLocation.getProvince() + ""
							+ amapLocation.getDistrict() + ""
							+ amapLocation.getStreet() + ""
							+ amapLocation.getStreetNum());
					Toast.makeText(getApplicationContext(), buffer.toString(),
							Toast.LENGTH_LONG).show();
					isFirstLoc = false;
					aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
				}

			} else {
				// 显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
				Log.e("AmapError",
						"location Error, ErrCode:"
								+ amapLocation.getErrorCode() + ", errInfo:"
								+ amapLocation.getErrorInfo());

				Toast.makeText(getApplicationContext(), "定位失败",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	// 自定义一个图钉，并且设置图标，当我们点击图钉时，显示设置的信息
	private MarkerOptions getMarkerOptions(AMapLocation amapLocation) {
		// 自定义系统定位小蓝点
		MarkerOptions options = new MarkerOptions();
		// 图标
		// options.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fire));
		// 位置
		options.position(new LatLng(amapLocation.getLatitude(), amapLocation
				.getLongitude()));
		StringBuffer buffer = new StringBuffer();
		buffer.append(amapLocation.getCountry() + ""
				+ amapLocation.getProvince() + "" + amapLocation.getCity() + ""
				+ amapLocation.getDistrict() + "" + amapLocation.getStreet()
				+ "" + amapLocation.getStreetNum());
		// 标题
		options.title(buffer.toString());
		// 子标题
		// options.snippet("这里好火");
		// 设置多少帧刷新一次图片资源
		options.period(60);
		return options;
	}

	// 激活定位
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;

	}

	// 停止定位
	@Override
	public void deactivate() {
		mListener = null;
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	/**
	 * 长按地图进行截屏
	 *
	 * @param latLng
	 */
	@Override
	public void onMapLongClick(LatLng latLng) {

		// 设置截屏监听接口，截取地图可视区域
		// 需要传入一个 AMap.OnMapLongClickListener 接口的实现者
		aMap.getMapScreenShot(this);
	}

	/**
	 * 截屏回调方法 保存截图
	 */

	@Override
	public void onMapScreenShot(Bitmap bitmap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			// 保存在SD卡根目录下，图片为png格式。
			FileOutputStream fos = new FileOutputStream(
					Environment.getExternalStorageDirectory() + "/test_"
							+ sdf.format(new Date()) + ".png");
			boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			try {
				fos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (b)

				Toast.makeText(MainMapLocation.this, "截屏成功", Toast.LENGTH_LONG)
						.show();
			else {
				Toast.makeText(MainMapLocation.this, "截屏失败", Toast.LENGTH_LONG)
						.show();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMapScreenShot(Bitmap arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		InputTask.getInstance(this, mAdapter, aMap).onSearch(s.toString(), "");
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// Item点击事件处理
	}

}
