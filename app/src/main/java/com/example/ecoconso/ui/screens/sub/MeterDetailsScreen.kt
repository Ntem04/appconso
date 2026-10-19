package com.example.ecoconso.ui.screens.sub

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ElectricMeter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import com.example.ecoconso.ui.theme.EcoRedError
import com.example.ecoconso.ui.theme.EcoSkyBlue
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun MeterDetailsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAddMeter: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EcoBackgroundLight)
    ) {
        EcoTopAppBar(title = "Mon compteur", onBackClick = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Meter Status Header Card
            EcoCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(EcoGreenLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.ElectricMeter, contentDescription = null, tint = EcoGreenPrimary, modifier = Modifier.size(28.dp))
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Compteur non synchronisé", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                        Text("N° ---", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoGreenPrimary)
                        Text("En attente de données", fontSize = 12.sp, color = EcoTextMuted)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = EcoBorderColor)
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Puissance actuelle", fontSize = 12.sp, color = EcoTextSecondary)
                        Text("0 kW", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Puissance souscrite", fontSize = 12.sp, color = EcoTextSecondary)
                        Text("0 kVA", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Domicile Info Card
            EcoCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Home, contentDescription = null, tint = EcoSkyBlue)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Mon domicile", fontSize = 12.sp, color = EcoTextSecondary)
                            Text("Résidence principale", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                        }
                    }
                    Text("Modifier", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = EcoBorderColor)
                Spacer(modifier = Modifier.height(12.dp))

                Text("Titulaire : ---", fontSize = 13.sp, color = EcoTextPrimary, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Référence d'abonnement", fontSize = 12.sp, color = EcoTextSecondary)
                Text("---", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Actions Buttons
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null, tint = EcoSurfaceWhite)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Synchroniser le compteur", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoSurfaceWhite)
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onNavigateToAddMeter,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Changer de compteur", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Retirer ce compteur", fontSize = 14.sp, color = EcoRedError)
            }
        }
    }
}
