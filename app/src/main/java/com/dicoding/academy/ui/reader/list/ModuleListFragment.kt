package com.dicoding.academy.ui.reader.list

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academy.R
import com.dicoding.academy.data.source.local.entity.ModuleEntity
import com.dicoding.academy.ui.reader.CourseReaderActivity
import com.dicoding.academy.ui.reader.CourseReaderCallback
import com.dicoding.academy.ui.reader.CourseReaderViewModel
import com.dicoding.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.content_detail_course.*
import kotlinx.android.synthetic.main.fragment_module_list.progress_bar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ModuleListFragment : Fragment(), MyAdapterClickListener {
    private lateinit var myActivity : Activity;

    companion object {
        val TAG = ModuleListFragment::class.java.simpleName

        fun newInstance() : ModuleListFragment = ModuleListFragment()
    }

    private lateinit var viewModel : CourseReaderViewModel

    private lateinit var adapters : ModuleListAdapter
    private lateinit var couseReaderCallback : CourseReaderCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
        adapters = ModuleListAdapter(this)

        progress_bar.visibility = View.VISIBLE
        viewModel.getModules().observe(this, Observer { modules ->
            progress_bar.visibility = View.GONE
            populateRecylerView(modules)
        })
    }

    override fun onAttach(context : Context) {
        super.onAttach(context)
        couseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        couseReaderCallback.moveTo(position, moduleId)
        viewModel.setSelectedModule(moduleId)
    }

    private fun populateRecylerView(modules: List<ModuleEntity>) {
        progress_bar.visibility = View.GONE
        adapters.setModules(modules)
        rv_module.layoutManager = LinearLayoutManager(context);
        rv_module.setHasFixedSize(true)
        rv_module.adapter = adapters

        val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
        rv_module.addItemDecoration(dividerItemDecoration)
    }
}