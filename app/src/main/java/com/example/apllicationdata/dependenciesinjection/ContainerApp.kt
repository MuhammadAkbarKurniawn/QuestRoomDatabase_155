package com.example.apllicationdata.dependenciesinjection

import android.content.Context
import com.example.apllicationdata.data.database.KrsDatabase
import com.example.apllicationdata.repository.LocalRepositoryMhs
import com.example.apllicationdata.repository.RepositoryMhs

interface InterfaceContainerApp{
    val repositoryMhs: RepositoryMhs
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryMhs:RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}