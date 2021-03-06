package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

import controllers.DatePickerController;

/**
 * Created by priyabratapatnaik on 03/11/15.
 */
public class SimpleWeekView extends WeekView {

    public SimpleWeekView(Context context, AttributeSet attr, DatePickerController controller) {
        super(context, attr, controller);
    }

    public SimpleWeekView(Context context, AttributeSet attrs) {
        super(context, attrs, null);
    }

    public SimpleWeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void drawWeekDate(Canvas canvas, Day day,
                             int x, int y, int startX, int stopX, int startY, int stopY) {
        Day selectedDay = mController == null ? null : mController.getSelectedDay();

        if (selectedDay != null && selectedDay.equals(day)) {
            canvas.drawCircle(x , y - (mDateTextSize / 3), mSelectedDayCircleSize,
                    mSelectedCirclePaint);
        }

        if (isHighlighted(day)) {
            canvas.drawCircle(x , y - (mDateTextSize / 3), mSelectedDayCircleSize,
                    mSelectedCirclePaint);
            mMonthNumPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        } else {
            mMonthNumPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        }

        // If we have a mindate or maxdate, gray out the day number if it's outside the range.
        if (mController != null && mController.isOutOfRange(day)) {
            mMonthNumPaint.setColor(mDisabledDayTextColor);
        } else if (selectedDay != null && selectedDay.equals(day)) {
            mMonthNumPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            mMonthNumPaint.setColor(mSelectedDayTextColor);
        } else if (mHasToday && mToday == day.getDate()) {
            mMonthNumPaint.setColor(mTodayNumberColor);
        } else {
            mMonthNumPaint.setColor(isHighlighted(day) ? mHighlightedDayTextColor : mDayTextColor);
        }

        canvas.drawText(String.format("%d", (day == null ? -1 : day.getDate())), x, y, mMonthNumPaint);
    }
}
