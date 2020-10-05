package hu.bme.aut.network

import org.json.JSONObject


interface ArtworkApi {

    suspend fun getArtworks(urlAddress: String): String

    suspend fun postArtwork( jsonObject: JSONObject): String

}