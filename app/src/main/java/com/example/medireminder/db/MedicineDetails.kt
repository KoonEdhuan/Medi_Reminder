package com.example.medireminder.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Timestamp

@Entity(tableName = "medicine_details")
data class MedicineDetails(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String? = null,
    var medicine_type: String? = null,
    var startDate: Date?,
    var endDate: Date?,
    var dosePerDay: Int? = 0,
    var active:Boolean

    )
