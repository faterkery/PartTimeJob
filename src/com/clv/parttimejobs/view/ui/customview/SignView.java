package com.clv.parttimejobs.view.ui.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Calendar;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.util.ecutescreen.ResolutionUtil;
import com.clv.parttimejobs.view.adapter.my.dailyattendance.CalendarAdapter;

/**
 * 签到日历控件 Created by E.M on 2016/4/20.
 */
public class SignView extends View {
	private static final String[] WEEK_MARK = { "一", "二", "三", "四", "五", "六",
			"日" };

	private static final int MAX_COLUMN = 7;
	/**
	 * 周内
	 */
	private static final int COLOR_MARKER_WEEKDAY = 0xFF999999;
	private static final int COLOR_MARKER_WEEKEND = 0xFF1B89CD;
	/**
	 * 已签到背景色
	 */
	private static final int COLOR_BACKGROUND_HIGHLIGHT = 0xFF1B89CD;
	/**
	 * 未签到背景色
	 */
	private static final int COLOR_BACKGROUND_NORMAL = 0xFF999999;
	/**
	 * 等待签到背景色
	 */
	private static final int COLOR_BACKGROUND_WAIT = 0xFF333333;
	/**
	 * 已签到文字颜色
	 */
	private static final int COLOR_TEXT_HIGHLIGHT = 0xFF333333;
	/**
	 * 未签到文字颜色
	 */
	private static final int COLOR_TEXT_NORMAL = 0xFF999999;
	// /**
	// * 不可用文字颜色
	// */
	// private static final int COLOR_TEXT_DISABLED = 0xFFD4D4D4;

	private static final int MARKER_TEXT_SIZE = 47;
	private static final int CELL_TEXT_SIZE = 47;

	private static final int VERTICAL_SPACE = 61;
	private static final int VERTICAL_MARGIN = 20;
	private static final int HORIZONTAL_MARGIN = 7;
	private static final int CELL_SIZE = 28;
	private static final int WAIT_LINE_SIZE = 16;

	private int dayOfMonthToday;
	private int markerTextY;
	private int verticalCellTop;
	private int sumDayOfMonth;
	private int daysOfFirstWeek;
	private int horizontalSpace;
	private int deltaTextCellY;
	private int deltaTextMarkerY;

	private int verticalSpace;
	private int verticalMargin;
	private int horizontalMargin;
	private int cellSize;
	private int waitLineSize;

	private Path waitPath;
	private Rect waitRect;
	private Paint paintWeekday;
	private Paint paintWeekend;
	private Paint paintTextNormal;
	private Paint paintTextHighlight;
	private Paint paintBackgroundWait;
	private Paint paintBackgroundNormal;
	private Paint paintBackgroundHighlight;
	private CalendarAdapter adapter;
	private ResolutionUtil resolutionUtil;
	private List<String> dayArray;

	private Calendar calendar;

	public SignView(Context context) {
		this(context, null);
	}

