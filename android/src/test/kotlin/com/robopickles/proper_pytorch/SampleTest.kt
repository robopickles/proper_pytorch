package com.robopickles.proper_pytorch
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import io.mockk.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll


class SampleTest {
    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

    }

    companion object {
        @BeforeAll
        @JvmStatic
        fun setUpClass() {
            mockkStatic(Bitmap::class)
            mockkStatic(Color::class)
            mockkStatic(Log::class)
            val bmMock = mockk<Bitmap>(relaxUnitFun = true)
            every { Bitmap.createScaledBitmap(any(), any(), any(), any()) } returns bmMock
            every { Bitmap.createBitmap(any(), any(), any()) } returns bmMock
            every { bmMock.compress(any(), any(), any())} returns true

            every { Color.argb(any<Int>(), any(), any(), any()) } returns 1
            every { Log.v(any(), any()) } returns 0
        }

        @AfterAll
        @JvmStatic
        fun tearDownClass() {
            unmockkStatic(Bitmap::class)
            unmockkStatic(Color::class)
            unmockkStatic(Log::class)
        }
    }

    @Test
    fun something() {
        assertTrue { true }
        val a = HeatmapHandler()
        val img: List<Float> = (0..256).map{ x -> x.toFloat()}
        a.sliceToHeatmap(img, 8)
        verify (atMost = 1) { Bitmap.createBitmap(8, 8, any()) }
    }

    @Test
    fun mocksCorrect() {
        val a = HeatmapHandler()
        val img: List<Float> = (256..1024).map{ x -> x.toFloat()}
        a.sliceToHeatmap(img, 16)
        verify (atMost = 1) { Bitmap.createBitmap(16, 16, any()) }

    }
}