package cf.feuerkrieg.homeaccounting.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import cf.feuerkrieg.homeaccounting.R
import cf.feuerkrieg.homeaccounting.viewmodels.FlatDetailsViewModel

class FlatHostFragment: Fragment() {

    lateinit var viewModel: FlatDetailsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val args = FlatHostFragmentArgs.fromBundle(requireArguments())

        viewModel = ViewModelProvider(this)
            .get(FlatDetailsViewModel::class.java)


        //Create flat for adding or prepare existing flat for editing
        if (args.flat == null)
            viewModel.createFlat(args.home)
        else
            viewModel.setExistingFlat(args.flat)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(
            R.layout.fragment_flat_host_layout,
            container,
            false)


        val viewPager = view.findViewById<ViewPager>(R.id.flat_view_pager)

        viewPager.adapter = FlatFragmentAdapter(
            childFragmentManager,
            requireContext())

        viewPager.currentItem = 0

        return view

    }



    class FlatFragmentAdapter(fragmentManager: FragmentManager,
                        context: Context)
        : FragmentPagerAdapter(fragmentManager,
        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


        private val titles = context.resources
            .getStringArray(R.array.flat_pages)

        override fun getCount(): Int {
            return 4
        }

        override fun getItem(position: Int): Fragment {

            return when(position) {
                0 -> FlatDetailsFragment()
                1 -> FlatKitchenFragment()
                2 -> FlatBathroomFragment()
                3 -> FlatSummaryFragment()
                else -> throw IllegalArgumentException(
                    "There is no fragment at position: $position")
            }
        }


        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }

    }

}