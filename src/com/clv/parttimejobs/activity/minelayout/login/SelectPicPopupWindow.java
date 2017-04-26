package com.clv.parttimejobs.activity.minelayout.login;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.clv.homework.R;
import com.clv.homework.R.layout;
import com.clv.parttimejobs.activity.mainlayout.MainActivity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectPicPopupWindow extends Activity {

	private static final int TAKE_PHOTO = 1;
	private static final int CHOOSE_PHOTO = 2;

	private Uri imageUri;
	private Button button_photo;
	private Button button_xiangce;
	private Button button_qx;
	Bitmap bitmap = null;
	Handler mHandler;
	FileOutputStream b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pic_popup_window_alert_dialog);
		button_photo = (Button) findViewById(R.id.btn_take_photo);
		button_xiangce = (Button) findViewById(R.id.btn_pick_photo);
		button_qx = (Button) findViewById(R.id.btn_cancel);
		InnerOnClickListener i = new InnerOnClickListener();
		button_photo.setOnClickListener(i);
		button_xiangce.setOnClickListener(i);
		button_qx.setOnClickListener(i);
	}

	private class InnerOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_take_photo: {
				takePhoto();
				break;
			}
			case R.id.btn_pick_photo: {
				takePhotoJI();
				break;
			}
			case R.id.btn_cancel: {
				SelectPicPopupWindow.this.finish();
				break;
			}
			}
		}

	}

	public void takePhoto() {
		File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
		try {
			if (outputImage.exists()) {
				outputImage.delete();
			}
			outputImage.createNewFile();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (Build.VERSION.SDK_INT >= 24) {
			imageUri = FileProvider.getUriForFile(SelectPicPopupWindow.this,
					"com.clv.cameraalbumtest.fileprovider", outputImage);
		} else {
			imageUri = Uri.fromFile(outputImage);
		}

		// 启动相机
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TAKE_PHOTO: {
			if (resultCode == RESULT_OK) {
				try {

					// 显示拍照图片
					Intent intent = this.getIntent();
					intent.setData(imageUri);
					setResult(110, intent);
					SelectPicPopupWindow.this.finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		}
		case CHOOSE_PHOTO: {
			if (resultCode == RESULT_OK) {
				if (Build.VERSION.SDK_INT >= 19) {
					handleImageOnKitKat(data);
				} else {
					handleImageBeforeKitKat(data);
				}
			}
			break;
		}
		}
	}

	private void handleImageOnKitKat(Intent data) {
		String imagePath = null;
		Uri uri = data.getData();
		if (DocumentsContract.isDocumentUri(this, uri)) {
			String docId = DocumentsContract.getDocumentId(uri);
			if ("com.android.providers.media.documents".equals(uri
					.getAuthority())) {
				String id = docId.split(":")[1];
				String selection = MediaStore.Images.Media._ID + "=" + id;
				imagePath = getImagePath(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
			} else if ("com.android.providers.downloads.documents".equals(uri
					.getAuthority())) {
				Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(docId));
				imagePath = getImagePath(contentUri, null);
			}
		} else if ("content".equalsIgnoreCase(uri.getScheme())) {
			imagePath = getImagePath(uri, null);
		} else if ("file".equalsIgnoreCase(uri.getScheme())) {
			imagePath = uri.getPath();
		}
		displayImage(uri);
	}

	private String getImagePath(Uri uri, String selection) {
		String path = null;
		Cursor cursor = getContentResolver().query(uri, null, selection, null,
				null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				path = cursor.getString(cursor
						.getColumnIndex(MediaStore.Images.Media.DATA));
			}
			cursor.close();
		}
		return path;
	}

	private void handleImageBeforeKitKat(Intent data) {
		Uri uri = data.getData();
		displayImage(uri);
	}

	private void displayImage(Uri imagePath) {
		if (imagePath != null) {
			// byte[] b = Bitmap2Bytes(bitmap);
			Intent intent3 = this.getIntent();
			intent3.setData(imagePath);
			setResult(101, intent3);
			SelectPicPopupWindow.this.finish();
		} else {
			Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
