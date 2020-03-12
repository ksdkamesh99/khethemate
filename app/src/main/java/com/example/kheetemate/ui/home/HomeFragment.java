package com.example.kheetemate.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kheetemate.MainActivity2;
import com.example.kheetemate.ProfileActivity;
import com.example.kheetemate.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    TextView textView,textView2;
    Button textView1;
    String name;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView=root.findViewById(R.id.text_home);
        textView1=root.findViewById(R.id.Text_Home);
        textView2=root.findViewById(R.id.text_home_1);
        textView.setSelected(true);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
        ProfileActivity profileActivity=new ProfileActivity();
        name=profileActivity.GetName();
        textView2.setText("Hello Farmer");
        /*final TextView textView = root.findViewById(R.id.text_home);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }


}