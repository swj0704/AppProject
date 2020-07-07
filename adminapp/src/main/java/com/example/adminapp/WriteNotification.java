package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WriteNotification extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;

    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> contents = new ArrayList<>();

    ItemNoti notification;

    Button btnWrite, btnReturn;
    ListView listView;

    EditText edTitle, edContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_notification);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notification");

        btnReturn = findViewById(R.id.btnReturn);
        btnWrite = findViewById(R.id.btnWrite);
        listView = findViewById(R.id.notificationList);
        edTitle = findViewById(R.id.notificationTitle);
        edContent = findViewById(R.id.editNotification);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notification = snapshot.getValue(ItemNoti.class);
                if(notification != null){
                    title = notification.getTitleStr();
                    contents = notification.getContentStr();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edTitle.getText().toString().isEmpty() || !edContent.getText().toString().isEmpty()) {
                    if (title.size() <= 3 || contents.size() <= 3) {
                        title.add(edTitle.getText().toString());
                        contents.add(edContent.getText().toString());
                    } else {
                        title.remove(0);
                        contents.remove(0);
                        title.add(edTitle.getText().toString());
                        contents.add(edContent.getText().toString());
                    }
                }
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }
}