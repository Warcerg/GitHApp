package ru.gbpractice.githapp.ui.users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gbpractice.githapp.domain.entities.UserEntity

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private val data = mutableListOf<UserEntity>()
    private var onUserClickListener: OnUserClickListener? = null


    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int) = getItem(position).id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), onUserClickListener)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserEntity>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }

    fun setOnUserClickListener(onUserClickListener: OnUserClickListener) {
        this.onUserClickListener = onUserClickListener
    }

    interface OnUserClickListener {
        fun onUserItemClick(userEntity: UserEntity)
    }

    private fun getItem(pos: Int) = data[pos]

}