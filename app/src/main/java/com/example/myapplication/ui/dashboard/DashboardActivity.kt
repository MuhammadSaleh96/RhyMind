package com.example.myapplication.ui.dashboard

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.ui.dashboard.fragments.HomeFragment
import com.example.myapplication.ui.dashboard.fragments.ScheduleFragment
import com.example.myapplication.ui.dashboard.fragments.SearchFragment
import com.example.myapplication.ui.dashboard.fragments.UserGroupsFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class DashboardActivity : AppCompatActivity() {

    private lateinit var bottomBar: AnimatedBottomBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.dashboard)

        bottomBar= findViewById(R.id.bottom_bar)

        replaceFragment(HomeFragment())

        bottomBar.setOnTabSelectListener(object:AnimatedBottomBar.OnTabSelectListener{
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when(newIndex){
                    0->replaceFragment(HomeFragment())
                        1->replaceFragment(ScheduleFragment())
                            2->replaceFragment(SearchFragment())
                                3->replaceFragment(UserGroupsFragment())
                    else->{

                    }
                }
            }
        })

    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragments, fragment).commit()
    }
}