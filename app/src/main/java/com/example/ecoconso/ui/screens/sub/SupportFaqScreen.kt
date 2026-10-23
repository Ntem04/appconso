package com.example.ecoconso.ui.screens.sub
import androidx.compose.material3.MaterialTheme

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Send
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
fun SupportFaqScreen(
    onNavigateBack: () -> Unit,
    onNavigateToPersonalInfo: () -> Unit
) {
    var expandedFaqIndex by remember { mutableStateOf(0) }
    var subject by remember { mutableStateOf("Données du compteur") }
    var messageText by remember { mutableStateOf("Bonjour, mon compteur est associé mais les données ne se mettent plus à jour depuis ce matin. Pouvez-vous m'aider ?") }
    var isSent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        EcoTopAppBar(title = "Aide et contact", onBackClick = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text("Questions fréquentes", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.height(12.dp))

            EcoCard {
                // FAQ Item 1
                Column(modifier = Modifier.fillMaxWidth().clickable { expandedFaqIndex = if (expandedFaqIndex == 0) -1 else 0 }.padding(vertical = 8.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Mes données ne se mettent pas à jour", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                        Icon(if (expandedFaqIndex == 0) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    if (expandedFaqIndex == 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Vérifiez votre connexion Internet, puis ouvrez Mon compteur et choisissez Synchroniser. Consultez l'heure de la dernière mise à jour.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                // FAQ Item 2
                Column(modifier = Modifier.fillMaxWidth().clickable { expandedFaqIndex = if (expandedFaqIndex == 1) -1 else 1 }.padding(vertical = 8.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Où trouver mon numéro de compteur ?", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                        Icon(if (expandedFaqIndex == 1) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    if (expandedFaqIndex == 1) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Le numéro se situe sur la face avant du compteur électrique Linky ou électronique, près du code-barres.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                // FAQ Item 3
                Column(modifier = Modifier.fillMaxWidth().clickable { expandedFaqIndex = if (expandedFaqIndex == 2) -1 else 2 }.padding(vertical = 8.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Pourquoi le coût est-il estimé ?", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                        Icon(if (expandedFaqIndex == 2) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    if (expandedFaqIndex == 2) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Le calcul repose sur les données kwh relevées et votre tarif souscrit, hors taxes locales et abonnement annuel.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Nous contacter", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Text("Décrivez le problème rencontré. La réponse sera envoyée à l'adresse e-mail de votre compte.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(bottom = 12.dp))

            EcoCard {
                Text("Objet", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = MaterialTheme.colorScheme.outline)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Votre message", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.fillMaxWidth().height(120.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = MaterialTheme.colorScheme.outline)
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text("N'indiquez jamais votre mot de passe ni votre code de vérification dans ce message.", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { isSent = true },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
                ) {
                    Icon(Icons.Default.Send, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Envoyer ma demande", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
                }

                if (isSent) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EcoGreenPrimary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Votre message a été transmis à notre support.", fontSize = 12.sp, color = EcoGreenPrimary, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Retrouvez vos coordonnées dans Informations personnelles.",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = EcoGreenPrimary,
                modifier = Modifier.align(Alignment.CenterHorizontally).clickable { onNavigateToPersonalInfo() }
            )
        }
    }
}
