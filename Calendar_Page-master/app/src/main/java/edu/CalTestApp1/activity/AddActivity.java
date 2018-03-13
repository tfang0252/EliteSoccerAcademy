package edu.CalTestApp1.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.CalTestApp1.R;
import edu.CalTestApp1.domain.Event;
import edu.CalTestApp1.domain.Meeting;
import edu.CalTestApp1.domain.Task;
import edu.CalTestApp1.utils.Utils;

public class AddActivity extends Activity {

    public static final String EMPTY = "";
    public static final String FIRST_TITLE_MEETING = "Date";
    public static final String FIRST_TITLE_TASK = "Title";
    public static final String SECOND_TITLE_MEETING = "Start time";
    public static final String SECOND_TITLE_TASK = "Start date";
    public static final String THIRD_TITLE_MEETING = "End time";
    public static final String THIRD_TITLE_TASK = "End date";
    public static final String FOURTH_TITLE_MEETING = "Description";
    public static final String FOURTH_TITLE_TASK = "Associated meeting";
    public static final String HELP_TASK = "Please, double tap the fields\nstart date, end date and associated meeting";
    public static final String HELP_MEETING = "Please, double tap the fields\ndate, start time and end time";
    public static final String TOAST_ERROR = "Some field is in a wrong format or empty";
    public static final String EMPTY_MEETINGS = "There is no meeting to associate";
    public static final String EVENT_TO_SEND = "EventToSend";
    public static final String ASSOCIATED_MEETING = "AssociatedMeeting";

