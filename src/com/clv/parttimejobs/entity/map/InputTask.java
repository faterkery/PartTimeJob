package com.clv.parttimejobs.entity.map;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.clv.parttimejobs.view.adapter.map.SearchAdapter;

public class InputTask implements OnPoiSearchListener {
	private static InputTask mInstance;
	private SearchAdapter mAdapter;
	private PoiSearch mSearch;
	private Context mContext;
	private AMap aMap;// 地图对象
	double lon;
	double lat;
	String title;
	String text;

	private InputTask(Context context, SearchAdapter adapter, AMap aMap) {
		this.mContext = context;
		this.mAdapter = adapter;
		this.aMap = aMap;
	}

	/**
	 * 获取实例
	 * 
	 * @param context
	 *            上下文
	 * @param adapter
	 *            数据适配器
	 * @return
	 */
	public static InputTask getInstance(Context context, SearchAdapter adapter,
			AMap aMap) {
		if (mInstance == null) {
			synchronized (InputTask.class) {
				if (mInstance == null) {
					mInstance = new InputTask(context, adapter, aMap);
				}
			}
		}
		return mInstance;
	}

	/**
	 * 设置数据适配器
	 * 
	 * @param adapter
	 */
	public void setAdapter(SearchAdapter adapter) {
		this.mAdapter = adapter;
	}

	/**
	 * POI搜索
	 * 
	 * @param key
	 *            关键字
	 * @param city
	 *            城市
	 */
	public void onSearch(String key, String city) {

		// POI搜索条件
		PoiSearch.Query query = new PoiSearch.Query(key, "", city);
		mSearch = new PoiSearch(mContext, query);
		// 设置异步监听
		mSearch.setOnPoiSearchListener(this);
		// 查询POI异步接口
		mSearch.searchPOIAsyn();
	}

	/**
	 * 异步搜索回调
	 */
	@Override
	public void onPoiSearched(PoiResult poiResult, int rCode) {
		if (poiResult != null) {
			ArrayList<AddressBean> data = new ArrayList<AddressBean>();
			ArrayList<PoiItem> items = poiResult.getPois();
			for (PoiItem item : items) {
				// 获取经纬度对象
				LatLonPoint llp = item.getLatLonPoint();
				lon = llp.getLongitude();
				lat = llp.getLatitude();
				// 获取标题
				title = item.getTitle();
				// 获取内容
				text = item.getSnippet();
				data.add(new AddressBean(lon, lat, title, text));
			}
			mAdapter.setData(data);
			mAdapter.notifyDataSetChanged();

			aMap.addMarker(getMarkerOptions());
		}
	}

	// 自定义一个图钉，并且设置图标，当我们点击图钉时，显示设置的信息
	private MarkerOptions getMarkerOptions() {
		// 自定义系统定位小蓝点
		MarkerOptions options = new MarkerOptions();
		// 图标
		// options.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fire));
		// 位置
		options.position(new LatLng(lon, lat));
		// 标题
		options.title(title);
		// 子标题
		options.snippet(text);
		// 设置多少帧刷新一次图片资源
		options.period(60);
		return options;
	}

	@Override
	public void onPoiItemSearched(PoiItem arg0, int arg1) {
		// TODO Auto-generated method stub

	}
}