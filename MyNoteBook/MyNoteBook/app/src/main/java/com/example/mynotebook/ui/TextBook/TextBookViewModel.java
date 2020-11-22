package com.example.mynotebook.ui.TextBook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TextBookViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TextBookViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("这是生词本界面" );
    }

    public LiveData<String> getText() {
        return mText;
    }
}