    private Event event;
    private ArrayList<String> meetings;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_events);
        inflater = LayoutInflater.from(AddActivity.this);
        event = (Event) getIntent().getSerializableExtra(MainActivity.EVENT_TO_ADD);
        meetings = (ArrayList<String>) getIntent().getSerializableExtra(MainActivity.LIST_MEETINGS);
        try {
            initializeView();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initializeView() throws ParseException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Button okButton = (Button) findViewById(R.id.okButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final TextView firstOption = (TextView) findViewById(R.id.meetingDateTitle);
        final EditText firstField = (EditText) findViewById(R.id.meetingDateField);

        final TextView secondOption = (TextView) findViewById(R.id.meetingHourStartTitle);
        final EditText secondField = (EditText) findViewById(R.id.meetingHourStartField);

        final TextView thirdOption = (TextView) findViewById(R.id.meetingHourEndTitle);
        final EditText thirdField = (EditText) findViewById(R.id.meetingHourEndField);

        final TextView fourthOption = (TextView) findViewById(R.id.meetingDescriptionTitle);
        final EditText fourthField = (EditText) findViewById(R.id.meetingDescriptionField);

        final TextView help = (TextView) findViewById(R.id.helpAdd);

        final RadioButton meetingButton =(RadioButton) findViewById(R.id.meeting_button);
        final RadioButton taskButton =(RadioButton) findViewById(R.id.task_button);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup_button);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case -1:
                        // Nothing checked it
                        break;
                    case R.id.meeting_button:
                        firstOption.setText(FIRST_TITLE_MEETING);
                        firstField.setVisibility(View.VISIBLE);
                        firstField.setText(EMPTY);
                        firstField.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
                        firstField.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final DatePicker datePicker = (DatePicker) inflater.inflate(getResources().getLayout(R.layout.date_picker), null);
                                new AlertDialog.Builder(AddActivity.this)
                                        .setTitle("Select a day")
                                        .setMessage("Select the meeting's day")
                                        .setView(datePicker)
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                firstField.setText(Utils.getDayInString(datePicker.getCalendarView().getDate()));
                                            }
                                        })
                                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                        })
                                        .setIcon(getResources().getDrawable(R.drawable.ic_menu_month))
                                        .show();
                            }
                        });
                        secondOption.setText(SECOND_TITLE_MEETING);
                        secondField.setVisibility(View.VISIBLE);
                        secondField.setText(EMPTY);
                        final TimePicker timePickerEndTime = new TimePicker(AddActivity.this);;
                        secondField.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final TimePicker timePicker = new TimePicker(AddActivity.this);
                                timePicker.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                                timePicker.setIs24HourView(false);
                                new AlertDialog.Builder(AddActivity.this)
                                        .setTitle("Select a start time")
                                        .setMessage("Select the meeting start time")
                                        .setView(timePicker)
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                secondField.setText(Utils.hourAndMinuteToString(timePicker.getCurrentHour(), timePicker.getCurrentMinute()));
                                                timePickerEndTime.setCurrentHour(timePicker.getCurrentHour());
                                                timePickerEndTime.setCurrentMinute(timePicker.getCurrentMinute());
                                            }
                                        })
                                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                        })
                                        .setIcon(getResources().getDrawable(R.drawable.ic_menu_recent_history))
                                        .show();
                            }
                        });
                        thirdOption.setText(THIRD_TITLE_MEETING);
                        thirdField.setVisibility(View.VISIBLE);
                        thirdField.setText(EMPTY);
                        thirdField.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePickerEndTime.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                                timePickerEndTime.setIs24HourView(false);
                                new AlertDialog.Builder(AddActivity.this)
                                        .setTitle("Select a end time")
                                        .setMessage("Select the meeting end time")
                                        .setView(timePickerEndTime)
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                thirdField.setText(Utils.hourAndMinuteToString(timePickerEndTime.getCurrentHour(), timePickerEndTime.getCurrentMinute()));
                                            }
                                        })
                                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                        })
                                        .setIcon(getResources().getDrawable(R.drawable.ic_menu_recent_history))
                                        .show();
                            }
                        });
                        fourthOption.setText(FOURTH_TITLE_MEETING);
                        fourthField.setVisibility(View.VISIBLE);
                        fourthField.setText(EMPTY);
                        fourthField.setOnClickListener(null);
                        help.setText(HELP_MEETING);
                        break;
                    case R.id.task_button:
                        firstOption.setText(FIRST_TITLE_TASK);
                        firstField.setVisibility(View.VISIBLE);
                        firstField.setText(EMPTY);
                        firstField.setInputType(InputType.TYPE_CLASS_TEXT);
                        firstField.setOnClickListener(null);
                        secondOption.setText(SECOND_TITLE_TASK);
                        secondField.setVisibility(View.VISIBLE);
                        secondField.setText(EMPTY);
                        secondField.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
                        final DatePicker datePickerEndDate = (DatePicker) inflater.inflate(getResources().getLayout(R.layout.date_picker), null);
                        secondField.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final DatePicker datePicker = (DatePicker) inflater.inflate(getResources().getLayout(R.layout.date_picker), null);
                                new AlertDialog.Builder(AddActivity.this)
                                        .setTitle("Select a start day")
                                        .setMessage("Select the task start day")
                                        .setView(datePicker)
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                secondField.setText(Utils.getDayInString(datePicker.getCalendarView().getDate()));
                                                Date date = new Date(datePicker.getCalendarView().getDate());
                                                datePickerEndDate.updateDate(Utils.getYear(date), Utils.getMonth(date), Utils.getDay(date));
                                            }
                                        })
                                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                        })
                                        .setIcon(getResources().getDrawable(R.drawable.ic_menu_month))
                                        .show();
                            }
                        });
                        thirdOption.setText(THIRD_TITLE_TASK);
                        thirdField.setVisibility(View.VISIBLE);
                        thirdField.setText(EMPTY);
                        thirdField.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
                        thirdField.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new AlertDialog.Builder(AddActivity.this)
                                        .setTitle("Select a end day")
                                        .setMessage("Select the task end day")
                                        .setView(datePickerEndDate)
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                thirdField.setText(Utils.getDayInString(datePickerEndDate.getCalendarView().getDate()));
                                            }
                                        })
                                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                        })
                                        .setIcon(getResources().getDrawable(R.drawable.ic_menu_month))
                                        .show();
                            }
                        });
                        fourthOption.setText(FOURTH_TITLE_TASK);
                        fourthField.setVisibility(View.VISIBLE);
                        fourthField.setText(EMPTY);
                        fourthField.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (meetings.size() != 0) {
                                    final RadioGroup group = new RadioGroup(AddActivity.this);
                                    group.setOrientation(LinearLayout.VERTICAL);
                                    for (String s : meetings) {
                                        RadioButton button = new RadioButton(AddActivity.this);
                                        button.setText(s);
                                        group.addView(button);
                                    }
                                    new AlertDialog.Builder(AddActivity.this)
                                            .setTitle("Select an associated meeting")
                                            .setMessage("Select the associated meeting (if it exists)")
                                            .setView(group)
                                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    String text = EMPTY;
                                                    for (int i = 0; i < group.getChildCount(); i++) {
                                                        RadioButton button = (RadioButton) group.getChildAt(i);
                                                        if (button.isChecked()) {
                                                            text = button.getText().toString();
                                                            break;
                                                        }

                                                    }
                                                    fourthField.setText(text);
                                                }
                                            })
                                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // do nothing
                                                }
                                            })
                                            .setIcon(getResources().getDrawable(R.drawable.ic_menu_attachment))
                                            .show();
                                }
                                else {
                                    final TextView textView = new TextView(AddActivity.this);
                                    textView.setText(EMPTY_MEETINGS);
                                    textView.setTypeface(null, Typeface.ITALIC);
                                    new AlertDialog.Builder(AddActivity.this)
                                            .setTitle("Select an associated meeting")
                                            .setMessage("Select the associated meeting (if it exists)")
                                            .setView(textView)
                                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    fourthField.setText(EMPTY);
                                                }
                                            })
                                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // do nothing
                                                }
                                            })
                                            .setIcon(getResources().getDrawable(R.drawable.ic_menu_attachment))
                                            .show();
                                }
                            }
                        });
                        help.setText(HELP_TASK);
                        break;
                    default:
                        // Nothing to do
                        break;
                }
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (meetingButton.isChecked()) {
                        int hourStart, minuteStart, hourEnd, minuteEnd;
                        Date startDate, endDate;
                        String description;

                        if (fourthField.getText().toString().isEmpty())
                            throw new ParseException("Empty Description", 138);
                        description = fourthField.getText().toString();

                        startDate = dateFormat.parse(firstField.getText().toString());
                        hourStart = Utils.getHour(secondField.getText().toString());
                        minuteStart = Utils.getMinute(secondField.getText().toString());
                        hourEnd = Utils.getHour(thirdField.getText().toString());
                        minuteEnd = Utils.getMinute(thirdField.getText().toString());

                        if (hourStart > hourEnd || (hourStart == hourEnd && minuteStart >= minuteEnd))
                            throw new ParseException("HourStart is bigger than HourEnd", 148);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(startDate);
                        calendar.set(Calendar.HOUR_OF_DAY, hourStart);
                        calendar.set(Calendar.MINUTE, minuteStart);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        startDate = calendar.getTime();

                        calendar.set(Calendar.HOUR_OF_DAY, hourEnd);
                        calendar.set(Calendar.MINUTE, minuteEnd);
                        endDate = calendar.getTime();

                        event = new Meeting(startDate, endDate, description);
                        Intent i = new Intent(AddActivity.this, MainActivity.class);
                        i.putExtra(EVENT_TO_SEND, event);
                        setResult(RESULT_OK, i);
                        finish();
                    } else if (taskButton.isChecked()) {
                        Date startDate, endDate;
                        String title;
                        String meetingAssociated = EMPTY;

                        if (firstField.getText().toString().isEmpty())
                            throw new ParseException("Empty Title", 183);
                        if (!fourthField.getText().toString().isEmpty())
                            meetingAssociated = fourthField.getText().toString();

                        startDate = dateFormat.parse(secondField.getText().toString());
                        endDate = dateFormat.parse(thirdField.getText().toString());
                        title = firstField.getText().toString();

                        if ((Utils.getYear(startDate) > Utils.getYear(endDate)) ||
                                (Utils.getYear(startDate) == Utils.getYear(endDate) && Utils.getMonth(startDate) > Utils.getMonth(endDate)) ||
                                (Utils.getYear(startDate) == Utils.getYear(endDate) && Utils.getMonth(startDate) == Utils.getMonth(endDate) && Utils.getDay(startDate) > Utils.getDay(endDate)))
                            throw new ParseException("startDate bigger than endDate", 188);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(startDate);
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        startDate = calendar.getTime();

                        calendar.setTime(endDate);
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        endDate = calendar.getTime();

                        event = new Task(title, startDate, endDate);
                        Intent i = new Intent(AddActivity.this, MainActivity.class);
                        i.putExtra(EVENT_TO_SEND, event);
                        i.putExtra(ASSOCIATED_MEETING, meetingAssociated);
                        setResult(RESULT_OK, i);
                        finish();
                    }
                } catch (ParseException pe) {
                    Toast.makeText(AddActivity.this, TOAST_ERROR, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}