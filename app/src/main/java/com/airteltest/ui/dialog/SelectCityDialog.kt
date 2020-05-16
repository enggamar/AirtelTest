package com.airteltest.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import com.airteltest.ui.adapter.SelectCityAdapter
import com.airteltest.ui.listener.OnDialogItemObjectClickListener
import com.example.airteltest.R
import com.example.airteltest.databinding.DialogSelectCityBinding

open class SelectCityDialog(private val mActivity: Context, private val dialogCallback: OnDialogItemObjectClickListener) : Dialog(mActivity), View.OnClickListener {
    private lateinit var mDialogBinding: DialogSelectCityBinding
    private lateinit var adapter: SelectCityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialogBinding = DialogSelectCityBinding.inflate(LayoutInflater.from(context))
        this.setContentView(mDialogBinding.root)
        window!!.setGravity(Gravity.CENTER)
        setCancelable(false)
        window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        setUpView()
    }


    private fun setUpView() {
        val mList = context.resources.getStringArray(R.array.city_list)
        adapter = SelectCityAdapter(mList, this)
        mDialogBinding.mAdapter = adapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_city_name -> {
                val city = v.tag as String
                dialogCallback.onPositiveBtnClick(city)
                dismiss()
            }
        }

    }

}