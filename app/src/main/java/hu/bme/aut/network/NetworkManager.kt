package hu.bme.aut.network

import android.util.Log
import hu.bme.aut.model.Artwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object NetworkManager {

    private const val SERVICE_URL = "https://api.openweathermap.org"

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



    /*//forrás: https://stackoverflow.com/questions/29465996/how-to-get-json-object-using-httpurlconnection-instead-of-volley/29466338
    //https://developer.android.com/kotlin/coroutines-adv
    override suspend fun getArtworks(urlAddress: String): String = withContext(Dispatchers.IO) {

        val result = StringBuilder()

        try {
            val url = URL(urlAddress)
            val conn = url.openConnection() as HttpURLConnection

            var reader =  BufferedReader(InputStreamReader(conn.inputStream))

            var line :String?
            do{
                line = reader.readLine()
                result.appendLine(line)
            } while(line != null)

        }catch(e: Exception) {

            Log.e("network-err", e.toString())

        }
        finally {

        }


        return@withContext result.toString()
    }


    //forrás: https://hmkcode.com/android-send-json-data-to-server/
    override suspend fun postArtwork(jsonObject: JSONObject) : String {

        val result = withContext(Dispatchers.IO) {
            val url = URL("http://localhost:8080/api/photo")


            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8")

            setPostRequestContent(conn, jsonObject)
            conn.connect()
            conn.responseMessage + ""
        }
        return result
    }



    private fun setPostRequestContent(conn: HttpURLConnection, jsonObject: JSONObject) {
        val os = conn.outputStream
        val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
        writer.write(jsonObject.toString())
        writer.flush()
        writer.close()
        os.close()
    }*/
//}



