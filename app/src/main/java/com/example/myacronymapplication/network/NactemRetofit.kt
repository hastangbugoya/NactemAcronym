package com.example.myacronymapplication.network

import com.example.myacronymapplication.data.NactemResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NactemRetofit {

    companion object {
        fun Namtec() : Retrofit = Retrofit.Builder().baseUrl("http://www.nactem.ac.uk/software/acromine/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getService() : NamtecService = Namtec().create(NamtecService::class.java)
    }

}

interface NamtecService {
    @GET("dictionary.py")
    suspend fun getFullForm(@Query("sf") sf: String = "") : Response<NactemResponse>
}