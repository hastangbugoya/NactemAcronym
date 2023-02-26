package com.example.myacronymapplication.data

import org.junit.Assert.*
import org.junit.Test

class LfTest {
    @Test
    fun testLfConstructor() {
        val (freq, lf1, since, vars) = Lf(1, "example", 2022, listOf(Var(0, "var1"), Var(1, "var2")))
        assertEquals(Integer.valueOf(1), freq)
        assertEquals("example", lf1)
        assertEquals(Integer.valueOf(2022), since)
        assertEquals(2, vars?.size ?: 0)
        assertEquals("var1", vars?.get(0)?.lf ?: "")
        assertEquals("var2", vars?.get(1)?.lf ?: "")
    }

    @Test
    fun testLfDefaults() {
        val sample = Lf()
        assertEquals(0, sample.freq)
        assertEquals("", sample.lf)
        assertEquals(0, sample.since)
        assertNotNull(sample.vars)
        assertEquals(0, sample.vars!!.size)
    }

    @Test
    fun testLfSetters() {
        val lf = Lf()
        lf.freq = 5
        lf.lf = "tester"
        lf.since = 1898
        lf.vars = listOf(Var(0, "var1", 0))
        assertEquals(5, lf.freq)
        assertEquals("tester", lf.lf)
        assertEquals(1898, lf.since)
        assertEquals(1, lf.vars!!.size)
        assertEquals("var1", lf.vars!![0].lf)
    }
}