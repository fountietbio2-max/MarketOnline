package com.example.mbokoshop


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun ConnexionScreen(navController : NavController){

    var mdp by remember { mutableStateOf(value = "") }
    var email by remember { mutableStateOf(value = "") }

    val context = LocalContext.current
    fun toast(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        //Logo
        Spacer(modifier = Modifier.height(100.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .align(Alignment.CenterHorizontally)
        )


        Spacer(modifier = Modifier.height(24.dp))

        // Zones de saisie
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Adresse email") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, // ou la couleur de votre choix
                unfocusedContainerColor = Color(0xFFEFB8C8),
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(36.dp))

        // Zones de saisie
        TextField(
            value = mdp,
            onValueChange = { mdp = it },
            label = { Text("Mot de passe") },
            modifier = Modifier.fillMaxWidth(),
              colors = androidx . compose . material3 . TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // ou la couleur de votre choix
            unfocusedContainerColor = Color(0xFFEFB8C8),
            focusedIndicatorColor = Color.Red,
            unfocusedIndicatorColor = Color.Gray)

        )

        Spacer(modifier = Modifier.height(50.dp))

        // Bouton
        Button(
            //verification des saisies
            onClick = {
                if (email.isBlank()) {
                    toast("Veuillez saisir votre email")
                    return@Button
                }

                if (mdp.isBlank()) {
                    toast("Veuillez saisir votre mot de passe")
                    return@Button
                }
                navController.navigate("choose")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),

        ) {
            Text(
                text = "Connexion",
                color = Color.White,
                fontSize = 19.sp
            )
        }


        Row(horizontalArrangement = Arrangement.Start){
            Button(
                onClick = {navController.navigate("inscription") {}},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black

                )) {
                Text(text = "Pas de compte ?")
            }
            Spacer(modifier = Modifier.fillMaxWidth())
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row  (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            //L'image facebook
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.width(56.dp))

            //L'image Google
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.width(56.dp))

            //L'image apple
            Image(
                painter = painterResource(id = R.drawable.apple),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Copyright
        Text( text = "Copyright © 2026 Mboko market",
            modifier = Modifier.size(width = 400.dp, height = 80.dp),
            fontSize = 20.sp,
            color = Color.Red,
            textAlign = TextAlign.Center
        )


    }

}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConnexionScreenPreview(){
    val navController = androidx.navigation.compose.rememberNavController()
    ConnexionScreen(navController = navController)
}
