package edu.CalTestApp1.utils;

import android.content.Context;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;

import edu.CalTestApp1.R;

public class TaskButton extends Button {

    public TaskButton(Context context, String text, boolean isDone) {
        super(context);
        int heightInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, heightInPx));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        if (isDone) setBackground(getResources().getDrawable(R.drawable.done_task_button));
        else setBackground(getResources().getDrawable(R.drawable.undone_task_button));
        setTextColor(getResources().getColor(R.color.white));
        setText(text);
    }
}
