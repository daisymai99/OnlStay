package com.daisymai99.onlstay.frag

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.daisymai99.onlstay.*
import com.daisymai99.onlstay.databinding.FragmentAccount2Binding
import com.daisymai99.onlstay.model.SavedPreference


class AccountFragment : Fragment() {
    lateinit var binding: com.daisymai99.onlstay.databinding.FragmentAccount2Binding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccount2Binding.inflate(layoutInflater)

        binding.btnInfo.setOnClickListener { v ->
            startActivity(Intent(this.context,Login::class.java))

        }

        binding.btnOwner.setOnClickListener { v ->

            startActivity(Intent(this.context,MainActivity::class.java))
        }

        binding.btnClient.setOnClickListener {

            startActivity(Intent(this.context,Owner_main::class.java))
        }

        binding.txtUserName.text = SavedPreference.getUsername(this.requireContext())
        binding.txtUserEmail.text = SavedPreference.getEmail(this.requireContext())

        return binding.root
    }


}