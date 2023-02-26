package com.example.myacronymapplication.data

import org.junit.Assert.*
import org.junit.Test

class NactemResponseItemTest {

    @Test
    fun testNactemResponseItemConstructor() {
        val lf1 = Lf(1, "example1", 2022, listOf(Var(1, "var1", 2022), Var(2, "var2", 2023)))
        val lf2 = Lf(2, "example2", 2023, listOf(Var(3, "var3", 2023), Var(4, "var4", 2024)))
        NactemResponseItem(listOf(lf1, lf2), "testing").apply {
            assertEquals(2, lfs!!.size)
            assertEquals("testing", sf)
            assertEquals(Integer.valueOf(1), lfs!![0].freq)
            assertEquals("example1", lfs!![0].lf)
            assertEquals(Integer.valueOf(2022), lfs!![0].since)
            assertEquals(2, lfs!![0].vars!!.size)
            assertEquals(Integer.valueOf(1), lfs!![0].vars!![0].freq)
            assertEquals("var1", lfs!![0].vars!![0].lf)
            assertEquals(Integer.valueOf(2022), lfs!![0].vars!![0].since)
            assertEquals(Integer.valueOf(2), lfs!![0].vars!![1].freq)
            assertEquals("var2", lfs!![0].vars!![1].lf)
            assertEquals(Integer.valueOf(2023), lfs!![0].vars!![1].since)
        }
    }

    @Test
    fun testNactemResponseItemDefaults() {
        NactemResponseItem().apply {
            assertNotNull(lfs)
            assertEquals(0, lfs!!.size)
            assertEquals("", sf)
        }

    }

    @Test
    fun testSettersAndGetters() {
        val item = NactemResponseItem().apply {
            lfs = listOf(
                Lf(
                    freq = 1,
                    lf = "example1",
                    since = 2022,
                    vars = listOf(
                        Var(freq = 1, lf = "var1", since = 2022),
                        Var(freq = 2, lf = "var2", since = 2023)
                    )
                )
            )
            sf = "surfaceForm"
        }.also {
            assertEquals(1, it.lfs?.size)
            assertEquals("surfaceForm", it.sf)
            assertEquals(1, it.lfs?.get(0)?.freq)
            assertEquals("example1", it.lfs?.get(0)?.lf)
            assertEquals(2022, it.lfs?.get(0)?.since)
            assertEquals(2, it.lfs?.get(0)?.vars?.size)
            assertEquals(1, it.lfs?.get(0)?.vars?.get(0)?.freq)
            assertEquals("var1", it.lfs?.get(0)?.vars?.get(0)?.lf)
            assertEquals(2022, it.lfs?.get(0)?.vars?.get(0)?.since)
            assertEquals(2, it.lfs?.get(0)?.vars?.get(1)?.freq)
            assertEquals("var2", it.lfs?.get(0)?.vars?.get(1)?.lf)
            assertEquals(2023, it.lfs?.get(0)?.vars?.get(1)?.since)
        }

    }
}