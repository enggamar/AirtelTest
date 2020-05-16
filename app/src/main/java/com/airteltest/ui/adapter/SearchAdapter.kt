package com.airteltest.ui.adapter

import AddressList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airteltest.databinding.SearchItemViewBinding


class SearchAdapter(private val addressList: ArrayList<AddressList>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(itemView: SearchItemViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        var mBinding: SearchItemViewBinding;

        init {
            mBinding = itemView
        }

        fun bind(addressList: AddressList) {
            mBinding.address = addressList.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
            SearchViewHolder(SearchItemViewBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = addressList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(addressList[position])
    }

    fun addAddresses(addressList: List<AddressList>) {
        this.addressList.apply {
            clear()
            addAll(addressList)
        }
    }

    fun clearAddress() {
        this.addressList.apply {
            clear()
        }
    }
}