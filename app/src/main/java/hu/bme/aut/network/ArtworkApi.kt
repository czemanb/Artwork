package hu.bme.aut.network

import hu.bme.aut.model.Artwork
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface ArtworkApi {

    @GET("/api/photo")
    suspend fun getArtworks(): Response<List<Artwork>>

    @Headers("Content-Type: application/json")
    @POST("/api/photo")
    fun addArtwork(@Body artwork : Artwork): Call<Artwork>

}