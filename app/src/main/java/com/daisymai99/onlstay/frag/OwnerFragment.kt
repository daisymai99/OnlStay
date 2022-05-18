package com.daisymai99.onlstay.frag

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.daisymai99.onlstay.Owner_room
import com.daisymai99.onlstay.adapter.AdsAdapter
import com.daisymai99.onlstay.adapter.OwnerRoomAdapter
import com.daisymai99.onlstay.databinding.FragmentOwnerBinding
import com.daisymai99.onlstay.model.SavedPreference

class OwnerFragment :Fragment() {

    lateinit var binding: FragmentOwnerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerBinding.inflate(layoutInflater)

        binding.btnFloating.setOnClickListener {
            startActivity(Intent(this.context,Owner_room::class.java))
        }

        binding.txtUserEmail.text = SavedPreference.getEmail(this.requireContext())
        binding.txtUserName.text = SavedPreference.getUsername(this.requireContext())

        var listRoom = OwnerRoomAdapter()
        binding.rclRoom.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        binding.rclRoom.setHasFixedSize(true)
        binding.rclRoom.adapter = listRoom

        return binding.root
    }
}