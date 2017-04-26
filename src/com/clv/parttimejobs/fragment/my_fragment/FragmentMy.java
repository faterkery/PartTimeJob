package com.clv.parttimejobs.fragment.my_fragment;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.clv.parttimejobs.activity.map.MainMapLocation;
import com.clv.parttimejobs.activity.minelayout.aboutus.AboutUs;
import com.clv.parttimejobs.activity.minelayout.contactus.AboutSoftware;
import com.clv.parttimejobs.activity.minelayout.creditlife.Mycredit;
import com.clv.parttimejobs.activity.minelayout.dailyattendance.QdActivity;
import com.clv.parttimejobs.activity.minelayout.identity.Sfz_activity;
import com.clv.parttimejobs.activity.minelayout.identity.Sfzrz;
import com.clv.parttimejobs.activity.minelayout.login.Login;
import com.clv.parttimejobs.activity.minelayout.login.SelectPicPopupWindow;
import com.clv.parttimejobs.activity.minelayout.mycollection.MySCactivity;
import com.clv.parttimejobs.activity.minelayout.mywallet.MyWalletActivity;
import com.clv.parttimejobs.activity.minelayout.resume.Wdjl_activity;
import com.clv.parttimejobs.activity.minelayout.resumeexperience.JZjlactivity;
import com.clv.parttimejobs.activity.minelayout.scheduling.Xcapactivity;
import com.clv.parttimejobs.activity.minelayout.schoollife.Myschoollayout;
import com.clv.parttimejobs.activity.minelayout.set.ST_mainActivity;
import com.clv.parttimejobs.activity.minelayout.specialattention.MyGZ_activity;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutefile.UploadImg;
import com.clv.parttimejobs.util.encrypt.MarkKey;
import com.clv.parttimejobs.view.ui.customview.CircleImageView;
import com.clv.parttimejobs.view.ui.customview.UserDefineScrollView;
import com.clv.homework.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class FragmentMy extends Fragment {

	private Context context;
	private View view;// 需要返回的布局
	private ListView mylist;
	private Button admin_headimage;
	private Button my_imagehead;
	private CircleImageView head_image;
	private UserDefineScrollView contentView;
	private PopupWindow popupWindow;
	private int phone_DPI;
	public static boolean isLogin = false;
	private Button mywallet_button;
	private Button jzxx_button;
	private Button xysh_button;
	private Button jzjl_button;
	private Button sfrz_button;
	private Button tbgz_button;
	private Button wdsc_button;
	private Button myschool_button;
	private Button xcap_button;
	private Button mrqd_button;
	private Button connectiontoour;
	private Button aboutsoft;
	private ImageButton my_imageview_shezhi;

	private DisplayImageOptions options;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setRetainInstance(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater
				.inflate(R.layout.activity_fragment_my, container, false);
		context = this.getActivity();
		my_imagehead = (Button) view.findViewById(R.id.my_toLogintextView1);
		admin_headimage = (Button) view.findViewById(R.id.btn_5);
		head_image = (CircleImageView) view.findViewById(R.id.btn_circleview);
		contentView = (UserDefineScrollView) view
				.findViewById(R.id.my_one_scroll);
		contentView.setVerticalScrollBarEnabled(false);// 隐藏进度条

		mywallet_button = (Button) view.findViewById(R.id.mywallet_button);
		jzxx_button = (Button) view.findViewById(R.id.jzxx_button);
		xysh_button = (Button) view.findViewById(R.id.xysh_button);
		jzjl_button = (Button) view.findViewById(R.id.jzjl_button);
		sfrz_button = (Button) view.findViewById(R.id.sfrz_button);
		tbgz_button = (Button) view.findViewById(R.id.tbgz_button);//
		wdsc_button = (Button) view.findViewById(R.id.wdsc_button);//
		myschool_button = (Button) view.findViewById(R.id.myschool_button);
		xcap_button = (Button) view.findViewById(R.id.xcap_button);
		mrqd_button = (Button) view.findViewById(R.id.mrqd_button);
		connectiontoour = (Button) view
				.findViewById(R.id.my_bottom_button_lianxiwomen);
		aboutsoft = (Button) view
				.findViewById(R.id.my_bottom_button_guanyuwomen);
		my_imageview_shezhi = (ImageButton) view
				.findViewById(R.id.my_imageview_shezhi);

		InnerOnClickListener i = new InnerOnClickListener();
		mywallet_button.setOnClickListener(i);
		my_imagehead.setOnClickListener(i);
		admin_headimage.setOnClickListener(i);
		mrqd_button.setOnClickListener(i);
		connectiontoour.setOnClickListener(i);
		aboutsoft.setOnClickListener(i);
		tbgz_button.setOnClickListener(i);
		wdsc_button.setOnClickListener(i);
		myschool_button.setOnClickListener(i);
		sfrz_button.setOnClickListener(i);
		jzxx_button.setOnClickListener(i);
		jzjl_button.setOnClickListener(i);
		xysh_button.setOnClickListener(i);
		xcap_button.setOnClickListener(i);
		my_imageview_shezhi.setOnClickListener(i);

		UserDao user = new UserDao(context);
		// user.detele();
		if (user.detailTable()) {
			String username = user.detailLogin();
			if (username.equals("???")) {
				username = "用户123";
			}
			my_imagehead.setText(username);
			isLogin = true;
			new Thread(new MyThread1()).start();
		}

		// 读取图片
		new Thread(new Runnable() {

			@Override
			public void run() {
				// 耗时操作，完成之后发送消息给Handler，完成UI更新；
				mHandler.sendEmptyMessage(0);
				// 需要数据传递，用下面方法；
				Message msg = new Message();
				msg.obj = "数据";// 可以是基本类型，可以是对象，可以是List、map等；
				mHandler.sendMessage(msg);
			}

		}).start();

		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.nologin_headimage) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.nologin_headimage) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.nologin_headimage) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象

		return view;
	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				// 完成主界面更新,拿到数据
				String data = (String) msg.obj;
				Bitmap b = getImage();
				if (b != null) {
					head_image.setImageBitmap(getImage());
				}
				break;
			default:
				break;
			}
		}

		public Bitmap getImage() {
			Bitmap b = null;
			String dir = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/images/head_image.jpg";
			File f = new File(dir);
			f.delete();
			if (f.exists()) {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 2;// 图片宽高都为原来的二分之一，即图片为原来的四分之一
				b = BitmapFactory.decodeFile(dir, options);
			}
			return b;

		}
	};

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private class InnerOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.mywallet_button: {
				Intent intent = new Intent(context, MyWalletActivity.class);
				startActivity(intent);
				break;
			}
			case R.id.my_imageview_shezhi: {
				Intent intent = new Intent(context, ST_mainActivity.class);
				startActivityForResult(intent, 3);
				break;
			}
			case R.id.my_toLogintextView1: {
				if (isLogin) {
					Intent intent = new Intent(context, Wdjl_activity.class);
					startActivityForResult(intent, 4);
				} else {
					createView(v);
				}
				//
				break;
			}
			case R.id.btn_5: {
				if (isLogin) {
					Intent intent2 = new Intent(context,
							SelectPicPopupWindow.class);
					startActivityForResult(intent2, 2);
				} else {
					createView(v);
				}
				break;
			}
			case R.id.my_bottom_button_lianxiwomen: {
				Intent intent = new Intent(context, AboutUs.class);
				startActivity(intent);
				break;
			}
			case R.id.my_bottom_button_guanyuwomen: {
				Intent intent = new Intent(context, AboutSoftware.class);
				startActivity(intent);
				break;
			}
			case R.id.jzxx_button: {
				if (isLogin) {
					Intent intent = new Intent(context, Wdjl_activity.class);
					startActivity(intent);
				} else {
					createView(v);
				}
				break;
			}
			case R.id.xysh_button: {
				if (isLogin) {
					Intent intent = new Intent(context, Mycredit.class);
					startActivity(intent);
				} else {
					createView(v);
				}
				break;
			}
			case R.id.jzjl_button: {
				if (isLogin) {
					Intent intent = new Intent(context, JZjlactivity.class);
					startActivity(intent);
				} else {
					createView(v);
				}
				break;
			}
			case R.id.sfrz_button: {
				if (isLogin) {
				Intent intent = new Intent(context, Sfzrz.class);
				startActivity(intent);
				} else {
					createView(v);
				}
				break;
			}
			case R.id.tbgz_button: {
				if (isLogin) {
				Intent intent = new Intent(context, MyGZ_activity.class);
				startActivity(intent);
				} else {
					createView(v);
				}
				break;
			}
			case R.id.wdsc_button: {
				if (isLogin) {
				Intent intent = new Intent(context, MySCactivity.class);
				startActivity(intent);
				} else {
					createView(v);
				}
				break;
			}
			case R.id.myschool_button: {
				Intent intent = new Intent(context, Myschoollayout.class);
				startActivity(intent);
				break;
			}
			case R.id.xcap_button: {
				Intent intent = new Intent(context, Xcapactivity.class);
				startActivity(intent);
				break;
			}
			case R.id.mrqd_button: {
				if (isLogin) {
				Intent intent = new Intent(context, QdActivity.class);
				startActivity(intent);
				} else {
					createView(v);
				}
				break;
			}

			}
		}

	}

	public Bitmap removeBitMap(Bitmap bm, int newWidth, int newHeight) {
		// 获得图片的宽高
		int widthi = bm.getWidth();
		int heighti = bm.getHeight();
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / widthi;
		float scaleHeight = ((float) newHeight) / heighti;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, widthi, heighti, matrix,
				true);
		return newbm;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == 100) {
				UserDao user = new UserDao(context);
				String username = user.detailLogin();
				my_imagehead.setText(username);
				isLogin = true;
				createGotoFinishZL(view);
				new Thread(new MyThread1()).start();
			}
		} else if (requestCode == 2) {
			if (resultCode == 110) {
				try {
					// BitmapFactory.decodeStream(this.getActivity().getContentResolver().openInputStream(data.getData()));
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize = 4;// 图片大小，设置越大，图片越不清晰，占用空间越小
					Bitmap bitmap = BitmapFactory.decodeStream(this
							.getActivity().getContentResolver()
							.openInputStream(data.getData()), null, options);
					head_image.setImageBitmap(bitmap);
					MyHandler m = new MyHandler(bitmap);
					m.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (resultCode == 101) {
				if (data != null) {
					Uri imageUri = data.getData();
					Bitmap bmp = null;
					bmp = lessenUriImage(getPath(imageUri));
					head_image.setImageBitmap(bmp);
				}
			}

		} else if (requestCode == 3) {
			if (resultCode == 100) {
				UserDao user = new UserDao(context);
				String username = user.detailLogin();
				my_imagehead.setText(username);
				if (user.isdetailLogin())
					isLogin = true;
				new Thread(new MyThread1()).start();
			} else if (resultCode == 101) {
				isLogin = false;
				my_imagehead.setText("未登陆");
				head_image.setImageResource(R.drawable.nologin_headimage);
			}
		} else if (requestCode == 4) {
			UserDao user = new UserDao(context);
			if (user.detailTable()) {
				String username = user.detailLogin();
				if (username.equals("???")) {
					username = "用户123";
				}
				my_imagehead.setText(username);
				isLogin = true;
				new Thread(new MyThread1()).start();
			}
		}
	}

	private void toUploadFile(String picPath) {
		System.out.println(picPath);
		try {
			UserDao user = new UserDao(context);
			User u = user.detailMessage();
			long id = u.getUser_id();
			String security_key = u.getSecurity_key();
			MarkKey re = new MarkKey();

			String fileKey = "file";
			// UploadUtil uploadUtil = UploadUtil.getInstance();
			// uploadUtil.setOnUploadProcessListener(this); //设置监听器监听上传状态

			Map<String, String> params = new HashMap<String, String>();
			String timekey = re.builderTimeKey();
			String id_en = re.builderId(id + "", security_key);
			String id_enery = re.Encrypt(id_en, timekey);
			// params.put("enId", URLEncoder.encode(id_enery, "UTF-8"));
			params.put("enId", id_enery);
			String url = new UriFactory().getModifyUserHeadPortraitUrl();
			// uploadUtil.uploadFile( picPath,fileKey, url,params);
			Map<String, String> fileMap = new HashMap<String, String>();
			fileMap.put("file", picPath);
			String ret = UploadImg.formUpload(url, params, fileMap);
			System.out.println(ret);
			ret = ret.trim();
			Message message = new Message();
			message.what = 2;
			handler1.sendMessage(message);
			String msg = JSONAnalysis(ret);
			String imgurl = JSONAnalysisMessage(ret);
			user.updataHeadImg(imgurl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class MyThread1 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String phone = "";
		String line = "";
		StringBuilder response;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				URL url = new URL(
						new UriFactory().getHeadPortraitUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				String msg = JSONAnalysis(response.toString().trim());
				System.out.println("=======================" + msg);
				UserDao user = new UserDao(context);
				String imgutl = user.detailImgUrl();
				String imageurl = msg + imgutl;
				System.out.println(imageurl);
				Message message = new Message();
				Bundle data = new Bundle();
				data.putString("imgurl", imageurl);
				message.what = 1;
				message.setData(data);
				handler1.sendMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (connection != null) {
					connection.disconnect();
				}
			}
		}

	}

	final Handler handler1 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				Bundle data = msg.getData();
				String imageurl = data.getString("imgurl");

				ImageLoader.getInstance().displayImage(imageurl, head_image,
						options);
				break;
			}
			case 2: {
				Toast.makeText(context, "上传完成", Toast.LENGTH_SHORT).show();
			}
			}
			super.handleMessage(msg);
		}
	};

	protected String JSONAnalysis(String string) {
		JSONObject jsonObject;
		String msg = "";
		try {
			jsonObject = new JSONObject(string);
			msg = jsonObject.getString("msg");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	protected String JSONAnalysisMessage(String string) {
		String imgurl = "";
		try {
			JSONObject jsonObj = new JSONObject(string);
			JSONArray resultJsonArray = jsonObj.getJSONArray("value");
			JSONObject resultJsonObject = resultJsonArray.getJSONObject(0);
			imgurl = resultJsonObject.getString("photoName");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgurl;
	}

	//
	class MyHandler extends Thread {
		Bitmap bitmap;

		public MyHandler(Bitmap bitmap) {
			this.bitmap = bitmap;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			preserved(bitmap);
		}

		public void preserved(Bitmap mBitmap) {
			String dir = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/images/";
			File f = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/images");
			if (!f.exists()) {
				f.mkdirs();
			}
			// 获取内部存储状态
			String state = Environment.getExternalStorageState();
			// 如果状态不是mounted，无法读写
			if (!state.equals(Environment.MEDIA_MOUNTED)) {
				return;
			}
			String fileName = "head_image";
			try {
				File file = new File(dir + fileName + ".jpg");
				FileOutputStream out = new FileOutputStream(file);
				mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String imgname = dir + fileName + ".jpg";
			toUploadFile(imgname);
		}
	}

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = this.getActivity().managedQuery(uri, projection, null,
				null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	public final static Bitmap lessenUriImage(String path) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回 bm 为空
		options.inJustDecodeBounds = false; // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = (int) (options.outHeight / (float) 320);
		if (be <= 0)
			be = 1;
		options.inSampleSize = be; // 重新读入图片，注意此时已经把 options.inJustDecodeBounds
									// 设回 false 了
		bitmap = BitmapFactory.decodeFile(path, options);
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		System.out.println(w + " " + h); // after zoom
		return bitmap;
	}

	//
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = this.getActivity().getWindow()
				.getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		this.getActivity().getWindow().setAttributes(lp);
	}

	private void createView(View v) {
		// 获取自定义布局文件activity_popupwindow_left.xml的视图
		View popupWindow_view = this.getActivity().getLayoutInflater()
				.inflate(R.layout.requset_login, null, false);

		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setFocusable(true);
		backgroundAlpha(0.7f);
		DisplayMetrics dm = new DisplayMetrics();
		this.getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(dm);

		int screenWidth = dm.widthPixels;

		int screenHeigh = dm.heightPixels;
		// 设置动画效果
		// popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 这里是位置显示方式,在屏幕的左侧
		popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

		Button button_exit = (Button) popupWindow_view.findViewById(R.id.exit);
		Button button_goto = (Button) popupWindow_view
				.findViewById(R.id.gotoLogin);
		button_exit.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
				backgroundAlpha(1f);
				return true;
			}
		});

		button_goto.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
				backgroundAlpha(1f);
				Intent intent = new Intent(context, Login.class);
				startActivityForResult(intent, 1);
				return true;
			}
		});

	}

	private void createGotoFinishZL(View v) {
		// 获取自定义布局文件activity_popupwindow_left.xml的视图
		View popupWindow_view = this.getActivity().getLayoutInflater()
				.inflate(R.layout.requsetfinishzl, null, false);

		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setFocusable(true);
		backgroundAlpha(0.7f);
		DisplayMetrics dm = new DisplayMetrics();
		this.getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(dm);

		int screenWidth = dm.widthPixels;

		int screenHeigh = dm.heightPixels;
		// 设置动画效果
		// popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 这里是位置显示方式,在屏幕的左侧
		popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, screenWidth / 5 * 1,
				screenHeigh / 6 * 2);

		Button button_exit = (Button) popupWindow_view.findViewById(R.id.exit);
		Button button_goto = (Button) popupWindow_view
				.findViewById(R.id.gotofinish);
		button_exit.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
				backgroundAlpha(1f);
				return true;
			}
		});

		button_goto.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
				backgroundAlpha(1f);
				Intent intent = new Intent(context, Wdjl_activity.class);
				startActivityForResult(intent, 1);
				return true;
			}
		});

	}

}
