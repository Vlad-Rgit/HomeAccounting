package cf.feuerkrieg.homeaccounting.fragments

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import cf.feuerkrieg.homeaccounting.R
import cf.feuerkrieg.homeaccounting.adapters.HomeAdapter
import cf.feuerkrieg.homeaccounting.databinding.FragmentHomeListLayoutBinding
import cf.feuerkrieg.homeaccounting.decorators.PaddingDecorator
import cf.feuerkrieg.homeaccounting.models.Home
import cf.feuerkrieg.homeaccounting.viewmodels.HomeListViewModel
import kotlinx.android.synthetic.main.fragment_home_list_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeListFragment: Fragment() {

    private lateinit var searchItem: SearchView
    private lateinit var refreshItem: MenuItem
    private lateinit var homeAdapter: HomeAdapter

    lateinit var viewModel: HomeListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this)
            .get(HomeListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)

        val binding = FragmentHomeListLayoutBinding.inflate(
            inflater, container, false)

        homeAdapter = HomeAdapter(requireContext(), this::onHomeClick)

        binding.rvHomes.apply {

            layoutManager = GridLayoutManager(context, 3)

            setHasFixedSize(true)

            addItemDecoration(PaddingDecorator())

            adapter = homeAdapter
        }

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        syncing_host.viewTreeObserver
            .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {

                val height = syncing_host.height

                val revealCardAnimator = ValueAnimator.ofInt(0, height).apply {
                    addUpdateListener {
                        val value = it.animatedValue as Int
                        val layoutParams = syncing_host.layoutParams
                        layoutParams.height = value
                        syncing_host.layoutParams = layoutParams
                    }

                    interpolator = AccelerateDecelerateInterpolator()
                }

                btn_close_syncing.setOnClickListener {
                    revealCardAnimator.reverse()
                }

                viewModel.isSyncing.observe(viewLifecycleOwner) {
                    if(it)
                        revealCardAnimator.start()
                    else
                        revealCardAnimator.reverse()
                }

                syncing_host.viewTreeObserver
                    .removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun onHomeClick(home: Home) {

        val action = HomeListFragmentDirections
            .actionHomeListFragmentToFragmentFlatList(home.homeId)

        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.home_menu, menu)

        searchItem = menu.findItem(R.id.app_bar_home_search)
            .actionView as SearchView


        searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                p0?.let {
                    lifecycleScope.launch(Dispatchers.Default) {
                        homeAdapter.filterByName(viewModel.homes.value!!.toMutableList(), it)
                    }
                }

                return true
            }

        })

        refreshItem = menu.findItem(R.id.app_bar_home_refresh)
        refreshItem.setOnMenuItemClickListener {
            if(!viewModel.isSyncing.value!!
                || viewModel.hasSyncingError.value!!)
                viewModel.refreshHomes()
            return@setOnMenuItemClickListener true
        }

    }


}