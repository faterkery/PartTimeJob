package com.clv.parttimejobs.fragment.my_fragment.resume;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
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
import org.json.JSONException;
import org.json.JSONObject;

import com.clv.homework.R;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.dao.user.UserPhotoDao;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.entity.my.resume.Image;
import com.clv.parttimejobs.entity.my.resume.UserPhoto;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.resume.ImageInterface;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutefile.UploadImg;
import com.clv.parttimejobs.util.encrypt.MarkKey;
import com.clv.parttimejobs.view.adapter.my.resume.ImageAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Toast;

public class Fragment_mycredit_grfc extends Fragment implements ImageInterface {

	private Context context;
	private static final int TAKE_PHOTO = 1;
	private static final int CHOOSE_PHOTO = 2;
	private Uri imageUri;
	public List<Image> images;
	/**
	 * GridView：图片列�??
	 */
	private GridView gvImages;
	/**
	 * 图片列表的Adapter
	 */
	private ImageAdapter imageAdapter;
	private Button button_bj;
	private ImageButton button_mycredit_grfc_rey_takephoto;
	private ImageButton button_mycredit_grfc_rey_fromphoto;
	private ImageButton button_mycredit_grfc_rey_esc;
	private ImageButton mycredit_grfc_imagebutton_deleImg;
	private LinearLayout linearlayout;
	private PopupWindow popupWindow;

