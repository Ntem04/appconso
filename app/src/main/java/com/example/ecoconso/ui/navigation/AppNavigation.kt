package com.example.ecoconso.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecoconso.ui.screens.auth.ForgotPasswordScreen
import com.example.ecoconso.ui.screens.auth.LoginScreen
import com.example.ecoconso.ui.screens.auth.OtpVerificationScreen
import com.example.ecoconso.ui.screens.auth.RegisterScreen
import com.example.ecoconso.ui.screens.auth.ResetPasswordScreen
import com.example.ecoconso.ui.screens.main.ConsoDetailScreen
import com.example.ecoconso.ui.screens.main.HistoryScreen
import com.example.ecoconso.ui.screens.main.HomeScreen
import com.example.ecoconso.ui.screens.main.ProfileScreen
import com.example.ecoconso.ui.screens.sub.AboutPrivacyScreen
import com.example.ecoconso.ui.screens.sub.AddMeterScreen
import com.example.ecoconso.ui.screens.sub.AlertDetailScreen
import com.example.ecoconso.ui.screens.sub.AppliancesScreen
import com.example.ecoconso.ui.screens.sub.EcoTipsScreen
import com.example.ecoconso.ui.screens.sub.ExportDataScreen
import com.example.ecoconso.ui.screens.sub.MeterDetailsScreen
import com.example.ecoconso.ui.screens.sub.MeterStatesScreen
import com.example.ecoconso.ui.screens.sub.MonthlyReportScreen
import com.example.ecoconso.ui.screens.sub.NotificationPrefScreen
import com.example.ecoconso.ui.screens.sub.NotificationsScreen
import com.example.ecoconso.ui.screens.sub.PersonalInfoScreen
import com.example.ecoconso.ui.screens.sub.SettingsScreen
import com.example.ecoconso.ui.screens.sub.SupportFaqScreen
import com.example.ecoconso.ui.screens.sub.ThresholdsBudgetScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // --- Auth Flow ---
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") { popUpTo("login") { inclusive = true } } },
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToForgotPassword = { navController.navigate("forgot_password") }
            )
        }

        composable("register") {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = { navController.navigate("otp_verification") }
            )
        }

        composable("forgot_password") {
            ForgotPasswordScreen(
                onNavigateBack = { navController.popBackStack() },
                onSendCode = { navController.navigate("otp_verification") }
            )
        }

        composable("otp_verification") {
            OtpVerificationScreen(
                onNavigateBack = { navController.popBackStack() },
                onOtpValidated = { navController.navigate("reset_password") }
            )
        }

        composable("reset_password") {
            ResetPasswordScreen(
                onNavigateBack = { navController.popBackStack() },
                onPasswordResetDone = { navController.navigate("home") { popUpTo("login") { inclusive = true } } }
            )
        }

        // --- Main Tabs ---
        composable("home") {
            HomeScreen(
                currentRoute = "home",
                onNavigate = { route -> navController.navigate(route) },
                onNavigateToNotifications = { navController.navigate("notifications") }
            )
        }

        composable("conso") {
            ConsoDetailScreen(
                currentRoute = "conso",
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable("history") {
            HistoryScreen(
                currentRoute = "history",
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable("profile") {
            ProfileScreen(
                currentRoute = "profile",
                onNavigate = { route -> navController.navigate(route) },
                onNavigateToPersonalInfo = { navController.navigate("personal_info") },
                onNavigateToMeterDetails = { navController.navigate("meter_details") },
                onNavigateToAppliances = { navController.navigate("appliances") },
                onNavigateToThresholds = { navController.navigate("thresholds") },
                onNavigateToNotifications = { navController.navigate("notifications") },
                onNavigateToSettings = { navController.navigate("settings") },
                onNavigateToSupport = { navController.navigate("support_faq") },
                onNavigateToAbout = { navController.navigate("about_privacy") },
                onLogout = { navController.navigate("login") { popUpTo("home") { inclusive = true } } }
            )
        }

        // --- Sub Features ---
        composable("personal_info") {
            PersonalInfoScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToChangePassword = { navController.navigate("reset_password") }
            )
        }

        composable("meter_details") {
            MeterDetailsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddMeter = { navController.navigate("add_meter") }
            )
        }

        composable("add_meter") {
            AddMeterScreen(
                onNavigateBack = { navController.popBackStack() },
                onMeterConnected = { navController.popBackStack() }
            )
        }

        composable("appliances") {
            AppliancesScreen(
                currentRoute = "profile",
                onNavigate = { route -> navController.navigate(route) },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("thresholds") {
            ThresholdsBudgetScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("alert_detail") {
            AlertDetailScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToConso = { navController.navigate("conso") },
                onNavigateToThresholds = { navController.navigate("thresholds") }
            )
        }

        composable("monthly_report") {
            MonthlyReportScreen(
                currentRoute = "profile",
                onNavigate = { route -> navController.navigate(route) },
                onNavigateToExport = { navController.navigate("export_data") },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("export_data") {
            ExportDataScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("notifications") {
            NotificationsScreen(
                currentRoute = "home",
                onNavigate = { route -> navController.navigate(route) },
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAlertDetail = { navController.navigate("alert_detail") }
            )
        }

        composable("settings") {
            SettingsScreen(
                currentRoute = "profile",
                onNavigate = { route -> navController.navigate(route) },
                onNavigateToNotificationPref = { navController.navigate("notification_pref") },
                onNavigateToThresholds = { navController.navigate("thresholds") },
                onNavigateToExport = { navController.navigate("export_data") },
                onNavigateToAbout = { navController.navigate("about_privacy") },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("notification_pref") {
            NotificationPrefScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToThresholds = { navController.navigate("thresholds") }
            )
        }

        composable("eco_tips") {
            EcoTipsScreen(
                currentRoute = "profile",
                onNavigate = { route -> navController.navigate(route) },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("support_faq") {
            SupportFaqScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPersonalInfo = { navController.navigate("personal_info") }
            )
        }

        composable("about_privacy") {
            AboutPrivacyScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToExport = { navController.navigate("export_data") },
                onNavigateToSupport = { navController.navigate("support_faq") }
            )
        }

        composable("meter_states") {
            MeterStatesScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddMeter = { navController.navigate("add_meter") }
            )
        }
    }
}
