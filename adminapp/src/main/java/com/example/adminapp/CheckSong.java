package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckSong extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> URL = new ArrayList<>();
    SongData songData;
    ListView listView;

    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_song);

        listView = findViewById(R.id.songList);
        btnReturn = findViewById(R.id.button);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RequestSong");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                songData = snapshot.getValue(SongData.class);
                if(songData != null){
                    name = songData.getSongName();
                    URL = songData.getSongURL();
                }
                makeAdapter(name, URL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(CheckSong.this)
                        .setTitle("노래를 삭제하시겠습니까?")
                        .setMessage("선택된 노래 : " + name.get(position))
                        .setCancelable(false)
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name.remove(position);
                                URL.remove(position);
                                myRef.setValue(new SongData(name, URL));
                            }
                        })
                        .setNegativeButton("아니오",null).show();
            }
        });

    }
    private void makeAdapter(ArrayList<String> name, ArrayList<String> url) {

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for(int i = 0; i < name.size(); i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("title", name.get(i));
            hashMap.put("contents",url.get(i));

            arrayList.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2, new String[]{"title","contents"},new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(adapter);

    }
}