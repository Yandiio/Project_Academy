package com.dicoding.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.academy.R
import com.dicoding.academy.data.source.local.entity.ModuleEntity

class ModuleListAdapter internal constructor(private val listener : MyAdapterClickListener) : RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>() {
    private val listModules = ArrayList<ModuleEntity>()

    internal fun setModules (modules: List<ModuleEntity>) {
        listModules.clear()
        listModules.addAll(modules)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_module_list_custom, parent, false)
        return ModuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = listModules[position]
        holder.bind(module)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(holder.adapterPosition, listModules[holder.adapterPosition].moduleId)
        }
    }

    override fun getItemCount(): Int = listModules.size

    inner class ModuleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)  {
        private val textTitle : TextView = itemView.findViewById(R.id.text_module_title)
        fun bind(module : ModuleEntity) {
            textTitle.text = module.title
        }
    }
}

internal interface MyAdapterClickListener {
    fun onItemClicked(position: Int, moduleId: String)
}