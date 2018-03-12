package club.elitesocceracademy.elitesocceracademy;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import android.util.*;
import android.*;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.AlertDialog;
import android.text.InputType;
import android.content.DialogInterface;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
public class AnnouncementActivity extends AppCompatActivity {



    private static final String TAG = "AnnouncementActivity";
    protected static ArrayList<String> list = new ArrayList<>(Arrays.asList(" ", "Practice Cancelled","Game Delayed","Wear Yellow Jersey","Game Field Change"));
    protected static ArrayList<String> desc = new ArrayList<>(Arrays.asList(" ","Practice is cancelled for 3/12, family emergency","Tonight's game will start" +
                    "an hour later than scheduled, from 6:45pm to 7:45pm", "Please wear yellow jerseys for Saturday's match",
                    "We are using field 5 next to the parking lot for tomorrow's game"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        final SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.listView);





        ArrayAdapter adapter = new ArrayAdapter(AnnouncementActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                descPopUp(position);
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Edit");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_trash);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        break;
                    case 1:
                        removeAnnouncement(listView,position);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        Context context = v.getContext();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Announcement");

// Set up the input
        final EditText input = new EditText(this);
        final EditText input2 = new EditText(this);
        input.setHint("Announcement Title");
        input2.setHint("Description");
        input2.setSingleLine(false);  //add this
        input2.setLines(4);
        input2.setMaxLines(7);
        input2.setGravity(Gravity.LEFT | Gravity.TOP);
        input2.setHorizontalScrollBarEnabled(false); //this
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(input);
        layout.addView(input2);
        builder.setView(layout);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String announcementTitle = input.getText().toString();
                String announcementDesc = input2.getText().toString();
                list.add(announcementTitle);
                desc.add(announcementDesc);
                System.out.println(announcementTitle);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();



    }

    public void descPopUp(int position){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(desc.get(position));
        builder1.setTitle(list.get(position));
        builder1.setCancelable(true);



        builder1.setNegativeButton(
                "Return",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    public void removeAnnouncement(SwipeMenuListView listView, int position){
        list.remove(position);
        desc.remove(position);
        listView.invalidateViews();

    }
}



