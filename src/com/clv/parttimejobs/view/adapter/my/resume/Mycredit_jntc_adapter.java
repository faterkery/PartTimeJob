package com.clv.parttimejobs.view.adapter.my.resume;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.resume.DeteTCInterface;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class Mycredit_jntc_adapter extends BaseAdapter {

	private Context context;
	private List<String> list;
	private DeteTCInterface tcinterface;
	public long index;

	private PopupWindow popupWindow;
	private TextView tvDelete;
	private int longClickPosition;

	public Mycredit_jntc_adapter(Context context, List<String> list,
			DeteTCInterface tcinterface) {
		super();
		this.context = context;
		this.list = list;
		this.tcinterface = tcinterface;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView,
			ViewGroup viewpartents) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		convertView = LayoutInflater.from(context).inflate(
				R.layout.item_mycredit_jntc, null);
		holder = new ViewHolder();
		holder.edittext = (EditText) convertView
				.findViewById(R.id.item_text_mycredit_editview);
		holder.textview = (TextView) convertView
				.findViewById(R.id.item_text_mycredit_textview);
		holder.relativelayout = (RelativeLayout) convertView
				.findViewById(R.id.item_text_mycredit_rey01);
		convertView.setTag(holder);
		String item = getItem(position);
		if ("".equals(item.trim())) {
			index = position;
		}
		holder.edittext.setText(item);

		if (holder.edittext.getTag() instanceof TextWatcher) {
			holder.edittext
					.removeTextChangedListener((TextWatcher) (holder.edittext
							.getTag()));
		}
		// 根据手指触碰的位置，获取当前EditText的位置；
		holder.edittext.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// 1.解决�?4个问�?(不可滑动的问�?)
				v.getParent().requestDisallowInterceptTouchEvent(true);
				if (event.getAction() == MotionEvent.ACTION_UP) {
					index = position;
					// 2.解决�?4个问�?(不可滑动的问�?)
					v.getParent().requestDisallowInterceptTouchEvent(false);
				}
				return false;
			}
		});
		holder.edittext
				.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if (hasFocus) {
							// 此处为得到焦点时的处理内�?
							holder.textview.setVisibility(View.VISIBLE);
						} else {
							// 此处为失去焦点时的处理内�?
							holder.textview.setVisibility(View.GONE);
							String data =holder.edittext.getText().toString();
							
						}
					}
				});
		TextWatcher watcher = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// 动�?�存储数�?
				tcinterface.addData(position, s.toString());
				holder.textview.requestFocus();
				Log.d("teg", "输入的文�?" + position + "" + s.toString());
				
			}
		};
		holder.edittext.addTextChangedListener(watcher);
		holder.edittext.setTag(watcher);

		
		if (index != -1 && index == position) {
			Log.d("teg", "index:" + position + "  position" + position);
			// 如果当前的行下标和点击事件中保存的index�?致，手动为EditText设置焦点�?
			holder.edittext.requestFocus();
			holder.textview.setVisibility(View.VISIBLE);
		} else {
			holder.textview.setVisibility(View.GONE);
		}
		// 解决光标始终处在�?�?始位置的问题�?
		holder.edittext.setSelection(holder.edittext.getText().length());
		holder.edittext.clearFocus();
		holder.relativelayout.setOnLongClickListener(longClickListener);
		holder.relativelayout.setTag(position);
		holder.edittext.setOnLongClickListener(longClickListener);
		holder.edittext.setTag(position);
		return convertView;
	}

	class ViewHolder {
		RelativeLayout relativelayout;
		EditText edittext;
		TextView textview;

		public ViewHolder() {
			super();
		}

	}

	public void getFocus(){
		
	}
	private OnLongClickListener longClickListener = new OnLongClickListener() {
		@Override
		public boolean onLongClick(View v) {
			longClickPosition = (Integer) v.getTag();
			showDialog(v);
			return true;
		}
	};

	private void showDialog(View view) {
		if (null == popupWindow) {
			View popView = LayoutInflater.from(context).inflate(
					R.layout.layout_long_click_dialog, null);
			tvDelete = (TextView) popView.findViewById(R.id.tv_delete);
			tvDelete.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					tcinterface.deteItem(longClickPosition);
					popupWindow.dismiss();
				}
			});
			popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			// popupWindow.setAnimationStyle(R.style.PopAnimStyle);
			popupWindow.setOutsideTouchable(true);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
		}
		if (popupWindow.isShowing())
			popupWindow.dismiss();

		// 第一次显示控件的时�?�宽高会�?0
		int deleteHeight = tvDelete.getHeight() == 0 ? 33 : tvDelete
				.getHeight();
		int deleteWidth = tvDelete.getWidth() == 0 ? 56 : tvDelete.getWidth();

		popupWindow.showAsDropDown(view, (view.getWidth() - deleteWidth) / 2,
				-view.getHeight() - deleteHeight);
	}

}
