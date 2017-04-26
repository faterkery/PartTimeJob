package com.clv.parttimejobs.util.ecuteimg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class BitmapUtil {
	private static Handler handler = new Handler();
	public static ExecutorService threadPoll = Executors.newFixedThreadPool(5);
	private static final String SD_PATH = Environment
			.getExternalStorageDirectory().getPath();
	private static String PIC_DIR = "image";
	public static final int PIC_BITMAP = 2;
	public static final int PIC_DRAWABLE = 1;
	private static HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

	private static Bitmap getBitmap(String url, Context context) {
		Bitmap bitmap = null;
		bitmap = getBitmapCache(url);
		if (null == bitmap) {
			bitmap = getBitmapSD(url);
			if (null == bitmap) {
				bitmap = getBitMapFromUrl(url, context);
				saveBitmapCache(url, bitmap);
				saveBitmapSD(url, bitmap);
			}
		}
		return bitmap;
	}

	private static void saveBitmapCache(String url, Bitmap bitmap) {
		if (!imageCache.containsKey(url)) {
			imageCache.put(url, new SoftReference<Bitmap>(bitmap));
		}
	}

	private static Bitmap getBitmapCache(String url) {
		Bitmap bitmap = null;
		if (imageCache.containsKey(url)) {
			bitmap = imageCache.get(url).get();
		}
		return bitmap;
	}

	private static boolean saveBitmapSD(String url, Bitmap bitmap) {
		boolean result = false;
		File dirFile = new File(SD_PATH + "/" + PIC_DIR);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File file = new File(dirFile.getPath() + "/" + getFileName(url));
		if (file.exists()) {
			file.delete();
		}
		if (null != bitmap) {
			try {
				file.createNewFile();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
						new FileOutputStream(file));
				result = true;
			} catch (Exception e) {
			}
		}
		return result;
	}

	private static Bitmap getBitmapSD(String url) {
		Bitmap bitmap = null;
		File file = new File(SD_PATH + "/" + PIC_DIR + "/" + getFileName(url));
		if (file.exists() && !file.isDirectory()) {
			bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
		}
		return bitmap;
	}

	private static String getMD5Code(String inputString) {
		if (inputString == null || "".equals(inputString)) {
			return "";
		}
		byte[] btInput = inputString.getBytes();
		MessageDigest mdInst = null;
		try {
			mdInst = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		mdInst.update(btInput);
		byte[] md = mdInst.digest();
		StringBuffer sb = new StringBuffer();
		int val;
		for (byte i = 0; i < md.length; i++) {
			val = ((int) md[i]) & 0xff;
			if (val < 16)
				sb.append("0");
			sb.append(Integer.toHexString(val));
		}
		return sb.toString();
	}

	private static String getFileName(String url) {
		return getMD5Code(url);
	}

	private static boolean isNetworkAvailable(Context cx) {
		ConnectivityManager cm = (ConnectivityManager) cx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		return (info != null && info.isConnected());
	}

	private static Bitmap getBitMapFromUrl(String url, Context context) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		if (isNetworkAvailable(context) && !TextUtils.isEmpty(url)) {
			try {
				myFileUrl = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) myFileUrl
						.openConnection();
				conn.setConnectTimeout(5000);
				conn.setDoInput(true);
				conn.connect();
				InputStream is = conn.getInputStream();
				bitmap = BitmapFactory.decodeStream(is);
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}

	private static Drawable changeBitmapToDrawable(Bitmap bitmap,
			Context context) {
		BitmapDrawable drawable = null;
		if (null != bitmap && null != context && context instanceof Activity) {
			drawable = new BitmapDrawable(bitmap);
			DisplayMetrics metric = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getMetrics(metric);
			drawable.setTargetDensity(metric);
		}
		return drawable;
	}

	public static void loadImg(final String url, final ImageView imageView,
			final Context context, final int type) {
		threadPoll.submit(new Runnable() {
			@Override
			public void run() {
				try {
					final Bitmap bitmap = getBitmap(url, context);
					if (null != bitmap) {
						handler.post(new Runnable() {
							@Override
							public void run() {
								if (type == PIC_BITMAP) {
									imageView.setImageBitmap(bitmap);
								}
								if (type == PIC_DRAWABLE) {
									imageView.setBackgroundDrawable(BitmapUtil
											.changeBitmapToDrawable(bitmap,
													context));
								}
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
