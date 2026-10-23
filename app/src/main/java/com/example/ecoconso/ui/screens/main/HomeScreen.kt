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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.ElectricBolt
import androidx.compose.material.icons.outlined.EnergySavingsLeaf
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecoconso.ui.components.BarChartItem
import com.example.ecoconso.ui.components.EcoBarChart
import com.example.ecoconso.ui.components.EcoBottomNavBar
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoLineSparkline
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoBorderColor
import com.example.ecoconso.ui.theme.EcoGreenLight
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoRedError
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary
import com.example.ecoconso.ui.viewmodels.HomeUiState
import com.example.ecoconso.ui.viewmodels.HomeViewModel
import java.util.Locale

@Composable
fun HomeScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onNavigateToNotifications: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = { EcoBottomNavBar(currentRoute = currentRoute, onNavigate = onNavigate) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            when (val state = uiState) {
                is HomeUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = EcoGreenPrimary
                    )
                }
                is HomeUiState.Error -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Outlined.ElectricBolt, contentDescription = null, tint = EcoRedError, modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(state.message, color = EcoRedError, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.fetchHomeData() }, colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)) {
                            Text("Réessayer")
                        }
                    }
                }
                is HomeUiState.Success -> {
                    val data = state.data
                    val weeklyBarItems = data.weeklyData.map { wd ->
                        BarChartItem(
                            label = wd.day,
                            value = wd.kwh,
                            isHighlighted = false
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 20.dp, vertical = 16.dp)
                    ) {
                        // --- Top Brand Header ---
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.ElectricBolt,
                                    contentDescription = null,
                                    tint = EcoGreenPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "E-Consommation",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                val systemInDark = androidx.compose.foundation.isSystemInDarkTheme()
                                val isDark = com.example.ecoconso.ui.theme.ThemeManager.isDarkModeManual ?: systemInDark

                                IconButton(onClick = { com.example.ecoconso.ui.theme.ThemeManager.toggleDarkMode(!isDark) }) {
                                    Icon(
                                        imageVector = if (isDark) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                                        contentDescription = "Mode Sombre",
                                        tint = MaterialTheme.colorScheme.onBackground
                                    )
                                }

                                IconButton(onClick = onNavigateToNotifications) {
                                    Icon(
                                        imageVector = Icons.Outlined.Notifications,
                                        contentDescription = "Notifications",
                                        tint = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // --- Welcome User Title ---
                        Text(
                            text = "Gardez le contrôle de votre consommation.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Bonjour, Ntem dilanet",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // --- Main Big Card: Consommation du jour (Green Card) ---
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = EcoGreenPrimary),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.ElectricBolt,
                                        contentDescription = null,
                                        tint = EcoSurfaceWhite,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Consommation du jour",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = EcoSurfaceWhite.copy(alpha = 0.9f)
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Column {
                                        Row(verticalAlignment = Alignment.Bottom) {
                                            Text(
                                                text = String.format(Locale.FRANCE, "%.1f", data.totalKwh),
                                                fontSize = 36.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = EcoSurfaceWhite
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "kWh",
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = EcoSurfaceWhite,
                                                modifier = Modifier.padding(bottom = 4.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = "${data.percentVsYesterday} % par rapport à hier",
                                            fontSize = 12.sp,
                                            color = EcoSurfaceWhite.copy(alpha = 0.85f)
                                        )
                                    }

                                    // White Line Sparkline Chart
                                    EcoLineSparkline(
                                        dataPoints = listOf(30f, 60f, 45f, 75f, 55f, 85f, 65f),
                                        lineColor = EcoSurfaceWhite,
                                        modifier = Modifier
                                            .width(130.dp)
                                            .height(60.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // --- Row of 3 Stat Cards ---
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Card 1: Coût estimé
                            EcoCard(modifier = Modifier.weight(1f)) {
                                Column {
                                    Text(
                                        text = "Coût estimé",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = String.format(Locale.FRANCE, "%.2f €", data.estimatedCostFcfa / 1000f),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }

                            // Card 2: Puissance
                            EcoCard(modifier = Modifier.weight(1f)) {
                                Column {
                                    Text(
                                        text = "Puissance",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = String.format(Locale.FRANCE, "%.1f kW", data.activePowerKw),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }

                            // Card 3: Votre abonnement
                            EcoCard(modifier = Modifier.weight(1f)) {
                                Column {
                                    Text(
                                        text = "Votre abonnement",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "${data.subscribedPowerKva} kVA",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // --- Card: Ma consommation (Bar Chart) ---
                        EcoCard {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Ma consommation",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "7 derniers jours",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            EcoBarChart(
                                items = weeklyBarItems,
                                barColor = EcoGreenPrimary,
                                highlightColor = EcoGreenPrimary,
                                chartHeight = 120
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Total de la semaine",
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = String.format(Locale.FRANCE, "%.1f kWh", data.weeklyTotalKwh),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // --- Bottom Eco Tip Banner ---
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(EcoGreenLight)
                                .padding(16.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.EnergySavingsLeaf,
                                    contentDescription = null,
                                    tint = EcoGreenPrimary,
                                    modifier = Modifier.size(28.dp)
                                )
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
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    com.example.ecoconso.ui.theme.EcoConsoTheme {
        HomeScreen(currentRoute = "home", onNavigate = {}, onNavigateToNotifications = {})
    }
}
