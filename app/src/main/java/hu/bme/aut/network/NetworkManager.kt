package hu.bme.aut.network

import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object NetworkManager : ArtworkApi{

    override fun getArtworks(urlAddress: String): String {

        val result = StringBuilder()

        try {
            val url = URL(urlAddress)
            val conn = url.openConnection() as HttpsURLConnection

            var reader =  BufferedReader(InputStreamReader(conn.inputStream))

            var line :String?
            do{
                line = reader.readLine()
                result.appendLine(line)
            } while(line != null)

        }catch(e: Exception) {

        }
        finally {

        }


        return result.toString();
    }


    override fun postArtwork(jsonObject: JSONObject) {
        val url = URL("") //TODO
        val conn = url.openConnection() as HttpsURLConnection
        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8")

        setPostRequestContent(conn, jsonObject)
        conn.connect()
        conn.responseMessage + ""
    }



    private fun setPostRequestContent(conn: HttpURLConnection, jsonObject: JSONObject) {

        val os = conn.outputStream
        val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
        writer.write(jsonObject.toString())
        writer.flush()
        writer.close()
        os.close()
    }
}



