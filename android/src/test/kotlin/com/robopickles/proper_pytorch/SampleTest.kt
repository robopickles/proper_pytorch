package com.robopickles.proper_pytorch
import android.graphics.Bitmap
import android.graphics.Bitmap.Config
import android.graphics.Color
import android.util.Log
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.AfterClass
import org.junit.BeforeClass
import java.lang.Exception
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

inline fun <T> forceType(fn: T)  = fn


class SampleTest {

    @MockK(relaxUnitFun = true)
    lateinit var bm: Bitmap

    @BeforeTest
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic(Bitmap::class)

        every { Bitmap.createBitmap(any(), any(), any()) } returns bm
        every { Bitmap.createScaledBitmap(any(), any(), any(), any()) } returns bm

        every { bm.compress(any(), any(), any())} returns true

        mockkStatic(Color::class)
        every { Color.argb(any<Int>(), any(), any(), any()) } returns 1

        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
    }

    companion object {
        @BeforeClass
        fun rrr() {
            throw Exception()
        }

        @AfterClass
        fun tearDownClass() {
            unmockkStatic(Bitmap::class)
            assert(false)
            throw Exception()
        }
    }

    @Test
    fun something() {
        assertTrue { true }
        val a = HeatmapHandler()
        val img: List<Float> = (0..256).map{ x -> x.toFloat()}
        a.sliceToHeatmap(img, 8)
        verify { Bitmap.createBitmap(8, 8, any()) }
    }

    @Test
    fun mocksCorrect() {
        val a = HeatmapHandler()
        val img: List<Float> = (256..1024).map{ x -> x.toFloat()}
        a.sliceToHeatmap(img, 16)
        verify { Bitmap.createBitmap(16, 16, any()) }
    }
}