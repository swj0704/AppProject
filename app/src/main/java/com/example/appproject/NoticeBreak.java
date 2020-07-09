package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeBreak extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;
    Button btnWriteBreak, btnReturn;
    EditText edTitle, edContents;
    BreakData data;
    ArrayList<String> breakTitle = new ArrayList<>();
    ArrayList<String> breakContents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_break);
        edTitle = findViewById(R.id.breakTitle);
        edContents = findViewById(R.id.breakContent);
        btnWriteBreak = findViewById(R.id.btnWriteBreak);
        btnReturn = findViewById(R.id.btnBack);


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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnWriteBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edTitle.getText().toString().isEmpty() && !edContents.getText().toString().isEmpty()){
                    breakTitle.add(edTitle.getText().toString());
                    breakContents.add(edContents.getText().toString());
                    myRef.setValue(new BreakData(breakTitle, breakContents));
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