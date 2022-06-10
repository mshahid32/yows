package com.example.yows

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.yows.databinding.ActivityMainBinding
import com.example.yows.fragments.NotificationFragment
import com.example.yows.fragments.OrderFragment
import com.example.yows.fragments.ProfileFragment
import com.example.yows.fragments.StoreFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityMainBinding
    private val firstFragment= StoreFragment()
    private val secondFragment= OrderFragment()
    private val thirdFragment= NotificationFragment()
    private val fourthFragment= ProfileFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toggle = ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.open, R.string.close)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()

        binding.navmenu.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.stores->{
                    setCurrentFragment(firstFragment)
                    binding.drawer.closeDrawer(GravityCompat.START)
                }
                R.id.orders->{
                    setCurrentFragment(secondFragment)
                    binding.drawer.closeDrawer(GravityCompat.START)
                }
                R.id.notifications->{
                    setCurrentFragment(thirdFragment)
                    binding.drawer.closeDrawer(GravityCompat.START)
                }
                R.id.profile->{
                    setCurrentFragment(fourthFragment)
                    binding.drawer.closeDrawer(GravityCompat.START)
                }
            }
            true
        }

        setCurrentFragment(firstFragment)
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.stores->setCurrentFragment(firstFragment)
                R.id.orders->setCurrentFragment(secondFragment)
                R.id.notifications->setCurrentFragment(thirdFragment)
                R.id.profile->setCurrentFragment(fourthFragment)
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment).addToBackStack(null)
            commit()
        }
    }
    override fun onBackPressed() {
        if (binding.bottomNavigation.selectedItemId == R.id.stores){
            super.onBackPressed()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }else{
            binding.bottomNavigation.selectedItemId = R.id.stores
        }

    }
}


