package com.edward.csvprocessor.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.edward.csvprocessor.R;
import com.edward.csvprocessor.viewmodel.ParserViewModel;

public class ParserActivity extends AppCompatActivity {
    private ParserViewModel parserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);
        parserViewModel= ViewModelProviders.of(this).get(ParserViewModel.class);


    }
}
