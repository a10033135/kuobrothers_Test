package com.sample.dialog

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sample.R
import com.sample.base.BaseDialogFragment
import com.sample.base.BaseViewModel
import com.sample.databinding.DialogErrorBinding
import com.sample.utils.Common.REQ_CHECK_INTERNET
import com.sample.utils.NetWork

class ErrorDialog : BaseDialogFragment() {

    private var msg: String? = null
    private var binding: DialogErrorBinding? = null
    private lateinit var viewModel: ErrorDialogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Dialog_TransparentWindow)
        arguments?.let {
            msg = it.getString("ErrorMsg")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogErrorBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ErrorDialogViewModel::class.java)

        with(binding!!) {
            lifecycleOwner = viewLifecycleOwner
            errBtConfirm.setOnClickListener { onclick() }
            viewModel = this@ErrorDialog.viewModel
        }
        viewModel.errMsg = liveData { emit(msg ?: getString(R.string.text_unknowError)) }
        return binding?.root
    }

    private fun onclick() {
        when (targetRequestCode) {
            REQ_CHECK_INTERNET -> checkNetConnected()
            else -> dismissAllowingStateLoss()
        }
    }

    private fun checkNetConnected() {
        if (NetWork.netConnected(targetFragment!!.requireActivity())) {
            targetFragment?.onActivityResult(targetRequestCode, RESULT_OK, null)
            dismissAllowingStateLoss()
        } else
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun newInstance(msg: String?): ErrorDialog {
            val args = Bundle()
            args.putString("ErrorMsg", msg)
            val fragment = ErrorDialog()
            fragment.arguments = args
            return fragment
        }
    }

}

class ErrorDialogViewModel : BaseViewModel() {
    lateinit var errMsg: LiveData<String>
}