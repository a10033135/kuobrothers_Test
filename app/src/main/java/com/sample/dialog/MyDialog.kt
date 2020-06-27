package com.sample.dialog

import android.util.Log
import androidx.fragment.app.DialogFragment
import com.sample.base.BaseFragment

/**
 *  DialogFragment 專用的類別
 */
object MyDialog {

    private val TAG = "MyDialog_TAG"
    private var dialogs = ArrayList<DialogFragment>()
    private var dialogFg: DialogFragment? = null

    /* 加載畫面 */
    fun showLoadingDialog(fg: BaseFragment) {
        dialogFg = LoadingDialog.newInstance()
        show(fg, null, false)
    }

    /* 僅關閉 LoadingDialog */
    fun closeLoadingDialog() {
        dialogs.forEach {
            if (it is LoadingDialog)
                it.dismissAllowingStateLoss()
        }
    }

    /* 錯誤提示畫面 */
    fun showErrorDialog(fg: BaseFragment, reqCode: Int?, msg: String) {
        dialogFg = ErrorDialog.newInstance(msg)
        show(fg, reqCode, false)
    }


    private fun show(fg: BaseFragment, reqCode: Int?, cancelable: Boolean) {

        with(dialogFg!!) {
            setTargetFragment(fg, reqCode ?: 0)
            isCancelable = cancelable
            show(fg.parentFragmentManager, "dialogFg")
            dialogs.add(this)
            Log.d("MyDialog", "Show " + javaClass.simpleName + ", ReqCode: " + reqCode)
        }

        dialogFg = null
    }

    /* 關閉所有開啟中的 DialogFragment ，此次無使用需求 */
    fun closeDialog() {
        try {
            for (dialog in dialogs) {
                dialog.dismissAllowingStateLoss()
                Log.d(TAG, dialog.javaClass.simpleName)
            }
            dialogs.clear()
        } catch (e: Exception) {
            with(e.message) {
                Log.e(TAG, this!!)
            }
        }
    }


}