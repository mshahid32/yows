package com.example.yows.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yows.R
import com.example.yows.RestourantActivity
import com.example.yows.adapters.ImageAdapter
import com.example.yows.databinding.FragmentStoreBinding
import java.util.*


class StoreFragment : Fragment() {
    private lateinit var binding: FragmentStoreBinding
    var timer: Timer? = null
    var handler: Handler? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStoreBinding.inflate(inflater,container,false)

        val imageList: MutableList<Int> = ArrayList()
        imageList.add(R.drawable.one)
        imageList.add(R.drawable.two)
        imageList.add(R.drawable.three)
        imageList.add(R.drawable.four)
        val myAdapter = ImageAdapter(imageList)
        binding.imgPager.adapter = myAdapter
        handler = Handler()
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler!!.post {
                    var i: Int = binding.imgPager.currentItem
                    if (i == imageList.size - 1) {
                        i = 0
                    } else {
                        i++
                    }
                    binding.imgPager.setCurrentItem(i, true)
                }
            }
        }, 4000, 4000)

        binding.cdRestourant.setOnClickListener {
            startActivity(Intent(activity,RestourantActivity::class.java))
        }

        return binding.root
    }

}