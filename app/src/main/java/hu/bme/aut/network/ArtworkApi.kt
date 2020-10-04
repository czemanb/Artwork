package hu.bme.aut.network

import org.json.JSONObject
import java.net.HttpURLConnection

interface ArtworkApi {

    fun getArtworks(urlAddress: String): String

    fun postArtwork(jsonObject: JSONObject)

}