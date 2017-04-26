package com.clv.parttimejobs.activity.minelayout.identity;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.clv.homework.R;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.entity.my.resume.RZMessage;
import com.clv.parttimejobs.entity.my.resume.UserMessageBean;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutefile.UploadImg;
import com.clv.parttimejobs.util.ecutefile.UploadUserPhoto;
import com.clv.parttimejobs.util.ecutejson.JsonParse;
import com.clv.parttimejobs.util.encrypt.Decrypt;
import com.clv.parttimejobs.util.encrypt.MarkKey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Sfzrz_sh extends Activity {

	private Context context;
	private static final int TAKE_PHOTO = 1;
	private static final int CHOOSE_PHOTO = 2;
	private Uri imageUri;
	private int imageUrl = 0;
	/**
	 * GridView：图片列�?
	 */
	private ImageButton button_mycredit_grfc_rey_takephoto;
	private ImageButton button_mycredit_grfc_rey_fromphoto;
	private ImageButton button_mycredit_grfc_rey_esc;
	private PopupWindow popupWindow;
	private ImageButton sfrz_imagebutton01;
	private ImageButton sfrz_imagebutton02;
	private ImageButton sfrz_imagebutton03;
	private RadioButton sfrz_radiobutton01;

	RelativeLayout layout;
	LayoutInflater inflater;
	LinearLayout lin;
	private ScrollView mysfyz_scrollview;
	private ImageView mysfyz_imageview02;

	private TextView sfrz_edittext01;
	private TextView sfrz_edittext03;

	private File outputImage1;
	private File outputImage2;

	// 待审核中
	private TextView textname;
	private TextView textsex;
	private TextView textsfz;
	private TextView sfrz_rey02_textview02;
	private TextView sfrz_rey02_txt7;
	private TextView sfrz_rey02_txt8;
	private ImageButton sfrz_imagebutton_resubmit;
	private ImageButton mycreadit_return_button;

	// 审核完成

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startsfrzlayout);
		context = this;
		init();
		new Thread(new MyThread3()).start();
	}

	private void init() {
		inflater = LayoutInflater.from(this);
		lin = (LinearLayout) findViewById(R.id.mysfyz_lin01);
		layout = (RelativeLayout) inflater.inflate(R.layout.sfrz_layout_01,
				null);
		lin.addView(layout);
		mysfyz_scrollview = (ScrollView) findViewById(R.id.mysfyz_scrollview);
		mysfyz_scrollview.setVerticalScrollBarEnabled(false);
		
		mysfyz_imageview02 = (ImageView) findViewById(R.id.mysfyz_imageview02);
		sfrz_radiobutton01 = (RadioButton) findViewById(R.id.sfrz_radiobutton01);
		sfrz_radiobutton01.setChecked(true);

		sfrz_imagebutton01 = (ImageButton) findViewById(R.id.sfrz_imagebutton01);
		sfrz_imagebutton02 = (ImageButton) findViewById(R.id.sfrz_imagebutton02);
		sfrz_imagebutton03 = (ImageButton) findViewById(R.id.sfrz_imagebutton03);

		sfrz_edittext01 = (TextView) findViewById(R.id.sfrz_edittext01);
		sfrz_edittext03 = (TextView) findViewById(R.id.sfrz_edittext03);
		//
		
		

		InnerOnclickListener i = new InnerOnclickListener();
		sfrz_imagebutton01.setOnClickListener(i);
		sfrz_imagebutton02.setOnClickListener(i);
		sfrz_imagebutton03.setOnClickListener(i);
		
	}

	private class InnerOnclickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.sfrz_imagebutton03: {

				if (("").equals(sfrz_edittext01.getText().toString().trim())) {
					Toast.makeText(Sfzrz_sh.this, "名字不能为空", Toast.LENGTH_SHORT)
							.show();
				} else if (("").equals(sfrz_edittext03.getText().toString()
						.trim())) {
					Toast.makeText(Sfzrz_sh.this, "身份证号不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (outputImage1 == null) {
					Toast.makeText(Sfzrz_sh.this, "图片1不能为空", Toast.LENGTH_SHORT)
							.show();
				} else if (outputImage2 == null) {
					Toast.makeText(Sfzrz_sh.this, "图片2不能为空", Toast.LENGTH_SHORT)
							.show();
				} else {
					startSendMessage();
					mysfyz_imageview02
							.setImageResource(R.drawable.myidcard_pic_two_set);
					lin.removeView(layout);
					layout = (RelativeLayout) inflater.inflate(
							R.layout.sfrz_layout_02, null);
					lin.addView(layout);

				}
				break;
			}
			case R.id.sfrz_imagebutton01: {
				imageUrl = 1;
				createView(view);
				break;
			}
			case R.id.sfrz_imagebutton02: {
				imageUrl = 2;
				createView(view);
				break;
			}
			case R.id.mycreadit_return_button:{
				Sfzrz_sh.this.finish();
				break;
			}
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void createView(View v) {
		// 获取自定义布�?文件activity_popupwindow_left.xml的视�?
		View popupWindow_view = this.getLayoutInflater().inflate(
				R.layout.mycredit_select, null, false);

		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setFocusable(true);
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);

		int screenWidth = dm.widthPixels;
		int screenHeigh = dm.heightPixels;
		// 设置动画效果
		// popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 这里是位置显示方�?,在屏幕的左侧
		popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);

		button_mycredit_grfc_rey_takephoto = (ImageButton) popupWindow_view
				.findViewById(R.id.mycredit_grfc_rey_takephoto);
		button_mycredit_grfc_rey_fromphoto = (ImageButton) popupWindow_view
				.findViewById(R.id.mycredit_grfc_rey_fromphoto);
		button_mycredit_grfc_rey_esc = (ImageButton) popupWindow_view
				.findViewById(R.id.mycredit_grfc_rey_esc);
		button_mycredit_grfc_rey_takephoto
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						popupWindow.dismiss();
						popupWindow = null;
						takePhoto();
					}
				});
		button_mycredit_grfc_rey_fromphoto
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						popupWindow.dismiss();
						popupWindow = null;
						takePhotoJI();
					}
				});
		button_mycredit_grfc_rey_esc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
			}
		});

	}

	public void takePhoto() {
		File outputImage = new File(this.getExternalCacheDir(),
				"output_image.jpg");
		try {
			if (outputImage.exists()) {
				outputImage.delete();
			}
			outputImage.createNewFile();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		imageUri = Uri.fromFile(outputImage);

		// 启动相机
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		System.out.println(imageUri);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, TAKE_PHOTO);
	}

	public void takePhotoJI() {
		openAlbm();
	}

	private void openAlbm() {
		Intent intent = new Intent("android.intent.action.GET_CONTENT");
		intent.setType("image/*");
		startActivityForResult(intent, CHOOSE_PHOTO);
	}

	// 【开始上传审核资料�??
	public void startSendMessage() {
		String name = "";
		String gender = "";
		String idNum = "";
		if (sfrz_radiobutton01.isChecked()) {
			gender = "�?";
		} else {
			gender = "�?";
		}
		name = sfrz_edittext01.getText().toString().trim();
		idNum = sfrz_edittext03.getText().toString().trim();
		new Thread(new MyThread1(name, gender, idNum)).start();
		new Thread(new MyThread2(outputImage1.getPath(), "101")).start();
		new Thread(new MyThread2(outputImage2.getPath(), "102")).start();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TAKE_PHOTO: {
			if (resultCode == -1) {
				try {
					File outputImage1 = new File(this.getExternalCacheDir(),
							"output_image1.jpg");
					Uri uri = Uri.fromFile(outputImage1);
					// 显示拍照图片
					Bitmap bitmap = getBitmapFormUri(this, uri);
					if (imageUrl == 1) {
						sfrz_imagebutton01.setImageBitmap(bitmap);
					} else if (imageUrl == 2) {
						sfrz_imagebutton02.setImageBitmap(bitmap);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		}
		case CHOOSE_PHOTO: {
			if (resultCode == -1) {
				try {
					Uri uri = data.getData();
					Bitmap bitmap = getBitmapFormUri(this, uri);
					if (imageUrl == 1) {
						sfrz_imagebutton01.setImageBitmap(bitmap);
						outputImage1 = new File(this.getExternalCacheDir(),
								"output_image1.jpg");
						saveImage(bitmap, outputImage1.getPath());

					} else if (imageUrl == 2) {
						sfrz_imagebutton02.setImageBitmap(bitmap);
						outputImage2 = new File(this.getExternalCacheDir(),
								"output_image2.jpg");
						saveImage(bitmap, outputImage2.getPath());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		}
		}
	}

	public Bitmap getBitmapFormUri(Activity ac, Uri uri)
			throws FileNotFoundException, IOException {
		InputStream input = ac.getContentResolver().openInputStream(uri);
		BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
		onlyBoundsOptions.inJustDecodeBounds = true;
		onlyBoundsOptions.inDither = true;// optional
		onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
		BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
		input.close();
		int originalWidth = onlyBoundsOptions.outWidth;
		int originalHeight = onlyBoundsOptions.outHeight;
		if ((originalWidth == -1) || (originalHeight == -1))
			return null;
		// 图片分辨率以480x800为标�?
		float hh = 445f;// 这里设置高度�?800f
		float ww = 240f;// 这里设置宽度�?480f
		// 缩放比�?�由于是固定比例缩放，只用高或�?�宽其中�?个数据进行计算即�?
		int be = 1;// be=1表示不缩�?
		if (originalWidth > originalHeight && originalWidth > ww) {// 如果宽度大的话根据宽度固定大小缩�?
			be = (int) (originalWidth / ww);
		} else if (originalWidth < originalHeight && originalHeight > hh) {// 如果高度高的话根据宽度固定大小缩�?
			be = (int) (originalHeight / hh);
		}
		if (be <= 0)
			be = 1;
		// 比例压缩
		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inSampleSize = be;// 设置缩放比例
		bitmapOptions.inDither = true;// optional
		bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
		input = ac.getContentResolver().openInputStream(uri);
		Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
		input.close();

		return compressImage(bitmap);// 再进行质量压�?
	}

	public static void saveImage(Bitmap photo, String spath) {
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(spath, false));
			photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
			bos.flush();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这�?100表示不压缩，把压缩后的数据存放到baos�?
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大�?100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			// 第一个参�? ：图片格�? ，第二个参数�? 图片质量�?100为最高，0为最�? ，第三个参数：保存压缩后的数据的�?
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos�?
			options -= 10;// 每次都减�?10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream�?
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	public int getBitmapsize(Bitmap bitmap) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			return bitmap.getByteCount();
		}
		// Pre HC-MR1
		return bitmap.getRowBytes() * bitmap.getHeight();

	}

	// 身份信息填写
	private class MyThread1 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String name;
		String gender;
		String idNum;
		String line = "";
		StringBuilder response;

		public MyThread1(String name, String gender, String idNum) {
			this.name = name;
			this.gender = gender;
			this.idNum = idNum;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String phone = u.getUser_phoneNo();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				System.out.println("�?�?");
				URL url = new URL(
						new UriFactory().getIdentityAuthenticationUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String timekey = re.builderTimeKey();
				String id_en = re.builderId(id + "", security_key);
				String id_enery = re.Encrypt(id_en, timekey);
				String message_key = re.builderMessageKey(phone, security_key);
				String name_encry = re.Encrypt(name, message_key);
				String gender_entry = re.Encrypt(gender, message_key);
				String idNum_entry = re.Encrypt(idNum, message_key);
				String a = "enId="
						+ URLEncoder.encode(id_enery, "UTF-8").trim()
						+ "&name="
						+ URLEncoder.encode(name_encry, "UTF-8").trim()
						+ "&gender="
						+ URLEncoder.encode(gender_entry, "UTF-8").trim()
						+ "&idNum="
						+ URLEncoder.encode(idNum_entry, "UTF-8").trim();
				out.writeBytes(a);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				System.out.println(response.toString().trim());
				Message message = new Message();
				Bundle data = new Bundle();
				String msg = new JsonParse().JSONAnalysis(response.toString()
						.trim());
				if ("success".equals(msg)) {
					data.putString("value", msg);
				} else if ("fail".equals(msg)) {
					String wrong = new JsonParse().JSONAnalysisWrong(response
							.toString().trim());
					data.putString("value", wrong);
				}
				user.destory();
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
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle data = msg.getData();
			String val = data.getString("value");
			if (("101").equals(val)) {
				Toast.makeText(context, "真实姓名过长（不能超�?30字符�?", Toast.LENGTH_SHORT)
						.show();
			} else if (("102").equals(val)) {
				Toast.makeText(context, "性别格式不符", Toast.LENGTH_SHORT).show();
			} else if (("103").equals(val)) {
				Toast.makeText(context, "身份证号长度不符", Toast.LENGTH_SHORT).show();
			} else if (("success").equals(val)) {
				Toast.makeText(context, "上传成功，正在审�?", Toast.LENGTH_SHORT).show();
			}

		}
	};

	private class MyThread2 implements Runnable {

		private String picpath = "";
		private String type = "";

		public MyThread2(String picpath, String type) {
			this.picpath = picpath;
			this.type = type;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			toUploadFile(picpath, type);
		}

	}

	private void toUploadFile(String picPath, String type) {
		try {
			UserDao user = new UserDao(context);
			User u = user.detailMessage();
			long id = u.getUser_id();
			String security_key = u.getSecurity_key();
			MarkKey re = new MarkKey();

			String fileKey = "file";
			Map<String, String> params = new HashMap<String, String>();
			String timekey = re.builderTimeKey();
			String id_en = re.builderId(id + "", security_key);
			String id_enery = re.Encrypt(id_en, timekey);
			params.put("enId", id_enery);

			params.put("type", type);
			String url = new UriFactory().getUploadCertificatePhotoUrl();
			Map<String, String> fileMap = new HashMap<String, String>();
			fileMap.put("file", picPath);

			String ret = UploadUserPhoto.formUpload(url, params, fileMap);
			ret = ret.trim();
			System.out.println(ret);
			Message message = new Message();
			Bundle data = new Bundle();
			String msg = new JsonParse().JSONAnalysis(ret);

			data.putString("msg", msg);
			if (("success").equals(msg)) {
				data.putString("value", msg);
			} else if (("fail").equals(msg)) {
				String wrong = new JsonParse().JSONAnalysisWrong(ret);
				data.putString("value", wrong);
			}

			message.what = 2;
			handler1.sendMessage(message);
			user.destory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	final Handler handler2 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle data = msg.getData();
			String val = data.getString("value");
			if (("301").equals(val)) {
				Toast.makeText(context, "格式不是图片格式", Toast.LENGTH_SHORT).show();
			} else if (("302").equals(val)) {
				Toast.makeText(context, "上传照片为空", Toast.LENGTH_SHORT).show();
			} else if (("success").equals(val)) {
				Toast.makeText(context, "发�?�成�?", Toast.LENGTH_SHORT).show();
			}

		}
	};

	// 查看用户提交信息
	private class MyThread3 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String line = "";
		StringBuilder response;

		public MyThread3() {

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String phone = u.getUser_phoneNo();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				System.out.println("�?�?");
				URL url = new URL(
						new UriFactory().getSelectIdentityAuditConclusionUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String timekey = re.builderTimeKey();
				String id_en = re.builderId(id + "", security_key);
				String id_enery = re.Encrypt(id_en, timekey);

				String a = "enId="
						+ URLEncoder.encode(id_enery, "UTF-8").trim();
				out.writeBytes(a);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				System.out.println(response.toString().trim());
				Message message = new Message();
				Bundle data = new Bundle();
				String msg = new JsonParse().JSONAnalysis(response.toString()
						.trim());
				if ("success".equals(msg)) {
					RZMessage rzmessage = JSONAnalysisUserMessage(response
							.toString().trim());
					message.what = 1;
					data.putSerializable("rzmessage", rzmessage);
				} else if ("fail".equals(msg)) {
					String wrong = new JsonParse().JSONAnalysisWrong(response
							.toString().trim());
					message.what = 2;
					data.putString("value", wrong);
				}
				user.destory();
				message.setData(data);
				handler3.sendMessage(message);

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

	final Handler handler3 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle data = msg.getData();
			if (msg.what == 1) {
				
				RZMessage rzmessage = (RZMessage) data
						.getSerializable("rzmessage");
				switch (rzmessage.getResult()) {
				case 0: {
					changeView(2);
					textname.setText(rzmessage.getUser_name());
					textsex.setText(rzmessage.getGender());
					textsfz.setText(rzmessage.getIdNum() + "");
					break;
				}
				case 1: {
					changeView(3);
					break;
				}
				case 2: {
					changeView(2);
					changelayout(rzmessage);
					sfrz_rey02_txt8.setText("姓名填写错误");
					break;
				}
				case 3: {
					changeView(2);
					changelayout(rzmessage);
					sfrz_rey02_txt8.setText("性别填写错误");
					break;
				}
				case 4: {
					changeView(2);
					changelayout(rzmessage);
					sfrz_rey02_txt8.setText("身份证填写错�?");
					break;
				}
				case 5: {
					changeView(2);
					changelayout(rzmessage);
					sfrz_rey02_txt8.setText("身份证照片拍摄不符合规范");
					break;
				}
				case 6: {
					changeView(2);
					changelayout(rzmessage);
					sfrz_rey02_txt8.setText("审核员认为非本人操作");
					break;
				}
				case 7: {
					changeView(2);
					changelayout(rzmessage);
					sfrz_rey02_txt8.setText("其他原因导致认证失败");
					break;
				}
				}
			} else if (msg.what == 2) {
				String val = data.getString("value");
				if (("101").equals(val)) {
					changeView(1);
					Toast.makeText(context, "没有提交过身份认证信�?", Toast.LENGTH_SHORT)
							.show();
				} else if (("102").equals(val)) {
					Toast.makeText(context, "返回内容加密失败，请重新发布请求",
							Toast.LENGTH_SHORT).show();
				}
			}

		}
	};

	private void changeView(int way) {
		switch (way) {
		case 1: {

			break;
		}
		case 2: {// 认证�?////认证失败
			mysfyz_imageview02
					.setImageResource(R.drawable.myidcard_pic_two_set);
			lin.removeView(layout);
			layout = (RelativeLayout) inflater.inflate(R.layout.sfrz_layout_02,
					null);
			lin.addView(layout);
			
			textname = (TextView) findViewById(R.id.sfrz_rey02_txt2);
			textsex = (TextView) findViewById(R.id.sfrz_rey02_txt4);
			textsfz = (TextView) findViewById(R.id.sfrz_rey02_txt6);
			sfrz_rey02_textview02 = (TextView) findViewById(R.id.sfrz_rey02_textview02);
			sfrz_rey02_txt7 = (TextView) findViewById(R.id.sfrz_rey02_txt7);
			sfrz_rey02_txt8 = (TextView) findViewById(R.id.sfrz_rey02_txt8);
			sfrz_imagebutton_resubmit = (ImageButton) findViewById(R.id.sfrz_imagebutton_resubmit);
			mycreadit_return_button=(ImageButton) findViewById(R.id.mycreadit_return_button);
			//
			InnerOnclickListener i = new InnerOnclickListener();
			mycreadit_return_button.setOnClickListener(i);
			break;
		}
		case 3: {// 认证成功
			mysfyz_imageview02
					.setImageResource(R.drawable.myidcard_pic_there_set);
			lin.removeView(layout);
			layout = (RelativeLayout) inflater.inflate(R.layout.sfrz_layout_03,
					null);
			lin.addView(layout);
		}
		case 4: {

			break;
		}
		}
	}

	private void changelayout(RZMessage rzmessage){
		textname.setText(rzmessage.getUser_name());
		textsex.setText(rzmessage.getGender());
		textsfz.setText(rzmessage.getIdNum() + "");
		sfrz_rey02_textview02.setVisibility(View.GONE);
		sfrz_rey02_txt7.setVisibility(View.VISIBLE);
		sfrz_rey02_txt8.setVisibility(View.VISIBLE);
		sfrz_imagebutton_resubmit.setVisibility(View.VISIBLE);
	}
	protected RZMessage JSONAnalysisUserMessage(String string) {
		RZMessage rzmessage = new RZMessage();
		try {
			JSONObject jsonObj = new JSONObject(string);
			JSONArray resultJsonArray = jsonObj.getJSONArray("value");
			JSONObject jsonobject = resultJsonArray.getJSONObject(0);
			String entrydata = jsonobject.getString("identity");
			String sString = new Decrypt().toDecrypt(context, entrydata.trim());
			System.out.println(sString);
			JSONObject resultJsonObject = new JSONObject(sString);
			rzmessage.setResult(resultJsonObject.getInt("auditType"));
			rzmessage.setGender(resultJsonObject.getString("gender"));
			rzmessage.setIdNum(resultJsonObject.getString("idNum"));
			rzmessage.setUser_name(resultJsonObject.getString("name"));
			rzmessage.setUserId(resultJsonObject.getInt("userId"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rzmessage;
	}
}
