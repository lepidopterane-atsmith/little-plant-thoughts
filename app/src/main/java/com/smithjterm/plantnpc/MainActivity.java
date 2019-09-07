package com.smithjterm.plantnpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // let's tell some sci-fi stories! :D - sarah

    // TODO: @Helen, I made help do the loading thing. we just need a load button that when clicked
    // does the startLoadActivity.

    static final int RESTART_REQUEST = 2;

    public static final String CHAPTER_TEXT_KEY="chapter text";

    // we won't need this after database integration
    public String[] prologueText = {"Eeeyah! Hwah!","EEP!",
        "Oh, you scared me, Caretaker. It’s just you, thank goodness.",
        "Wait, what do you mean you didn’t know you could hear our thoughts? I wouldn’t exactly call it that…",
        "If a plant cares about you a great deal, and it's clear to such a plant you care about it a great deal, we can communicate telepathically! It's a deal! A great one, at that!",
        "You have been watering me well, and I do enjoy that you've taken care to give me my proper amount of lighting.",
        "There's more behind this connection than just you caring about me, I feel it in my tiny plant soul. I see you have some solid outfit choices, though I'm not one to talk, being a plant.",
        "I mean, in my experience, you seem to me like the kind of human I'd like to be friends with. You seem like you'd listen to me, especially since you're doing that right now. And you have a really kind demeanor when you're near me, anyway.",
        "But if you leave me with any less water than you're supposed to give me, even one day, I may shrivel a little - and I'll need to recover from that, you know.",
        "Recovery takes time, and once I'm back to my normal self, I'll feel good and know you care about me enough to start thinking my little plant thoughts at you again.",
        "I don't think you'll ever leave me without a drink, though. You wouldn't do something like that to me, would you, good friend?",
        "Yes, yes I just did puppy dog eyes at you. I mean, humans can and they're not puppy dogs last time I checked…",
        "Wait, you really mean it? You'll keep taking care of me? And you still want to be friends?",
        "This is going to be such fun! You and I, I think we'll come to love our little chats…"};

    // firebase instance variable
    private DatabaseReference firebaseDatabaseRef;

    private ViewGroup rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The test below makes sure isFull works.

        //  String result = "test result: "+mainTree.isFull();
        //  Log.i("MainActivity",result);

        // 2131165250 1 2 3 4 5 6

        /*
            Intent i = new Intent(this, SecondActivity.class);

            i.putExtra(POST_KEY,postView.getText().toString());

            startActivity(i);

        } */

        firebaseDatabaseRef = FirebaseDatabase.getInstance().getReference().child("prologue");

        Log.i( "MainActivity", firebaseDatabaseRef.getKey());

        rootView = (ViewGroup) findViewById(R.id.linearLayout3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == RESTART_REQUEST){
            if (resultCode == RESULT_OK){

            }
        }
    }

    // TODO: refactor this so it pulls dialogue stored on the server and ships it into playActivity
    public void playActivity(View view){

        //Log.i("MainActivity",mainTree.toString(""));

        Intent intent = new Intent(this, PlayActivity.class);

        intent.putExtra(CHAPTER_TEXT_KEY, prologueText);

        startActivityForResult(intent, RESTART_REQUEST);

    }

    public void helpActivity(View view){
        Log.i( "MainActivity", firebaseDatabaseRef.getKey());
        Intent intent = new Intent(this,help.class);
        startActivity(intent);
    }

}
