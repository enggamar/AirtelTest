package com.airteltest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airteltest.ui.dialog.SelectCityDialog
import com.example.airteltest.databinding.AdapterCityListItemViewBinding

class SelectCityAdapter(private val mList: Array<String>, listener: SelectCityDialog) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val listener: View.OnClickListener

    init {
        this.listener = listener
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val mBinding = AdapterCityListItemViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return SelectCityViewHolder(mBinding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val holder = viewHolder as SelectCityViewHolder
        holder.bind(mList[i])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    private inner class SelectCityViewHolder(private val mBinding: AdapterCityListItemViewBinding) : RecyclerView.ViewHolder(mBinding.root) {
        init {
            mBinding.tvCityName.setOnClickListener(listener)
        }
        fun bind(city: String?) {
            mBinding.tvCityName.tag = city
            mBinding.cityString = city
        }
    }


}