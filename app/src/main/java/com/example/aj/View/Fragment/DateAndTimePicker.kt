package com.example.aj.View.Fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.aj.R
import java.text.SimpleDateFormat
import java.util.*

class DateAndTimePicker : Fragment() , DatePickerDialog.OnDateSetListener,
TimePickerDialog.OnTimeSetListener {
    lateinit var spinner: Spinner
    lateinit var timezonesText: TextView
    lateinit var dateTimeText: TextView
    lateinit var dateTimePickBtn: Button

     var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_date_and_time_picker, container, false)
        spinner =view!!.findViewById(R.id.spinner)
         timezonesText=view!!.findViewById(R.id.timeZonesText)
        dateTimeText=view!!.findViewById(R.id.dateText)
        dateTimePickBtn=view!!.findViewById(R.id.btnPick)
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss a")
        val currentDate = sdf.format(Date())
        dateTimeText.text=currentDate
        dateTimePickBtn.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(getActivity()!!, this, year, month,day)
            datePickerDialog.show()
        }

        val timeAme=TimeZone.getTimeZone("America/Chicago")
        val dateFormat=SimpleDateFormat("dd-MM-yyyy  hh:mm:ss a")
        dateFormat.setTimeZone(timeAme)
        val date=Date()
        val curr_date1=dateFormat.format(date)

        val timeAmee=TimeZone.getTimeZone("Canada/Yukon")
        dateFormat.setTimeZone(timeAmee)
        val curr_date2=dateFormat.format(date)

        val timeAmeee=TimeZone.getTimeZone("Australia/Victoria")
        dateFormat.setTimeZone(timeAmeee)
        val curr_date3=dateFormat.format(date)

        val timeAmeee1=TimeZone.getTimeZone("Asia/Kuwait")
        dateFormat.setTimeZone(timeAmeee1)
        val curr_date4=dateFormat.format(date)


        val timeAmeee2=TimeZone.getTimeZone("Hongkong")
        dateFormat.setTimeZone(timeAmeee2)
        val curr_date5=dateFormat.format(date)
        val timezones = resources.getStringArray(R.array.timezones)

        if (spinner != null) {
            val adapter = context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item, timezones
                )
            }
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    when (position) {
                        1 ->
                            timezonesText.text = curr_date1+"America/Chicago"
                        2->
                            timezonesText.text=curr_date2 +"Canada/Yukon"
                        3->
                            timezonesText.text=curr_date3 +"Australia/Victoria"
                        4->
                            timezonesText.text=curr_date4 +"Asia/Kuwait"
                        5->
                            timezonesText.text=curr_date5 +"Hongkong"
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
        return view
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = day
        myYear = year
        myMonth = month
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(getActivity()!!, this, hour, minute,
            false)
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val hi:String
        myHour = hourOfDay
        myMinute = minute
        if(hourOfDay>=0 && hourOfDay<12){
           hi= " AM"
        } else {
            if(hourOfDay >= 12){
                hi= "PM";
            }
        }
        dateTimeText.text =  "Hi " +myYear + "/" + myMonth + "/"  + myDay + "\n"  + myHour + ":"  + myMinute
            }    }

