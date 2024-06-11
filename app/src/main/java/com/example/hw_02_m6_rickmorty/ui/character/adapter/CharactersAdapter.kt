import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hw_02_m6_rickmorty.R
import com.example.hw_02_m6_rickmorty.data.model.Character
import com.example.hw_02_m6_rickmorty.databinding.ItemCharacterBinding

class CharactersAdapter : PagingDataAdapter<Character, CharactersAdapter.RickAndMortyViewHolder>(diffUtil) {
    inner class RickAndMortyViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(character: Character) = with(binding) {
            characterName.text = character.name
            lastKnownLocation.text = character.location?.name
            firstSeenIn.text = character.origin?.name
            characterImage.load(character.image) {
                crossfade(true)
            }
            characterStatus.text = character.status
            colorIndicator.setImageResource(
                when {
                    character.status?.contains("Dead") == true -> R.drawable.ic_circle_red
                    character.status?.contains("Alive") == true -> R.drawable.ic_circle_green
                    else -> R.drawable.ic_circle_gray
                }
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyViewHolder {
        return RickAndMortyViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RickAndMortyViewHolder, position: Int) {
        getItem(position).let {
            if (it != null) {
                holder.onBind(it)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
