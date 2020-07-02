package com.example.appproject.ui.food;

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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kr.go.neis.api.Menu;
import kr.go.neis.api.NEISException;
import kr.go.neis.api.School;

public class Food extends Fragment {

    TextView breakfast, lunch, dinner;

    Calendar cal = Calendar.getInstance();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food, container, false);
        cal.setTime(new Date());
        breakfast = root.findViewById(R.id.breakfast);
        lunch = root.findViewById(R.id.lunch);
        dinner = root.findViewById(R.id.dinner);


        makeText(2020,7,1);

        return root;
    }
    public void makeText(final int year, final int month, final int day){
        new Thread(){
            @Override
            public void run() {
                try {
                    School school = new School(School.Type.HIGH,School.Region.GWANGJU,"F100000120");

                    List<Menu> menu = school.getMonthlyMenu(2020, 7);
                    breakfast.setText(menu.get(1).getBreakfast());
                    lunch.setText(menu.get(1).getLunch());
                    dinner.setText(menu.get(1).getDinner());
                } catch (NEISException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}