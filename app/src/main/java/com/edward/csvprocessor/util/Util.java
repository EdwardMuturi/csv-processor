package com.edward.csvprocessor.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

public class Util {
    public static Intent openFile() {
        Intent chooseFile= new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("applicaton/image");

        return Intent.createChooser(chooseFile, "Choose a CSV");
    }
}
