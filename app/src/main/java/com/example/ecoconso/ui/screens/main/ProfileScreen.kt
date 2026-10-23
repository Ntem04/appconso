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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.ElectricMeter
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.EcoBottomNavBar
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoGreenLight
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoRedError
import com.example.ecoconso.ui.theme.EcoSurfaceWhite

@Composable
fun ProfileScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onNavigateToPersonalInfo: () -> Unit,
    onNavigateToMeterDetails: () -> Unit,
    onNavigateToAppliances: () -> Unit,
    onNavigateToThresholds: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToSupport: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = { EcoTopAppBar(title = "Profil") },
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
            // Profile Card Header
            EcoCard {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(EcoGreenPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("ND", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = EcoSurfaceWhite)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text("Ntem dilane", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        Text("jean.dupont@email.fr", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("06 12 34 56 78", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Navigation Items List
            EcoCard {
                ProfileMenuItem(
                    icon = Icons.Default.Person,
                    title = "Mes informations",
                    subtitle = "Coordonnées personnelles",
                    onClick = onNavigateToPersonalInfo
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                ProfileMenuItem(
                    icon = Icons.Default.ElectricMeter,
                    title = "Mon compteur",
                    subtitle = "N° 01234567890123",
                    onClick = onNavigateToMeterDetails
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                ProfileMenuItem(
                    icon = Icons.Default.Devices,
                    title = "Mes appareils",
                    subtitle = "Répartition et équipements",
                    onClick = onNavigateToAppliances
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                ProfileMenuItem(
                    icon = Icons.Default.Tune,
                    title = "Seuils et budget",
                    subtitle = "Alertes de consommation",
                    onClick = onNavigateToThresholds
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                ProfileMenuItem(
                    icon = Icons.Default.Notifications,
                    title = "Notifications",
                    subtitle = "Avis et alertes récents",
                    onClick = onNavigateToNotifications
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                ProfileMenuItem(
                    icon = Icons.Default.Settings,
                    title = "Paramètres",
                    subtitle = "Langue, sobriété et sync",
                    onClick = onNavigateToSettings
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                ProfileMenuItem(
                    icon = Icons.AutoMirrored.Filled.HelpOutline,
                    title = "Aide et support",
                    subtitle = "FAQ et contact client",
                    onClick = onNavigateToSupport
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                ProfileMenuItem(
                    icon = Icons.Default.Info,
                    title = "À propos",
                    subtitle = "Confidentialité et version",
                    onClick = onNavigateToAbout
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Logout Button Item
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onLogout() },
                color = MaterialTheme.colorScheme.surface,
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, tint = EcoRedError)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Se déconnecter", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = EcoRedError)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("E-Consommation · Version 1.0.0", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Vos données restent privées.", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(EcoGreenLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = EcoGreenPrimary, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                Text(text = subtitle, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    com.example.ecoconso.ui.theme.EcoConsoTheme {
        ProfileScreen(
            currentRoute = "profile",
            onNavigate = {},
            onNavigateToPersonalInfo = {},
            onNavigateToMeterDetails = {},
            onNavigateToAppliances = {},
            onNavigateToThresholds = {},
            onNavigateToNotifications = {},
            onNavigateToSettings = {},
            onNavigateToSupport = {},
            onNavigateToAbout = {},
            onLogout = {}
        )
    }
}
