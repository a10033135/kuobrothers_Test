package com.sample.fragment

import android.os.Bundle
import android.util.Log
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
import com.sample.databinding.ViewIssueListBinding
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
        viewModel.issues = liveData {
            emit(RepositoryManager.getSuggest().issues)
            MyDialog.closeLoadingDialog()
        }

        viewModel.issues.observe(viewLifecycleOwner, Observer {
            suggestListAdapter.issues = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.recyclerList?.adapter = null
        binding = null
    }
}

class SuggestViewModel : BaseViewModel() {

    var issues = liveData {
        emit(listOf<Issue>())
    }

}

class SuggestListAdapter : RecyclerView.Adapter<SuggestHolder>() {

    var issues: List<Issue>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestHolder {
        val binding = ViewIssueListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuggestHolder(binding)
    }

    override fun onBindViewHolder(holder: SuggestHolder, position: Int) {
        val issue = issues?.getOrNull(position)
        issue.let {
            holder.binding.tvStartDate.text = "開始日期：" + it?.startDate
            holder.binding.tvEndDate.text = "結束日期：" + it?.endDate
            holder.binding.tvTitle.text = it?.title
            holder.binding.tvDescriptionFilterHtml.text = it?.descriptionFilterHtml

        }
    }

    override fun getItemCount(): Int {
        return issues?.size ?: 0
    }
}

/* EventFragment 使用 findViewById ，考量此Adapter為專用，因此仍使用 viewBinding 形式 */
class SuggestHolder(var binding: ViewIssueListBinding) : RecyclerView.ViewHolder(binding.root)