	private boolean isShow = false;
	private boolean isenterBJ = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mycredit_grfc, null);
		context = this.getActivity();
		images = new ArrayList<Image>();
		// 初始化控�??
		gvImages = (GridView) view.findViewById(R.id.lv_images);
		// 显示列表
		Resources res = getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res,
				R.drawable.myjlxx_icon_tjzp_nor);
		
		imageAdapter = new ImageAdapter(this.getActivity(), images, this);
		gvImages.setAdapter(imageAdapter);
		//
		button_bj = (Button) view.findViewById(R.id.mycredit_grfc_textview);
		mycredit_grfc_imagebutton_deleImg = (ImageButton) view
				.findViewById(R.id.mycredit_grfc_imagebutton_deleImg);

		InnerOnClickListener i = new InnerOnClickListener();
		button_bj.setOnClickListener(i);
		mycredit_grfc_imagebutton_deleImg.setOnClickListener(i);
		
		new Thread(new MyThread2()).start();
		return view;
	}

	private class InnerOnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.mycredit_grfc_textview: {
				if (!isenterBJ) {
					isenterBJ = true;
					mycredit_grfc_imagebutton_deleImg
							.setVisibility(View.VISIBLE);
					button_bj.setText("取消");
				} else {
					isenterBJ = false;
					mycredit_grfc_imagebutton_deleImg.setVisibility(View.GONE);
					button_bj.setText("编辑");
					for (Image img : images) {
						img.setSelect(false);
					}
				}
				imageAdapter.notifyDataSetChanged();
				break;
			}
			case R.id.mycredit_grfc_imagebutton_deleImg:{
				if(isAn()){
					System.out.println("点击删除");
					UserDao user = new UserDao(context);
					User u = user.detailMessage();
					long id = u.getUser_id();
					UserPhotoDao userphoto =new UserPhotoDao(context);
					List<UserPhoto> userphotoList =userphoto.detailPhoto(id);
					user.destory();
					userphoto.destory();
					List<String> imgname =new ArrayList<String>();
					System.out.println(images.size());
					for (int i=0;i< images.size();i++) {
						if (images.get(i).isSelect()) {
							System.out.println("�?"+i+"个被选中");
							imgname.add(userphotoList.get(i).getUser_imgname());
						}
					}
					System.out.println("�?始发送删�?");
					for(int i=0;i<imgname.size();i++){
						System.out.println("�?"+i+"个被删除");
						new Thread(new MyThread3(imgname.get(i))).start();
					}
					deleteImg();
				}
				break;
			}
			}
		}

	}

	private void createView(View v) {
		// 获取自定义布�??��件activity_popupwindow_left.xml的视�??
		View popupWindow_view = this.getActivity().getLayoutInflater()
				.inflate(R.layout.mycredit_select, null, false);

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
		// 这里是位置显示方�??在屏幕的左侧
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
						backgroundAlpha(1f);
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
						backgroundAlpha(1f);
						takePhotoJI();
					}
				});
		button_mycredit_grfc_rey_esc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
				backgroundAlpha(1f);
			}
		});

	}

	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = this.getActivity().getWindow()
				.getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		this.getActivity().getWindow().setAttributes(lp);
	}

	public void takePhoto() {
		File outputImage = new File(this.getActivity().getExternalCacheDir(),
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
					File outputImage = new File(this.getActivity()
							.getExternalCacheDir(), "output_image.jpg");
					Uri uri = Uri.fromFile(outputImage);
					// 显示拍照图片
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize = 1;// 图片大小，设置越大，图片越不清晰，占用空间越�??

					Bitmap bitmap = BitmapFactory.decodeStream(this
							.getActivity().getContentResolver()
							.openInputStream(uri), null, options);
					int width = options.outWidth;
					int height = options.outHeight;
					int size = getBitmapsize(bitmap);
					String path = imageUri.toString();
//					Image image = new Image(1, path, size, width, height,
//							bitmap, false);
//					images.add(0, image);

					new Thread(new MyThread1(outputImage.getPath())).start();
//					imageAdapter.notifyDataSetChanged();
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
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize = 1;// 图片大小，设置越大，图片越不清晰，占用空间越�??
					Bitmap bitmap = BitmapFactory.decodeStream(this
							.getActivity().getContentResolver()
							.openInputStream(uri), null, options);
					int width = options.outWidth;
					int height = options.outHeight;
					int size = getBitmapsize(bitmap);
					String path = uri.toString();
					
					File outputImage = new File(this.getActivity().getExternalCacheDir(),
							"output_image.jpg");
					saveImage(bitmap,outputImage.getPath());
					new Thread(new MyThread1(outputImage.getPath())).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		}
		}
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
	public int getBitmapsize(Bitmap bitmap) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			return bitmap.getByteCount();
		}
		// Pre HC-MR1
		return bitmap.getRowBytes() * bitmap.getHeight();

	}

	
	@Override
	public boolean getBJZT() {
		// TODO Auto-generated method stub
		return isenterBJ;
	}

	private class MyThread1 implements Runnable {

		private String picpath = "";

		public MyThread1(String picpath) {
			this.picpath = picpath;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			toUploadFile(picpath);
		}

	}

	private void toUploadFile(String picPath) {
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
			String url = new UriFactory().getAddPhotoUrl();
			Map<String, String> fileMap = new HashMap<String, String>();
			fileMap.put("file", picPath);
			String ret = UploadImg.formUpload(url, params, fileMap);
			ret = ret.trim();
			Message message = new Message();
			Bundle data = new Bundle();
			String msg = JSONAnalysis(ret);
			String imgurl = JSONAnalysisMessage(ret);
			data.putString("msg", msg);
			message.what = 2;
			handler1.sendMessage(message);
			user.destory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	final Handler handler1 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1:{
				break;
			}
			case 2: {
				Bundle data = msg.getData();
				String msgcontext = data.getString("msg");
				if (("fail").equals(msgcontext)) {
					Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT).show();
				}
				new Thread(new MyThread2()).start();
				break;
			}
			}
			super.handleMessage(msg);
		}
	};

	final Handler handler2 = new Handler(){
		public void handleMessage(Message msg) { // handle message
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				UserPhotoDao userphoto =new UserPhotoDao(context);
				List<UserPhoto> userphotoList =userphoto.detailPhoto(id);
				images.clear();
				for(UserPhoto userp:userphotoList){
					addImg(userp.getUser_imgurl()+userp.getUser_imgname());
				}
				flush();
				user.destory();
				userphoto.destory();
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

	private class MyThread2 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String phone = "";
		String line = "";
		StringBuilder response;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				URL url = new URL(
						new UriFactory().getSelectPhotoUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				String timekey = re.builderTimeKey();
				String id_en = re.builderId(id + "", security_key);
				String id_enery = re.Encrypt(id_en, timekey);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String a = "enId=" + URLEncoder.encode(id_enery, "UTF-8").trim();
				out.writeBytes(a);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				System.out.println(response.toString().trim());
				String imgurl = JSONAnalysis(response.toString().trim());
				List<String> imgnameArray= JSONAnalysisMessageimg(response.toString().trim());
				addImage(id+"",imgurl,imgnameArray);
				user.destory();
				
				Message message = new Message();
				message.what = 1;
				handler2.sendMessage(message);
				
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

	private class MyThread3 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String photoname;
		String line = "";
		StringBuilder response;

		public MyThread3(String photoname){
			this.photoname=photoname;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				URL url = new URL(
						new UriFactory().getDeletePhotoUrl());
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
					String a = "enId=" + URLEncoder.encode(id_enery, "UTF-8").trim()
							+"&photoName="+URLEncoder.encode(photoname, "UTF-8").trim();
					out.writeBytes(a);
					out.flush();
					InputStream in = connection.getInputStream();
					reader = new BufferedReader(new InputStreamReader(in));
					response = new StringBuilder();
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					System.out.println(response.toString().trim());
					String msg = JSONAnalysis(response.toString().trim());
					if("success".equals(msg)){
						System.out.println("删除成功");
					}else if("fail".equals(msg)){
						System.out.println("删除失败");
					}
				user.destory();
				
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
	protected List<String> JSONAnalysisMessageimg(String string) {
		List<String> imgnameArray =new ArrayList<String>();
		String imgname = "";
		try {
			JSONObject jsonObj = new JSONObject(string);
			JSONArray resultJsonArray = jsonObj.getJSONArray("value");
			int length = resultJsonArray.length();
			for (int i = 0; i < length; i++) {
				JSONObject resultJsonObject = resultJsonArray.getJSONObject(i);
				imgname = resultJsonObject.getString("photoName");
				imgnameArray.add(imgname);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgnameArray;
	}
	public void addImage(String user_id,String user_url,List<String> imgnameArray){
		UserPhotoDao userphoto =new UserPhotoDao(context);
		userphoto.deleImgAll();
		for(String user_img : imgnameArray){
			userphoto.insert(user_id, user_img, user_url);
		}
		userphoto.destory();
	}
	public void addImg(String path){
		Image image =new Image();
		image.setSelect(false);
		image.setPath(path);
		images.add(image);
		imageAdapter.notifyDataSetChanged();
	}
	public void deleteImg(){
		while(isAn()){
			for (int i=0;i< images.size();i++) {
				if (images.get(i).isSelect()) {
					images.remove(i);
				}
			}
			flush();
		}
		changeImg();
	}
	public void flush(){
		imageAdapter.notifyDataSetChanged();
	}
	@Override
	public void onclick() {
		// TODO Auto-generated method stub
		createView(gvImages);
	}

	@Override
	public void selectImg(int position) {
		// TODO Auto-generated method stub
		if (images.get(position).isSelect()) {
			images.get(position).setSelect(false);
		} else {
			images.get(position).setSelect(true);
		}
		imageAdapter.notifyDataSetChanged();
		changeImg();
	}

	@Override
	public void delteImg() {
		// TODO Auto-generated method stub

	}

	// 实时查看删除回收箱图片是否是可按状�??
	public boolean isAn(){
		boolean isHave = false;
		for (Image img : images) {
			if (img.isSelect()) {
				isHave = true;
				break;
			}
		}
		return isHave;
	}
	public void changeImg() {
		if (isAn()) {
			mycredit_grfc_imagebutton_deleImg
					.setImageResource(R.drawable.myjlxx_icon_shanchu_set);
		} else {
			mycredit_grfc_imagebutton_deleImg
					.setImageResource(R.drawable.myjlxx_icon_shanchu_nor);
		}
	}

	
}
