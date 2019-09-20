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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kotlinx.coroutines.MainCoroutineDispatcher;

public class Util {
//    private static final String STORAGE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String TAG = Util.class.getSimpleName();

    /**
     * open file manager
     * allow selection of any text file
     *
     * @return Intent
     */
    public static Intent openFileIntent() {
        Intent chooseFile;
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("text/*");
        return Intent.createChooser(chooseFile, "Choose a .CSV file");
    }

    /**
     * @param context get file path from given uri  {@param fileUri}
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
     *
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
            Cities cities = null;

            //get titles in first line
            String[] titles = bufferedReader.readLine().split(splitter);


            //read and store remaining data; body
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(splitter);

                if (values.length == 1) {
                    cities = new Cities(values[0]);

                } else if (values.length == 2)
                    cities = new Cities(values[0], values[1]);

                else if (values.length == 3)
                    cities = new Cities(values[0], values[1], values[2]);

                else if (values.length == 4)
                    cities = new Cities(values[0], values[1], values[2], values[3]);

                else if (values.length == 5)
                    cities = new Cities(values[0], values[1], values[2], values[3], values[4]);
                else if (values.length == 6)
                    cities = new Cities(values[0], values[1], values[2], values[3], values[4], values[5]);

                else if (values.length == 7)
                    cities = new Cities(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);

                else if (values.length == 8)
                    cities = new Cities(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7]);

                else if (values.length == 9)
                    cities = new Cities(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8]);

                else if (values.length == 10)
                    cities = new Cities(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);
                else
                    Toast.makeText(context, "Error reading file chosen file, choose a different file", Toast.LENGTH_LONG).show();


                citiesList.add(cities);

            }

            for (Cities city : citiesList) {
                System.out.println("Some value" + city);
            }

            bufferedReader.close(); //close file

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to open file " + path);
            Toast.makeText(context, "Unable to open file, choose a different file", Toast.LENGTH_LONG).show();

        } catch (IOException ioException) {
            System.out.println("Error reading file " + path);
            Toast.makeText(context, "Error reading file chosen file, choose a different file", Toast.LENGTH_LONG).show();

        } catch (ArrayIndexOutOfBoundsException indexOutOfBoundException) {
            System.out.println("Error reading file " + indexOutOfBoundException);
            Toast.makeText(context, "Cannot read this file, choose a different file", Toast.LENGTH_LONG).show();

        }
        return citiesList;
    }

    public int convertToInteger(String string){
        return Integer.parseInt(string);
    }

    public String convertToDate(String date){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US);
            SimpleDateFormat format;
        String formattedDate = null;
        try {
            Date dateToConvert= simpleDateFormat.parse(date);
            format = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = format.format(dateToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return  formattedDate;
    }

}
