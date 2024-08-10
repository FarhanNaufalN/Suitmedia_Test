package com.example.km_test_suitmedia.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.km_test_suitmedia.core.response.Data
import com.example.km_test_suitmedia.databinding.ItemUserBinding
import com.example.km_test_suitmedia.ui.PagetwoActivity

class UserAdapter(private val name: String) : PagingDataAdapter<Data, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, name)
        }
    }

    class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data, name: String) {
            binding.apply {
                firstname.text = item.first_name
                lastname.text = item.last_name
                email.text = item.email
                Glide.with(itemView.context).load(item.avatar).into(Image)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, PagetwoActivity::class.java).apply {
                        putExtra("name", name)
                        putExtra("namechoosen", "${item.first_name} ${item.last_name}")
                    }
                    println("Sending name: $name")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }
}
