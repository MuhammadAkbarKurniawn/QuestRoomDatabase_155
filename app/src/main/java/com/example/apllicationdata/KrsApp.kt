package com.example.apllicationdata

import android.app.Application
import com.example.apllicationdata.dependenciesinjection.ContainerApp

class KrsApp : Application(){
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) //membuat instance
        // Instance adalah object yang dibuat dari class
    }
}