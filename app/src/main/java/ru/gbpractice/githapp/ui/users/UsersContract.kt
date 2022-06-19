package ru.gbpractice.githapp.ui.users

import ru.gbpractice.githapp.domain.entities.UserEntity

interface UsersContract {

    interface View {
        fun showUsersList(users: List<UserEntity>)
        fun showLoading(isLoading: Boolean)
        fun showError(t: Throwable)

    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun onRefreshUserList()
    }
}