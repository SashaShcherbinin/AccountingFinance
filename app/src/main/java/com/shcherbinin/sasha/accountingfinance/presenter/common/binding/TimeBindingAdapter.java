package com.shcherbinin.sasha.accountingfinance.presenter.common.binding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created with Android Studio.
 * User: Sasha Shcherbinin
 * Date: 2/15/17
 * Time: 9:05 AM
 */

public class TimeBindingAdapter {

    @BindingAdapter({"time", "pattern"})
    public static void setTime(TextView textView, long time, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.UK);
        textView.setText(simpleDateFormat.format(new Date(time)));
    }
}
