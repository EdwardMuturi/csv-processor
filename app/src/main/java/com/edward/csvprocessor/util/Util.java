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
            Cities cities;

            //get titles in first line
            String[] titles = bufferedReader.readLine().split(splitter);


            while ((line = bufferedReader.readLine()) != null) {
                //split data by comma ,
                String[] values = line.split(",");
                String value1; String value2 ;String value3; String value4; String value5; String value6; String value7; String value8; String value9; String value10;

//                a very crude way of handling null fields
                if (values[0].length() > 0) {
                    value1 = values[0];
                } else {
                    value1 = "";
                }

                if (values[1].length() > 0) {
                    value2 = values[1];
                } else {
                    value2 = "";
                }

                if (values[2].length() > 0) {
                    value3 = values[2];
                } else {
                    value3 = "";
                }

                if (values[3].length() > 0) {
                    value4 = values[3];
                } else {
                    value4 = "";
                }

                if (values[4].length() > 0) {
                    value5 = values[4];
                } else {
                    value5 = "";
                }

                if (values[5].length() > 0) {
                    value6 = values[5];
                } else {
                    value6 = "";
                }

                if (values[6].length() > 0) {
                    value7 = values[6];
                } else {
                    value7 = "";
                }

                if (values[7].length() > 0) {
                    value8 = values[7];
                } else {
                    value8 = "";
                }

                if (values[8].length() > 0) {
                    value9 = values[8];
                } else {
                    value9 = "";
                }

                if (values[9].length() > 0) {
                    value10 = values[9];
                } else {
                    value10 = "";
                }

                cities = new Cities(value1, value2, value3, value4, value5, value6, value7, value8, value9, value10);
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
