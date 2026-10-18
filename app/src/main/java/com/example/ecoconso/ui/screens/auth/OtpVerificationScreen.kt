package com.example.ecoconso.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun OtpVerificationScreen(
    onNavigateBack: () -> Unit,
    onOtpValidated: () -> Unit
) {
    val otpDigits = listOf("4", "8", "2", "9", "1", "6")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EcoBackgroundLight)
    ) {
        EcoTopAppBar(title = "Vérification du compte", onBackClick = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text(
                text = "Vérifiez votre compte",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = EcoTextPrimary
            )

            Text(
                text = "Pour terminer votre inscription, saisissez le code à 6 chiffres envoyé à :\njean.dupont@email.fr",
                fontSize = 14.sp,
                color = EcoTextSecondary,
                modifier = Modifier.padding(top = 6.dp, bottom = 24.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(EcoSurfaceWhite)
                    .padding(20.dp)
            ) {
                Text("Code reçu", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = EcoTextSecondary)
                Spacer(modifier = Modifier.height(12.dp))

                // OTP Digits Display
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    otpDigits.forEach { digit ->
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(EcoBackgroundLight)
                                .border(1.5.dp, EcoGreenPrimary, RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = digit,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = EcoTextPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Validated Status Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(EcoGreenLight)
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EcoGreenPrimary)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Code validé", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                            Text("Votre adresse e-mail est vérifiée. Votre compte est prêt.", fontSize = 12.sp, color = EcoTextSecondary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onOtpValidated,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
                ) {
                    Text("Continuer vers mon tableau de bord", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = EcoSurfaceWhite)
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(46.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Renvoyer le code", fontSize = 14.sp, color = EcoGreenPrimary)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Vous n'avez pas reçu le code ? Vérifiez les indésirables avant de demander un nouvel envoi.",
                fontSize = 12.sp,
                color = EcoTextMuted
            )

            Spacer(modifier = Modifier.height(20.dp))

            EcoCard {
                Text("Un contact à corriger ?", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Revenez à l'inscription pour modifier votre téléphone ou votre adresse e-mail.", fontSize = 12.sp, color = EcoTextSecondary)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Modifier mes coordonnées",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = EcoGreenPrimary,
                    modifier = Modifier.clickable { onNavigateBack() }
                )
            }
        }
    }
}
