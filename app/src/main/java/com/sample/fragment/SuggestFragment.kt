package com.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.api.RepositoryManager
import com.sample.base.BaseFragment
import com.sample.base.BaseViewModel
import com.sample.databinding.FragmentSuggestBinding
import com.sample.databinding.ViewSuggestListBinding
import com.sample.dialog.MyDialog
import com.sample.module.Issue


class SuggestFragment : BaseFragment() {
    private var binding: FragmentSuggestBinding? = null
    private lateinit var viewModel: SuggestViewModel
    private val suggestListAdapter by lazy { SuggestListAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSuggestBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SuggestViewModel::class.java)

        with(binding!!) {
            lifecycleOwner = viewLifecycleOwner

            recyclerList.layoutManager = LinearLayoutManager(context)
            recyclerList.adapter = suggestListAdapter
            recyclerList.setHasFixedSize(true)
        }

        return binding?.root
    }

    override fun loadData() {
        MyDialog.showLoadingDialog(this)
        viewModel.suggest.observe(viewLifecycleOwner, Observer {
            suggestListAdapter.issueList = it
            MyDialog.closeLoadingDialog()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

class SuggestViewModel : BaseViewModel() {

    val suggest = liveData {
        emit(RepositoryManager.getSuggest().issue)
    }

}

class SuggestListAdapter : RecyclerView.Adapter<SuggestHolder>() {

    var issueList: List<Issue>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestHolder {
        val binding = ViewSuggestListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuggestHolder(binding)
    }

    override fun getItemCount(): Int {
        return issueList?.size ?: 0
    }

    override fun onBindViewHolder(holder: SuggestHolder, position: Int) {
        val issue = issueList?.getOrNull(position)
        issue.let {
            holder.binding.tvStartDate.text = "開始日期："+it?.startDate
            holder.binding.tvEndDate.text = "結束日期："+it?.endDate
            holder.binding.tvTitle.text = it?.title
            holder.binding.tvDescriptionFilterHtml.text = it?.descriptionFilterHtml

        }
    }
}

class SuggestHolder(var binding: ViewSuggestListBinding) : RecyclerView.ViewHolder(binding.root)