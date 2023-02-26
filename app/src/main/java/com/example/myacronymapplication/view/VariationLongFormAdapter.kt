package com.example.myacronymapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myacronymapplication.data.Var
import com.example.myacronymapplication.databinding.VariationItemBinding

class VariationLongFormAdapter : RecyclerView.Adapter<VariationLongFormAdapter.VariationLongFormAdapterViewHolder>() {

    var lfList: List<Var> = listOf()

    inner class VariationLongFormAdapterViewHolder(binding: VariationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: VariationItemBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariationLongFormAdapterViewHolder {
        val binding =
            VariationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VariationLongFormAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int = lfList.size

    override fun onBindViewHolder(holder: VariationLongFormAdapterViewHolder, position: Int) {
        holder.binding.apply {
            varLfText.text = lfList.get(position).lf
            varSince.text = lfList.get(position).since?.let {
                "(${it.toString()})"
            }
        }
    }

    fun setVarList(l : List<Var>) {
        lfList = l
        notifyDataSetChanged()
    }
}