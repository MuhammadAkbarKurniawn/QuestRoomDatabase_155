package com.example.apllicationdata.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.apllicationdata.data.entity.Mahasiswa

@Dao
interface MahasiswaDao {

    @Insert
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
}