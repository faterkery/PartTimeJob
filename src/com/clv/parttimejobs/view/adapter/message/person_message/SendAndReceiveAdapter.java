package com.clv.parttimejobs.view.adapter.message.person_message;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.message.person.MessageSend;
import com.clv.parttimejobs.util.music.MediaManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class SendAndReceiveAdapter extends BaseAdapter implements
		OnClickListener {

	private Context context;
	private List<MessageSend> messagelist;
	private int mMinItemWith;// 设置对话框的最大宽度和最小宽度
	private int mMaxItemWith;
	private Callback mCallback;

	private DisplayImageOptions options;

	public interface Callback {
		public void click(View v);
	}

	public SendAndReceiveAdapter(Context context,
			List<MessageSend> messagelist, Callback callback) {
		this.context = context;
		this.messagelist = messagelist;
		this.mCallback = callback;
		// 获取系统宽度
		WindowManager wManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wManager.getDefaultDisplay().getMetrics(outMetrics);
		mMaxItemWith = (int) (outMetrics.widthPixels * 0.7f);
		mMinItemWith = (int) (outMetrics.widthPixels * 0.15f);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messagelist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return messagelist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final MessageSend messagesend = messagelist.get(position);
		ViewHolder1 holder1;
		ViewHolder2 holder2;
		ViewHolder3 holder3;
		ViewHolder4 holder4;
		if (messagesend.getIsMy() == 1) {
			if (messagesend.getIstext() == 1) {
				// 自己发出的文字
				convertView = View.inflate(context,
						R.layout.chatting_msg_text_one_right, null);
				holder1 = new ViewHolder1(convertView);
				holder1.head_image.setImageBitmap(BitmapFactory
						.decodeFile(messagesend.getHeadimg_url()));
				holder1.seconds.setText(messagesend.getDatetime());
				holder1.text_content.setText(messagesend.getTextcontext());

			} else {
				// 自己发出的语音
				convertView = View.inflate(context,
						R.layout.chatting_msg_yuyin_one_right, null);
				holder2 = new ViewHolder2(convertView);
				if (!messagesend.getHeadimg_url().equals(""))
					holder2.head_image.setImageBitmap(BitmapFactory
							.decodeFile(messagesend.getHeadimg_url()));
				holder2.seconds.setText(messagesend.getTime() + "”");
				//
				ViewGroup.LayoutParams lParams = holder2.length
						.getLayoutParams();
				lParams.width = (int) (mMinItemWith + mMaxItemWith / 60f
						* messagesend.getTime() + 45);
				holder2.length.setLayoutParams(lParams);
				//
				holder2.text_head_time.setText(messagesend.getDatetime());
				holder2.image_yuyin.setOnClickListener(this);
				String po = position + "1";
				holder2.image_yuyin.setTag(po);

			}
		} else {
			if (messagesend.getIstext() == 1) {
				// 别人发出的文字
				convertView = View.inflate(context,
						R.layout.chatting_item_msg_text_left, null);

				holder3 = new ViewHolder3(convertView);
				if (!messagesend.getHeadimg_url().equals(""))
					holder3.head_image.setImageBitmap(BitmapFactory
							.decodeFile(messagesend.getHeadimg_url()));
				holder3.seconds.setText(messagesend.getDatetime());
				holder3.text_content.setText(messagesend.getTextcontext());
			} else {
				// 别人发出的语音
				convertView = View.inflate(context,
						R.layout.chatting_item_msg_yuyin_left, null);
				holder4 = new ViewHolder4(convertView);
				if (!messagesend.getHeadimg_url().equals(""))
					holder4.head_image.setImageBitmap(BitmapFactory
							.decodeFile(messagesend.getHeadimg_url()));
				holder4.seconds.setText(messagesend.getDatetime());
				holder4.text_head_name.setText(messagesend.getName());//
				ViewGroup.LayoutParams lParams = holder4.length
						.getLayoutParams();
				lParams.width = (int) (mMinItemWith + mMaxItemWith / 60f
						* messagesend.getTime());
				holder4.length.setLayoutParams(lParams);
				//
				holder4.text_head_time.setText(messagesend.getDatetime());
			}
		}

		return convertView;
	}

	class ViewHolder1 {
		TextView seconds;// 时间
		ImageView head_image;
		TextView text_content;
		TextView text_head_name;

		public ViewHolder1(View view) {
			seconds = (TextView) view.findViewById(R.id.tv_sendtime_my);
			text_content = (TextView) view.findViewById(R.id.tv_chatcontent_my);
			head_image = (ImageView) view.findViewById(R.id.iv_userhead_my);
			text_head_name = (TextView) view.findViewById(R.id.tv_username);
			view.setTag(this);
		}
	}

	class ViewHolder2 {
		TextView seconds;// 时间
		View length;// 对话框长度
		TextView text_head_time;
		TextView text_head_name;
		ImageView head_image;
		View image_yuyin;

		public ViewHolder2(View view) {
			text_head_time = (TextView) view
					.findViewById(R.id.tv_sendtime_my_yuyin);
			length = view.findViewById(R.id.recorder_length_my_yuyin);
			seconds = (TextView) view
					.findViewById(R.id.tv_time_my_yuyin_textview);
			head_image = (ImageView) view
					.findViewById(R.id.iv_userhead_my_yuyin);
			text_head_name = (TextView) view
					.findViewById(R.id.tv_username_my_yuyin);
			image_yuyin = view.findViewById(R.id.id_recorder_anim_my_yuyin);
			view.setTag(this);
		}
	}

	class ViewHolder3 {
		TextView seconds;// 时间
		ImageView head_image;
		TextView text_content;
		TextView text_head_name;
		TextView text_head_time;

		public ViewHolder3(View view) {
			seconds = (TextView) view.findViewById(R.id.tv_sendtime);
			text_content = (TextView) view.findViewById(R.id.tv_chatcontent);
			head_image = (ImageView) view.findViewById(R.id.iv_userhead);
			text_head_name = (TextView) view.findViewById(R.id.tv_username);

			view.setTag(this);
		}
	}

	class ViewHolder4 {
		TextView seconds;// 时间
		View length;// 对话框长度
		TextView text_head_time;
		TextView text_head_name;
		ImageView head_image;
		TextView text_long;

		public ViewHolder4(View view) {
			seconds = (TextView) view.findViewById(R.id.tv_time_yuyin);
			length = view.findViewById(R.id.recorder_length_yuyin);
			text_head_name = (TextView) view
					.findViewById(R.id.tv_username_yuyin);
			text_head_time = (TextView) view
					.findViewById(R.id.tv_sendtime_yuyin);
			head_image = (ImageView) view.findViewById(R.id.iv_userhead_yuyin);
			text_long = (TextView) view.findViewById(R.id.tv_username_yuyin);
			view.setTag(this);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCallback.click(v);
	}
}
