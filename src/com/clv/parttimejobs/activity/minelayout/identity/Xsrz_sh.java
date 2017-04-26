package com.clv.parttimejobs.activity.minelayout.identity;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.clv.homework.R;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.util.ecutefile.UploadImg;
import com.clv.parttimejobs.util.ecutejson.JsonParse;
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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Xsrz_sh extends Activity {

	private static final int TAKE_PHOTO = 1;
	private static final int CHOOSE_PHOTO = 2;
	private Uri imageUri;
	private int imageUrl = 0;
	private Context context;
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
    private ImageButton mycreadit_return_button;
	
	RelativeLayout layout;
	LayoutInflater inflater;
	LinearLayout lin;
	private ScrollView mysfyz_scrollview;
	private ImageView mysfyz_imageview02;
	private ArrayAdapter<String> mArrayAdapter;
	private Spinner spinner;

	private String[] cates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startxsrzlayout);
		context = this;

		mysfyz_scrollview = (ScrollView) findViewById(R.id.myxsrz_scrollview);
		mysfyz_scrollview.setVerticalScrollBarEnabled(false);
		inflater = LayoutInflater.from(this);
		lin = (LinearLayout) findViewById(R.id.mysfyz_lin01);
		mysfyz_imageview02 = (ImageView) findViewById(R.id.mysfyz_imageview02);
		mycreadit_return_button=(ImageButton) findViewById(R.id.mycreadit_return_button);
		layout = (RelativeLayout) inflater.inflate(R.layout.xsrz_layout_01,
				null);
		lin.addView(layout);

		cates = new String[] { "博士", "研究�?", "本科", "本科以下", "其他" };
		spinner = (Spinner) findViewById(R.id.xsrz_spinner);
		mArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_model,
				cates) {
			@Override
			public View getDropDownView(int position, View convertView,
					android.view.ViewGroup parent) {
				if (convertView == null) {
					// 设置spinner展开的Item布局
					convertView = getLayoutInflater().inflate(
							R.layout.dropdown_stytle, parent, false);
				}
				TextView spinnerText = (TextView) convertView
						.findViewById(R.id.spinner_textView);
				spinnerText.setText(getItem(position));
				return convertView;
			}
		};

		mArrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
		spinner.setAdapter(mArrayAdapter);
		spinner.setSelection(2, true);

		sfrz_imagebutton01 = (ImageButton) findViewById(R.id.xsrz_imagebutton01);
		sfrz_imagebutton02 = (ImageButton) findViewById(R.id.xsrz_imagebutton02);
		sfrz_imagebutton03 = (ImageButton) findViewById(R.id.xsrz_imagebutton03);
		InnerOnclickListener i = new InnerOnclickListener();
		sfrz_imagebutton01.setOnClickListener(i);
		sfrz_imagebutton02.setOnClickListener(i);
		sfrz_imagebutton03.setOnClickListener(i);
		mycreadit_return_button.setOnClickListener(i);
	}

	private class InnerOnclickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.xsrz_imagebutton03: {
				mysfyz_imageview02
						.setImageResource(R.drawable.myidcard_pic_two_set);
				lin.removeView(layout);
				layout = (RelativeLayout) inflater.inflate(
						R.layout.xsrz_layout_02, null);
				lin.addView(layout);

				break;
			}
			case R.id.xsrz_imagebutton01: {
				imageUrl = 1;
				createView(view);
				break;
			}
			case R.id.xsrz_imagebutton02: {
				imageUrl = 2;
				createView(view);
				break;
			}
			case R.id.mycreadit_return_button:{
				Xsrz_sh.this.finish();
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

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TAKE_PHOTO: {
			if (resultCode == -1) {
				try {
					File outputImage = new File(this.getExternalCacheDir(),
							"output_image.jpg");
					Uri uri = Uri.fromFile(outputImage);
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

					} else if (imageUrl == 2) {
						sfrz_imagebutton02.setImageBitmap(bitmap);
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

	
}
