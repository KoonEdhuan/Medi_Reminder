package com.example.medireminder.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface MedicineDetailsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(medicineDetails: MedicineDetails)
}