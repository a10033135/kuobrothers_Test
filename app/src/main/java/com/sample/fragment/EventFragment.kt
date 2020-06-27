package com.sample.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.R
import com.sample.utils.NetWork
import com.sample.api.RepositoryManager
import com.sample.base.BaseFragment
import com.sample.base.BaseViewModel
import com.sample.databinding.FragmentEventBinding
import com.sample.dialog.MyDialog
import com.sample.module.Event

class EventFragment : BaseFragment() {
    private var binding: FragmentEventBinding? = null
    private lateinit var viewModel: EventViewModel
    private val eventAdapter by lazy { EventListAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        binding = FragmentEventBinding.inflate(inflater, container, false)
        with(binding!!) {
            lifecycleOwner = viewLifecycleOwner

            recyclerList.layoutManager = LinearLayoutManager(context)
            recyclerList.setHasFixedSize(true)
            recyclerList.adapter = eventAdapter
        }

        return binding?.root
    }

    override fun loadData() {
        MyDialog.showLoadingDialog(this)
        viewModel.events.observe(viewLifecycleOwner, Observer {
            eventAdapter.events = it
            MyDialog.closeLoadingDialog()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.recyclerList?.adapter = null
        binding = null
    }
}

class EventViewModel : BaseViewModel() {
    val events = liveData {
        emit(RepositoryManager.getEventList())
    }
}

class EventListAdapter : RecyclerView.Adapter<BaseHolder>() {
    var events: List<Event>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_event_list, parent, false)

        return BaseHolder(view)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        val event = events?.getOrNull(position)
        event?.let {
            holder.title.text = it.subject
            holder.content.text = Html.fromHtml(it.detailContent)
        }
    }

    override fun getItemCount() = events?.size ?: 0
}

class BaseHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.tv_title)
    val content: TextView = view.findViewById(R.id.tv_content)
}
