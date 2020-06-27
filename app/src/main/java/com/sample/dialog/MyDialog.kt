package com.sample.dialog

import androidx.fragment.app.DialogFragment
import com.sample.base.BaseFragment

/**
 * 專門呼喚 Dialog */
object MyDialog {

    private var dialogs = ArrayList<DialogFragment>()
    private var dialogFg: DialogFragment? = null

    fun showErrorDialog(fg: BaseFragment, reqCode: Int?, msg: String) {
        dialogFg = ErrorDialog.newInstance(msg)
        show(fg, reqCode, false)
    }

    fun showLoadingDialog(fg: BaseFragment) {
        dialogFg = LoadingDialog.newInstance()
        show(fg, null, false)
    }

    fun closeLoadingDialog() {

        dialogs.forEach {
            if (it is LoadingDialog)
                it.dismissAllowingStateLoss()
        }

    }

    private fun show(fg: BaseFragment, reqCode: Int?, cancelable: Boolean) {

        dialogFg!!.setTargetFragment(fg, reqCode ?: 0)
        dialogFg!!.isCancelable = cancelable
        dialogFg!!.show(fg.parentFragmentManager, "dialogFg")
        dialogs.add(dialogFg!!)
        dialogFg = null

    }

    fun closeDialog() {

    }


}