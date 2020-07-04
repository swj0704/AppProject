package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestSong extends AppCompatActivity {

    FirebaseFirestore firestore;
    Button btnRequest, btnReturn;
    TextView songName, songURL;
    ArrayList<SongData> arrayList;
    SongData songData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_song);
        btnRequest = findViewById(R.id.btnRequestSong);
        btnReturn = findViewById(R.id.btnReturn);
        songName = findViewById(R.id.songName);
        songURL = findViewById(R.id.songUrl);

        firestore = FirebaseFirestore.getInstance();

        

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(songName.getText().toString().isEmpty() || songURL.getText().toString().isEmpty()){
                    Toast.makeText(RequestSong.this, "노래 이름이나 링크를 제대로 적어주세요!",Toast.LENGTH_LONG).show();
                }else {
                    arrayList.add(songData);
                    firestore.collection("RequestSong").document("Request").set(arrayList);
                }
            }
        });
    }
}