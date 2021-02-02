package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Define variables outside of method
    private Button button_hi;
    private int number = 0;
    private TextView textView_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //part of the android lifecycle


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Links the activity to the set activity called activity_main

        // Look up the button by it's Id

        button_hi = findViewById(R.id.button_hello);
        textView_count = findViewById(R.id.textView_count);

        // add an event listener to listen for the click

        button_hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sayHello(v);
                launchNextActivity(v);
            }
        });
        // when click happens, I do something
    }



    public void sayHello (View view){
        // create a toast with a message saying hello
        Toast toast = Toast.makeText(MainActivity.this , R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view){
        //increases the value of the number
        // set the text view to the number
        number++;
        // when you see an object, you want to make sure to check it's not null
        if (textView_count != null){
            textView_count.setText(Integer.toString(number));
        }
    }

    public void countDown(View view){
        if(number > 0){
            number --;
        }
        if(textView_count != null){
            textView_count.setText(Integer.toString(number));
        }
    }

    public void launchNextActivity(View view){
        // Create an intent and you need to specify from and to
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }
}