package com.clv.parttimejobs.view.ui.customview;

import com.clv.parttimejobs.model.interfacebackage.mainlayout.mywallet.MyWalletLrmmListener;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

public class PassWordEditTextView extends EditText {
	private Paint mBorderPaint; // 外框画笔
	private Paint mBorderKuangPaint;
	private Paint mLinePaint; // 线的画笔
	private Paint mPasswordPaint; // 密码画笔
	private int mPasswordTextLength; // 输入密码的长度
	private int mWidth;
	private int mHeight;

	private static final int PASSWORD_LENGTH = 6;// 密码的长度
	private static final int PASSWORD_RADIUS = 15;

	private int position =0;
	private MyWalletLrmmListener listener;
	
	public PassWordEditTextView(Context context) {
		this(context, null);
	}

	public PassWordEditTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PassWordEditTextView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		initView();
	}

	private void initView() {
		setFocusable(true);

		mBorderPaint = new Paint();
		mBorderPaint.setStrokeWidth(1);
		mBorderPaint.setColor(Color.WHITE);
		mBorderPaint.setStyle(Paint.Style.FILL);

		mBorderKuangPaint = new Paint();
		mBorderKuangPaint.setStrokeWidth(2);
		mBorderKuangPaint.setColor(Color.GRAY);
		mBorderKuangPaint.setStyle(Paint.Style.STROKE);
		
		mLinePaint = new Paint();
		mLinePaint.setColor(Color.parseColor("#838B8B"));
		mLinePaint.setStrokeWidth(4);

		mPasswordPaint = new Paint();
		mPasswordPaint.setColor(Color.BLACK);
		mPasswordPaint.setStrokeWidth(12);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		mWidth = getMeasuredWidth();
		mHeight = getMeasuredHeight();

		drawRoundRect(canvas);
		// 是否需要分割线
		// drawLine(canvas);
		drawPassword(canvas);
	}

	/**
	 * 绘制圆角矩形背景
	 * 
	 * 
	 */
	private void drawRoundRect(Canvas canvas) {
		RectF r1 = new RectF(); // Rect对象
		r1.left = 2; // 左边
		r1.top = 2; // 上边
		r1.right = mWidth-2; // 右边
		r1.bottom = mHeight-2; // 下边
		canvas.drawRoundRect(r1, 15, 15, mBorderPaint);
		RectF r2 = new RectF(); // Rect对象
		r2.left = 0; // 左边
		r2.top = 0; // 上边
		r2.right = mWidth; // 右边
		r2.bottom = mHeight; // 下边
		canvas.drawRoundRect(r1, 15, 15, mBorderKuangPaint);
	}

	/**
	 * 绘制分割线
	 * 
	 * @param canvas
	 */
	private void drawLine(Canvas canvas) {
		for (int i = 1; i < PASSWORD_LENGTH; i++) {
			float x = mWidth * i / PASSWORD_LENGTH;
			canvas.drawLine(x, 12, x, mHeight - 12, mLinePaint);
		}
	}

	/**
	 * 绘制密码
	 * 
	 * @param canvas
	 */
	private void drawPassword(Canvas canvas) {
		float cx, cy = mHeight / 2;
		float half = mWidth / PASSWORD_LENGTH / 2;
		for (int i = 0; i < mPasswordTextLength; i++) {
			cx = mWidth * i / PASSWORD_LENGTH + half;
			canvas.drawCircle(cx, cy, PASSWORD_RADIUS, mPasswordPaint);
		}
	}

	@Override
	protected void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		super.onTextChanged(text, start, lengthBefore, lengthAfter);

		mPasswordTextLength = text.toString().length();

		if (mPasswordTextLength == PASSWORD_LENGTH) {
//			Toast.makeText(getContext(), "您设置的密码为: " + text, Toast.LENGTH_SHORT)
//					.show();
//			;
			if(listener!=null){
				listener.finish(position);
			}
		}else{
			if(listener!=null){
				listener.dontfinish(position);
			}
		}

		invalidate();
	}

	public void reset() {
		setText("");
		invalidate();
	}
	public void setListener(MyWalletLrmmListener listener,int position){
		this.listener=listener;
		this.position=position;
	}
}
