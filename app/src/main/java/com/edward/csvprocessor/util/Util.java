package com.edward.csvprocessor.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class Util {
    private static final String STORAGE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String TAG = Util.class.getSimpleName();
    private static String FILE_PATH = "com.edward.csvprocessor.util.FILE_PATH";

    /**
     * @param context Open file dialog
     *                Return selected file
     */
    public static void openFile(Context context, TextView textView) {
//        String path= "path";
        new ChooserDialog(context)
                .withStartFile(STORAGE_PATH)
                .withChosenListener((filePath, file) ->
                        textView.setText(readFileContents(context, file.getAbsolutePath()))
                )

                .withOnCancelListener(dialogInterface -> {
                    Toast.makeText(context, "CANCEL", Toast.LENGTH_LONG).show();
                    dialogInterface.cancel();
                })
                .build()
                .show();

    }

    public static String readFileContents(Context context, String path) {
        StringBuilder stringBuilder = new StringBuilder();
        Toast.makeText(context, FILE_PATH, Toast.LENGTH_LONG).show();

        try (InputStream inputStream =
                     context.getContentResolver().openInputStream(Uri.parse(path));
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                Log.d(TAG, "here" + stringBuilder.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

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
    public static String readCSVFile(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            //read text in default encoding
            FileReader fileReader = new FileReader(path);

            //wrap file reader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                System.out.println(line);
            }

            bufferedReader.close(); //close file
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to open file " + path);
        } catch (IOException ioException) {
            System.out.println("Error reading file " + path);
        }
            return stringBuilder.toString();
    }

}
