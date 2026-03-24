package pro.branium.learnjetpackcompose.lesson41.data.remote.chat

import pro.branium.learnjetpackcompose.lesson41.data.remote.ChatApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChatRetrofitClient {
    private const val BASE_URL = "https://us-central1-learnfirebase-307d6.cloudfunctions.net/"

    val api: ChatApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApiService::class.java)
    }
}