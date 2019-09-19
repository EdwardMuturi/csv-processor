package com.edward.csvprocessor.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.edward.csvprocessor.R;
import com.edward.csvprocessor.viewmodel.ParserViewModel;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParserActivity extends AppCompatActivity {
    private ParserViewModel parserViewModel;
    private static final int OPEN_FILE_REQUEST_CODE= 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);
        ButterKnife.bind(this);
        parserViewModel= ViewModelProviders.of(this).get(ParserViewModel.class);


    }

    @OnClick(R.id.btnOpenFile)
    void openFile(){
        Toast.makeText(ParserActivity.this, "Clicked", Toast.LENGTH_LONG).show();
        String path= Environment.getExternalStorageDirectory().getAbsolutePath();
        new ChooserDialog(ParserActivity.this)
                .withStartFile(path)
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String s, File file) {
                        Toast.makeText(ParserActivity.this, "File: " + path, Toast.LENGTH_LONG).show();
                    }
                })

                .withOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Toast.makeText(ParserActivity.this, "CANCEL", Toast.LENGTH_LONG).show();
                        dialogInterface.cancel();
                    }
                })
                .build()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
