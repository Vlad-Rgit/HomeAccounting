package cf.feuerkrieg.homeaccounting.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cf.feuerkrieg.homeaccounting.databinding.FragmentFlatSummaryLayoutBinding
import cf.feuerkrieg.homeaccounting.viewmodels.FlatDetailsViewModel

class FlatSummaryFragment: Fragment() {
    private lateinit var viewModel: FlatDetailsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(requireParentFragment())
            .get(FlatDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFlatSummaryLayoutBinding
            .inflate(inflater, container, false)

        return binding.root
    }
}