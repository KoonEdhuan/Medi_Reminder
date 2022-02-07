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
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class AddMedicine : AppCompatActivity() {

    private lateinit var binding: ActivityAddMedicineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdown()

        binding.autoCompleteMedicineType.setOnItemClickListener { adapterView, view, pos, id ->
            Toast.makeText(this,"${adapterView.getItemAtPosition(pos)} selected",Toast.LENGTH_SHORT).show()
            if (adapterView.getItemAtPosition(pos) == "Pill"){
                binding.cardView.visibility = View.GONE
            }
            else binding.cardView.visibility = View.VISIBLE
        }

        binding.dateButton1.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {
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
            binding.dateButton1.text = "${picker.hour}:${picker.minute} $picker"
        }
    }

    private fun setupDropdown(){
        val medicineType = resources.getStringArray(R.array.medicine_types)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item,medicineType)
        binding.autoCompleteMedicineType.setAdapter(arrayAdapter)

        val frequency = resources.getStringArray(R.array.dose_frequency)
        val arrayAdapter1 = ArrayAdapter(this,R.layout.dropdown_item,frequency)
        binding.autoCompleteDosagePerDay.setAdapter(arrayAdapter1)
    }
}