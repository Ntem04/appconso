package com.example.ecoconso.data.models

data class ConsommationTodayResponse(
    val totalKwh: Float,
    val percentVsYesterday: Float,
    val estimatedCostFcfa: Int,
    val activePowerKw: Float,
    val subscribedPowerKva: Int,
    val weeklyTotalKwh: Float,
    val weeklyData: List<WeeklyData>
)

data class WeeklyData(
    val day: String,
    val kwh: Float
)

data class ApplianceResponse(
    val category: String,
    val description: String,
    val percentage: Int
)
