package com.example.ecoconso.ui.screens.sub

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricMeter
import androidx.compose.material.icons.filled.SignalCellularConnectedNoInternet4Bar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoGreenLight
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoRedError
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun MeterStatesScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAddMeter: () -> Unit
) {
    var selectedStateTab by remember { mutableIntStateOf(0) }
    val stateTabs = listOf("Chargement", "Sans compteur", "Erreur réseau")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EcoBackgroundLight)
    ) {
        EcoTopAppBar(title = "Ma consommation", onBackClick = onNavigateBack)

        TabRow(
            selectedTabIndex = selectedStateTab,
            containerColor = EcoSurfaceWhite,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedStateTab]),
                    color = EcoGreenPrimary
                )
            }
        ) {
            stateTabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedStateTab == index,
                    onClick = { selectedStateTab = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 12.sp,
                            fontWeight = if (selectedStateTab == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedStateTab == index) EcoGreenPrimary else EcoTextSecondary
                        )
                    }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            when (selectedStateTab) {
                0 -> {
                    // Loading State
                    EcoCard {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(color = EcoGreenPrimary, modifier = Modifier.size(48.dp))
                            Spacer(modifier = Modifier.height(20.dp))
                            Text("Chargement de vos données...", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("Veuillez patienter, nous récupérons votre consommation électrique.", fontSize = 13.sp, color = EcoTextSecondary, textAlign = TextAlign.Center)
                        }
                    }
                }
                1 -> {
                    // Empty Meter State
                    EcoCard {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier.size(60.dp).clip(CircleShape).background(EcoGreenLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.ElectricMeter, contentDescription = null, tint = EcoGreenPrimary, modifier = Modifier.size(32.dp))
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Aucune donnée disponible", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("Votre compteur n'est pas encore relié.\nAjoutez-le pour suivre votre consommation.", fontSize = 13.sp, color = EcoTextSecondary, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(20.dp))
                            Button(
                                onClick = onNavigateToAddMeter,
                                modifier = Modifier.fillMaxWidth().height(48.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
                            ) {
                                Text("Relier mon compteur", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoSurfaceWhite)
                            }
                        }
                    }
                }
                2 -> {
                    // Network Error State
                    EcoCard {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier.size(60.dp).clip(CircleShape).background(EcoRedError.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.SignalCellularConnectedNoInternet4Bar, contentDescription = null, tint = EcoRedError, modifier = Modifier.size(32.dp))
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Impossible de charger les données", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("Vérifiez votre connexion Internet, puis réessayez. Vos données sont conservées.", fontSize = 13.sp, color = EcoTextSecondary, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedButton(
                                onClick = { },
                                modifier = Modifier.fillMaxWidth().height(48.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Réessayer", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Text("Pas de données, pas de dépense inutile : une interface légère, même en attente.", fontSize = 12.sp, color = EcoTextMuted, textAlign = TextAlign.Center)
        }
    }
}
