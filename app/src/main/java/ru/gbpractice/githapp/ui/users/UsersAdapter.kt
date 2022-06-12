package ru.gbpractice.githapp.ui.users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gbpractice.githapp.domain.entities.UserEntity

class UsersAdapter: RecyclerView.Adapter<UserViewHolder>() {
    private val data = mutableListOf<UserEntity>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserEntity>){
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }
}