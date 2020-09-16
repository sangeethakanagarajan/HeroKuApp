package com.ex.herokuapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ex.herokuapp.R
import com.ex.herokuapp.network.model.ArticlesList
import com.ex.herokuapp.network.model.Data
import kotlinx.android.synthetic.main.row_item_article.view.*

class ArticlesListAdapter(val context: Context) :
    RecyclerView.Adapter<ArticlesListAdapter.OrdersListViewHolder>() {

    private var data: List<Data> = ArrayList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_article, parent, false)
        return OrdersListViewHolder(view, context)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: OrdersListViewHolder, position: Int) {
        val data: Data = data[position]
        holder.bind(data, context)
    }

    fun setArticlesList(list: ArticlesList?) {
        if (list != null) {
            data = list.data
        }
        notifyDataSetChanged()
    }

    class OrdersListViewHolder(itemView: View, context: Context?) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var article: Data

        fun bind(data: Data, context: Context) {
            this.article = data
            itemView.text_title.text = article.article.articleGroup
            itemView.text_desc.text = article.article.articleDesc
            itemView.text_articleid.text = article.articleId
            itemView.text_count.text = String.format(
                context.resources.getString(R.string.item_order_stock),
                article.articleStockQty,
                article.orderQty
            )

            Glide.with(itemView)
                .load(article.article.image)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .fallback(R.drawable.placeholder)
                .into(itemView.img_item)
        }
    }
}