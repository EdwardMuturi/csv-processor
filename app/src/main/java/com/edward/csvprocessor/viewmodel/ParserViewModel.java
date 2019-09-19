package com.edward.csvprocessor.viewmodel;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.edward.csvprocessor.util.Util;

/**
 * Created by Edward Muturi on 19/09/2019.
 */
public class ParserViewModel extends ViewModel {

    public Intent openFile(){
        return Util.openFile();
    }
}
