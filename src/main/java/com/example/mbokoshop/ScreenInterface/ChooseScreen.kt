package com.example.mbokoshop.ScreenInterface

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mbokoshop.Data.NavigationItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseScreen(navController: NavController) {
    val vertMboko = Color(0xFF028342)
    val rougeMboko = Color.Red
    val fondGrisClair = Color(0xFFF8F9FA)

    Scaffold(
        containerColor = fondGrisClair
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenue sur MbokoShop",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFFF9800),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Choisissez votre type de profil pour continuer",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ChoiceCard(
                    title = "Être client",
                    description = "Pour acheter des produits",
                    icon = Icons.Default.AccountCircle,
                    color = vertMboko,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate("home_client") // Cette ligne "appelle" l'écran suivant
                    }
                )

                ChoiceCard(
                    title = "Vendeur",
                    description = "Pour gérer votre boutique",
                    icon = Icons.Default.Storefront,
                    color = rougeMboko,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate("home_vendeur") // <-- Cette ligne "appelle" l'écran suivant
                    }
                )
            }
        }
    }
}

@Composable
fun ChoiceCard(
    title: String,
    description: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(220.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(35.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2D2D)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenClient(navController: NavController) {
    val rougeMboko = Color(0xFFE91E63)
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MbokoShop", color = Color.White, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Panier", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = rougeMboko)
            )
        },
        bottomBar = { MbokoBottomBar(navController, isVendeur = false) } // AJOUT ICI

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Rechercher un produit...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = rougeMboko,
                    focusedLabelColor = rougeMboko,
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text("Produits populaires", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(6) { index -> ProductItem(index) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenVendeur(navController: NavController) {
    val rougeMboko = Color.Red
    val vertMboko = Color(0xFF028342)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Espace Vendeur", color = Color.White, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { /* Action paramètres boutique */ }) {
                        Icon(Icons.Default.Storefront, contentDescription = "Ma Boutique", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = rougeMboko)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Action ajouter produit */ },
                containerColor = rougeMboko,
                contentColor = Color.White
            ) {
                Text("+", fontSize = 24.sp)
            }
        },

        bottomBar = { MbokoBottomBar(navController, isVendeur = true) } // AJOUT ICI
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Tableau de bord", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            // Cartes de statistiques
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard("Ventes", "150 000 F", vertMboko, Modifier.weight(1f))
                StatCard("Commandes", "12", rougeMboko, Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Mes Produits", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            // Liste des produits du vendeur
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(4) { index ->
                    VendeurProductItem(index, rougeMboko)
                }
            }
        }
    }
}
/*************************************************************************************************/
@Composable
fun MbokoBottomBar(navController: NavController, isVendeur: Boolean) {
    val vertMboko = Color(0xFF028342)
    val rougeMboko = Color.Red
    val activeColor = if (isVendeur) rougeMboko else vertMboko

    val items = listOf(
        NavigationItem(
            "Accueil",
            Icons.Default.AccountCircle,
            if (isVendeur) "home_vendeur" else "home_client"
        ),
        NavigationItem("Catégories", Icons.Default.Search, "categories"), // À créer plus tard
        NavigationItem("Profil", Icons.Default.AccountCircle, "profil")   // À créer plus tard
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title, tint = activeColor) },
                label = { Text(item.title, fontSize = 12.sp) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = activeColor,
                    selectedTextColor = activeColor,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = activeColor.copy(alpha = 0.1f)
                )
            )
        }
    }
}

@Composable
fun StatCard(title: String, value: String, color: Color, modifier: Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        border = androidx.compose.foundation.BorderStroke(1.dp, color)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontSize = 14.sp, color = Color.Gray)
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = color)
        }
    }
}

@Composable
fun VendeurProductItem(index: Int, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Produit Stock #$index", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("Stock: 5", color = Color.Gray, fontSize = 12.sp)

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Modifier", fontSize = 11.sp, color = Color.White)
            }
        }
    }
}
@Composable
fun ProductItem(index: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Produit #$index", fontWeight = FontWeight.Bold)
            Text("15 000 FCFA", color = Color(0xFF028342), fontSize = 14.sp)
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Acheter", fontSize = 12.sp, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChooseScreenPreview(){
    val navController = rememberNavController()
    HomeScreenClient(navController = navController)
}