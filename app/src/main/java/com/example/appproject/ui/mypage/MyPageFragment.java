package com.example.appproject.ui.mypage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class MyPageFragment extends Fragment {

    Button btnLogout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mypage, container, false);

        btnLogout = root.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(listener);

        return root;
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnLogout:
                    FirebaseAuth.getInstance().signOut();
                    getActivity().finish();
                    break;

            }
        }
    };
}
