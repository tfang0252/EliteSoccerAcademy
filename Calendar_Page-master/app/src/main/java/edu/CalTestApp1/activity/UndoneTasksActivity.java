package edu.CalTestApp1.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.CalTestApp1.R;
import edu.CalTestApp1.domain.Task;
import edu.CalTestApp1.utils.TaskLayout;
import edu.CalTestApp1.utils.Utils;

public class UndoneTasksActivity extends Activity {

    public static final String TASKS_TO_MODIFY = "TasksToModify";
    private ArrayList<Task> undoneTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.undone_tasks);
        undoneTasks = (ArrayList<Task>) getIntent().getSerializableExtra(MainActivity.UNDONE_TASKS_TO_SHOW);
        initializeView();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(UndoneTasksActivity.this, MainActivity.class);
        i.putExtra(TASKS_TO_MODIFY, undoneTasks);
        setResult(RESULT_OK, i);
        finish();
    }

    private void initializeView() {
        if (!undoneTasks.isEmpty()) {
            TextView emptyInfo = (TextView) findViewById(R.id.emptyUndoneTasksInfo);
            emptyInfo.setVisibility(View.GONE);
        }
        else return;
        LinearLayout layoutToAdd = (LinearLayout) findViewById(R.id.undoneTaskLayout);
        int marginTenInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        int marginEightInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        int marginOneInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams layoutParams;
        for (Task t : undoneTasks) {
            final Long id = t.getId();
            final TaskLayout layout = new TaskLayout(this, t.getTitle(), Utils.dateToString(t.getDateStart()), Utils.dateToString(t.getDateEnd()), t.getMeetingAssociated());
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(marginTenInPx, 0, 0, marginTenInPx);
            layoutToAdd.addView(layout, layoutParams);

            final View view = new View(this);
            view.setBackgroundColor(getResources().getColor(R.color.line));
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, marginOneInPx);
            layoutParams.setMargins(0, 0, 0, marginEightInPx);
            layoutToAdd.addView(view, layoutParams);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(UndoneTasksActivity.this)
                            .setTitle("Set Done a task")
                            .setMessage("Are you sure you want to set this task as done?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    for (Task t : undoneTasks) {
                                        if (t.getId() == id) {
                                            t.setDone(true);
                                            break;
                                        }
                                    }
                                    layout.setVisibility(View.GONE);
                                    view.setVisibility(View.GONE);

                                    int cont = 0;
                                    for (Task t : undoneTasks) {
                                        if (t.isDone()) ++cont;
                                    }
                                    if (cont == undoneTasks.size()) {
                                        TextView emptyInfo = (TextView) findViewById(R.id.emptyUndoneTasksInfo);
                                        emptyInfo.setVisibility(View.VISIBLE);
                                    }

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_menu_edit)
                            .show();
                }
            });
        }
    }
}
