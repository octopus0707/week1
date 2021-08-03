package com.example.test0630;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class inventer extends AppCompatActivity {

    private static final String  TAG = "inventActivity";
    String key; //位置
    TextView invent;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventer);
        Log.d(TAG, "onCreate: started.");

        invent = (TextView)findViewById(R.id.intent);

        getIncomingIntent();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("competition/"+key).child("organizer");
        reference.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                invent.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIncomingIntent(){
        Log.d(TAG,"getIncomingIntent:");
        if(getIntent().hasExtra("url")){
            Log.d(TAG,"Found");

            String url = getIntent().getStringExtra("url");
            setUrl(url);

        }
    }

    private void setUrl(String url){
        Log.d(TAG, "setUrl to widgets.");
        key = url;
    }
}
