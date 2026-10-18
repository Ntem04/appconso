package com.example.ecoconso.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoBorderColor
import com.example.ecoconso.ui.theme.EcoGreenLight
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoSkyBlue
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun ForgotPasswordScreen(
    onNavigateBack: () -> Unit,
    onSendCode: () -> Unit
) {
    var input by remember { mutableStateOf("jean.dupont@email.fr") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EcoBackgroundLight)
    ) {
        EcoTopAppBar(title = "Récupérer mon compte", onBackClick = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(EcoGreenLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LockReset,
                    contentDescription = null,
                    tint = EcoGreenPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Mot de passe oublié ?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = EcoTextPrimary
            )

            Text(
                text = "Indiquez le téléphone ou l'e-mail utilisé pour votre compte. Nous vous enverrons un code de vérification.",
                fontSize = 14.sp,
                color = EcoTextSecondary,
                modifier = Modifier.padding(top = 6.dp, bottom = 20.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(EcoSurfaceWhite)
                    .padding(20.dp)
            ) {
                Text("Téléphone ou e-mail", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = EcoTextSecondary)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = EcoTextMuted) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = EcoBorderColor)
                )
                Text("Le code sera envoyé à cette adresse e-mail.", fontSize = 12.sp, color = EcoGreenPrimary, modifier = Modifier.padding(top = 4.dp))

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onSendCode,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
                ) {
                    Text("Envoyer le code de vérification", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = EcoSurfaceWhite)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Retour à la connexion",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = EcoTextSecondary,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable { onNavigateBack() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Explanation Section
            EcoCard {
                Text("Comment ça marche ?", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(EcoGreenPrimary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("1", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Recevez votre code", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("Consultez votre boîte e-mail ou vos SMS.", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(EcoSkyBlue.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("2", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EcoSkyBlue)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Choisissez un nouveau mot de passe", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("Après validation du code reçu.", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Si le message n'arrive pas, vérifiez vos courriers indésirables ou votre numéro de téléphone.",
                fontSize = 12.sp,
                color = EcoTextMuted
            )
        }
    }
}
