package com.example.appproject.ui.food;

import android.os.Bundle;
import android.util.Log;
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

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DATE)-1;


        makeText(year, month, day);

        Log.d("숫자",String.valueOf(month));
        Log.d("숫자",String.valueOf(day));

        return root;
    }
    public void makeText(final int year, final int month, final int day){
        new Thread(){
            @Override
            public void run() {
                try {
                    School school = new School(School.Type.HIGH,School.Region.GWANGJU,"F100000120");

                    List<Menu> menu = school.getMonthlyMenu(year,month);
                    breakfast.setText(menu.get(day).getBreakfast());
                    lunch.setText(menu.get(day).getLunch());
                    dinner.setText(menu.get(day).getDinner());
                } catch (NEISException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}