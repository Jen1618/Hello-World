package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SecondActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private LinearLayout linearLayout;
    private Button button_goBack;
    private String receivedMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //display a message
        //x was passed from main activity,


        //extract intent extras information
        Intent intent = getIntent();
        receivedMessage = intent.getStringExtra("count");
        Log.d("Data from Main Activity", receivedMessage);

        //grab the recycler view in second activity
        constraintLayout = findViewById(R.id.second_root_layout);
        linearLayout = findViewById(R.id.second_linear_layout);
        button_goBack = findViewById(R.id.button_goBack);

        //create a textView
        //set the text to receiveMessage + "was passed from main activity"
        //add this textView to recyclerView
        /**
        TextView textView = new TextView(this);
        textView.setText(receivedMessage + " was passed from main activity");
        textView.setTextSize(30);
        constraintLayout.addView(textView);
         */
        //if you do not know how many views you will need to create
        for(int i=0; i< Integer.parseInt(receivedMessage); i++){
            TextView textView = new TextView(this);
            textView.setText("hello");
            linearLayout.addView(textView);
        }

        //add on clicklistner for button
        button_goBack.setOnClickListener(v -> {//lamba version
            replayIntent(v);
        });
    }

    public void replayIntent(View view){
        // create a reply intent and pack the information, send it back to main activity
        Intent replyIntent = new Intent();
        replyIntent.putExtra("replyCount",receivedMessage);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
