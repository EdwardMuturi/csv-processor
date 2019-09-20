package com.edward.csvprocessor.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.edward.csvprocessor.util.Util;

/**
 * Created by Edward Muturi on 19/09/2019.
 */
public class ParserViewModel extends AndroidViewModel {
    private Context context;
    public ParserViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }

//    public void openStorageFile(){
//        Util.openFile( context);
//    }


}
