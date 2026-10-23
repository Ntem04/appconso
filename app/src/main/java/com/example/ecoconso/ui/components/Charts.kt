package com.example.ecoconso.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoSkyBlue
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextSecondary

data class BarChartItem(
    val label: String,
    val value: Float,
    val isHighlighted: Boolean = false
)

@Composable
fun EcoBarChart(
    items: List<BarChartItem>,
    maxVal: Float = items.maxOfOrNull { it.value }?.takeIf { it > 0f } ?: 100f,
    barColor: Color = EcoGreenPrimary,
    highlightColor: Color = EcoSkyBlue,
    chartHeight: Int = 140
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(chartHeight.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            items.forEach { item ->
                val ratio = (item.value / maxVal).coerceIn(0.05f, 1f)
                val activeColor = if (item.isHighlighted) highlightColor else barColor

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    if (item.isHighlighted) {
                        Text(
                            text = "${item.value}",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = activeColor,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                    Box(
                        modifier = Modifier
                            .width(18.dp)
                            .fillMaxHeight(ratio)
                            .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                            .background(activeColor)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items.forEach { item ->
                Text(
                    text = item.label,
                    fontSize = 11.sp,
                    color = if (item.isHighlighted) EcoGreenPrimary else EcoTextMuted,
                    fontWeight = if (item.isHighlighted) FontWeight.Bold else FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(24.dp)
                )
            }
        }
    }
}

@Composable
fun EcoLineSparkline(
    dataPoints: List<Float> = listOf(30f, 60f, 45f, 75f, 55f, 85f, 65f),
    lineColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.Canvas(modifier = modifier) {
        if (dataPoints.isEmpty()) return@Canvas
        val min = dataPoints.minOrNull() ?: 0f
        val max = dataPoints.maxOrNull() ?: 100f
        val range = (max - min).coerceAtLeast(1f)

        val path = androidx.compose.ui.graphics.Path()
        val width = size.width
        val height = size.height
        val stepX = width / (dataPoints.size - 1).coerceAtLeast(1)

        dataPoints.forEachIndexed { i, pt ->
            val x = i * stepX
            val y = height - ((pt - min) / range * (height - 10.dp.toPx())) - 5.dp.toPx()
            if (i == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }

        drawPath(
            path = path,
            color = lineColor,
            style = androidx.compose.ui.graphics.drawscope.Stroke(
                width = 3.dp.toPx(),
                cap = androidx.compose.ui.graphics.StrokeCap.Round,
                join = androidx.compose.ui.graphics.StrokeJoin.Round
            )
        )
    }
}
