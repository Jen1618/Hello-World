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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SecondActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private LinearLayout linearLayout;
    private Button button_goBack;
    private String receivedMessage;

    private static final String api_url = "https://icanhazdadjoke.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();

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
            //replayIntent(v);
            launchNextActivity(v);
        });
    }
    /**
    public void replayIntent(View view){
        // create a reply intent and pack the information, send it back to main activity
        Intent replyIntent = new Intent();
        replyIntent.putExtra("replyCount",receivedMessage);
        setResult(RESULT_OK, replyIntent);
        finish();
    }*/

    public void launchNextActivity(View view){
        //when button is clicked
        //I want to send a get request to the API
        //add the data received form the response to the internet
        //send it to third activity to be displayed

        //set the header because of the api endpoint
        client.addHeader("Accept", "application/json");
        //send a get request to the api url
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //when you get a 200 status code
                Log.d("api response", new String(responseBody));

                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    //add the joke into te intent
                    intent.putExtra("joke", json.getString("joke"));

                    //convert any json data into a string to put into the intent
                    //when you receive the intent in the next activity,
                    //convert it back to the json data

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //when you get a 400-499 status code
                Log.e("api error", new String(responseBody));
            }
        });
    }
}
