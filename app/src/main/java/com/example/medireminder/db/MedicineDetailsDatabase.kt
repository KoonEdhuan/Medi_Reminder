package com.example.medireminder.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(
    entities =[MedicineDetails::class],
    version = 1
)
@TypeConverters(Converter::class)
public abstract class MedicineDetailsDatabase: RoomDatabase() {

abstract fun getMedicineDetailsDAO(): MedicineDetailsDAO
}