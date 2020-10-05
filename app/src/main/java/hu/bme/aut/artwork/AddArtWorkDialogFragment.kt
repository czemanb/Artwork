package hu.bme.aut.artwork

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatDialogFragment
import hu.bme.aut.model.Artwork

class AddArtWorkDialogFragment : AppCompatDialogFragment() {
    private var listener: AddArtworkDialogListener? = null
    private var nameEditText: EditText? = null
    private var prizeEditText: EditText? = null
    private var ownerEditText: EditText? = null

    interface AddArtworkDialogListener {
        fun onArtworkAdded(artwork: Artwork)
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = if (activity is AddArtworkDialogListener) {
            activity as AddArtworkDialogListener?
        } else {
            throw RuntimeException("Implementálni kell az AddArtworkListener-t")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_artwork)
            .setView(contentView)
            .setPositiveButton(R.string.ok) { _, _ ->
                listener?.onArtworkAdded(
                    Artwork(nameEditText?.text.toString(),
                             ownerEditText?.text.toString(),
                            prizeEditText?.text.toString().toInt(),
                    )
                )
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    private val contentView: View
        get() {
            val view: View = LayoutInflater.from(context).inflate(R.layout.dialog_new_artwork, null)
            nameEditText = view.findViewById(R.id.etNewArtworkName)
            prizeEditText = view.findViewById(R.id.etNewArtworkPrize)
            ownerEditText = view.findViewById(R.id.etNewArtworkOwner)
            return view
        }





}