package com.cis2818.ksproj4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cis2818.ksproj4.databinding.ItemPlantBinding

// adapter to connect plant list to recycler
class PlantAdapter(
    private val onLongDelete: (Plant) -> Unit
) : RecyclerView.Adapter<PlantAdapter.VH>() {

    // list plants
    private val items = mutableListOf<Plant>()
    // replaces with new list
    fun submit(list: List<Plant>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    // vh for a single row view
    inner class VH(val binding: ItemPlantBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemPlantBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    // returns plant number in list
    override fun getItemCount() = items.size

    // binds plant data to a row at a position, had to look this up
    override fun onBindViewHolder(holder: VH, position: Int) {
        val plant = items[position]
        holder.binding.name.text = plant.name
        holder.binding.lightLabel.text = "Light: ${plant.light}%"
        holder.binding.lightBar.progress = plant.light
        holder.binding.difficulty.rating = plant.difficulty.toFloat()

        // long press to delete plant (maybe it died)
        holder.itemView.setOnLongClickListener {
            onLongDelete(plant)
            true
        }
    }
}
