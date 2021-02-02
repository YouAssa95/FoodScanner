package com.univ_amu.food_scanner.ui;

import android.content.res.Resources;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;

import com.univ_amu.food_scanner.R;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ConverterUtil {

    @BindingAdapter("app:date")
    public static void setDate(TextView view, Date date) {
        if (date==null) return;
        Resources resources = view.getContext().getApplicationContext().getResources();
        view.setText(getStringFromDate(resources,date));
    }

    private static String getStringFromDate(Resources resources, Date date) {
        LocalDate today = LocalDate.now();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int dayCount = (int) ChronoUnit.DAYS.between(localDate, today);
        int monthCount = (int) ChronoUnit.MONTHS.between(localDate, today);
        int yearCount = (int) ChronoUnit.YEARS.between(localDate, today);
        if (dayCount == 0)
            return resources.getString(R.string.today);
        if (yearCount >= 1)
            return resources.getQuantityString(R.plurals.years_ago, yearCount, yearCount);
        if (monthCount >= 1)
            return resources.getQuantityString(R.plurals.months_ago, monthCount, monthCount);

        return resources.getQuantityString(R.plurals.days_ago, dayCount, dayCount);
    }
}