package com.example.ecoconso.ui.screens.sub

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoBorderColor
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun NotificationPrefScreen(
    onNavigateBack: () -> Unit,
    onNavigateToThresholds: () -> Unit
) {
    var alertConso by remember { mutableStateOf(true) }
    var maintenanceReseau by remember { mutableStateOf(true) }
    var rapportsMensuels by remember { mutableStateOf(true) }
    var conseilsEconomies by remember { mutableStateOf(true) }

    var pushEnabled by remember { mutableStateOf(true) }
    var emailEnabled by remember { mutableStateOf(true) }
    var smsEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EcoBackgroundLight)
    ) {
        EcoTopAppBar(title = "Préférences de notifications", onBackClick = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text("Choisissez les informations que vous souhaitez recevoir.", fontSize = 14.sp, color = EcoTextSecondary, modifier = Modifier.padding(bottom = 16.dp))

            Text("Types de notifications", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
            Spacer(modifier = Modifier.height(12.dp))

            EcoCard {
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Alertes de consommation", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("Dépassement du seuil ou du budget", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                    Switch(checked = alertConso, onCheckedChange = { alertConso = it }, colors = SwitchDefaults.colors(checkedThumbColor = EcoSurfaceWhite, checkedTrackColor = EcoGreenPrimary))
                }
                HorizontalDivider(color = EcoBorderColor)

                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Maintenance du réseau", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("Interventions annoncées dans votre secteur", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                    Switch(checked = maintenanceReseau, onCheckedChange = { maintenanceReseau = it }, colors = SwitchDefaults.colors(checkedThumbColor = EcoSurfaceWhite, checkedTrackColor = EcoGreenPrimary))
                }
                HorizontalDivider(color = EcoBorderColor)

                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Rapports mensuels", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("Un message lorsque votre bilan est disponible", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                    Switch(checked = rapportsMensuels, onCheckedChange = { rapportsMensuels = it }, colors = SwitchDefaults.colors(checkedThumbColor = EcoSurfaceWhite, checkedTrackColor = EcoGreenPrimary))
                }
                HorizontalDivider(color = EcoBorderColor)

                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Conseils économies", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("Des gestes simples, sans publicité", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                    Switch(checked = conseilsEconomies, onCheckedChange = { conseilsEconomies = it }, colors = SwitchDefaults.colors(checkedThumbColor = EcoSurfaceWhite, checkedTrackColor = EcoGreenPrimary))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Canaux de réception", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
            Spacer(modifier = Modifier.height(12.dp))

            EcoCard {
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Sur mon téléphone", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("Notifications de l'application", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                    Switch(checked = pushEnabled, onCheckedChange = { pushEnabled = it }, colors = SwitchDefaults.colors(checkedThumbColor = EcoSurfaceWhite, checkedTrackColor = EcoGreenPrimary))
                }
                HorizontalDivider(color = EcoBorderColor)

                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Par e-mail", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("jean.dupont@email.fr", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                    Switch(checked = emailEnabled, onCheckedChange = { emailEnabled = it }, colors = SwitchDefaults.colors(checkedThumbColor = EcoSurfaceWhite, checkedTrackColor = EcoGreenPrimary))
                }
                HorizontalDivider(color = EcoBorderColor)

                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Par SMS", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                        Text("06 12 34 56 78", fontSize = 12.sp, color = EcoTextSecondary)
                    }
                    Switch(checked = smsEnabled, onCheckedChange = { smsEnabled = it }, colors = SwitchDefaults.colors(checkedThumbColor = EcoSurfaceWhite, checkedTrackColor = EcoGreenPrimary))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Seuls les types activés sont envoyés sur les canaux choisis. Les messages restent consultables dans l'application.", fontSize = 12.sp, color = EcoTextMuted)

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onNavigateBack,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
            ) {
                Text("Enregistrer mes préférences", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoSurfaceWhite)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Gérer les seuils et le budget",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = EcoGreenPrimary,
                modifier = Modifier.align(Alignment.CenterHorizontally).clickable { onNavigateToThresholds() }
            )
        }
    }
}
