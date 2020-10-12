package hu.bme.aut.artwork

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.model.Artwork
import hu.bme.aut.network.NetworkManager
import kotlinx.android.synthetic.main.item_artwork.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ArtworkAdapter : RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder>() {
   private var artworks: MutableList<Artwork>

    init {
        artworks = ArrayList()
        GlobalScope.launch(Dispatchers.IO) {
            val temp = NetworkManager.getArtworks()
            artworks.addAll(temp)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_artwork, parent, false)
        return ArtworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        val item = artworks[position]
        holder.tvArtworkname.text = item.name
        holder.tvArtworkprice.text = item.price.toString()
        holder.tvArtworkowner.text = item.owner
    }

    override fun getItemCount(): Int {
        return artworks.size
    }

    fun addArtwork(newArtwork: Artwork) {
        artworks.add(newArtwork)
        NetworkManager.addArtwork(newArtwork) {
            if (it?.name != null) {
                Log.d("TAG", "Registering new artwork")
            } else {
                Log.d("TAG", "Error registering new artwork")
            }
        }
    }

    inner class ArtworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvArtworkname = itemView.tvArtworkname
        val tvArtworkprice = itemView.tvArtworkprice
        val tvArtworkowner= itemView.tvArtworkowner

    }


}