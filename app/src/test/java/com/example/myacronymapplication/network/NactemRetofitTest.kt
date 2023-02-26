package com.example.myacronymapplication.network

import com.example.myacronymapplication.data.NactemResponseItem
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NactemRetofitTest {
    @Test
    fun TestRetrofitInstance() {
        val instance = NactemRetofit.buildRetrofitInstance()
        assert(instance.baseUrl().toString() == "http://www.nactem.ac.uk/software/acromine/")
    }

    @Test
    fun TestEndpoints() {
        var response: List<NactemResponseItem>
        runBlocking {
            response = NactemRetofit.getService().getFullForm("ASP")
        }
        assert(!response.isEmpty())
        runBlocking {
            response = NactemRetofit.getService().getFullForm("")
        }
        assert(response.isEmpty())
    }
}