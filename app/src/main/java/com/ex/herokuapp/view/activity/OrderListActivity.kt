package com.ex.herokuapp.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.ex.herokuapp.R
import com.ex.herokuapp.view.adapter.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.custom_tab.view.*


class OrderListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        //add toolbar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setSupportActionBar(toolbar)

        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabLayout: TabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)
        createTabIcons(tabLayout);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    private fun createTabIcons(tabLayout: TabLayout) {
        //Tab 1
        val tabOne: View = LayoutInflater.from(this).inflate(R.layout.custom_tab, null)
        tabOne.text_count.text = getString(R.string.text_zero)
        tabOne.text_name.text = resources.getString(R.string.tab_text_1)
        tabOne.img_icon.text = resources.getString(R.string.fa_sticky_note)
        tabLayout.getTabAt(0)?.customView = tabOne

        //Tab 2
        val tabTwo: View = LayoutInflater.from(this).inflate(R.layout.custom_tab, null)
        tabTwo.text_count.text = getString(R.string.text_two)
        tabTwo.text_name.text = resources.getString(R.string.tab_text_2)
        tabTwo.img_icon.text = resources.getString(R.string.fa_hand_point_up)
        tabTwo.isSelected = true
        tabLayout.getTabAt(1)?.select();
        tabLayout.getTabAt(1)?.customView = tabTwo

        //Tab 3
        val tabThree: View = LayoutInflater.from(this).inflate(R.layout.custom_tab, null)
        tabThree.text_count.text = getString(R.string.text_zero)
        tabThree.text_name.text = resources.getString(R.string.tab_text_3)
        tabThree.img_icon.text = resources.getString(R.string.fa_store_solid)
        tabLayout.getTabAt(2)?.customView = tabThree
    }
}