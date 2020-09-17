package cf.feuerkrieg.homeaccounting.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cf.feuerkrieg.homeaccounting.adapters.FlatAdapter
import cf.feuerkrieg.homeaccounting.databinding.FragmentFlatListLayoutBinding
import cf.feuerkrieg.homeaccounting.viewmodels.FlatListViewModel
import cf.feuerkrieg.homeaccounting.viewmodels.factories.FlatListViewModelFactory

class FlatListFragment: Fragment() {


    lateinit var viewModel: FlatListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val homeId = FlatListFragmentArgs.fromBundle(requireArguments()).homeId

        viewModel = ViewModelProvider(this,
            FlatListViewModelFactory(homeId))
            .get(FlatListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentFlatListLayoutBinding.inflate(
            inflater, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val flatAdapter = FlatAdapter()

        binding.rvFlats.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = flatAdapter
        }

        binding.btnAddFlat.setOnClickListener {

           /* val action = FlatListFragmentDirections
                .actionFragmentFlatListToFragmentFlatDetails(null)*/

        }

        return binding.root
    }

}