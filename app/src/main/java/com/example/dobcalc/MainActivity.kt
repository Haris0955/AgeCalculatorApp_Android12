package com.example.dobcalc


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView?=null
    private var tvAgeInDays : TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val selectBtn : Button = findViewById(R.id.selectBtn)
        tvSelectedDate= findViewById(R.id.id_tvSelectedDate)
        tvAgeInDays = findViewById(R.id.id_tvAgeInDays)
        selectBtn.setOnClickListener {
            openDatePicker()
        }
    }
    private fun openDatePicker() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,
                "Year was $selectedYear, Month was ${selectedMonth+1}, Day was $selectedDayOfMonth",
                Toast.LENGTH_LONG).show()

                val dateSelected = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = dateSelected

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedDate = sdf.parse(dateSelected)
                selectedDate?.let {
                    val selectedDateInMillis = selectedDate.time
                    val currentDate = Calendar.getInstance().time
                    val currentDateInMillis = currentDate.time

                    val differenceInMillis = currentDateInMillis - selectedDateInMillis
                    val differenceInDays = differenceInMillis / (1000 * 60 * 60 * 24) // Convert milliseconds to days

                    tvAgeInDays?.text = differenceInDays.toString()
                }
            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
        dpd.show()
    }


}

//private fun openDatePicker() {
//
//    val myCalender = Calendar.getInstance()
//    val year = myCalender.get(Calendar.YEAR)
//    val month = myCalender.get(Calendar.MONTH)
//    val day = myCalender.get(Calendar.DAY_OF_MONTH)
//
//    val dpd =  DatePickerDialog(this,
//        DatePickerDialog.OnDateSetListener { view, selectedYear,selectedMonth, selectedDayOfMonth ->
//
//            Toast.makeText(this,
//                "Year was $selectedYear, Month was ${selectedMonth+1}, Day was ${selectedDayOfMonth}",
//                Toast.LENGTH_LONG).show()
//
//            val dateSelected = "$selectedDayOfMonth/ ${selectedMonth+1}/$selectedYear"
//            tvSelectedDate?.text  = dateSelected
//
//            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
//
//            val theDate = sdf.parse(dateSelected)
//
//            theDate?.let {  val selectedDateInMin =  theDate.time/60000
//
//                val currentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
//                currentDate?.let {  val currentDateInMinutes = currentDate.time/60000
//
//                    val differenceInMinutes = currentDateInMinutes - selectedDateInMin
//
//                    tvAgeInMin?.text=differenceInMinutes.toString()
//                }
//            }
//
//
//        },
//        year,
//        month,
//        day)
//
//    dpd.datePicker.maxDate= System.currentTimeMillis() - 8640000
//    dpd.show()
//}