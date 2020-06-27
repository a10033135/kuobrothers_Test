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

        /* 進入頁面時判斷網路情況，若網路無問題即下載資料 */
        if (NetWork.netConnected(requireContext()))
            loadData()
        else
            MyDialog.showErrorDialog(this, REQ_CHECK_INTERNET, getString(R.string.text_Error_Connected))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /* 從 ErrorDialog 確認已排除網路問題，重新加載資料 */
        if (requestCode == REQ_CHECK_INTERNET && resultCode == RESULT_OK)
            loadData()

    }

    /* 並非每一頁皆使用，因此採非 abstract 的形式 */
    open  fun loadData() {}

}