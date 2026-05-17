package com.example.mbokoshop.ScreenInterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController) {
    val vertMboko = Color(0xFF028342)
    val rougeMboko = Color.Red
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détails du produit", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Aller au panier */ }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Panier")
                    }
                }
            )
        },
        bottomBar = {
            // Barre d'action fixe en bas
            BottomAppBar(containerColor = Color.White, tonalElevation = 8.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("25 000 FCFA", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = vertMboko)
                    Button(
                        onClick = { /* Ajouter au panier */ },
                        colors = ButtonDefaults.buttonColors(containerColor = rougeMboko),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Ajouter au panier", color = Color.White)
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            // 1. Aperçu Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.LightGray)
            ) {
                // Ici vous mettrez votre Image()
                Text("Image du Produit", modifier = Modifier.align(Alignment.Center), color = Color.Gray)
            }

            Column(modifier = Modifier.padding(16.dp)) {
                // 2. Titre et Note
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Smartphone Mboko X1", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFB400))
                    Text(" 4.5", fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 3. Caractéristiques (Détails)
                Text("Caractéristiques", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                CaractItem("Marque", "Mboko Tech")
                CaractItem("Écran", "6.5 pouces Super AMOLED")
                CaractItem("Batterie", "5000 mAh")
                CaractItem("Stockage", "128 Go")

                Spacer(modifier = Modifier.height(24.dp))

                // 4. Description
                Text("Description", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Ce smartphone haute performance est conçu pour répondre à tous vos besoins quotidiens. Avec sa caméra haute résolution et sa batterie longue durée, restez connecté partout au Cameroun.",
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 5. Produits Similaires
                Text("Produits Similaires", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(5) { index ->
                        SimilarProductItem()
                    }
                }
            }
        }
    }
}

@Composable
fun CaractItem(label: String, value: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Text("$label : ", fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
        Text(value, color = Color.Gray)
    }
}

@Composable
fun SimilarProductItem() {
    Card(
        modifier = Modifier.width(140.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp)))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Produit lié", fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1)
            Text("18 000 F", fontSize = 12.sp, color = Color(0xFF028342))
        }
    }
}









@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailScreenPreview(){
    val navController = androidx.navigation.compose.rememberNavController()
    DetailScreen(navController = navController)
}