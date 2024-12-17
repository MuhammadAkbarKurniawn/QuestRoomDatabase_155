package com.example.apllicationdata.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apllicationdata.ui.navigation.AlamatNavigasi.DestinasiDetail
import com.example.apllicationdata.ui.navigation.AlamatNavigasi.DestinasiHome
import com.example.apllicationdata.ui.view.mahasiswa.DestinasiInsert
import com.example.apllicationdata.ui.view.mahasiswa.DetailMhsView
import com.example.apllicationdata.ui.view.mahasiswa.HomeMhsView
import com.example.apllicationdata.ui.view.mahasiswa.InsertMhsView
import com.example.apllicationdata.ui.view.mahasiswa.InsertMhsView
import com.example.apllicationdata.ui.view.mahasiswa.UpdateMhsView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        // Halaman Home
        composable(route = DestinasiHome.route) {
            HomeMhsView(
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                    println("PengelolaHalaman: nim = $nim")
                },
                onAddMhs = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }

        // Halaman Insert
        composable(route = DestinasiInsert.route) {
            InsertMhsView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        // Halaman Detail dengan Argument
        composable(
            route = DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM) {
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DestinasiDetail.NIM)
            // Detail view dapat menggunakan nilai 'nim' di sini

            nim?.let { nim ->
                DetailMhsView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiEdit.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = DestinasiEdit.routesWithArg,
                arguments = listOf(
                    navArgument(DestinasiEdit.NIM) {
                        type = NavType.StringType
                    }
                )
            ) {
                UpdateMhsView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate = {
                        navController.popBackStack()
                    },
                    modifier = modifier
                )
            }

        }
    }
}
