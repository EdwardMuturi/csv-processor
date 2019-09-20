package com.edward.csvprocessor.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.edward.csvprocessor.model.Cities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {
    private static final String STORAGE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String TAG = Util.class.getSimpleName();


    public static Intent openFileIntent() {
        Intent chooseFile;
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("text/*");
        return Intent.createChooser(chooseFile, "Choose a .CSV file");
    }

    public static String getPathFromURI(Context context, Uri fileUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(fileUri, proj, null, null, null);
        if (cursor == null)
            return null;

        int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    //read csv file

    /**
     * @param path
     * @return String
     */
    public static List<Cities> readCSVFile(String path, String splitter) {
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        List<Cities> citiesList = new ArrayList<>();
        try {
            //read text in default encoding
            FileReader fileReader = new FileReader(path);
            //wrap file reader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //get titles in first line
            String [] titles = bufferedReader.readLine().split(splitter);


            while ((line = bufferedReader.readLine()) != null) {
                //split data by comma ,
                String[] values = line.split(",");
                Cities cities = new Cities(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);
                citiesList.add(cities);


                //read data

                stringBuilder.append(line);
                System.out.println(stringBuilder);
            }

            bufferedReader.close(); //close file
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to open file " + path);
        } catch (IOException ioException) {
            System.out.println("Error reading file " + path);
        }
        return citiesList;
    }

}
