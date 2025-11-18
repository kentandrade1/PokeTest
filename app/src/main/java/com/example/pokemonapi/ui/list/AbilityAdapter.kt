package com.example.pokemonapi.ui.list

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.example.pokemonapi.R
import com.example.pokemonapi.models.AbilityItem

class AbilityAdapter(
    private val onTap: (AbilityItem) -> Unit
) : ListAdapter<AbilityItem, AbilityAdapter.VH>(Diff()) {

    class VH(view: View) : RecyclerView.ViewHolder(view)

    class Diff : DiffUtil.ItemCallback<AbilityItem>() {
        override fun areItemsTheSame(a: AbilityItem, b: AbilityItem) = a.url == b.url
        override fun areContentsTheSame(a: AbilityItem, b: AbilityItem) = a == b
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ability, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, pos: Int) {
        val item = getItem(pos)
        holder.itemView.apply {
            findViewById<TextView>(R.id.txtId).text = "#${item.id}"
            findViewById<TextView>(R.id.txtTitle).text = item.name.replaceFirstChar { it.uppercase() }
            setOnClickListener { onTap(item) }
        }
    }
}
