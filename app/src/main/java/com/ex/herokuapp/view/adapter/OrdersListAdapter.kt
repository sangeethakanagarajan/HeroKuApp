package com.ex.herokuapp.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ex.herokuapp.R
import com.ex.herokuapp.network.model.Orders
import com.ex.herokuapp.util.DateUtils
import com.ex.herokuapp.view.activity.OrderDetailActivity
import com.ex.herokuapp.view.fragment.OrdersListFragment
import kotlinx.android.synthetic.main.row_item_order.view.*

class OrdersListAdapter(val ordersListFragment: OrdersListFragment) :
    RecyclerView.Adapter<OrdersListAdapter.OrdersListViewHolder>() {

    private var list: List<Orders> = ArrayList<Orders>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersListViewHolder {
        val view = LayoutInflater.from(ordersListFragment.requireContext())
            .inflate(R.layout.row_item_order, parent, false)
        return OrdersListViewHolder(view, ordersListFragment.requireContext())
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OrdersListViewHolder, position: Int) {
        val orders: Orders = list[position]
        holder.bind(orders, ordersListFragment.requireContext())
    }

    fun setNewsList(list: List<Orders>?) {
        if (list != null) {
            this.list = list
        }
        notifyDataSetChanged()
    }

    class OrdersListViewHolder(itemView: View, context: Context) :
        RecyclerView.ViewHolder(itemView) {

        private var orders: Orders? = null

        init {
            itemView.setOnClickListener {
                orders.let {
                    val intent = Intent(context, OrderDetailActivity::class.java)
                    intent.putExtra("webOrderId", orders?.webOrderId)
                    intent.putExtra("totalOrderQty", orders?.totalOrderQty)
                    intent.putExtra("sapBlacklistTime", orders?.sapBlacklistTime)
                    context.startActivity(intent)
                }
            }

        }

        fun bind(
            orders: Orders,
            context: Context
        ) {
            this.orders = orders
            itemView.text_total_order_qty.text = orders.totalOrderQty.toString()
            itemView.text_web_order_id.text = orders.webOrderId
            itemView.text_blacklist_time.text = String.format(
                context.resources.getString(R.string.item_time),
                DateUtils.getTime(orders.sapBlacklistTime)
            )

        }
    }
}