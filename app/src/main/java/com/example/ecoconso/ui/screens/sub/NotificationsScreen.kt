package com.example.ecoconso.ui.screens.sub

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Warning
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
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoBorderColor
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoRedError
import com.example.ecoconso.ui.theme.EcoSkyBlue
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

data class NotificationItemData(
    val title: String,
    val description: String,
    val time: String,
    val icon: ImageVector,
    val iconColor: Color,
    val isUnread: Boolean = false
)

@Composable
fun NotificationsScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToAlertDetail: () -> Unit
) {
    val notificationList = listOf(
        NotificationItemData(
            title = "Consommation maîtrisée",
            description = "Votre consommation a diminué de 8 % par rapport à hier. Continuez ainsi !",
            time = "Il y a 10 min",
            icon = Icons.Default.CheckCircle,
            iconColor = EcoGreenPrimary,
            isUnread = true
        ),
        NotificationItemData(
            title = "Alerte de surconsommation",
            description = "Vous avez dépassé votre seuil quotidien de 15 kWh. Vérifiez vos appareils.",
            time = "Il y a 1 h",
            icon = Icons.Default.Warning,
            iconColor = EcoRedError,
            isUnread = true
        ),
        NotificationItemData(
            title = "Maintenance du réseau",
            description = "Une intervention est prévue le 30 septembre, de 9 h à 11 h, dans votre secteur.",
            time = "Hier, 16:30",
            icon = Icons.Default.Engineering,
            iconColor = EcoSkyBlue
        ),
        NotificationItemData(
            title = "Conseil économie",
            description = "Débranchez les appareils en veille : un petit geste pour réduire votre facture.",
            time = "Hier, 09:00",
            icon = Icons.Default.Lightbulb,
            iconColor = EcoGreenPrimary
        ),
        NotificationItemData(
            title = "Rapport mensuel disponible",
            description = "Votre bilan de septembre est prêt. Retrouvez-le dans votre historique.",
            time = "26 sept., 08:00",
            icon = Icons.Default.Assessment,
            iconColor = EcoSkyBlue
        )
    )

    Scaffold(
        topBar = { EcoTopAppBar(title = "Notifications", onBackClick = onNavigateBack) },
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("2 nouvelles notifications", fontSize = 14.sp, color = EcoTextSecondary, fontWeight = FontWeight.Medium)
                Text("Tout marquer comme lu", fontSize = 13.sp, color = EcoGreenPrimary, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { })
            }

            Spacer(modifier = Modifier.height(16.dp))

            EcoCard {
                notificationList.forEachIndexed { index, notif ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (notif.title == "Alerte de surconsommation") {
                                    onNavigateToAlertDetail()
                                }
                            }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(notif.iconColor.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(notif.icon, contentDescription = null, tint = notif.iconColor, modifier = Modifier.size(20.dp))
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(notif.title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoTextPrimary)
                                if (notif.isUnread) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .clip(CircleShape)
                                            .background(EcoGreenPrimary)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(notif.description, fontSize = 12.sp, color = EcoTextSecondary)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(notif.time, fontSize = 11.sp, color = EcoTextMuted)
                        }
                    }
                    if (index < notificationList.size - 1) {
                        HorizontalDivider(color = EcoBorderColor)
                    }
                }
            }
        }
    }
}
