package com.example.ecoconso.ui.screens.sub

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.BarChartItem
import com.example.ecoconso.ui.components.EcoBarChart
import com.example.ecoconso.ui.components.EcoBottomNavBar
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoCategoryProgressItem
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoBorderColor
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoSkyBlue
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun MonthlyReportScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onNavigateToExport: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val monthlyData = listOf(
        BarChartItem("Août", 0f),
        BarChartItem("Sept", 0f, isHighlighted = true)
    )

    Scaffold(
        topBar = { EcoTopAppBar(title = "Rapport mensuel", onBackClick = onNavigateBack) },
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
            Text("Septembre 2025", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)

            Spacer(modifier = Modifier.height(16.dp))

            // Main Summary Card
            EcoCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Consommation", fontSize = 13.sp, color = EcoTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("0 kWh", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Coût estimé", fontSize = 13.sp, color = EcoTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("0 FCFA", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Coût en FCFA · Estimation, hors valeur de facture", fontSize = 11.sp, color = EcoTextMuted)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Monthly Comparison Chart Card
            EcoCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Évolution mensuelle", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                    Text("0 %", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                }

                Spacer(modifier = Modifier.height(16.dp))

                EcoBarChart(items = monthlyData, chartHeight = 120)

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = EcoBorderColor)
                Spacer(modifier = Modifier.height(12.dp))

                Text("0 kWh d'écart par rapport au mois précédent.", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = EcoGreenPrimary)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Estimated Category Breakdown
            EcoCard {
                Text("Répartition estimée", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                Spacer(modifier = Modifier.height(12.dp))

                EcoCategoryProgressItem("Électroménager", 0, EcoGreenPrimary)
                EcoCategoryProgressItem("Chauffage", 0, EcoSkyBlue)
                EcoCategoryProgressItem("Éclairage", 0, EcoGreenPrimary.copy(alpha = 0.8f))
                EcoCategoryProgressItem("Autres", 0, EcoTextMuted)

                Spacer(modifier = Modifier.height(10.dp))
                Text("Parts indicatives par catégorie d'appareils.", fontSize = 11.sp, color = EcoTextMuted)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onNavigateToExport,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
            ) {
                Icon(Icons.Default.Download, contentDescription = null, tint = EcoSurfaceWhite)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Exporter ce rapport", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoSurfaceWhite)
            }
        }
    }
}
