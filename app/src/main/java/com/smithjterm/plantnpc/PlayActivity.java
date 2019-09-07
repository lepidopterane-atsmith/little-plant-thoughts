package com.smithjterm.plantnpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    TextView title, body;
    int chatIndex = 0;
    String[] chapterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        title = (TextView) findViewById(R.id.playTitle);
        body = (TextView) findViewById(R.id.playBody);

        title.setText("Your Plant");

        Intent i = getIntent();

        Bundle extras = i.getExtras();

        chapterText = extras.getStringArray(MainActivity.CHAPTER_TEXT_KEY);

        body.setText(chapterText[0]);

    }

    public void makeChoice(View view){
        chatIndex++;

        if (chatIndex > chapterText.length-1) {
            Intent i = getIntent();
            setResult(MainActivity.RESULT_OK, i);
            finish();

        } else {
            body.setText(chapterText[chatIndex]);
        }
    }

}
