package com.example.appproject.ui.mypage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appproject.R;
import com.example.appproject.ui.home.HomeViewModel;

public class MyPageFragment extends Fragment {

    private MyPageViewModel myPageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myPageViewModel =
                ViewModelProviders.of(this).get(MyPageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mypage, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        myPageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
