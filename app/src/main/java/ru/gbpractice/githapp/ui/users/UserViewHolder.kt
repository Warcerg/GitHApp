package ru.gbpractice.githapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.gbpractice.githapp.R
import ru.gbpractice.githapp.databinding.ItemUserBinding
import ru.gbpractice.githapp.domain.entities.UserEntity

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)

    fun bind(userEntity: UserEntity, onUserClickListener: UsersAdapter.OnUserClickListener?) {
        binding.avatarImageView.load(userEntity.avatarUrl)
        binding.loginTextView.text = userEntity.login
        binding.uidTextView.text = userEntity.id.toString()
        binding.root.setOnClickListener { onUserClickListener?.onUserItemClick(userEntity) }
    }
}