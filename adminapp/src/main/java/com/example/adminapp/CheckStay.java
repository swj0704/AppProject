package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CheckStay extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;
    ArrayList<String> studentName = new ArrayList<>();
    ArrayList<String> studentID = new ArrayList<>();

    ListView listView;

    Stay stay;

    Button btnReturn;

    Calendar calendar = Calendar.getInstance();
    String week = calendar.get(Calendar.YEAR) + ""  +calendar.get(Calendar.WEEK_OF_YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_stay);

        listView = findViewById(R.id.StayList);
        btnReturn = findViewById(R.id.btnMain);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RequestStay").child(week);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stay = snapshot.getValue(Stay.class);
                if(stay != null) {
                    studentName = stay.getStayList();
                    studentID = stay.getStayID();
                }
                makeAdapter(studentName, studentID);
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