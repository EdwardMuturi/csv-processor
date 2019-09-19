package com.edward.csvprocessor.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

public class Util {
    public static Intent openFile() {
        File file= new File(Environment.getExternalStorageDirectory(), "C2PKMSampleImport.csv");
        Uri path= Uri.fromFile(file);
        Intent openFileIntent= new Intent(Intent.ACTION_VIEW);
        openFileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        openFileIntent.setDataAndType(path, "application/csv");

        return openFileIntent;
    }
}
