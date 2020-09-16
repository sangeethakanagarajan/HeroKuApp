package com.ex.herokuapp.view.activity

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex.herokuapp.R
import com.ex.herokuapp.util.DateUtils
import com.ex.herokuapp.view.adapter.ArticlesListAdapter
import com.ex.herokuapp.view.fragment.CustomDialogFragment
import com.ex.herokuapp.viewmodel.ArticlesListViewModel
import kotlinx.android.synthetic.main.activity_detail.*


class OrderDetailActivity : AppCompatActivity() {

    private var sapBlacklistTime: String? = null
    private var totalOrderQty: String? = null
    private var webOrderId: String? = null
    private lateinit var articlesAdapter: ArticlesListAdapter
    private lateinit var articlesListViewModel: ArticlesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //add toolbar
        actionBar?.setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(android.R.drawable.arrow_down_float);
        toolbar.title = resources.getString(R.string.pick)
        actionBar?.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar)


        val extras = intent.extras
        if (extras != null) {
            webOrderId = extras.getString("webOrderId")
            totalOrderQty = extras.getString("totalOrderQty")
            sapBlacklistTime = extras.getString("sapBlacklistTime")
        }

        text_time.text = String.format(
            resources.getString(R.string.item_time),
            DateUtils.getTime(sapBlacklistTime)
        )

        text_basket.text = "test"
        text_web_orderid.text = webOrderId

        articlesListViewModel =
            ViewModelProvider(this).get(ArticlesListViewModel::class.java)
        if (savedInstanceState == null) {
            webOrderId?.let { articlesListViewModel.getArticlesList(it) }
        }

        articlesAdapter = ArticlesListAdapter(this)
        recycler_order_detail.adapter = articlesAdapter
        recycler_order_detail.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        articlesListViewModel.articlesList.observe(this, Observer {
            articlesAdapter.setArticlesList(it)

        })

        img_printer.setOnClickListener {
            val ft = getSupportFragmentManager().beginTransaction()
            val dialogFragment = CustomDialogFragment.newInstance("Scan Printer QR's code or")
            dialogFragment.show(ft, "Printer")

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onUserSelectValue(value: Boolean) {
        if (value) {
            img_printer.setTextColor(Color.GREEN)
        } else {
            img_printer.setTextColor(Color.DKGRAY)
        }
    }
}

