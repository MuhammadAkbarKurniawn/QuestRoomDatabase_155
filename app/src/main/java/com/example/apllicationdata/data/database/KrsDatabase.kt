package com.example.apllicationdata.data.database

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apllicationdata.data.dao.MahasiswaDao
import com.example.apllicationdata.data.entity.Mahasiswa

//Mendefinisikan Database dengan tabel mahasiswa
@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase() {

    //Mendefinisikan fungsi untuk mengakses data
    abstract fun mahasiswaDao(): MahasiswaDao

    companion object{
        @Volatile //Memastikan bahwa nilai variabel instance selalu sama di semua
        private var Instance:KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabase::class.java,
                    "KrsDatabase"
                )
                    .build().also {  Instance = it }
            })
        }
    }

}