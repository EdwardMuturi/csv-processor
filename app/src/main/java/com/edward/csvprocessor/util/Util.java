package com.edward.csvprocessor.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.edward.csvprocessor.model.Cities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.MainCoroutineDispatcher;

public class Util {
    private static final String STORAGE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String TAG = Util.class.getSimpleName();

    /**
     * open file manager
     * allow selection of any text file
     * @return Intent
     */
    public static Intent openFileIntent() {
        Intent chooseFile;
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("text/*");
        return Intent.createChooser(chooseFile, "Choose a .CSV file");
    }

    /**
     * @param context
     * get file path from given uri  {@param fileUri}
     * @return String
     */
    public static String getPathFromURI(Context context, Uri fileUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(fileUri, proj, null, null, null);
        if (cursor == null)
            return null;

        int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    /**
     * locate and read file from {@param path}
     * @return String
     */
    public static List<Cities> readCSVFile(Context context, String path, String splitter) {
        String line = null;
        List<Cities> citiesList = new ArrayList<>();

        try {
            //read text in default encoding
            FileReader fileReader = new FileReader(path);
            //wrap file reader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Cities cities;

            //get titles in first line
            String[] titles = bufferedReader.readLine().split(splitter);


                //read and store remaining data; body
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(splitter);


                cities = new Cities(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);
                citiesList.add(cities);

            }

            bufferedReader.close(); //close file

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to open file " + path);
            Toast.makeText(context, "Unable to open file, choose a different file", Toast.LENGTH_LONG ).show();

        } catch (IOException ioException) {
            System.out.println("Error reading file " + path);
            Toast.makeText(context, "Error reading file chosen file, choose a different file", Toast.LENGTH_LONG ).show();

        } catch (ArrayIndexOutOfBoundsException indexOutOfBoundException){
            System.out.println("Error reading file " + indexOutOfBoundException);
            Toast.makeText(context, "Cannot read this file, choose a different file", Toast.LENGTH_LONG ).show();

        }
        return citiesList;
    }

}
