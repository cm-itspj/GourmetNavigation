package jp.co.cybermissions.itspj.android.gourmetnavigation.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import jp.co.cybermissions.itspj.android.gourmetnavigation.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    /** ViewModel */
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO
        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)

        viewModel.navigateToSelectedRest.observe(this, Observer {  })

        return binding.root
    }
}