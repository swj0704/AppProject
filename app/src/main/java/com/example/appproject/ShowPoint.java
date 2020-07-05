package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowPoint extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String id = user.getEmail().substring(0,6);
    ArrayList<String> reason = new ArrayList<>();
    ArrayList<Integer> point = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_point);
        listView = findViewById(R.id.pointList);
        database= FirebaseDatabase.getInstance();
        myRef = database.getReference("student").child(id).child("studentData").child("pointList");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PointData data = snapshot.getValue(PointData.class);
                if(data != null){
                    reason = data.getReason();
                    point = data.getPoint();
                }
                makeAdapter(reason, point);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void makeAdapter(ArrayList<String> reason, ArrayList<Integer> point) {

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for(int i = 0; i < reason.size(); i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("reason", reason.get(i));
            hashMap.put("point", point.get(i).toString());

            arrayList.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, R.layout.row_point, new String[]{"reason","point"},new int[]{R.id.reason, R.id.point});
        listView.setAdapter(adapter);

    }
}