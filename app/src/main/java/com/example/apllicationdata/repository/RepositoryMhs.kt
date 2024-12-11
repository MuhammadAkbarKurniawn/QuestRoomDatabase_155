package com.example.apllicationdata.repository

import com.example.apllicationdata.data.entity.Mahasiswa

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
}