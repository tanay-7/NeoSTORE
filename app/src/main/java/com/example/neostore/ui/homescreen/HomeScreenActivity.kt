package com.example.neostore.ui.homescreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.viewpager.widget.ViewPager
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.example.neostore.ui.base.BaseActivity
import com.example.neostore.ui.mvp.login.LoginActivity
import com.example.neostore.ui.mvp.productlisting.ProductListingActivity
import com.example.neostore.ui.mvvm.mycart.MyCartActivity
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.activity_navigation_drawer.*
import kotlinx.android.synthetic.main.app_bar_navigation_drawer.*
import kotlinx.android.synthetic.main.content_navigation_drawer.*
import java.util.*
import kotlin.collections.ArrayList

class HomeScreenActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, OnMenuItemListener {
    override var getLayout = R.layout.activity_navigation_drawer
    internal lateinit var viewPager: ViewPager
    private var imageModelArrayList: ArrayList<ImageModel>? = null

    private val myimagelist =
        intArrayOf(R.drawable.slider_img1, R.drawable.slider_img2, R.drawable.slider_img3, R.drawable.slider_img4)
    val menu: ArrayList<DrawerModel> = ArrayList()

    override fun init() {
        val toolbar: Toolbar = toolbar
        setSupportActionBar(toolbar)
        val action_bar = supportActionBar
        action_bar!!.setDisplayShowTitleEnabled(false)

        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()
        showPager()

        //Creation of Navigation Drawer
        addMenu()

        var drawerView = layoutInflater.inflate(R.layout.layout_recycler, null)

        nav_view.addView(drawerView)

        var rvMenuitem = drawerView.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_menuitem)

        // Creates a vertical Layout Manager
        rvMenuitem.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        rvMenuitem.adapter = DrawerAdapter(menu, this, this)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        table_icon.onClick {
            var bundle = Bundle()
            bundle.putString("product_id", "1")
            val intent = Intent(this, ProductListingActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        chair_icon.onClick {
            var bundle = Bundle()
            bundle.putString("product_id", "2")
            val intent = Intent(this, ProductListingActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        sofa_icon.onClick {
            var bundle = Bundle()
            bundle.putString("product_id", "3")
            val intent = Intent(this, ProductListingActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        cupboards_icon.onClick {
            var bundle = Bundle()
            bundle.putString("product_id", "4")
            val intent = Intent(this, ProductListingActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search_icon_menu, menu)
        return true
    }

    fun addMenu() {
        menu.add(DrawerModel("My Cart", R.drawable.ic_cart))
        menu.add(DrawerModel("Tables", R.drawable.ic_table))
        menu.add(DrawerModel("Sofas", R.drawable.ic_sofa))
        menu.add(DrawerModel("Chairs", R.drawable.ic_chair))
        menu.add(DrawerModel("Cupboards", R.drawable.ic_cupboard))
        menu.add(DrawerModel("My Account", R.drawable.ic_myaccount))
        menu.add(DrawerModel("Store Locator", R.drawable.ic_storelocator))
        menu.add(DrawerModel("My Orders", R.drawable.ic_myorders))
        menu.add(DrawerModel("Logout", R.drawable.ic_logout))
    }

    override fun onMenuItemClick(position: Int) {
        Log.d("POSITION", "pos is ${position}")
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

        //Logout
        if (position == 8) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        if (position == 0) {
            val intent = Intent(this, MyCartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun populateList(): ArrayList<ImageModel> {
        Log.d("Tag", "populate method")
        val list = ArrayList<ImageModel>()
        for (i in 0..myimagelist.size - 1) {
            var imageModel = ImageModel()
            imageModel.image_drawable = myimagelist[i]
            list.add(imageModel)
        }
        return list
    }

    private fun showPager() {
        var mPager = findViewById(R.id.viewPager) as ViewPager
        mPager.adapter = SlidingImageAdapter(this, this.imageModelArrayList!!)
        val indicator = findViewById(R.id.indicator) as CirclePageIndicator
        indicator.setViewPager(mPager)
        // val density=resources.displayMetrics.density
        indicator.radius
        NUM_PAGES = imageModelArrayList!!.size

        val handler = Handler()
        val update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 1000, 1000)


        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                currentPage = p0
            }
        })
    }

    companion object {

        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }
}
