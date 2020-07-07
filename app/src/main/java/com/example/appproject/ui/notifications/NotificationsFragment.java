package com.example.appproject.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appproject.ItemNoti;
import com.example.appproject.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    ListView listView;
    ArrayList<ItemNoti> dataList = new ArrayList<ItemNoti>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        listView = root.findViewById(R.id.listView);

        dataList.add(new ItemNoti("제목1","내용1"));
        dataList.add(new ItemNoti("제목2","내용2"));
        dataList.add(new ItemNoti("제목3","내용3"));

        final NotiAdapter adapter = new NotiAdapter(getActivity().getApplicationContext(), R.layout.row_notifications, dataList);

        listView.setAdapter(adapter);

        return root;
    }



}