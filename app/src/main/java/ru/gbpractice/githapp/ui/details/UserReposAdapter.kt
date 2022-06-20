package ru.gbpractice.githapp.ui.details

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gbpractice.githapp.domain.entities.UserRepoEntity

class UserReposAdapter: RecyclerView.Adapter<UserRepoViewHolder>() {
    private val data = mutableListOf<UserRepoEntity>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        return UserRepoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(repos: List<UserRepoEntity>){
        data.clear()
        data.addAll(repos)
        notifyDataSetChanged()
    }
}