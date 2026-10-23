package com.example.ecoconso.ui.screens.sub
import androidx.compose.material3.MaterialTheme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.EcoCard
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoBorderColor
import com.example.ecoconso.ui.theme.EcoGreenLight
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun ExportDataScreen(
    onNavigateBack: () -> Unit
) {
    var selectedFormat by remember { mutableStateOf("PDF") }
    var period by remember { mutableStateOf("1 – 30 septembre 2025") }
    var isDownloaded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        EcoTopAppBar(title = "Exporter mes données", onBackClick = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text("Conservez votre suivi ou analysez vos relevés dans un tableur.", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(bottom = 16.dp))

            EcoCard {
                Text("Période à exporter", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = period,
                    onValueChange = { period = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = MaterialTheme.colorScheme.outline)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Choisir un format", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // PDF Format Card
                    val isPdf = selectedFormat == "PDF"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isPdf) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimary)
                            .border(1.5.dp, if (isPdf) EcoGreenPrimary else MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                            .clickable { selectedFormat = "PDF" }
                            .padding(12.dp)
                    ) {
                        Column {
                            Text("PDF", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if (isPdf) EcoGreenPrimary else MaterialTheme.colorScheme.onSurface)
                            Text("Bilan et graphiques", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }

                    // CSV Format Card
                    val isCsv = selectedFormat == "CSV"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isCsv) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimary)
                            .border(1.5.dp, if (isCsv) EcoGreenPrimary else MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                            .clickable { selectedFormat = "CSV" }
                            .padding(12.dp)
                    ) {
                        Column {
                            Text("CSV", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if (isCsv) EcoGreenPrimary else MaterialTheme.colorScheme.onSurface)
                            Text("Relevés pour tableur", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Summary Card
            EcoCard {
                Text("Résumé de l'export", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Compteur", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Aucun compteur enregistré", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Consommation", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("324,6 kWh", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Coût estimé", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("50 000 FCFA", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                Spacer(modifier = Modifier.height(12.dp))

                Text("Le PDF inclut le bilan, la comparaison mensuelle et la répartition estimée. Les coûts ne valent pas facture.", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { isDownloaded = true },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
            ) {
                Icon(Icons.Default.Download, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Télécharger le $selectedFormat", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
            }

            if (isDownloaded) {
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EcoGreenPrimary)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Téléchargement terminé", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EcoGreenPrimary)
                            Text("E-Consommation_2025-09.pdf a été enregistré sur votre appareil.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("Ce fichier contient vos données de consommation. Partagez-le uniquement avec les personnes choisies.", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        }
    }
}
