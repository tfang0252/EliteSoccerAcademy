package com.example.danny.esa_hw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Danny on 3/12/2018.
 */

public class addDrill extends AppCompatActivity {

    protected void OnCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        System.out.println("1");
        setContentView(R.layout.adddrill);

        Button EnterButton = (Button) findViewById(R.id.EnterButton);
        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(addDrill.this,MainActivity.class));
            }
        });

    }


}
