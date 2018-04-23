package club.elitesocceracademy.elitesocceracademy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GetTime {
    public static HashMap<String, HashMap<String,Integer>> totalPlayerTime;
    private  HashMap<String, LinkedList<Integer>> onTimeMap = new HashMap();
    private  HashMap<String, LinkedList<Integer>> offTimeMap = new HashMap();
    private  ArrayList<String> list = new ArrayList<>();
    private static int total;
    public static HashMap<String, HashMap<String, HashMap<String,Integer>>> testHashMap = new HashMap<>();

    GetTime(HashMap<String, HashMap<String,Integer>> totalPlayerTime,HashMap<String, LinkedList<Integer>> onTimeMap,
            HashMap<String, LinkedList<Integer>> offTimeMap,ArrayList<String> list){
        this.totalPlayerTime = totalPlayerTime;
        this.offTimeMap = offTimeMap;
        this.onTimeMap = onTimeMap;
        this.list = list;
        calcTime();
    }


    public void calcTime(){


        for (String players : list) {
            total = 0;
            for (int i = 0; i < onTimeMap.get(players).size(); i++) {
                if (offTimeMap.get(players).isEmpty()) {
                    offTimeMap.get(players).add(onTimeMap.get(players).getLast());
                }
                System.out.println("onTimeMap.get(" + players+"): " + onTimeMap.get(players) +" " + "offTimeMap.get(" + players+"): " + offTimeMap.get(players));
                total = total + (onTimeMap.get(players).get(i) - offTimeMap.get(players).get(i));
                totalPlayerTime.get(players).put("3",total);

                //System.out.println(totalPlayerTime.get(players).get("3"));
            }

            System.out.println(totalPlayerTime.get(players));
            testHashMap.put(players,totalPlayerTime);
        }
    }
}
