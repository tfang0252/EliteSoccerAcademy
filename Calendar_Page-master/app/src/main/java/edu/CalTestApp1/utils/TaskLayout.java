package edu.CalTestApp1.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.CalTestApp1.R;

public class TaskLayout extends LinearLayout {

    public TaskLayout(Context context, String title, String startDate, String endDate, String associatedMeeting) {
        super(context);

        // Initialize of Margins
        int marginTenInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        // Initialize of Main LinearLayout
        setOrientation(VERTICAL);

        // Initialize of Title TextView
        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTypeface(null, Typeface.BOLD);
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        titleView.setTextColor(getResources().getColor(R.color.softblack));
        LinearLayout.LayoutParams layoutParamsOfTextView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfTextView.setMargins(0, 0, 0, marginTenInPx);
        addView(titleView, layoutParamsOfTextView);

        // Initialize of Second LinearLayout
        LinearLayout layoutToAdd = new LinearLayout(context);
        layoutToAdd.setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams layoutParamsOfSecondLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(layoutToAdd, layoutParamsOfSecondLayout);

        // Initialize of Start Date TextView
        TextView startDateView = new TextView(context);
        startDateView.setText(startDate);
        startDateView.setTypeface(null, Typeface.ITALIC);
        startDateView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        startDateView.setTextColor(getResources().getColor(R.color.gray));
        LinearLayout.LayoutParams layoutParamsOfStartDate = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfStartDate.setMargins(0, 0, marginTenInPx, 0);
        layoutToAdd.addView(startDateView, layoutParamsOfStartDate);

        // Initialize of End Date TextView
        TextView endDateView = new TextView(context);
        endDateView.setText(endDate);
        endDateView.setTypeface(null, Typeface.ITALIC);
        endDateView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        endDateView.setTextColor(getResources().getColor(R.color.gray));
        LinearLayout.LayoutParams layoutParamsOfEndDate = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfEndDate.setMargins(0, 0, marginTenInPx, 0);
        layoutToAdd.addView(endDateView, layoutParamsOfEndDate);

        // Initialize of Associated Meeting TextView
        TextView associatedMeetingView = new TextView(context);
        associatedMeetingView.setText(associatedMeeting);
        associatedMeetingView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        associatedMeetingView.setTextColor(getResources().getColor(R.color.gray));
        associatedMeetingView.setGravity(Gravity.RIGHT);

        LinearLayout.LayoutParams layoutParamsOfAssociatedMeeting = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfAssociatedMeeting.setMargins(0, 0, marginTenInPx, 0);
        layoutToAdd.addView(associatedMeetingView, layoutParamsOfAssociatedMeeting);

    }
}
