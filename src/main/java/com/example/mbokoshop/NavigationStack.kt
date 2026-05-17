package com.example.mbokoshop

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mbokoshop.ScreenInterface.ChooseScreen
import com.example.mbokoshop.ScreenInterface.DetailScreen
import com.example.mbokoshop.ScreenInterface.HomeScreenClient
import com.example.mbokoshop.ScreenInterface.HomeScreenVendeur

@Composable
fun NavigationStack(){

    //Etape 1 : Créer un navController
    val navController = rememberNavController()

    //Etape 2 : Créer les routes
    NavHost(navController = navController, startDestination = "connexion") {
        // Navigation graph defined here

        composable("connexion") {
            ConnexionScreen(navController)
        }

        composable("inscription") {
            InscriptionScreen(navController)
        }
        // Déclaration de l'écran de choix
        composable("choose") {
            ChooseScreen(navController)
        }
        // Déclaration de l'écran d'accueil client
        composable("home_client") {
            HomeScreenClient(navController)
        }

        composable("home_vendeur") {
            HomeScreenVendeur(navController)
        }

        composable("product_detail") {
            DetailScreen(navController)
        }
    }
}