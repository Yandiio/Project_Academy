package com.dicoding.academy.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.academy.R
import com.dicoding.academy.data.ModuleEntity
import kotlinx.android.synthetic.main.item_module_list.view.*

class DetailCourseAdapter : RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder>() {
    private var listModules = ArrayList<ModuleEntity>()

    fun setModules(module: List<ModuleEntity>?) {
        if (module == null) return
        listModules.clear()
        listModules.addAll(module)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailCourseAdapter.ModuleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_module_list, parent, false)
        return ModuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailCourseAdapter.ModuleViewHolder, position: Int) {
        val module = listModules[position]
        holder.bind(module)
    }

    override fun getItemCount(): Int = listModules.size

    inner class ModuleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind (module : ModuleEntity) {
            with (itemView) {
                text_module_title.text = module.title
            }
        }
    }
}