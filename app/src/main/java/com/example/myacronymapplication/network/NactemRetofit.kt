package com.example.myacronymapplication.network

import com.example.myacronymapplication.data.NactemResponseItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NactemRetofit {

    companion object {
        fun buildRetrofitInstance() : Retrofit = Retrofit.Builder().baseUrl("http://www.nactem.ac.uk/software/acromine/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        fun getService() : NactemService = buildRetrofitInstance().create(NactemService::class.java)
    }

}

interface NactemService {
    @GET("dictionary.py")
    suspend fun getFullForm(@Query("sf") sf: String) : List<NactemResponseItem>
}