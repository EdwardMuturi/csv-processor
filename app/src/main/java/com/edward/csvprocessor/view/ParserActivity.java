package com.edward.csvprocessor.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.edward.csvprocessor.R;
import com.edward.csvprocessor.util.Util;
import com.edward.csvprocessor.viewmodel.ParserViewModel;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParserActivity extends AppCompatActivity {
    private static final String TAG = ParserActivity.class.getSimpleName();
    private ParserViewModel parserViewModel;
    private static final int OPEN_FILE_REQUEST_CODE = 100;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);
        ButterKnife.bind(this);
        parserViewModel = ViewModelProviders.of(this).get(ParserViewModel.class);
        context = ParserActivity.this;


    }

    @OnClick(R.id.btnOpenFile)
    void openFile() {
        startActivityForResult(Util.openFileIntent(), OPEN_FILE_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == OPEN_FILE_REQUEST_CODE) {
            try {
                //get file path
                Uri uri = data.getData();
                String filePath = Util.getPathFromURI(context, uri);



            } catch (NullPointerException e) {
                Log.e(TAG, e.toString());
            }
        }

    }


}
