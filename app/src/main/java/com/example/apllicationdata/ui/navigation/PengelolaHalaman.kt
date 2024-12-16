package com.example.apllicationdata.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apllicationdata.ui.view.mahasiswa.DestinasiInsert
import com.example.apllicationdata.ui.view.mahasiswa.InsertMhsView
import com.example.apllicationdata.ui.view.mahasiswa.InsertMhsView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(navController, startDestination = DestinasiInsert.route) {
        composable(
            route = DestinasiInsert.route
        ){
            InsertMhsView(
                onBack = {}, onNavigate = {}
            )
        }
    }
}