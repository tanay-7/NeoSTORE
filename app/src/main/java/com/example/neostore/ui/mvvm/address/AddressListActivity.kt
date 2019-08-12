package com.example.neostore.ui.mvvm.address

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Paint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.example.neostore.ui.base.BaseActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class AddressListActivity : BaseActivity() {
    override var getLayout = R.layout.activity_address_list
    private lateinit var mAddressViewModel: AddressViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AddressListAdapter
    private val p = Paint()

    override fun init() {
        setMyActionBar()
        initViewModel()
        toolbarSetting()
        //onItemCloseBtnClick()
    }

    /*private fun onItemCloseBtnClick() {
        iv_close_item.onClick {

        }
    }*/

    private fun initRecyclerView() {
        mRecyclerView = findViewById(R.id.rv_address_list)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setRecyclerViewAdapter() {
        mAdapter = AddressListAdapter()
        mRecyclerView.adapter = mAdapter
    }

    private fun initViewModel() {
        //As we are providing this, The Android System will get to know that which lifecycle it has to scoped with.
        //Android System will destroy the ViewModel when Activity is finished.
        initRecyclerView()
        mAddressViewModel = ViewModelProviders.of(this).get(AddressViewModel::class.java)
        mAddressViewModel.getAllAddresses().observe(this,
            Observer<List<AddressEntity>> {
                //Update RecyclerView.
                setRecyclerViewAdapter()
                mAdapter.setItems(it)
            })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                mAddressViewModel.delete(mAdapter.getItem(p0.adapterPosition))
            }

        }).attachToRecyclerView(mRecyclerView)
    }

    private fun toolbarSetting() {
        txt_product_toolbar.text = "Address List"
        iv_back_button.onClick {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_icon_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_add_item ->
                startActivity(Intent(this, AddAddressActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
