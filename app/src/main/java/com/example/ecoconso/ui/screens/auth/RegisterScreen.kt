package com.example.ecoconso.ui.screens.auth

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.components.EcoTopAppBar
import com.example.ecoconso.ui.theme.EcoBackgroundLight
import com.example.ecoconso.ui.theme.EcoBorderColor
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoRedError
import com.example.ecoconso.ui.theme.EcoSurfaceWhite
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    var fullName by remember { mutableStateOf("Jean Dupont") }
    var phone by remember { mutableStateOf("06 12 34 56 78") }
    var email by remember { mutableStateOf("jean.dupont@email.fr") }
    var password by remember { mutableStateOf("••••••••••") }
    var confirmPassword by remember { mutableStateOf("••••••••") }
    var acceptTerms by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EcoBackgroundLight)
    ) {
        EcoTopAppBar(title = "", onBackClick = onNavigateBack)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Créez votre compte",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = EcoTextPrimary
            )

            Text(
                text = "Rejoignez E-Consommation et suivez votre consommation électrique.",
                fontSize = 14.sp,
                color = EcoTextSecondary,
                modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(EcoSurfaceWhite)
                    .padding(20.dp)
            ) {
                // Nom complet
                Text("Nom complet", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = EcoTextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = EcoTextMuted) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = EcoBorderColor)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Téléphone
                Text("Téléphone", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = EcoTextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = EcoTextMuted) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = EcoBorderColor)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // E-mail
                Text("E-mail", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = EcoTextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = EcoTextMuted) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = EcoBorderColor)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Mot de passe
                Text("Mot de passe", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = EcoTextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = EcoTextMuted) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = EcoBorderColor)
                )
                Text("8 caractères minimum, dont un chiffre.", fontSize = 11.sp, color = EcoTextMuted, modifier = Modifier.padding(top = 2.dp))

                Spacer(modifier = Modifier.height(12.dp))

                // Confirmer mot de passe
                Text("Confirmer le mot de passe", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = EcoTextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = EcoTextMuted) },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EcoGreenPrimary, unfocusedBorderColor = EcoRedError)
                )
                Text("Les mots de passe ne correspondent pas.", fontSize = 11.sp, color = EcoRedError, modifier = Modifier.padding(top = 2.dp))

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = acceptTerms,
                        onCheckedChange = { acceptTerms = it },
                        colors = CheckboxDefaults.colors(checkedColor = EcoGreenPrimary)
                    )
                    Text(
                        text = "J'accepte les conditions d'utilisation et la politique de confidentialité.",
                        fontSize = 12.sp,
                        color = EcoTextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onRegisterSuccess,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EcoGreenPrimary)
                ) {
                    Text("Créer mon compte", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EcoSurfaceWhite)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Déjà un compte ? ", fontSize = 14.sp, color = EcoTextSecondary)
                    Text(
                        text = "Se connecter",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = EcoGreenPrimary,
                        modifier = Modifier.clickable { onNavigateBack() }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
