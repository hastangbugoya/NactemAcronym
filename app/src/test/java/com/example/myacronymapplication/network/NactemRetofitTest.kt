package com.example.myacronymapplication.network

import com.example.myacronymapplication.data.NactemResponseItem
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit

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

    @Test
    fun testBuildRetrofitInstance() {
        val retrofit: Retrofit = NactemRetofit.buildRetrofitInstance()
        assertNotNull(retrofit)
    }

    @Test
    fun testGetService() {
        val nactemService: NactemService = NactemRetofit.getService()
        assertNotNull(nactemService)
    }

    @Test
    fun testGetFullForm() = runBlocking {
        val nactemService: NactemService = NactemRetofit.getService()
        val response: List<NactemResponseItem> = nactemService.getFullForm("test")
        assertNotNull(response)
    }
}