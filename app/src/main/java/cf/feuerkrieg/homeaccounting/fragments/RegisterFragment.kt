package cf.feuerkrieg.homeaccounting.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import cf.feuerkrieg.homeaccounting.activities.MainActivity
import cf.feuerkrieg.homeaccounting.databinding.FragmentRegisterLayoutBinding
import cf.feuerkrieg.homeaccounting.viewmodels.RegisterViewModel

class RegisterFragment: Fragment() {

    private lateinit var viewModel: RegisterViewModel

    override fun onAttach(context: Context) {

        super.onAttach(context)

        viewModel = ViewModelProvider(this)
            .get(RegisterViewModel::class.java)

        viewModel.registerSuccesfull = {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = FragmentRegisterLayoutBinding.inflate(
            inflater, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.btnToLogin.setOnClickListener {
            it.findNavController().navigateUp()
        }

        return binding.root
    }
}