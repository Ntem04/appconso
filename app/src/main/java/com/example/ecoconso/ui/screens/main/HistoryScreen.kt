package com.example.ecoconso.ui.screens.main

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.EcoBottomNavBar
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoBorderColor
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

data class DailyHistoryItem(
    val day: String,
    val kwh: String,
    val cost: String
)

@Composable
fun HistoryScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    val historyList = listOf(
        DailyHistoryItem("Samedi 27 septembre", "12,4 kWh", "5 000 FCFA"),
        DailyHistoryItem("Vendredi 26 septembre", "13,5 kWh", "2 000 FCFA"),
        DailyHistoryItem("Jeudi 25 septembre", "11,8 kWh", "2 500 FCFA"),
        DailyHistoryItem("Mercredi 24 septembre", "14,2 kWh", "1 000 FCFA"),
        DailyHistoryItem("Mardi 23 septembre", "12,6 kWh", "4 000 FCFA"),
        DailyHistoryItem("Lundi 22 septembre", "10,9 kWh", "5 200 FCFA"),
        DailyHistoryItem("Dimanche 21 septembre", "11,4 kWh", "2 000 FCFA")
    )

    Scaffold(
        topBar = { EcoTopAppBar(title = "Historique") },
        bottomBar = { EcoBottomNavBar(currentRoute = currentRoute, onNavigate = onNavigate) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(EcoBackgroundLight)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Month selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(EcoSurfaceWhite)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.ChevronLeft, contentDescription = null, tint = EcoTextSecondary, modifier = Modifier.clickable { })
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = EcoGreenPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Septembre 2025", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                }
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = EcoTextSecondary, modifier = Modifier.clickable { })
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Summary Card
            EcoCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Consommation du mois", fontSize = 13.sp, color = EcoTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("324,6 kWh", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Coût estimé", fontSize = 13.sp, color = EcoTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("50 000 FCFA", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Consommation par jour", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
            Text("Les 7 derniers jours", fontSize = 12.sp, color = EcoTextSecondary, modifier = Modifier.padding(bottom = 12.dp))

            // History List Card
            EcoCard {
                historyList.forEachIndexed { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(item.day, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = EcoTextPrimary)
                            Text(item.kwh, fontSize = 12.sp, color = EcoGreenPrimary, fontWeight = FontWeight.Medium)
                        }
                        Text(item.cost, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                    }
                    if (index < historyList.size - 1) {
                        HorizontalDivider(color = EcoBorderColor)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Les coûts sont estimés selon votre tarif en vigueur.",
                fontSize = 12.sp,
                color = EcoTextMuted
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
