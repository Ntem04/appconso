package com.example.ecoconso.ui.screens.main

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
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.BarChartItem
import com.example.ecoconso.ui.components.EcoBarChart
import com.example.ecoconso.ui.components.EcoBottomNavBar
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoStatCard
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoGreenLight
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoSkyBlue
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun HomeScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onNavigateToNotifications: () -> Unit
) {
    val weeklyData = listOf(
        BarChartItem("Lun", 10.9f),
        BarChartItem("Mar", 12.6f),
        BarChartItem("Mer", 14.2f),
        BarChartItem("Jeu", 11.8f),
        BarChartItem("Ven", 13.5f),
        BarChartItem("Sam", 12.4f, isHighlighted = true),
        BarChartItem("Dim", 11.4f)
    )

    Scaffold(
        bottomBar = { EcoBottomNavBar(currentRoute = currentRoute, onNavigate = onNavigate) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(EcoBackgroundLight)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "E-Consommation",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = EcoGreenPrimary
                    )
                    Text(
                        text = "Bonjour, Ntem dilane",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = EcoTextPrimary
                    )
                }

                IconButton(onClick = onNavigateToNotifications) {
                    BadgedBox(
                        badge = { Badge { Text("2") } }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = EcoTextPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Consommation du jour Card
            EcoStatCard(
                title = "Consommation du jour",
                value = "12,4 kWh",
                badgeText = "8 % par rapport à hier",
                isPositiveChange = true,
                icon = Icons.Default.ElectricBolt,
                iconTint = EcoGreenPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Coût estimé & Puissance row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                EcoStatCard(
                    modifier = Modifier.weight(1f),
                    title = "Coût estimé",
                    value = "2 850 FCFA",
                    subtitle = "Tarif en vigueur",
                    icon = Icons.Default.Bolt,
                    iconTint = EcoSkyBlue
                )

                EcoStatCard(
                    modifier = Modifier.weight(1f),
                    title = "Puissance",
                    value = "3,2 kW",
                    subtitle = "Compteur actif",
                    icon = Icons.Default.Speed,
                    iconTint = EcoGreenPrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Chart Card (7 derniers jours)
            EcoCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Ma consommation", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                        Text("7 derniers jours", fontSize = 12.sp, color = EcoTextSecondary)
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = EcoGreenLight
                    ) {
                        Text(
                            text = "Abonnement: 6 kVA",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = EcoGreenPrimary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                EcoBarChart(items = weeklyData, chartHeight = 130)

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(EcoBackgroundLight)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total de la semaine", fontSize = 13.sp, color = EcoTextSecondary)
                    Text("86,8 kWh", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Eco Tip Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(EcoGreenLight)
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(EcoGreenPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = EcoSurfaceWhite,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Un petit geste, une grande économie",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = EcoGreenPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Éteignez vos appareils en veille pour réduire votre consommation.",
                            fontSize = 12.sp,
                            color = EcoTextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
