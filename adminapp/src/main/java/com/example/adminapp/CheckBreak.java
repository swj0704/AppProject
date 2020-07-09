package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckBreak extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;
    BreakData data;
    ArrayList<String> breakTitle = new ArrayList<>();
    ArrayList<String> breakContents = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_break);

        listView = findViewById(R.id.breakList);

        database= FirebaseDatabase.getInstance();
        myRef = database.getReference("NoticeBreak");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data = snapshot.getValue(BreakData.class);
                if(data != null){
                    breakTitle = data.getBreakTitle();
                    breakContents = data.getBreakContents();
                }
                makeAdapter(breakTitle, breakContents);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void makeAdapter(ArrayList<String> breakTitle, ArrayList<String> breakContents) {

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for(int i = 0; i < breakTitle.size(); i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("breakTitle", breakTitle.get(i));
            hashMap.put("breakContents", breakContents.get(i));

            arrayList.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2, new String[]{"breakTitle","breakContents"},new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }
}