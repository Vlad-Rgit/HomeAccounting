package cf.feuerkrieg.homeaccounting.fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cf.feuerkrieg.homeaccounting.R
import cf.feuerkrieg.homeaccounting.databinding.FragmentFlatDetailsLayoutBinding
import cf.feuerkrieg.homeaccounting.viewmodels.FlatDetailsViewModel

class FlatDetailsFragment: Fragment() {

    private lateinit var viewModel: FlatDetailsViewModel
    private lateinit var binding: FragmentFlatDetailsLayoutBinding

    override fun onAttach(context: Context) {

        super.onAttach(context)

        viewModel = ViewModelProvider(requireParentFragment())
            .get(FlatDetailsViewModel::class.java)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        binding = FragmentFlatDetailsLayoutBinding.inflate(
            inflater, container, false
        )

        initTextInputLayouts()
        initSpinner()

        return binding.root
    }


    /**
     * Init TextInputLayouts of the FragmentFlatDetails
     */
    private fun initTextInputLayouts() {


        //Set entrance field
        val entranceAdapter = ArrayAdapter<Int>(requireContext(),
            android.R.layout.select_dialog_item)

        entranceAdapter.addAll((1..8).toList())

        binding.txtLayoutEntrance.setEndIconOnClickListener {
           AlertDialog.Builder(requireContext())
                .setTitle(requireContext().getString(R.string.entrance))
                .setAdapter(entranceAdapter) {
                        dialog: DialogInterface, i: Int ->
                    val value = entranceAdapter.getItem(i)
                    binding.edEntrance.setText(value.toString())
                    dialog.dismiss()
                }
                .show()
        }

        //Set floor field
        val floorAdapter = ArrayAdapter<Int>(requireContext(),
            android.R.layout.select_dialog_item)

        floorAdapter.addAll((1..5).toList())

        binding.txtLayoutFloor.setEndIconOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(requireContext().getString(R.string.floor))
                .setAdapter(floorAdapter) {
                        dialog: DialogInterface, i: Int ->
                    val value = floorAdapter.getItem(i)
                    binding.edFloor.setText(value.toString())
                    dialog.dismiss()
                }
                .show()
        }


        //Set post field
        val postAdapter = ArrayAdapter<Int>(requireContext(),
            android.R.layout.select_dialog_item)

        postAdapter.addAll((1..8).toList())

        binding.txtLayoutPost.setEndIconOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(requireContext().getString(R.string.post))
                .setAdapter(postAdapter) {
                        dialog: DialogInterface, i: Int ->
                    val value = postAdapter.getItem(i)
                    binding.edPost.setText(value.toString())
                    dialog.dismiss()
                }
                .show()
        }

    }

    /**
     * Init Spinner of the FragmentFlatDetails
     */
    private fun initSpinner() {

        val spinnerAdapter = ArrayAdapter<String>(requireContext(),
                    android.R.layout.simple_spinner_item)


        spinnerAdapter.addAll(
            requireContext().getString(R.string.no),
            requireContext().getString(R.string.yes))

        spinnerAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerHasAccessToFlat.apply {

            adapter = spinnerAdapter

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(adapter: AdapterView<*>?,
                                            view: View?,
                                            position: Int,
                                            id: Long) {

                    //No has position 0 (false) and yes has position 1 (true)
                    viewModel.hasFlatAccess.value = position.toByte()
                }

                override fun onNothingSelected(adapter: AdapterView<*>?) {

                }

            }

            setSelection(viewModel.hasFlatAccess.value!!.toInt())
        }


    }
}