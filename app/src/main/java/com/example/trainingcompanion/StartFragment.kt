package com.example.trainingcompanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.trainingcompanion.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding:FragmentStartBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_start,
            container, false)
        binding.llStart.setOnClickListener { view:View ->
            Navigation.findNavController(view).navigate(R.id.action_startFragment_to_exerciseFragment)
            setToast()
        }
        return binding.root
    }
    private fun setToast(){

        Toast.makeText(activity,"Jumping Jacks",Toast.LENGTH_SHORT).show()

    }


}