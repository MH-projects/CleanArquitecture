/*
 * DatePicker.kt
 * Android Kotlin App
 * Created by Manuel Hidalgo on 01/09/2022, 16:07:53
 * Copyright (c) 2022 Develop-MX. All rights reserved.
 */

package com.mh.mhapp.presentation

import android.app.DatePickerDialog
import android.content.Context
import com.mh.mhapp.presentation.ui.View
import java.util.*

class DatePickerAlert(private val ctx: Context, private val view: View) {

    fun show() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR) - 18
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        /**
         * @NonNull Context context,
         * @Nullable DatePickerDialog.OnDateSetListener listener,
         * int year,
         * int month,
         * int dayOfMonth
         */
        val datePickerDialog = DatePickerDialog(
            ctx,
            { _, y, m, d ->
                println("Date: $d - $m - $y")
                view.showBirthday(String.format("%02d/%02d/%d", d, m + 1, y))
            },
            year,
            month,
            day
        )

        calendar.set(Calendar.YEAR, year)
        datePickerDialog.datePicker.maxDate = calendar.timeInMillis

        calendar.set(Calendar.YEAR, year - 62)
        datePickerDialog.datePicker.minDate = calendar.timeInMillis

        datePickerDialog.show()
    }
}
