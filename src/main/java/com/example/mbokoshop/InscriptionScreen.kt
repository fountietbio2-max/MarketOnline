package com.example.mbokoshop

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState // Import pour le scroll
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll // Import pour le scroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun InscriptionScreen(navController: NavController) {

    // États pour les champs de saisie
    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var dateNais by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tel by remember { mutableStateOf("") }
    var pays by remember { mutableStateOf("") }
    var ville by remember { mutableStateOf("") }
    var mdp by remember { mutableStateOf("") }
    var conMdp by remember { mutableStateOf("") }
    var codepostal by remember { mutableStateOf("") }
    var accepteConditions by remember { mutableStateOf(false) }

    // État pour la gestion du scroll
    val scrollState = rememberScrollState()

    val context = LocalContext.current
    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Utilisation de imePadding pour que le clavier ne cache pas les boutons
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                // Activation du défilement vertical ici
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "Logo App",
                modifier = Modifier
                    .width(200.dp) // Réduit un peu pour la cohérence visuelle
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Liste des champs (réutilisant votre style OutlinedTextField)
            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFEFB8C8),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Gray
            )

            OutlinedTextField(
                value = nom,
                onValueChange = { nom = it },
                label = { Text("Nom*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = prenom,
                onValueChange = { prenom = it },
                label = { Text("Prénoms*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = dateNais,
                onValueChange = { dateNais = it },
                label = { Text("Date de naissance*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Adresse email*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = tel,
                onValueChange = { tel = it },
                label = { Text("Téléphone*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = pays,
                onValueChange = { pays = it },
                label = { Text("Pays*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = ville,
                onValueChange = { ville = it },
                label = { Text("Ville*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = codepostal,
                onValueChange = { codepostal = it },
                label = { Text("Code postal ou Quartier*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = mdp,
                onValueChange = { mdp = it },
                label = { Text("Mot de passe*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = conMdp,
                onValueChange = { conMdp = it },
                label = { Text("Confirmation mot de passe*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = accepteConditions,
                    onCheckedChange = { accepteConditions = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF028342)
                    )
                )

                // Création du texte avec une partie stylisée et cliquable
                val annotatedString = buildAnnotatedString {
                    append("*J'accepte les ")

                    // On définit le début de la zone cliquable et stylisée
                    pushStringAnnotation(tag = "CONDITIONS", annotation = "https://votre-lien.com")
                    withStyle(style = SpanStyle(
                        color = Color(0xFFEFB8C8), // Couleur verte
                        textDecoration = TextDecoration.Underline, // Souligné
                        fontWeight = FontWeight.Bold
                    )
                    ) {
                        append("conditions d'utilisation")
                    }
                    pop()
                }

                ClickableText(
                    text = annotatedString,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray),
                    onClick = { offset ->
                        // On vérifie si l'utilisateur a cliqué sur la zone "CONDITIONS"
                        annotatedString.getStringAnnotations(tag = "CONDITIONS", start = offset, end = offset)
                            .firstOrNull()?.let {
                                // Action à effectuer (ouvrir un lien ou une nouvelle page)
                                toast("Ouverture des conditions...")
                            }
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Bouton Inscription
            Button(
                onClick = {
                    if (nom.isBlank() || prenom.isBlank() || dateNais.isBlank() ||
                        email.isBlank() || tel.isBlank() || pays.isBlank() ||
                        ville.isBlank() || codepostal.isBlank() || mdp.isBlank()) {
                        toast("Veuillez remplir tous les champs")
                        return@Button
                    }
                    if (conMdp != mdp) {
                        toast("Les mots de passe ne correspondent pas")
                        return@Button
                    }
                    if (!accepteConditions) {
                        toast("Veuillez accepter les conditions d'utilisation")
                        return@Button
                    }
                    navController.navigate("choose")
                    // Logique d'inscription ici
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
            ) {
                Text(
                    text = "Inscription",
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Ou s'inscrire avec",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Bouton Google
                SocialIconButton(
                    iconRes = R.drawable.google,
                    onClick = { /* TODO: Logique Google Login */ }
                )

                // Bouton Facebook
                SocialIconButton(
                    iconRes = R.drawable.facebook,
                    onClick = { /* TODO: Logique Facebook Login */ }
                )

                // Bouton Instagram
                SocialIconButton(
                    iconRes = R.drawable.apple,
                    onClick = { /* TODO: Logique Apple Login */ }
                )
            }

            // Un peu d'espace en bas pour que le scroll soit plus agréable
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SocialIconButton(iconRes: Int, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.size(55.dp),
        shape = androidx.compose.foundation.shape.CircleShape,
        contentPadding = PaddingValues(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InscriptionScreenPreview(){
    val navController = androidx.navigation.compose.rememberNavController()
    InscriptionScreen(navController = navController)
}