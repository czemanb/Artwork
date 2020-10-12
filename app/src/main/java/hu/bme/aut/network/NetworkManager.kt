package hu.bme.aut.network


import hu.bme.aut.model.Artwork
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    private const val SERVICE_URL = "" //ToDo

    private val artworkApi: ArtworkApi


    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        artworkApi = retrofit.create(ArtworkApi::class.java)
    }

    suspend fun getArtworks(): List<Artwork>
    {
        val response = artworkApi.getArtworks()
            return response.body()!!

    }

    fun addArtwork(artworkData: Artwork, onResult: (Artwork?) -> Unit){
        artworkApi.addArtwork(artworkData).enqueue(
            object : Callback<Artwork> {
                override fun onFailure(call: Call<Artwork>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<Artwork>, response: Response<Artwork>) {
                    val addedArtwork = response.body()
                    onResult(addedArtwork)
                }
            }
        )
    }
}





