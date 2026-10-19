package com.example.ecoconso.ui.screens.main

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.BarChartItem
import com.example.ecoconso.ui.components.EcoBarChart
import com.example.ecoconso.ui.components.EcoBottomNavBar
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoCategoryProgressItem
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoRedError
import com.example.ecoconso.ui.theme.EcoSkyBlue
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun ConsoDetailScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val timeTabs = listOf("Jour", "Semaine", "Mois", "Année")

    val hourlyData = listOf(
        BarChartItem("00h", 0f),
        BarChartItem("04h", 0f),
        BarChartItem("08h", 0f),
        BarChartItem("12h", 0f),
        BarChartItem("16h", 0f, isHighlighted = true),
        BarChartItem("20h", 0f),
        BarChartItem("24h", 0f)
    )

    Scaffold(
        topBar = { EcoTopAppBar(title = "Détails de consommation") },
        bottomBar = { EcoBottomNavBar(currentRoute = currentRoute, onNavigate = onNavigate) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(EcoBackgroundLight)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Tab Row
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = EcoSurfaceWhite,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = EcoGreenPrimary
                    )
                }
            ) {
                timeTabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTabIndex == index) EcoGreenPrimary else EcoTextSecondary
                            )
                        }
                    )
                }
            }

            Column(modifier = Modifier.padding(20.dp)) {
                // Date selector row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "Précédent",
                        tint = EcoTextSecondary,
                        modifier = Modifier.clickable { }
                    )
                    Text(
                        text = "Samedi 27 septembre 2025",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = EcoTextPrimary
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Suivant",
                        tint = EcoTextSecondary,
                        modifier = Modifier.clickable { }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Consumption Total Card
                EcoCard {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Consommation totale", fontSize = 13.sp, color = EcoTextSecondary)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("12,4 kWh", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(EcoGreenPrimary.copy(alpha = 0.12f))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text("0 % vs hier", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    EcoBarChart(items = hourlyData, chartHeight = 150)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Appliance Breakdown Card
                EcoCard {
                    Text("Répartition par appareil", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                    Spacer(modifier = Modifier.height(16.dp))

                    EcoCategoryProgressItem("Électroménager", 45, EcoGreenPrimary)
                    EcoCategoryProgressItem("Chauffage", 30, EcoSkyBlue)
                    EcoCategoryProgressItem("Éclairage", 15, EcoGreenPrimary.copy(alpha = 0.7f))
                    EcoCategoryProgressItem("Autres", 10, EcoTextMuted)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Live Counter Footer Banner
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(EcoSurfaceWhite)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = EcoSkyBlue, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Données du compteur · Mise à jour à 14:32",
                        fontSize = 12.sp,
                        color = EcoTextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConsoDetailScreenPreview() {
    com.example.ecoconso.ui.theme.EcoConsoTheme {
        ConsoDetailScreen(currentRoute = "conso", onNavigate = {})
    }
}
