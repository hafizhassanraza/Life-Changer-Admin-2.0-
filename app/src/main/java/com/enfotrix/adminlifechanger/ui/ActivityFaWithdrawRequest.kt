package com.enfotrix.adminlifechanger.ui

import User
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.enfotrix.adminlifechanger.Adapters.FaWithdrawViewPagerAdapter
import com.enfotrix.adminlifechanger.Adapters.WithdrawViewPagerAdapter
import com.enfotrix.adminlifechanger.Constants
import com.enfotrix.adminlifechanger.Models.InvestmentViewModel
import com.enfotrix.adminlifechanger.Models.NomineeViewModel
import com.enfotrix.adminlifechanger.R
import com.enfotrix.adminlifechanger.databinding.ActivityFaWithdrawRequestBinding
import com.enfotrix.adminlifechanger.databinding.ActivityWithdrawRequestBinding
import com.enfotrix.lifechanger.Models.UserViewModel
import com.enfotrix.lifechanger.SharedPrefManager
import com.enfotrix.lifechanger.Utils
import com.google.android.material.tabs.TabLayoutMediator

class ActivityFaWithdrawRequest : AppCompatActivity() {


    private val userViewModel: UserViewModel by viewModels()
    private val nomineeViewModel: NomineeViewModel by viewModels()
    private val investmentViewModel: InvestmentViewModel by viewModels()
    var constant= Constants()



    private lateinit var binding: ActivityFaWithdrawRequestBinding
    private lateinit var utils: Utils
    private lateinit var mContext: Context
    private lateinit var constants: Constants
    private lateinit var user: User
    private lateinit var sharedPrefManager : SharedPrefManager
    private lateinit var dialog : Dialog




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityFaWithdrawRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Investment Request"

        mContext=this@ActivityFaWithdrawRequest
        utils = Utils(mContext)
        constants= Constants()
        sharedPrefManager = SharedPrefManager(mContext)



        setTitle("WithDraw")
        setupViewPager()
        setupTabLayout()

    }
    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if(position==0) tab.text ="Pending"
            else if(position==1) tab.text="Approved"
        }.attach()
    }

    private fun setupViewPager() {
        val adapter = FaWithdrawViewPagerAdapter(this, 2)
        binding.viewPager.adapter = adapter
    }

    override fun onBackPressed() {
        val viewPager = binding.viewPager
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}