package com.example.ecoconso.ui.components
import androidx.compose.material3.MaterialTheme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoconso.ui.theme.EcoBorderColor
import com.example.ecoconso.ui.theme.EcoGreenPrimary
import com.example.ecoconso.ui.theme.EcoSkyBlue
import com.example.ecoconso.ui.theme.EcoTextMuted
import com.example.ecoconso.ui.theme.EcoTextPrimary
import com.example.ecoconso.ui.theme.EcoTextSecondary

data class BarChartItem(
    val label: String,
    val value: Float,
    val isHighlighted: Boolean = false
)

data class DonutSegment(
    val percentage: Float,
    val color: Color
)

@Composable
fun EcoBarChart(
    items: List<BarChartItem>,
    maxVal: Float = items.maxOfOrNull { it.value }?.takeIf { it > 0f } ?: 100f,
    barColor: Color = EcoGreenPrimary,
    highlightColor: Color = EcoSkyBlue,
    chartHeight: Int = 140
) {
    if (items.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(chartHeight.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Aucune donnée disponible pour l'instant",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        return
    }

    val allZero = items.all { it.value <= 0f }

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
                val ratio = if (maxVal > 0f && item.value > 0f) {
                    (item.value / maxVal).coerceIn(0.05f, 1f)
                } else {
                    0f
                }
                val activeColor = if (item.isHighlighted) highlightColor else barColor

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    if (item.isHighlighted && item.value > 0f) {
                        Text(
                            text = "${item.value}",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = activeColor,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                    if (ratio > 0f) {
                        Box(
                            modifier = Modifier
                                .width(18.dp)
                                .fillMaxHeight(ratio)
                                .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                                .background(activeColor)
                        )
                    } else {
                        // Marqueur discret pour barre vide / 0 kWh
                        Box(
                            modifier = Modifier
                                .width(18.dp)
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.35f))
                        )
                    }
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
                    color = if (item.isHighlighted && !allZero) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (item.isHighlighted && !allZero) FontWeight.Bold else FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(24.dp)
                )
            }
        }
    }
}

@Composable
fun EcoLineSparkline(
    dataPoints: List<Float> = emptyList(),
    lineColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        if (dataPoints.isEmpty() || dataPoints.all { it <= 0f }) {
            // Ligne de base subtile pour graphe initialisé à vide
            val y = size.height * 0.75f
            drawLine(
                color = lineColor.copy(alpha = 0.35f),
                start = androidx.compose.ui.geometry.Offset(0f, y),
                end = androidx.compose.ui.geometry.Offset(size.width, y),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round
            )
            return@Canvas
        }
        val min = dataPoints.minOrNull() ?: 0f
        val max = dataPoints.maxOrNull() ?: 100f
        val range = (max - min).coerceAtLeast(1f)

        val path = Path()
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
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}

// --- Curved Line Area Chart for ConsoDetailScreen ---
@Composable
fun EcoSmoothAreaChart(
    labels: List<String>,
    dataPoints: List<Float>,
    primaryColor: Color = EcoGreenPrimary,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height

                // Draw background horizontal grid lines
                val gridLines = 3
                for (i in 0..gridLines) {
                    val y = height * (i.toFloat() / gridLines)
                    drawLine(
                        color = Color.LightGray.copy(alpha = 0.4f),
                        start = androidx.compose.ui.geometry.Offset(0f, y),
                        end = androidx.compose.ui.geometry.Offset(width, y),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                if (dataPoints.isEmpty() || dataPoints.all { it == 0f }) {
                    // Empty state line
                    val y = height * 0.8f
                    drawLine(
                        color = primaryColor.copy(alpha = 0.3f),
                        start = androidx.compose.ui.geometry.Offset(0f, y),
                        end = androidx.compose.ui.geometry.Offset(width, y),
                        strokeWidth = 2.dp.toPx()
                    )
                    return@Canvas
                }

                val min = 0f
                val max = (dataPoints.maxOrNull() ?: 100f).coerceAtLeast(10f)
                val range = max - min
                val stepX = width / (dataPoints.size - 1).coerceAtLeast(1)

                val linePath = Path()
                val fillPath = Path()

                dataPoints.forEachIndexed { i, pt ->
                    val x = i * stepX
                    val y = height - ((pt - min) / range * (height * 0.8f)) - 10.dp.toPx()

                    if (i == 0) {
                        linePath.moveTo(x, y)
                        fillPath.moveTo(x, height)
                        fillPath.lineTo(x, y)
                    } else {
                        val prevX = (i - 1) * stepX
                        val prevY = height - ((dataPoints[i - 1] - min) / range * (height * 0.8f)) - 10.dp.toPx()
                        val controlX1 = prevX + (stepX / 2f)
                        val controlY1 = prevY
                        val controlX2 = prevX + (stepX / 2f)
                        val controlY2 = y

                        linePath.cubicTo(controlX1, controlY1, controlX2, controlY2, x, y)
                        fillPath.cubicTo(controlX1, controlY1, controlX2, controlY2, x, y)
                    }
                }

                fillPath.lineTo(width, height)
                fillPath.close()

                // Draw Gradient Fill
                drawPath(
                    path = fillPath,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            primaryColor.copy(alpha = 0.35f),
                            primaryColor.copy(alpha = 0.05f)
                        )
                    )
                )

                // Draw Smooth Line
                drawPath(
                    path = linePath,
                    color = primaryColor,
                    style = Stroke(
                        width = 3.dp.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // X-Axis Labels
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            labels.forEach { label ->
                Text(
                    text = label,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// --- Donut Ring Chart for Appliance Distribution ---
@Composable
fun EcoDonutChart(
    segments: List<DonutSegment>,
    valueText: String,
    unitText: String,
    modifier: Modifier = Modifier
) {
    val outlineColor = MaterialTheme.colorScheme.outline

    Box(
        modifier = modifier.size(140.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 22.dp.toPx()
            val total = segments.sumOf { it.percentage.toDouble() }.toFloat()

            if (total <= 0f) {
                // Empty Track Ring
                drawArc(
                    color = outlineColor,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth)
                )
            } else {
                var currentAngle = -90f
                segments.forEach { seg ->
                    val sweepAngle = (seg.percentage / total) * 360f
                    if (sweepAngle > 0f) {
                        drawArc(
                            color = seg.color,
                            startAngle = currentAngle,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            style = Stroke(width = strokeWidth)
                        )
                        currentAngle += sweepAngle
                    }
                }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = valueText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = unitText,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
