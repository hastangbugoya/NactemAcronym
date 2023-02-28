package com.example.myacronymapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myacronymapplication.R
import com.example.myacronymapplication.data.Lf
import com.example.myacronymapplication.databinding.LongformItemBinding
import android.content.Context
import com.example.myacronymapplication.data.Var

class LongFormMainAdapter(private var context: Context) : RecyclerView.Adapter<LongFormMainAdapter.LongFormMainViewHolder>() {

    private var lfList: List<Lf> = listOf()

    inner class LongFormMainViewHolder(var binding: LongformItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Lf) {
                binding.lf = item
                binding.executePendingBindings()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LongFormMainViewHolder {
        val binding =
            LongformItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LongFormMainViewHolder(binding)
    }

    override fun getItemCount(): Int = lfList.size

    override fun onBindViewHolder(holder: LongFormMainViewHolder, position: Int) {
        holder.binding.apply {
            holder.bind(lfList[position])
            val adapter = VariationLongFormAdapter(context)
            varRecycler.adapter = adapter
            lfList[position].vars?.let {
                if (it.size > 1) {
                    // sublist is start-inclusive, end-exclusive
                    adapter.setVarList(it.subList(1,it.size))
                    varRecycler.visibility = View.VISIBLE
                } else {
                    varRecycler.visibility = View.GONE
                }
            }
        }
    }

    fun setLFList(l: List<Lf>) {
        lfList = l
        notifyDataSetChanged()
    }
}