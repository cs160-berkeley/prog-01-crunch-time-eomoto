package com.example.ellenomoto.project01;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.ellenomoto.project01.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView calories = (TextView) findViewById(R.id.calories_amount);
        final TextView pushup_amount = (TextView) findViewById(R.id.pushup_amount);
        final TextView situp_amount = (TextView) findViewById(R.id.situp_amount);
        final TextView jumpingjacks_amount = (TextView) findViewById(R.id.jumpingjacks_amount);
        final TextView jogging_amount = (TextView) findViewById(R.id.jogging_amount);
        final Button button = (Button) findViewById(R.id.calculate);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                EditText editText = (EditText) findViewById(R.id.modify_amount);
                String input = editText.getText().toString();
                int radioGroup_index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
                List<String> messageList = calculations(input, radioGroup_index);
                calories.setText(messageList.get(0));
                pushup_amount.setText(messageList.get(1));
                situp_amount.setText(messageList.get(2));
                jumpingjacks_amount.setText(messageList.get(3));
                jogging_amount.setText(messageList.get(4));
            }
        });
    }

    public List<String> calculations(String input_str, int radioGroup_index){
        List<String> messageList = new ArrayList<String>();
        int pushup_amount = 0;
        int situp_amount = 0;
        int jumpingjacks_amount = 0;
        int jogging_amount = 0;
        int calories = 0;
        //350r, 200r, 10m, 12m == 100c
        try {
            int input = Integer.valueOf(input_str);
            switch (radioGroup_index) {
                case 0://pushup
                    calories = input * 100 / 350;
                    pushup_amount = input;
                    situp_amount = calories * 200 / 100;
                    jumpingjacks_amount = calories * 10 / 100;
                    jogging_amount = calories * 12 / 100;
                    break;
                case 1://situp
                    calories = input * 100 / 200;
                    pushup_amount = calories * 350 / 100;
                    situp_amount = input;
                    jumpingjacks_amount = calories * 10 / 100;
                    jogging_amount = calories * 12 / 100;
                    break;
                case 2://jumping jacks
                    calories = input * 100 / 10;
                    pushup_amount = calories * 350 / 100;
                    situp_amount = calories * 200 / 100;
                    jumpingjacks_amount = input;
                    jogging_amount = calories * 12 / 100;
                    break;
                case 3://jogging
                    calories = input * 100 / 12;
                    pushup_amount = calories * 350 / 100;
                    situp_amount = calories * 200 / 100;
                    jumpingjacks_amount = calories * 10 / 100;
                    jogging_amount = input;
                    break;
                default://none
                    messageList.add("");
                    messageList.add("");
                    messageList.add("");
                    messageList.add("");
                    messageList.add("");
                    return messageList;
            }
        }
        catch (Exception e){
            messageList.add("");
            messageList.add("");
            messageList.add("");
            messageList.add("");
            messageList.add("");
            return messageList;
        }

        messageList.add(String.valueOf(calories));
        messageList.add(String.valueOf(pushup_amount) + " reps");
        messageList.add(String.valueOf(situp_amount)+ " reps");
        messageList.add(String.valueOf(jumpingjacks_amount)+ " minutes");
        messageList.add(String.valueOf(jogging_amount)+ " minutes");
        return messageList;
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* Called when the user clicks the Send button */
//    public void sendMessage(View view) {
//        // Do something in response to button
////        Intent intent = new Intent(this, DisplayMessageActivity.class);
////        EditText editText = (EditText) findViewById(R.id.modify_amount);
////        String message = editText.getText().toString();
////        intent.putExtra(EXTRA_MESSAGE, message);
////        startActivity(intent);
//        R.id.modify_amount.setText("New text");
//    }
}
