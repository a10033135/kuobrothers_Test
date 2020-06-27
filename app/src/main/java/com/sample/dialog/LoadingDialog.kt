package com.sample.dialog

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sample.R
import com.sample.base.BaseDialogFragment
import com.sample.databinding.DialogLoadingBinding

class LoadingDialog : BaseDialogFragment() {

    var binding: DialogLoadingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Dialog_TransparentWindow)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogLoadingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aniVector()
    }

    private fun aniVector() {
        val avd = binding!!.loadProgressBar.drawable
        if (avd is AnimatedVectorDrawable) {
            avd.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun newInstance(): DialogFragment {
            return LoadingDialog()
        }
    }

}