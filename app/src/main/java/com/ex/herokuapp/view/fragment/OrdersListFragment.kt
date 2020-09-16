package com.ex.herokuapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.ex.herokuapp.R
import com.ex.herokuapp.view.adapter.OrdersListAdapter
import com.ex.herokuapp.viewmodel.OrdersListViewModel
import kotlinx.android.synthetic.main.fragment_order_list.*

class OrdersListFragment : Fragment() {
    private lateinit var orderAdapter: OrdersListAdapter

    companion object {
        fun newInstance() = OrdersListFragment()
    }

    internal lateinit var ordersListViewModel: OrdersListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ordersListViewModel = ViewModelProvider(this).get(OrdersListViewModel::class.java)

        if (ordersListViewModel.isNetworkConnected(this.requireContext())) {
            ordersListViewModel.getOrdersList()
        } else {
            AlertDialog.Builder(this.requireContext()).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        orderAdapter = OrdersListAdapter(this)
        recycler_order.adapter = orderAdapter

        recycler_order.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        ordersListViewModel.ordersList.observe(viewLifecycleOwner, Observer {
            orderAdapter.setNewsList(it)
        })
    }

}