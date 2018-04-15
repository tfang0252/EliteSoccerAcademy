package club.elitesocceracademy.elitesocceracademy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private boolean onSwitch = false;
    private int time = 0;
    private int timeOfStart = 0;
    private int x = 1500;
    private HashMap<String, Integer> playerTimeMap = new HashMap<>();
    private HashMap<String,Boolean> timeSwitchMap = new HashMap<>();
    private HashMap<String, Integer> buttonTimeMap = new HashMap<>();
    private HashMap<String, LinkedList<Integer>> toggleTimesMap = new HashMap();

    PlayerTimers playerTimers;

    public MyCustomAdapter(ArrayList<String> list, Context context) {

        this.list = list;
        this.context = context;
        for(String players : list){
            playerTimeMap.put(players,time);
            timeSwitchMap.put(players,onSwitch);
            buttonTimeMap.put(players,time);
        }

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

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.toggle_btn);

        final int time = 0;


        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonStatus(position);
                notifyDataSetChanged();
            }
        });


        return view;
    }

    public void buttonStatus(int position){
        if(timeSwitchMap.get(list.get(position))==true){
            timeSwitchMap.put(list.get(position),false);
            System.out.println("pos: " + list.get(position) + " status: "+ timeSwitchMap.get(list.get(position)));
        }else{
            timeOfStart = playerTimers.timeLeftInSeconds;
            buttonTimeMap.put(list.get(position), timeOfStart);
            timeSwitchMap.put(list.get(position),true);
            System.out.println("pos: " + list.get(position) +" status: "+ timeSwitchMap.get(list.get(position)));
        }
    }
    public void getTimers(String selectedItem, int pos, int secLeft){
        calPlayerTime(selectedItem,secLeft);
        //System.out.println(selectedItem + " " + timeSwitchMap.get(selectedItem));
        System.out.println(selectedItem + " " + playerTimeMap.get(selectedItem));
    }

    public void calPlayerTime(String selectedItem, int timeLeft){
        if(timeSwitchMap.get(selectedItem))
        playerTimeMap.put(selectedItem,(buttonTimeMap.get(selectedItem)- timeLeft));



//        for (HashMap.Entry<String, Boolean> entry : timeSwitchMap.entrySet()) {
//            String key = entry.getKey();
//            Boolean value = entry.getValue();
//            int newTime =  (buttonTimeMap.get(key)-timeLeft);
//            if(value){
//                playerTimeMap.put(key,newTime);
//            }
            // ...
 //       }


    }
}
