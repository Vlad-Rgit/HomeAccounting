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
import cf.feuerkrieg.homeaccounting.R
import cf.feuerkrieg.homeaccounting.activities.MainActivity
import cf.feuerkrieg.homeaccounting.databinding.FragmentLoginLayoutBinding
import cf.feuerkrieg.homeaccounting.viewmodels.LoginViewModel

class LoginFragment: Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onAttach(context: Context) {

        super.onAttach(context)

        viewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)

        viewModel.loginSuccesful = {
            goToMainActivity()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = FragmentLoginLayoutBinding
            .inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.btnToRegister.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    private fun goToMainActivity(){
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

}