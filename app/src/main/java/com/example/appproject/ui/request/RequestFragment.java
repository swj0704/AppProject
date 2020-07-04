package com.example.appproject.ui.request;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appproject.R;
import com.example.appproject.RequestLaptop;
import com.example.appproject.RequestSong;
import com.example.appproject.RequestStay;

public class RequestFragment extends Fragment {

    ConstraintLayout btnLaptop, btnSong, btnStay;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_request, container, false);

        btnLaptop = root.findViewById(R.id.btn_laptop);
        btnSong = root.findViewById(R.id.btn_song);
        btnStay = root.findViewById(R.id.btn_stay);

        btnLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RequestLaptop.class);
                startActivity(intent);
            }
        });

        btnSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RequestSong.class);
                startActivity(intent);
            }
        });

        btnStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RequestStay.class);
                startActivity(intent);
            }
        });

        return root;
    }
}