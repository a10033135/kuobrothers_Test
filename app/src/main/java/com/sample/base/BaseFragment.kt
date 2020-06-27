package com.sample.base

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sample.R
import com.sample.dialog.MyDialog
import com.sample.utils.Common.REQ_CHECK_INTERNET
import com.sample.utils.NetWork

open class BaseFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (NetWork.netConnected(requireContext()))
            loadData()
        else
            MyDialog.showErrorDialog(this, REQ_CHECK_INTERNET, getString(R.string.text_Error_Connected))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CHECK_INTERNET && resultCode == RESULT_OK)
            loadData()
    }

    open fun loadData() {}

}