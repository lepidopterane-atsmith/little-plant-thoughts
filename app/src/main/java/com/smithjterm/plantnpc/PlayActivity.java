package com.smithjterm.plantnpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlayActivity extends AppCompatActivity {

    TextView title, body;
    ImageView sprite;
    int chatIndex = 0;
    List<String> storyList, spriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        title = (TextView) findViewById(R.id.playTitle);
        body = (TextView) findViewById(R.id.playBody);
        sprite = (ImageView) findViewById(R.id.imageView8);

        title.setText("Your Plant");

        Intent i = getIntent();

        Bundle extras = i.getExtras();

        storyList = extras.getStringArrayList(MainActivity.CHAPTER_TEXT_KEY);
        body.setText(storyList.get(0));

        spriteList = extras.getStringArrayList(MainActivity.CHAPTER_SPRITES_KEY);
        setSprite(spriteList.get(0));

    }

    public void makeChoice(View view){
        chatIndex++;

        if (chatIndex > storyList.size()-1) {
            Intent i = getIntent();
            setResult(MainActivity.RESULT_OK, i);
            finish();

        } else {
            body.setText(storyList.get(chatIndex));
            setSprite(spriteList.get(chatIndex));
        }
    }

    public void setSprite(String setting){
        if(setting.indexOf("blink") > -1){
            sprite.setImageResource(R.drawable.plant_blink);
        } else if (setting.indexOf("blonk") > -1) {
            sprite.setImageResource(R.drawable.plant_blonk);
        } else if (setting.indexOf("loveydovey") > -1) {
            sprite.setImageResource(R.drawable.plant_loveydovey);
        } else if(setting.indexOf("puppydog") > -1) {
            sprite.setImageResource(R.drawable.plant_puppydog);
        } else { sprite.setImageResource(R.drawable.plant_normal);}
    }

}
