package com.edward.csvprocessor.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.edward.csvprocessor.model.Cities;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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
    public static List<Cities> readCSVFile(Context context, @Nullable String path, String splitter) {
        String line = null;
        List<Cities> citiesList = new ArrayList<>();

        try {
            //read text in default encoding
            FileReader fileReader = new FileReader(path);
            //wrap file reader in BufferedReader
            Cities cities = null;
            BufferedReader bufferedReader = new BufferedReader(fileReader);


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
                    showAlertDialog(context, "File has too many columns, maximum number of columns supported is 10");


                citiesList.add(cities);

            }

            for (Cities city : citiesList) {
                System.out.println("Some value" + city);
            }

            bufferedReader.close(); //close file

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to open file " + path);
            showAlertDialog(context, "Unable to open file, choose a different file");

        } catch (IOException ioException) {
            System.out.println("Error reading file " + path);
            showAlertDialog(context, "Error reading file chosen file, choose a different file");

        } catch (ArrayIndexOutOfBoundsException indexOutOfBoundException) {
            System.out.println("Error reading file " + indexOutOfBoundException);
            showAlertDialog(context, "Cannot read this file, choose a different file");

        }
        return citiesList;
    }


    public static List<Cities> readCSVFileFromUri(Context context, Uri uri, String splitter) {
        String line = null;
        List<Cities> citiesList = new ArrayList<>();

        try {
            //read text in default encoding

            Cities cities = null;
            //wrap file reader in BufferedReader


            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));


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
                    showAlertDialog(context, "Error reading file chosen file, choose a different file");


                citiesList.add(cities);

            }

            for (Cities city : citiesList) {
                System.out.println("Some value" + city);
            }

            bufferedReader.close(); //close file

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to open file " + uri.toString());
            showAlertDialog(context, "Unable to open file, choose a different file");

        } catch (IOException ioException) {
            System.out.println("Error reading file " + uri.toString());
            showAlertDialog(context, "Error reading file chosen file, choose a different file");

        } catch (ArrayIndexOutOfBoundsException indexOutOfBoundException) {
            System.out.println("Error reading file " + indexOutOfBoundException);
            showAlertDialog(context, "Cannot read this file, choose a different file");

        }
        return citiesList;
    }

    public int convertToInteger(String string) {
        return Integer.parseInt(string);
    }

    public String convertToDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US);
        SimpleDateFormat format;
        String formattedDate = null;
        try {
            Date dateToConvert = simpleDateFormat.parse(date);
            format = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = format.format(dateToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return formattedDate;
    }

    public static void showAlertDialog(Context context, String message) {
        AlertDialog alertDialog = new MaterialAlertDialogBuilder(context)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

}
