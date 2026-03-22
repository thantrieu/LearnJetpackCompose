package pro.branium.learnjetpackcompose.lesson41.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChatRetrofitClient {
    private const val BASE_URL = "https://sendmessagerest-ircrt6piha-uc.a.run.app/"

    val api: ChatApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApiService::class.java)
    }
}