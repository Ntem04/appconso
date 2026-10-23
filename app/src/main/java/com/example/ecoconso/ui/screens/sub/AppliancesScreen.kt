package com.example.ecoconso.ui.screens.sub
import androidx.compose.material3.MaterialTheme

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.EcoBottomNavBar
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoCategoryProgressItem
import com.example.ecoconso.ui.components.EcoEmptyState
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoBorderColor
import com.example.ecoconso.ui.theme.EcoGreenLight
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoSkyBlue
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary
import androidx.compose.material.icons.filled.Thermostat

data class ApplianceCategory(
    val name: String,
    val description: String,
    val percentage: Int,
    val icon: ImageVector,
    val color: Color
)

@Composable
fun AppliancesScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val categories = emptyList<ApplianceCategory>()

    Scaffold(
        topBar = { EcoTopAppBar(title = "Mes appareils", onBackClick = onNavigateBack) },
        bottomBar = { EcoBottomNavBar(currentRoute = currentRoute, onNavigate = onNavigate) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Données du backend", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Modifier", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Distribution Summary
            EcoCard {
                Text("Répartition estimée", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(12.dp))

                EcoEmptyState("Aucun appareil enregistré.")

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Estimation par catégorie, et non une mesure individuelle de chaque appareil.",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Appliance Category Items List
            EcoCard {
                if (categories.isEmpty()) {
                    EcoEmptyState("Les appareils seront affichés après synchronisation.")
                }
                categories.forEachIndexed { index, cat ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(cat.color.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(cat.icon, contentDescription = null, tint = cat.color, modifier = Modifier.size(22.dp))
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(cat.name, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                                Text(cat.description, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                        Text("${cat.percentage} %", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    }
                    if (index < categories.size - 1) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Ajouter un appareil", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Complétez vos équipements pour affiner votre suivi.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
