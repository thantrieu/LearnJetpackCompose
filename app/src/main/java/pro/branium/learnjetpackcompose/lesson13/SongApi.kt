package pro.branium.learnjetpackcompose.lesson13

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SongApi {
    @GET("/services/services.php")
    fun getAllSongs(@Query("queryType") queryType: String): Call<SongListDto>
}