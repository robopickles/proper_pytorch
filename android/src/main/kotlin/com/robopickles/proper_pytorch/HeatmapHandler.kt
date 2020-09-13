package com.robopickles.proper_pytorch

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import java.io.ByteArrayOutputStream


typealias Coord = List<Double>
typealias  ListCoord = MutableList<Coord>

data class HeatmapCoord(val heatmap: ByteArray, val coord: Coord)
data class HeatmapsCoordinates(val heatmaps: MutableList<ByteArray>, val points: ListCoord)

class HeatmapHandler {
    fun sliceToHeatmap(heatmap: List<Float>, size: Int): HeatmapCoord {
        val start = System.currentTimeMillis()
        val bmapS = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val max = heatmap.max()!!
        var maxVal = 0
        var maxX = 0
        var maxY = 0
        heatmap.forEachIndexed {idx, it -> run {
            val c = maxOf((it * 255 / max ).toInt(), 0)

            val color = Color.argb(c, c, c, 0)
            val x = idx % size
            val y = idx / size
            if (c > maxVal) {
                maxVal = c
                maxX = x
                maxY = y
            }
            bmapS.setPixel(x, y, color)
        }}
        Log.v("flutter", "Create scalled bitmap")
        val bmapOut = Bitmap.createScaledBitmap(bmapS, size, size, false)
        val outArray = bitmapToByteArray(bmapOut)
        val end = System.currentTimeMillis() - start
        val coord = listOf(
                maxX.toDouble() / size.toDouble(),
                maxY.toDouble() / size.toDouble()
        )
        Log.v("flutter", "HM creation Execution time: ${end}")
        return HeatmapCoord(outArray, coord)
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
        return outStream.toByteArray()
    }
}