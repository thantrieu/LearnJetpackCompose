package pro.branium.learnjetpackcompose.lesson20.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson20.data.remote.AlbumApi
import pro.branium.learnjetpackcompose.lesson20.data.remote.SongApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://thantrieu.com"

    // Gson đơn giản
    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .create()

    // Retrofit tối giản
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // API interface
    @Provides
    @Singleton
    fun provideSongApi(retrofit: Retrofit): SongApi =
        retrofit.create(SongApi::class.java)

    @Provides
    @Singleton
    fun provideAlbumApi(retrofit: Retrofit): AlbumApi =
        retrofit.create(AlbumApi::class.java)
}