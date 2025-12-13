package pro.branium.learnjetpackcompose.lesson20.data.remote

import pro.branium.learnjetpackcompose.lesson20.data.remote.dto.SongsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SongApi {
    @GET("services/services.php/songs")
    suspend fun getLimitedSongs(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<SongsResponse>
}
