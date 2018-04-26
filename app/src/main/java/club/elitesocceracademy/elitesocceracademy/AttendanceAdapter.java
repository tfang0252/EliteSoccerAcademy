package club.elitesocceracademy.elitesocceracademy;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class AttendanceAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();

    //private HashMap<String, Integer> playerTimeMap = new HashMap<>();

    private ToggleButton attBtn;
    private RadioButton radioButton5;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Context context;
    private RadioGroup group;


    public AttendanceAdapter(ArrayList<String> list, Context context) {

        this.list = list;
        this.context = context;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.attendance_inflate, null);
        }


        TextView listItemText = (TextView) view.findViewById(R.id.list_item);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        attBtn = (ToggleButton) view.findViewById(R.id.toggleButton);
//        radioButton1=(RadioButton) view.findViewById(R.id.toggle_btn1);
//        radioButton2=(RadioButton) view.findViewById(R.id.toggle_btn2);
//        radioButton3=(RadioButton) view.findViewById(R.id.toggle_btn3);
//        radioButton4=(RadioButton) view.findViewById(R.id.toggle_btn4);
//        radioButton5=(RadioButton) view.findViewById(R.id.toggle_btn5);
        RadioButton button1;
        RadioButton button2;
        RadioButton button3;
        RadioButton button4;
        RadioButton button5;


        RadioGroup group = (RadioGroup) view.findViewById(R.id.radioGroup1);

        button1 = new RadioButton(context);
        button1.setId(R.id.radio1);//button.setText("Button " + i);
        button1.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
        group.addView(button1);
        button2 = new RadioButton(context);
        button2.setId(R.id.radio2);//button.setText("Button " + i);
        button2.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
        group.addView(button2);
        button3 = new RadioButton(context);
        button3.setId(R.id.radio3);//button.setText("Button " + i);
        button3.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
        group.addView(button3);
        button4 = new RadioButton(context);
        button4.setId(R.id.radio4);//button.setText("Button " + i);
        button4.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
        group.addView(button4);
        button5 = new RadioButton(context);
        button5.setId(R.id.radio5);//button.setText("Button " + i);
        button5.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
        group.addView(button5);

        final int time = 0;


        attBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("position: "+position);


            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {

                    case R.id.radio1:
                        // TODO Something
                        break;
                    case R.id.radio2:
                        // TODO Something
                        break;
                    case R.id.radio3:
                        // TODO Something
                        break;
                    case R.id.radio4:
                        // TODO Something
                        break;
                    case R.id.radio5:
                        // TODO Something
                        break;
                }
            }
        });





        return view;
    }




}