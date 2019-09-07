package com.smithjterm.plantnpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class PlayActivity extends AppCompatActivity {

    TextView title, body;
    int chatIndex = 0;
    List<String> storyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        title = (TextView) findViewById(R.id.playTitle);
        body = (TextView) findViewById(R.id.playBody);

        title.setText("Your Plant");

        Intent i = getIntent();

        Bundle extras = i.getExtras();

        storyList = extras.getStringArrayList(MainActivity.CHAPTER_TEXT_KEY);

        body.setText(storyList.get(0));

    }

    public void makeChoice(View view){
        chatIndex++;

        if (chatIndex > storyList.size()-1) {
            Intent i = getIntent();
            setResult(MainActivity.RESULT_OK, i);
            finish();

        } else {
            body.setText(storyList.get(chatIndex));
        }
    }

}
