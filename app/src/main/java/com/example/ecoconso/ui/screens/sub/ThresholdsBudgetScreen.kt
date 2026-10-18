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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.example.ecoconso.ui.theme.EcoRedError
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun ThresholdsBudgetScreen(
    onNavigateBack: () -> Unit
) {
    var dailyThreshold by remember { mutableStateOf("15 kWh") }
    var dailyAlertEnabled by remember { mutableStateOf(true) }
    var monthlyBudget by remember { mutableStateOf("55 000 FCFA") }
    var budgetAlertEnabled by remember { mutableStateOf(true) }
    var isSaved by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EcoBackgroundLight)
    ) {
        EcoTopAppBar(title = "Seuils et budget", onBackClick = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text(
                "Définissez vos repères et soyez averti lorsque votre consommation les dépasse.",
                fontSize = 14.sp,
                color = EcoTextSecondary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Daily Threshold Card
            EcoCard {
                Text("Seuil quotidien", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = EcoTextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = dailyThreshold,
                    onValueChange = { dailyThreshold = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = EcoBorderColor)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Alerte de dépassement", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("Dès que le seuil quotidien est dépassé", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                    Switch(
                        checked = dailyAlertEnabled,
                        onCheckedChange = { dailyAlertEnabled = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = EcoSurfaceWhite, checkedTrackColor = EcoGreenPrimary)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Monthly Budget Card
            EcoCard {
                Text("Budget mensuel", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = EcoTextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = monthlyBudget,
                    onValueChange = { monthlyBudget = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = EcoBorderColor)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Alerte de budget", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("À partir de 90 % du budget estimé", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                    Switch(
                        checked = budgetAlertEnabled,
                        onCheckedChange = { budgetAlertEnabled = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = EcoSurfaceWhite, checkedTrackColor = EcoGreenPrimary)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = EcoBorderColor)
                Spacer(modifier = Modifier.height(16.dp))

                Text("Votre repère de septembre", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("50 000 FCFA estimés", fontSize = 13.sp, color = EcoTextSecondary)
                    Text("91 %", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EcoRedError)
                }

                Spacer(modifier = Modifier.height(6.dp))

                LinearProgressIndicator(
                    progress = { 0.91f },
                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color = EcoRedError,
                    trackColor = EcoBorderColor
                )

                Spacer(modifier = Modifier.height(6.dp))
                Text("Il reste 5 000 FCFA avant le budget défini.", fontSize = 12.sp, color = EcoTextSecondary)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Les coûts sont des estimations selon votre tarif. Ils ne remplacent pas votre facture. Les alertes ne coupent pas l'électricité.",
                fontSize = 12.sp,
                color = EcoTextMuted
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { isSaved = true },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
            ) {
                Text("Enregistrer mes seuils", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoSurfaceWhite)
            }

            if (isSaved) {
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(EcoGreenLight)
                        .padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EcoGreenPrimary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Seuils enregistrés", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                            Text("Vos alertes de consommation et de budget sont actives.", fontSize = 12.sp, color = EcoTextSecondary)
                        }
                    }
                }
            }
        }
    }
}
