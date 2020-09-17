package cf.feuerkrieg.homeaccounting.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cf.feuerkrieg.homeaccounting.databinding.FragmentFlatDetailsLayoutBinding
import cf.feuerkrieg.homeaccounting.viewmodels.FlatListViewModel

class FragmentFlatDetails: Fragment() {

    private lateinit var viewModel: FlatListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val binding = FragmentFlatDetailsLayoutBinding.inflate(
            inflater, container, false
        )


        return binding.root
    }

}