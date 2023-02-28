package com.example.myacronymapplication.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myacronymapplication.R
import com.example.myacronymapplication.data.Var
import com.example.myacronymapplication.databinding.VariationItemBinding

class VariationLongFormAdapter(private var context: Context) : RecyclerView.Adapter<VariationLongFormAdapter.VariationLongFormAdapterViewHolder>() {

    private var lfList: List<Var> = listOf()

    inner class VariationLongFormAdapterViewHolder(var binding: VariationItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariationLongFormAdapterViewHolder {
        val binding =
            VariationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VariationLongFormAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int = lfList.size

    override fun onBindViewHolder(holder: VariationLongFormAdapterViewHolder, position: Int) {
        holder.binding.apply {
            varLfText.text = lfList[position].lf
            varSince.text = context.getString(R.string.var_since_date_format, lfList[position].since.toString())
        }
    }

    fun setVarList(l : List<Var>) {
        lfList = l
        notifyDataSetChanged()
    }
}