	public SignView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public SignView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initResolution();
		initPaint();
		initData();
	}

	private void initResolution() {
		resolutionUtil = ResolutionUtil.getInstance();
		// verticalSpace = resolutionUtil.formatVertical(VERTICAL_SPACE);
		// verticalMargin = resolutionUtil.formatVertical(VERTICAL_MARGIN);
		// horizontalMargin =
		// resolutionUtil.formatHorizontal(HORIZONTAL_MARGIN);
		// cellSize = resolutionUtil.formatVertical(CELL_SIZE);
		// waitLineSize = resolutionUtil.formatVertical(WAIT_LINE_SIZE);
		verticalSpace = VERTICAL_SPACE;
		verticalMargin = VERTICAL_MARGIN;
		horizontalMargin = HORIZONTAL_MARGIN;
		cellSize = CELL_SIZE;
		waitLineSize = WAIT_LINE_SIZE;
		System.out.println(verticalSpace + "---" + verticalMargin + "----"
				+ horizontalMargin + "----" + cellSize + "---" + waitLineSize);
	}

	private void initPaint() {
		// int markerTextSize = resolutionUtil.formatVertical(MARKER_TEXT_SIZE);
		// int cellTextSize = resolutionUtil.formatVertical(CELL_TEXT_SIZE);
		int markerTextSize = MARKER_TEXT_SIZE;
		int cellTextSize = CELL_TEXT_SIZE;

		paintWeekday = new Paint();
		paintWeekday.setAntiAlias(true);
		paintWeekday.setColor(COLOR_MARKER_WEEKDAY);
		paintWeekday.setTextSize(markerTextSize);
		paintWeekday.setTextAlign(Paint.Align.CENTER);

		paintWeekend = new Paint();
		paintWeekend.setAntiAlias(true);
		paintWeekend.setColor(COLOR_MARKER_WEEKEND);
		paintWeekend.setTextSize(markerTextSize);
		paintWeekend.setTextAlign(Paint.Align.CENTER);

		paintTextNormal = new Paint();
		paintTextNormal.setAntiAlias(true);
		paintTextNormal.setColor(COLOR_TEXT_NORMAL);
		paintTextNormal.setTextSize(cellTextSize);
		paintTextNormal.setTextAlign(Paint.Align.CENTER);

		paintTextHighlight = new Paint();
		paintTextHighlight.setAntiAlias(true);
		paintTextHighlight.setColor(COLOR_TEXT_HIGHLIGHT);
		paintTextHighlight.setTextSize(cellTextSize);
		paintTextHighlight.setTextAlign(Paint.Align.CENTER);

		paintBackgroundWait = new Paint();
		paintBackgroundWait.setAntiAlias(true);
		paintBackgroundWait.setColor(COLOR_BACKGROUND_WAIT);
		paintBackgroundWait.setStrokeWidth(2);
		paintBackgroundWait.setStyle(Paint.Style.STROKE);

		paintBackgroundNormal = new Paint();
		paintBackgroundNormal.setAntiAlias(true);
		paintBackgroundNormal.setColor(COLOR_BACKGROUND_NORMAL);
		paintBackgroundNormal.setStrokeWidth(2);
		paintBackgroundNormal.setStyle(Paint.Style.STROKE);

		paintBackgroundHighlight = new Paint();
		paintBackgroundHighlight.setAntiAlias(true);
		paintBackgroundHighlight.setColor(COLOR_BACKGROUND_HIGHLIGHT);
		paintBackgroundHighlight.setStyle(Paint.Style.FILL_AND_STROKE);
	}

	private void initData() {
		Paint.FontMetricsInt fmiMarker = paintWeekday.getFontMetricsInt();
		deltaTextMarkerY = -(fmiMarker.bottom - fmiMarker.top) / 2
				- fmiMarker.top;
		markerTextY = verticalMargin + cellSize / 2;

		Paint.FontMetricsInt fmiCell = paintTextNormal.getFontMetricsInt();
		deltaTextCellY = -(fmiCell.bottom - fmiCell.top) / 2 - fmiCell.top;
		verticalCellTop = verticalMargin + cellSize;

		Calendar calendarToday = Calendar.getInstance();
		calendar = calendarToday;
		dayOfMonthToday = calendarToday.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek;
		sumDayOfMonth = calendarToday.getActualMaximum(Calendar.DAY_OF_MONTH);

		Calendar calendarFirstDay = Calendar.getInstance();
		calendarFirstDay.set(Calendar.DAY_OF_MONTH, 1);
		dayOfWeek = calendarFirstDay.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.SUNDAY) {
			dayOfWeek = 7;
		} else {
			dayOfWeek = dayOfWeek - 1;
		}
		daysOfFirstWeek = MAX_COLUMN - dayOfWeek + 1;
	}

	public void setDate(Calendar c) {
		calendar = c;
		System.out.println(c.get(Calendar.MONTH));
		sumDayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int dayOfWeek;
		c.set(Calendar.DAY_OF_MONTH, 1);
		dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		System.out.println(dayOfMonthToday + "---" + sumDayOfMonth + "---"
				+ dayOfWeek);
		if (dayOfWeek == Calendar.SUNDAY) {
			dayOfWeek = 7;
		} else {
			dayOfWeek = dayOfWeek - 1;
		}
		daysOfFirstWeek = MAX_COLUMN - dayOfWeek + 1;
		System.out.println(daysOfFirstWeek);
	}

	private void createWaitBackground(int topX, int topY) {
		waitPath = new Path();
		waitPath.moveTo(topX, topY + waitLineSize);
		waitPath.lineTo(topX, topY);
		waitPath.lineTo(topX + waitLineSize, topY);

		waitPath.moveTo(topX + cellSize - waitLineSize, topY + cellSize);
		waitPath.lineTo(topX + cellSize, topY + cellSize);
		waitPath.lineTo(topX + cellSize, topY + cellSize - waitLineSize);

		waitRect = new Rect(topX, topY, topX + cellSize, topY + cellSize);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		horizontalSpace = (w - MAX_COLUMN * cellSize - horizontalMargin * 2-9)
				/ (MAX_COLUMN - 1);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		drawWeekMark(canvas);
		drawCellsBackground(canvas);
		drawCells(canvas);
		drawcurrentCells(canvas);
		drawGift(canvas);
	}

	private void drawcurrentCells(Canvas canvas) {
		Calendar calendar = Calendar.getInstance();

	}

	// 画星期一到星期日
	private void drawWeekMark(Canvas canvas) {
		int x = 0;
		int y = markerTextY + deltaTextMarkerY;
		for (int i = 0; i < 7; i++) {
			x = horizontalMargin + i * (horizontalSpace + cellSize) + cellSize
					/ 2;
			if (i < 7) {
				canvas.drawText(WEEK_MARK[i], x+7, y, paintWeekday);
			} else {
				canvas.drawText(WEEK_MARK[i], x+7, y, paintWeekend);
			}
		}
	}

	// 画日期的圆
	private void drawCellsBackground(Canvas canvas) {
		for (int i = 1; i <= dayOfMonthToday; i++) {
			drawCellBackground(canvas, i, getColumnIndex(i), getRowIndex(i));
		}
	}

	/**
	 * 根据行列序号绘制日期背景
	 *
	 * @param canvas
	 *            画布
	 * @param dayOfMonth
	 *            日期
	 * @param column
	 *            列序号
	 * @param row
	 *            行序号
	 */
	private void drawCellBackground(Canvas canvas, int dayOfMonth, int column,
			int row) {
		int x = horizontalMargin + column * (horizontalSpace + cellSize)
				+ cellSize / 2;
		int y = verticalCellTop + verticalSpace * (row + 1) + cellSize * row
				+ cellSize / 2;
		Calendar calendar1 = Calendar.getInstance();
		int month = calendar1.get(Calendar.MONTH);
		int year = calendar1.get(Calendar.YEAR);
		if ((year == calendar.get(Calendar.YEAR))
				&& (month == calendar.get(Calendar.MONTH) && (dayOfMonth == dayOfMonthToday))) {
			canvas.drawCircle(x+7, y, cellSize, paintBackgroundHighlight);
		} else if (adapter != null) {
			DayType dayType = adapter.getType(dayOfMonth);
			switch (dayType) {
			case WAITING:
				if (waitPath == null) {
					createWaitBackground(x - cellSize / 2, y - cellSize / 2);
				}
				// canvas.drawPath(waitPath, paintBackgroundWait);
				break;
			case SIGNED: {// 已签到
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.defult_icon_annular_nor);
				canvas.drawBitmap(bitmap, x - 30, y - 30,
						paintBackgroundHighlight);
				break;
			}
			case UNSIGNED: {
				break;
			}
			default:
				break;
			}
		} else {
			// if(dayOfMonth==dayOfMonthToday)
			// canvas.drawCircle(x, y, cellSize / 2, paintBackgroundHighlight);
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.defult_icon_annular_nor);
			canvas.drawBitmap(bitmap, x - 30, y - 30, paintBackgroundHighlight);
		}
	}

	private void drawGift(Canvas canvas){
		for (int i = 1; i <= sumDayOfMonth; i++) {
			drawGiftImg(canvas, i, getColumnIndex(i), getRowIndex(i));
		}
	}
	private void drawGiftImg(Canvas canvas, int dayOfMonth, int column,
			int row) {
		int x = horizontalMargin + column * (horizontalSpace + cellSize)
				+ cellSize / 2;
		int y = verticalCellTop + verticalSpace * (row + 1) + cellSize * row
				+ cellSize / 2;
		if (adapter != null){
			DayType dayType = adapter.getType(dayOfMonth);
			switch (dayType) {
			case DISABLED:{//礼物
				System.out.println("画礼物");
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.defult_icon_gift_nor);
				canvas.drawBitmap(bitmap, x- 30, y-30, paintBackgroundHighlight);
				break;
			}
			}
		}
	}
	private void drawCells(Canvas canvas) {

		for (int i = 1; i <= sumDayOfMonth; i++) {
			drawCell(canvas, i, getColumnIndex(i), getRowIndex(i));
		}
	}

	/**
	 * 根据行列序号绘制日期
	 *
	 * @param canvas
	 *            画布
	 * @param dayOfMonth
	 *            日期
	 * @param column
	 *            列序号
	 * @param row
	 *            行序号
	 */
	private void drawCell(Canvas canvas, int dayOfMonth, int column, int row) {
		Calendar calendar1 = Calendar.getInstance();
		int month_current = calendar1.get(Calendar.MONTH);
		int year_current = calendar1.get(Calendar.YEAR);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int x = horizontalMargin + column * (horizontalSpace + cellSize)
				+ cellSize / 2;
		int y = verticalCellTop + verticalSpace * (row + 1) + cellSize * row
				+ cellSize / 2 + deltaTextCellY;
		// 这里要写判断 字的颜色
		if(year_current>year){
			canvas.drawText(String.valueOf(dayOfMonth), x+7, y,
					paintTextNormal);
		}else if((year_current == year) && (month_current > month)){
			canvas.drawText(String.valueOf(dayOfMonth), x+7, y,
					paintTextNormal);
		}
		else if ((year_current == year) && (month_current == month)) {
			if (dayOfMonth < dayOfMonthToday) {
				canvas.drawText(String.valueOf(dayOfMonth), x+7, y,
						paintTextNormal);
			} else {
				canvas.drawText(String.valueOf(dayOfMonth), x+7, y,
						paintTextHighlight);
			}
		} else {
			canvas.drawText(String.valueOf(dayOfMonth), x+7, y,
					paintTextHighlight);
		}
	}

	/**
	 * 获取列序号
	 *
	 * @param dayOfMonth
	 *            日期
	 * @return 列序号
	 */
	private int getColumnIndex(int dayOfMonth) {
		calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.SUNDAY) {
			dayOfWeek = 6;
		} else {
			dayOfWeek = dayOfWeek - 2;
		}
		return dayOfWeek;
	}

	/**
	 * 获取行序号
	 *
	 * @param dayOfMonth
	 *            日期
	 * @return 行序号
	 */
	private int getRowIndex(int dayOfMonth) {
		float weight = (dayOfMonth - daysOfFirstWeek) / (MAX_COLUMN * 1f);
		System.out.println(weight);
		double rowIndexDouble = Math.abs(Math.ceil(weight));
		return (int) rowIndexDouble;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			float x = event.getX();
			float y = event.getY();
			if (waitPath != null) {
				if (adapter.getType(dayOfMonthToday).equals(DayType.WAITING)) {
					if (x >= waitRect.left && y >= waitRect.top
							&& x <= waitRect.right && y <= waitRect.bottom) {
						if (onTodayClickListener != null) {
							onTodayClickListener.onTodayClick();
						}
					}
				}
			}
		}
		return true;
	}

	public void setAdapter(CalendarAdapter adapter) {
		this.adapter = adapter;
		this.invalidate();
	}

	public void clearAdapter(){
		this.adapter=null;
		this.invalidate();
	}
	public int getDayOfMonthToday() {
		return dayOfMonthToday;
	}

	public void notifyDataSetChanged() {
		invalidate();
	}

	private OnTodayClickListener onTodayClickListener;

	public void setOnTodayClickListener(
			OnTodayClickListener onTodayClickListener) {
		this.onTodayClickListener = onTodayClickListener;
	}

	public interface OnTodayClickListener {
		void onTodayClick();
	}

	public enum DayType {
		/**
		 * 已签到状态，时间已过
		 */
		SIGNED(0),
		/**
		 * 未签到状态，时间已过
		 */
		UNSIGNED(1),
		/**
		 * 等待状态，即当日还未签到
		 */
		WAITING(2),
		/**
		 * 不可达到状态，未到时间
		 */
		UNREACHABLE(3),
		/**
		 * 不可用状态，非当前月份
		 */
		DISABLED(4);
		/**
		 * 礼物
		 */

		private int value;

		DayType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static DayType valueOf(int value) {
			switch (value) {
			case 0:
				return SIGNED;
			case 1:
				return UNSIGNED;
			case 2:
				return WAITING;
			case 3:
				return UNREACHABLE;
			case 4:
				return DISABLED;
			default:
				return DISABLED;
			}
		}
	}
}
