package pro.branium.learnjetpackcompose.lesson20.data.remote

import pro.branium.learnjetpackcompose.lesson20.data.remote.dto.AlbumResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AlbumApi {
    @POST("services/services.php/albums")
    suspend fun getLimitedAlbums(
        @Body param: RequestParam
    ): Response<AlbumResponse>
}