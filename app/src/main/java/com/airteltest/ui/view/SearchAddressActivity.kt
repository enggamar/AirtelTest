package com.airteltest.ui.view

import SearchData
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airteltest.data.api.ApiHelper
import com.airteltest.data.api.RetrofitBuilder
import com.airteltest.ui.adapter.SearchAdapter
import com.airteltest.ui.base.ViewModelFactory
import com.airteltest.ui.dialog.SelectCityDialog
import com.airteltest.ui.listener.OnDialogItemObjectClickListener
import com.airteltest.ui.viewmodel.SearchViewModel
import com.airteltest.utils.Status.*
import com.example.airteltest.R
import com.example.airteltest.databinding.ActivitySearchBinding

class SearchAddressActivity : AppCompatActivity(), OnDialogItemObjectClickListener {

    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter
    private lateinit var mBinding: ActivitySearchBinding
    private var city: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        mBinding.actionBar.context = this
        openCityDialog()
        setupViewModel()
        setupUI()
    }

    fun openCityDialog() {
        SelectCityDialog(this, this).show()
    }

    private fun setCity(city: String) {
        this.city = city
        mBinding.actionBar.cityName = city
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(SearchViewModel::class.java)
        mBinding.searchViewModel = viewModel

        viewModel.isVisbileLiveData.observe(this, Observer { visibility ->
            mBinding.crossVisibility = visibility
        })

        viewModel.callApi().observe(this, Observer { searchQuery ->
            mBinding.crossVisibility = searchQuery.length > 0
            if (searchQuery.isEmpty()) {
                mBinding.etSearch.text?.clear();
                clearAddressList()
            } else {
                setupObservers(searchQuery, city)
            }
        })
    }

    private fun setupUI() {
        adapter = SearchAdapter(arrayListOf())
        mBinding.mAdapter = adapter
    }

    private fun setupObservers(queryString: String, city: String) {
        viewModel.getSearchAddresses(queryString, city).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        resource.data?.let { data ->
                            retrieveAddressList(data)
                        }
                    }
                    ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                    }
                }
            }
        })
    }

    private fun retrieveAddressList(data: SearchData) {
        adapter.apply {
            addAddresses(data.data.addressList)
            notifyDataSetChanged()
        }
    }

    private fun clearAddressList() {
        adapter.apply {
            clearAddress()
            notifyDataSetChanged()
        }
    }

    override fun onPositiveBtnClick(city: String) {
        setCity(city)
    }


}
