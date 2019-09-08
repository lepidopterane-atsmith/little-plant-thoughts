package com.smithjterm.plantnpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // let's tell some sci-fi stories! :D - sarah

    // TODO: @Helen, I made help do the loading thing. we just need a load button that when clicked
    // does the startLoadActivity.

    static final int RESTART_REQUEST = 2;

    public static final String CHAPTER_TEXT_KEY="chapter text";
    public ArrayList<String> storyList = new ArrayList<>();

    public static final String CHAPTER_SPRITES_KEY="chapter sprites";
    public ArrayList<String> spritesList = new ArrayList<>();

    // firebase instance variable
    private DatabaseReference firebaseDatabaseRef;
    private DatabaseReference soilValidator;

    private ViewGroup rootView;

    private int k = 0;
    private int children = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button prologueButton = (Button) findViewById(R.id.prologue);
        prologueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentRequestedChapter("prologue");
            }
        });

        monitorSoil();

        // The test below makes sure isFull works.

        //  String result = "test result: "+mainTree.isFull();
        //  Log.i("MainActivity",result);

        // 2131165250 1 2 3 4 5 6

        /*
            Intent i = new Intent(this, SecondActivity.class);

            i.putExtra(POST_KEY,postView.getText().toString());

            startActivity(i);

        } */


    }


    public void monitorSoil(){
        soilValidator = FirebaseDatabase.getInstance().getReference().child("soilTimeline");
        soilValidator.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                children += ((int) dataSnapshot.getChildrenCount() / 2) ;
                int l = 2;
                k = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (l % 2 == 0) {
                        k += Integer.parseInt(""+ds.getValue());
                        Log.i("MainActivity", "now added:"+ds.getValue());
                    }
                    l++;
                }
                k = ( k / ((int) dataSnapshot.getChildrenCount() / 2));
                Log.i("MainActivity", "k =  "+k);

               if (k > 2000){
                   Log.i("MainActivity", "child count: "+children);
                   if (children > 503){
                       Log.i("MainActivity", "CHAPTER 1 UNLOCKED");
                       Button chapterOneButton = (Button) findViewById(R.id.ch1_button);
                       chapterOneButton.setVisibility(View.VISIBLE);
                       chapterOneButton.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               setCurrentRequestedChapter("chapter1");
                           }
                       });
                   }
                   if (children > 506){
                       Log.i("MainActivity", "CHAPTER 2 UNLOCKED");
                       Button chapterTwoButton = (Button) findViewById(R.id.ch2_button);
                       chapterTwoButton.setVisibility(View.VISIBLE);
                       chapterTwoButton.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               setCurrentRequestedChapter("chapter2");
                           }
                       });
                   }
                   if (children > 509){
                       Log.i("MainActivity", "CHAPTER 3 UNLOCKED");
                       Button chapterThreeButton = (Button) findViewById(R.id.ch3_button);
                       chapterThreeButton.setVisibility(View.VISIBLE);
                       chapterThreeButton.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               setCurrentRequestedChapter("chapter3");
                           }
                       });
                   }
               }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i("MainActivity", "edit in soilTimeline");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("MainActivity", "removal in soilTimeline");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i("MainActivity", "move made in soilTimeline");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("MainActivity", "soilTimeline monitoring cancelled");
            }
        });
    }

    public void setCurrentRequestedChapter(String chapter){
        storyList = new ArrayList<>();
        spritesList = new ArrayList<>();
        firebaseDatabaseRef = FirebaseDatabase.getInstance().getReference().child("chapters").child(chapter);
        firebaseDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                {

                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        Object storyLine = ds.getValue();
                        String[] entries = storyLine.toString().split("_");
                        Log.d("story is",storyLine.toString());
                        storyList.add(entries[0]);
                        spritesList.add(entries[1]);
                    }
                    playActivity();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error oops", String.valueOf(databaseError.getCode()));
            }
        });
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

    public void playActivity(){

        //Log.i("MainActivity",mainTree.toString(""));

        Intent intent = new Intent(this, PlayActivity.class);
        intent.putStringArrayListExtra(CHAPTER_TEXT_KEY,storyList);
        intent.putStringArrayListExtra(CHAPTER_SPRITES_KEY,spritesList);
        startActivityForResult(intent, RESTART_REQUEST);

    }

    public void helpActivity(View view){
        Intent intent = new Intent(this,help.class);
        startActivity(intent);
    }

}
