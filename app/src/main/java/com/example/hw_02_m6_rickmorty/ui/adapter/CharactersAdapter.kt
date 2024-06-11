import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hw_02_m6_rickmorty.data.model.Character
import com.example.hw_02_m6_rickmorty.databinding.ItemCharacterBinding

class CharactersAdapter : PagingDataAdapter<Character, CharactersAdapter.CharacterViewHolder>(DiffCallback()) {

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(character: Character) = with(binding) {
            characterName.text = character.name
            lastKnownLocation.text = character.location?.name
            firstSeenIn.text = character.origin?.name
            characterImage.load(character.image) {
                crossfade(true)
            }
            characterSpecies.text = character.species
            characterStatus.text = character.status
            colorIndicator.setImageResource(
                when {
                    character.status?.contains("Dead") == true -> com.example.hw_02_m6_rickmorty.R.drawable.ic_circle_red
                    character.status?.contains("Alive") == true -> com.example.hw_02_m6_rickmorty.R.drawable.ic_circle_green
                    else -> com.example.hw_02_m6_rickmorty.R.drawable.ic_circle_gray
                }
            )
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        if (character != null) {
            holder.onBind(character)
        }
        holder.binding.root.setOnClickListener {
            if (character != null) {
                onItemClickListener?.invoke(character)
            }
            if (character != null) {
                Log.d("TAG", "${character.id}")
            }
        }
    }

    private var onItemClickListener: ((Character) -> Unit)? = null

    fun setOnItemClickListener(listener: (Character) -> Unit) {
        onItemClickListener = listener
    }
}
