import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.hw_02_m6_rickmorty.R
import com.example.hw_02_m6_rickmorty.data.model.Character
import com.example.hw_02_m6_rickmorty.databinding.ItemCharacterBinding

class CharactersAdapter : ListAdapter<Character, CharactersAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            with(binding) {
                characterImage.load(character.image) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                characterName.text = character.name
                characterStatus.text = character.status
                characterSpecies.text = character.species
                lastKnownLocationText.text = character.location.name
                firstSeenInText.text = character.origin.name

                colorIndicator.setImageResource(
                    when {
                        character.status?.contains("Dead") == true -> R.drawable.ic_circle_red
                        character.status?.contains("Alive") == true -> R.drawable.ic_circle_green
                        else -> R.drawable.ic_circle_gray
                    }
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}
