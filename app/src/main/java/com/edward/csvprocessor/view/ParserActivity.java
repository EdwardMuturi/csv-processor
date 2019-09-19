package com.edward.csvprocessor.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.edward.csvprocessor.R;
import com.edward.csvprocessor.viewmodel.ParserViewModel;

import butterknife.OnClick;

public class ParserActivity extends AppCompatActivity {
    private ParserViewModel parserViewModel;
    private static final int OPEN_FILE_REQUEST_CODE= 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);
        parserViewModel= ViewModelProviders.of(this).get(ParserViewModel.class);


    }

    @OnClick(R.id.btnOpenFile)
    void openFile(){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
