package club.elitesocceracademy.elitesocceracademy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    protected static ArrayList<String> onNames = new ArrayList<>();
    private Context context;
    private boolean onSwitch = false;
    private int time = 0;
    private int timeOfStart = 0;
    private int timeOfFinish = 0;
    private int x = 1500;

    //private HashMap<String, Integer> playerTimeMap = new HashMap<>();
    protected HashMap<String, Boolean> timeSwitchMap = new HashMap<>();
    private HashMap<String, Integer> buttonTimeMap = new HashMap<>();
    private static HashMap<String, LinkedList<Integer>> onTimeMap = new HashMap();
    private static HashMap<String, LinkedList<Integer>> offTimeMap = new HashMap();
    protected static HashMap<String, Boolean> autoSwitch = new HashMap();
    private static HashMap<String, HashMap<String,Integer>> totalPlayerTime = new HashMap<>();
    private static LinkedList<Boolean> trueFalseList = new LinkedList<>();
    private ToggleButton deleteBtn;
    PlayerTimers playerTimers;
    //Formation formation;



    public MyCustomAdapter(ArrayList<String> list, Context context) {

        this.list = list;
        this.context = context;

        for (String players : list) {
            LinkedList<Integer> offList = new LinkedList<>();
            LinkedList<Integer> onList = new LinkedList<>();
            LinkedList<Integer> timeList = new LinkedList<>();
            HashMap<String,Integer> gameIDTimeMap = new HashMap<>();

            //playerTimeMap.put(players, time);
            timeSwitchMap.put(players, onSwitch);
            buttonTimeMap.put(players, time);
            onTimeMap.put(players, onList);
            offTimeMap.put(players, offList);
            totalPlayerTime.put(players, gameIDTimeMap);
            onTimeMap.get(players).add(0,playerTimers.startingTime);

        }



        toggleButtons();
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
            view = inflater.inflate(R.layout.game_toggle_button, null);
        }


        TextView listItemText = (TextView) view.findViewById(R.id.list_item);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        deleteBtn = (ToggleButton) view.findViewById(R.id.toggle_btn);
        deleteBtn.setChecked(trueFalseList.get(position));
        if(trueFalseList.get(position)){
            onTimeMap.get(list.get(position)).set(0,playerTimers.startingTime);
        }
        final int time = 0;


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (trueFalseList.get(position)) {
                    timeOfFinish = playerTimers.timeLeftInSeconds;
                    timeSwitchMap.put(list.get(position), false);
                    trueFalseList.set(position,false);
                    offTimeMap.get(list.get(position)).add(timeOfFinish);
                    System.out.println("pos: " + list.get(position) + " status: " + timeSwitchMap.get(list.get(position)));
                } else if (!trueFalseList.get(position) ) {
                    timeOfStart = playerTimers.timeLeftInSeconds;
                    buttonTimeMap.put(list.get(position), timeOfStart);
                    timeSwitchMap.put(list.get(position), true);
                    trueFalseList.set(position,true);
                    onTimeMap.get(list.get(position)).add(timeOfStart);
                    System.out.println("pos: " + list.get(position) + " status: " + timeSwitchMap.get(list.get(position)));
                }
                //buttonStatus(position);
                //deleteBtn.setChecked(trueFalseList.get(position));
                System.out.println("position: "+position);

                //notifyDataSetChanged();
            }
        });


        return view;
    }

    //  public void buttonStatus(int position) {
//        if (trueFalseList.get(position)) {
//            timeOfFinish = playerTimers.timeLeftInSeconds;
//            timeSwitchMap.put(list.get(position), false);
//            trueFalseList.add(position,false);
//            offTimeMap.get(list.get(position)).add(timeOfFinish);
//            System.out.println("pos: " + list.get(position) + " status: " + timeSwitchMap.get(list.get(position)));
//        } else if (!trueFalseList.get(position)) {
//            timeOfStart = playerTimers.timeLeftInSeconds;
//            buttonTimeMap.put(list.get(position), timeOfStart);
//            timeSwitchMap.put(list.get(position), true);
//            trueFalseList.add(position,true);
//            onTimeMap.get(list.get(position)).add(timeOfStart);
//            System.out.println("pos: " + list.get(position) + " status: " + timeSwitchMap.get(list.get(position)));
//        }
    //   }

    public void getTimers(String selectedItem, int pos, int secLeft) {
        calPlayerTime(selectedItem, secLeft);
        //System.out.println(selectedItem + " " + timeSwitchMap.get(selectedItem));
        //System.out.println(selectedItem + " " + playerTimeMap.get(selectedItem));
        System.out.println(selectedItem + " on times:" + onTimeMap.get(selectedItem));
        System.out.println(selectedItem + " off times:" + offTimeMap.get(selectedItem));
    }

    public void calPlayerTime(String selectedItem, int timeLeft) {
        //if (timeSwitchMap.get(selectedItem))
        //playerTimeMap.put(selectedItem, (buttonTimeMap.get(selectedItem) - timeLeft));
    }

    public void getTotalTime() {
        int total = 0;
//        for(int onTimes : onTimeMap.get(selectedItem)){
//            for(int offTimes: offTimeMap.get(selectedItem)){
//                System.out.println(onTimes + "-"+ offTimes + "=" + (onTimes-offTimes));
//                addTimes.get(selectedItem).add(onTimes-offTimes);
//        }


//        for (String players : list) {
//            for (int i = 0; i < onTimeMap.get(players).size(); i++) {
//                if (offTimeMap.get(players).isEmpty()) {
//                    offTimeMap.get(players).add(onTimeMap.get(players).getLast());
//                }
//                total = total + (onTimeMap.get(players).get(i) - offTimeMap.get(players).get(i));
//                totalPlayerTime.get(players).put("3",total);
//                System.out.println(totalPlayerTime.get(players).get("3")+ " @@@@@@@@@@@");
//            }
//
//            System.out.println(totalPlayerTime.get(players));
//
//        }
        GetTime getTime = new GetTime(totalPlayerTime,onTimeMap,offTimeMap,list);
    }

    public void toggleButtons() {
        //System.err.println("FML");


        for (String names : onNames) {
            System.out.println("%%%%"+ names + " : " + autoSwitch.get(names));
            trueFalseList.add(autoSwitch.get(names));
            timeSwitchMap.put(names,autoSwitch.get(names));
            //System.out.println("%%%%"+ names + " : " + autoSwitch.get(names));
        }

        for(Boolean lol : trueFalseList){
            System.out.println(lol);
        }
    }








}