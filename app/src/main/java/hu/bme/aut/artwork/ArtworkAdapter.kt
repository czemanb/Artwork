package hu.bme.aut.artwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.model.Artwork
import hu.bme.aut.network.NetworkManager
import kotlinx.android.synthetic.main.item_artwork.view.*
import org.json.JSONArray
import org.json.JSONObject

class ArtworkAdapter : RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder>() {
    private val artworks: MutableList<Artwork>
    private val networkManager = NetworkManager
    init {
        artworks = ArrayList()


        val temp = networkManager.getArtworks("")
        handleJson(temp)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_artwork, parent, false)
        return ArtworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        val item = artworks[position]
        holder.tvArtworkname.text = item.name
        holder.tvArtworkprize.text = item.prize.toString()
        holder.tvArtworkowner.text = item.owner
    }

    override fun getItemCount(): Int {
        return artworks.size
    }

    fun addArtwork(newArtwork: Artwork) {
        //artworks.add(newArtwork)
        val jsonObject = buidJsonObject(newArtwork)
        networkManager.postArtwork(jsonObject)
    }

    private fun buidJsonObject(newArtwork: Artwork): JSONObject {

        val jsonObject = JSONObject()
        jsonObject.accumulate("name", newArtwork.name)
        jsonObject.accumulate("prize", newArtwork.prize)
        jsonObject.accumulate("owner", newArtwork.owner)


        return jsonObject
    }

    private fun handleJson(jsonString: String?) {

        val jsonArray = JSONArray(jsonString)

        var i = 0
        while (i < jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            artworks.add(
                Artwork(
                    jsonObject.getString("name"),
                    jsonObject.getInt("prize"),
                    jsonObject.getString("owner")
                )
            )
            i++
        }

    }


    inner class ArtworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvArtworkname = itemView.tvArtworkname
        val tvArtworkprize = itemView.tvArtworkprize
        val tvArtworkowner= itemView.tvArtworkowner

    }


}