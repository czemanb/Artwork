package hu.bme.aut.artwork

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.model.Artwork
import kotlinx.android.synthetic.main.content_artwork.*

class ArtworkActivity : AppCompatActivity(), AddArtWorkDialogFragment.AddArtworkDialogListener {
    private lateinit var adapter: ArtworkAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artwork)
        initFab()
        initRecyclerView()
    }

    private fun initFab() {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            AddArtWorkDialogFragment().show(supportFragmentManager, AddArtWorkDialogFragment::class.java.simpleName)
        }
    }

    private fun initRecyclerView() {
        MainRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArtworkAdapter()
        adapter.addArtwork(Artwork("Mona Lisa","Louvre",5000000))

        MainRecyclerView.adapter = adapter
    }


    override fun onArtworkAdded(artwork: Artwork) {
        adapter.addArtwork(artwork)
    }
}