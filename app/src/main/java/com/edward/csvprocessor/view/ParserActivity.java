package com.edward.csvprocessor.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

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
                cities = Util.readCSVFile(context, filePath, ",");

                csvParserAdapter.submitList(cities);

                recyclerView.setAdapter(csvParserAdapter);


            } catch (NullPointerException e) {
                Log.e(TAG, e.toString());
            }
        }

    }


}
