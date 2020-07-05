package com.example.appproject.ui.mypage;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.appproject.LoginActivity;
import com.example.appproject.R;
import com.example.appproject.ShowPoint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPageFragment extends Fragment {

    TextView userName, userInfo, plusText, minusText;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Button btnLogout, btnCheckPoint;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String id = user.getEmail().substring(0,6);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mypage, container, false);

        database= FirebaseDatabase.getInstance();
        myRef = database.getReference("student").child(id).child("studentData");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ID = snapshot.child("ID").getValue(String.class);
                String name = snapshot.child("name").getValue(String.class);
                Integer plus = snapshot.child("plus").getValue(Integer.class);
                Integer minus = snapshot.child("minus").getValue(Integer.class);
                if(ID != null || name != null || plus != null || minus != null) {
                    makeText(ID, name,plus,minus);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        userName = root.findViewById(R.id.userName);
        userInfo = root.findViewById(R.id.userInfo);
        plusText = root.findViewById(R.id.bonusPoint);
        minusText = root.findViewById(R.id.minusPoint);

        btnLogout = root.findViewById(R.id.btnLogout);
        btnCheckPoint = root.findViewById(R.id.btnCheckPoint);

        btnLogout.setOnClickListener(listener);
        btnCheckPoint.setOnClickListener(listener);

        return root;
    }

    private void makeText(String ID, String name, Integer plus, Integer minus) {
        userName.setText(name);
        userInfo.setText(ID);
        plusText.setText(String.valueOf(plus));
        minusText.setText(String.valueOf(minus));
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnLogout:
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    break;
                case R.id.btnCheckPoint:
                    Intent pointIntent = new Intent(getActivity().getApplicationContext(), ShowPoint.class);
                    startActivity(pointIntent);
                    break;

            }
        }
    };
}
