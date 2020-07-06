package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class SetPointActivity extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;
    String[] studentID = {
            "s19066", "s19004", "s19007", "s19058", "s19062",
            "s19010", "s19063", "s19032", "s19011", "s19072",
            "s19013", "s19034", "s19036", "s19067", "s19017",
            "s19018", "s19019", "s19020", "s19038", "s19040"
    };
    ArrayList<String> nameList;
    ArrayList<String> IDList;

    String name;
    String ID;

    ListView listView;

    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_point);

        nameList = new ArrayList<>();
        IDList = new ArrayList<>();

        listView = findViewById(R.id.listView);
        btnReturn = findViewById(R.id.btnReturn);

        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("student");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(int i = 0; i < studentID.length; i++){
                    ID = snapshot.child(studentID[i]).child("studentData").child("ID").getValue(String.class);
                    name = snapshot.child(studentID[i]).child("studentData").child("name").getValue(String.class);

                    if(ID != null || name != null) {
                        IDList.add(ID);
                        nameList.add(name);
                    }

                    makeText(nameList, IDList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CheckPoint.class);
                intent.putExtra("name",nameList.get(position));
                intent.putExtra("ID", IDList.get(position));
                intent.putExtra("studentID", studentID[position]);
                startActivity(intent);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void makeText(ArrayList<String> nameList, ArrayList<String> idList) {

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for(int i = 0; i < nameList.size(); i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("name", nameList.get(i));
            hashMap.put("ID", idList.get(i));

            arrayList.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2, new String[]{"name","ID"},new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }

}