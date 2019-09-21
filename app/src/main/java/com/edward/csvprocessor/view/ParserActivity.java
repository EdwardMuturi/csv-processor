package com.edward.csvprocessor.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.csvprocessor.R;
import com.edward.csvprocessor.adapter.CSVParserAdapter;
import com.edward.csvprocessor.model.Cities;
import com.edward.csvprocessor.util.Util;
import com.edward.csvprocessor.viewmodel.ParserViewModel;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParserActivity extends AppCompatActivity {
    private static final String TAG = ParserActivity.class.getSimpleName();
    private ParserViewModel parserViewModel;
    private static final int OPEN_FILE_REQUEST_CODE = 100;
    private Context context;
    private FirebaseAnalytics mFirebaseAnalytics;

    @BindView(R.id.rvData)
    RecyclerView recyclerView;

    List<Cities> cities = new ArrayList<>();
    CSVParserAdapter csvParserAdapter = new CSVParserAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);
        ButterKnife.bind(this);
        parserViewModel = ViewModelProviders.of(this).get(ParserViewModel.class);
        context = ParserActivity.this;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


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


                if (TextUtils.isEmpty(filePath)) {
                    Toast.makeText(context, uri.getPath(), Toast.LENGTH_LONG).show();
                    cities = Util.readCSVFileFromUri(context, uri, ",");
                } else
                    cities = Util.readCSVFile(context, filePath, ",");


                csvParserAdapter.submitList(cities);

                recyclerView.setAdapter(csvParserAdapter);


            } catch (NullPointerException e) {
                Log.e(TAG, e.toString());
                Util.showAlertDialog(context, "Unable to open file, please try to open a file in  a different location");
            }
        }

    }


}
