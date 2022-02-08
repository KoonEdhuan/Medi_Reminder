package com.example.medireminder.ui.addmedicine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.medireminder.R
import com.example.medireminder.databinding.ActivityAddMedicineBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class AddMedicine : AppCompatActivity() {

    private lateinit var binding: ActivityAddMedicineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdown()
        setStartDate()

        binding.autoCompleteDosageFrequency.setOnItemClickListener { adapterView, view, pos, id ->
            disableButtonViews(adapterView,pos)
        }

        binding.timeButton1.setOnClickListener {
            openTimePickerForFirstDose()
        }
        binding.timeButton2.setOnClickListener {
            openTimePickerForSecondDose()
        }
        binding.timeButton3.setOnClickListener {
            openTimePickerForThirdDose()
        }
        binding.dateButton1.setOnClickListener {
            openDatePicker()
        }

        val checkedRadioButtonId = binding.radioGroup.checkedRadioButtonId
        binding.radioGroup.setOnCheckedChangeListener { radioGroup, id ->  }
    }

    private fun setStartDate(){
        val sdf = SimpleDateFormat("MMM dd, yyyy")
        val currentDate = sdf.format(Date())
        binding.dateButton1.text =  "Start date: $currentDate"
    }

    /*
        Enable and disable date picker buttons according to frequency selection
     */
    private fun disableButtonViews(adapterView: AdapterView<*>,pos: Int) {
        if (adapterView.getItemAtPosition(pos) == "Once daily"){
            binding.timeButton1.visibility = View.VISIBLE
            binding.timeButton2.visibility = View.GONE
            binding.timeButton3.visibility = View.GONE
        }
        else if (adapterView.getItemAtPosition(pos) == "2 times a day"){
            binding.timeButton1.visibility = View.VISIBLE
            binding.timeButton2.visibility = View.VISIBLE
            binding.timeButton3.visibility = View.GONE
        }
        else if (adapterView.getItemAtPosition(pos) == "3 times a day"){
            binding.timeButton1.visibility = View.VISIBLE
            binding.timeButton2.visibility = View.VISIBLE
            binding.timeButton3.visibility = View.VISIBLE
        }
        else if(adapterView.getItemAtPosition(pos) == "Every number of hours"){
            binding.timeButton1.visibility = View.GONE
            binding.timeButton2.visibility = View.GONE
            binding.timeButton3.visibility = View.GONE
        }
    }

    private fun openDatePicker(){
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Start Date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        picker.show(supportFragmentManager,"tag")

        picker.addOnPositiveButtonClickListener {
            binding.dateButton1.text = "Start date: ${picker.headerText}"
        }
    }

    private fun openTimePickerForFirstDose() {
        val isSystem24Hour = is24HourFormat(this)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(8)
            .setMinute(0)
            .setTitleText("Set Alarm")
            .build()
        picker.show(supportFragmentManager, "tag")

        picker.addOnPositiveButtonClickListener{
            binding.timeButton1.text = "${picker.hour}:${picker.minute}"
        }
    }

    private fun openTimePickerForSecondDose(){
        val isSystem24Hour = is24HourFormat(this)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(8)
            .setMinute(0)
            .setTitleText("Set Alarm")
            .build()
        picker.show(supportFragmentManager, "tag")

        picker.addOnPositiveButtonClickListener{
            binding.timeButton2.text = "${picker.hour}:${picker.minute}"
        }
    }

    private fun openTimePickerForThirdDose(){
        val isSystem24Hour = is24HourFormat(this)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(8)
            .setMinute(0)
            .setTitleText("Set Alarm")
            .build()
        picker.show(supportFragmentManager, "tag")

        picker.addOnPositiveButtonClickListener{
            binding.timeButton3.text = "${picker.hour}:${picker.minute}"
        }
    }

    private fun setupDropdown(){
        val medicineType = resources.getStringArray(R.array.medicine_types)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item,medicineType)
        binding.autoCompleteMedicineType.setAdapter(arrayAdapter)

        val frequency = resources.getStringArray(R.array.dose_frequency)
        val arrayAdapter1 = ArrayAdapter(this,R.layout.dropdown_item,frequency)
        binding.autoCompleteDosageFrequency.setAdapter(arrayAdapter1)
    }
}