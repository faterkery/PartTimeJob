package com.clv.parttimejobs.view.adapter.my.resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.resume.Image;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.resume.ImageInterface;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private List<Image> data;
	private ImageInterface imageinterface;
	private DisplayImageOptions options;

	public ImageAdapter(Context context, List<Image> data,
			ImageInterface imageinterface) {
		super();
		this.context = context;
		this.data = new ArrayList<Image>();
		this.data = data;
		this.imageinterface = imageinterface;
		
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.nologin_headimage) // 设置图片下载期间显示的图�?
		.showImageForEmptyUri(R.drawable.nologin_headimage) // 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.nologin_headimage) // 设置图片加载或解码过程中发生错误显示的图�?
		.cacheInMemory(true) // 设置下载的图片是否缓存在内存�?
		.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
		.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图�?
		.build(); // 创建配置过得DisplayImageOption对象
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size() + 1;
	}

	@Override
	public Image getItem(int poisiton) {
		// TODO Auto-generated method stub
		return data.get(poisiton);
	}

	@Override
	public long getItemId(int poisiton) {
		// TODO Auto-generated method stub
		return poisiton;
	}

	@Override
	public View getView(final int position, View convertView,
			ViewGroup viewpartents) {
		// TODO Auto-generated method stub
		// 1. 判断convertView是否为null，并获取ViewHolder对象
		ViewHolder holder;
		convertView = View.inflate(context, R.layout.list_item_image, null);
		holder = new ViewHolder();
		holder.ivImage = (ImageButton) convertView
				.findViewById(R.id.iv_image_item_image);
		holder.ivImageSelect = (ImageButton) convertView
				.findViewById(R.id.iv_image_item_select);
		convertView.setTag(holder);
		// 判断是否为最后一�?

		if (position + 1 == getCount()) {
			if (!imageinterface.getBJZT()) {
				holder.ivImage.setVisibility(View.VISIBLE);
				holder.ivImage
						.setImageResource(R.drawable.myjlxx_icon_tjzp_nor);
				holder.ivImage.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						imageinterface.onclick();
					}
				});
			} else {
				holder.ivImage.setVisibility(View.GONE);
			}
		} else {
			// 2. 获取数据
			Image image = data.get(position);
			// 判断是否进入编辑模式
			// 3. 显示
			if (imageinterface.getBJZT()) {
				if (image.isSelect()) {
					holder.ivImageSelect.setVisibility(View.VISIBLE);
					holder.ivImage.setAlpha(0.5f);
					holder.ivImageSelect.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							imageinterface.selectImg(position);
						}
					});
				} else {
					holder.ivImageSelect.setVisibility(View.GONE);
					holder.ivImage.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							imageinterface.selectImg(position);
						}
					});
				}
			} else {
				holder.ivImageSelect.setVisibility(View.GONE);
			}
			holder.ivImage.setImageResource(R.drawable.ic_launcher);
			ImageLoader.getInstance().displayImage(image.getPath(), holder.ivImage, options);
			// �??��该控件是否有匹配的任�??
//			LoadImageTask task = tasks.get(convertView);
//			if (task != null) {
//				// 任务存在，则取消任务
//				task.cancel(true);
//			}
//
//			// 创建当前�??��执行的任务，即：加载当前应该显示的图�??
//			task = new LoadImageTask(holder.ivImage, image);
//
//			// 将新的任务存储在Map�??
//			tasks.put(convertView, task);
//			// 执行任务
//			task.execute();
		}
		return convertView;
	}

	private Map<View, LoadImageTask> tasks = new HashMap<View, ImageAdapter.LoadImageTask>();

	/**
	 * 加载图片的任�??
	 */
	private class LoadImageTask extends AsyncTask<Void, Void, Void> {
		/**
		 * 显示图片的ImageView控件
		 */
		private ImageButton imageView;
		/**
		 * 图片的数�??
		 */
		private Image image;

		public LoadImageTask(ImageButton imageView, Image image) {
			super();
			this.imageView = imageView;
			this.image = image;
		}

		@Override
		protected Void doInBackground(Void... params) {
			if (image.getBitmap() == null) {
				int rate = 1;
				if (image.getWidth() > MAX_SIZE && image.getHeight() > MAX_SIZE) {
					if (image.getWidth() > image.getHeight()) {
						rate = image.getHeight() / MAX_SIZE;
					} else {
						rate = image.getWidth() / MAX_SIZE;
					}
				}

				BitmapFactory.Options opts = new BitmapFactory.Options();
				opts.inSampleSize = rate;
				Bitmap bm = BitmapFactory.decodeFile(image.getPath(), opts);
				image.setBitmap(bm);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			imageView.setImageBitmap(image.getBitmap());
		}

	}

	private static final int MAX_SIZE = 45;

	class ViewHolder {
		ImageButton ivImage;
		ImageButton ivImageSelect;
	}
}
