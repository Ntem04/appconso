package com.example.ecoconso.ui.screens.sub

import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.Thermostat
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
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoGreenLight
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoSkyBlue
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

data class TipItemData(
    val title: String,
    val category: String,
    val description: String,
    val icon: ImageVector,
    val iconColor: Color
)

@Composable
fun EcoTipsScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val tipsList = listOf(
        TipItemData(
            title = "Coupez les veilles inutiles",
            category = "Appareils du quotidien",
            description = "Éteignez télévision, console et chargeurs inutilisés avec une multiprise à interrupteur. Gardez branchés les équipements qui doivent fonctionner en continu.",
            icon = Icons.Default.Power,
            iconColor = EcoGreenPrimary
        ),
        TipItemData(
            title = "Lavez avec une charge complète",
            category = "Électroménager · 45 % de la répartition",
            description = "Attendez de remplir le lave-linge sans le surcharger. Choisissez le programme éco lorsque le linge le permet et évitez le prélavage inutile.",
            icon = Icons.Default.Kitchen,
            iconColor = EcoSkyBlue
        ),
        TipItemData(
            title = "Ajustez le chauffage",
            category = "Chauffage · 30 % de la répartition",
            description = "Réglez la température selon l'occupation des pièces. Fermez les fenêtres pendant le chauffage et dégagez les radiateurs.",
            icon = Icons.Default.Thermostat,
            iconColor = EcoGreenPrimary
        ),
        TipItemData(
            title = "Profitez de la lumière naturelle",
            category = "Éclairage · 15 % de la répartition",
            description = "Ouvrez les rideaux en journée et éteignez les lampes en quittant une pièce. Remplacez progressivement les ampoules usées par des LED adaptées.",
            icon = Icons.Default.Lightbulb,
            iconColor = EcoSkyBlue
        )
    )

    Scaffold(
        topBar = { EcoTopAppBar(title = "Conseils économies", onBackClick = onNavigateBack) },
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
            Text("Des gestes à choisir selon vos usages, sans changer tout votre quotidien.", fontSize = 14.sp, color = EcoTextSecondary, modifier = Modifier.padding(bottom = 16.dp))

            tipsList.forEach { tip ->
                EcoCard {
                    Row(verticalAlignment = Alignment.Top) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(tip.iconColor.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(tip.icon, contentDescription = null, tint = tip.iconColor, modifier = Modifier.size(22.dp))
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(tip.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                            Text(tip.category, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = tip.iconColor)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(tip.description, fontSize = 13.sp, color = EcoTextSecondary)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Les effets varient selon vos équipements et vos habitudes.", fontSize = 12.sp, color = EcoTextMuted, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}
