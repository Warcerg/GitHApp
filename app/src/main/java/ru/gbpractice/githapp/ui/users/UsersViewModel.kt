package ru.gbpractice.githapp.ui.users

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.repos.UserListRepo
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userListRepo: UserListRepo
) : UsersContract.ViewModel, ViewModel() {

    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val loadingLiveData: Observable<Boolean> = BehaviorSubject.create()
    override val showUserDetailsLiveData: Observable<UserEntity> = BehaviorSubject.create()


    override fun onRefreshUserList() {
        loadingLiveData.mutable().onNext(true)
        userListRepo.getUserList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
            onSuccess = {
                loadingLiveData.mutable().onNext(false)
                usersLiveData.mutable().onNext(it)

            },
            onError = {
                loadingLiveData.mutable().onNext(false)
                errorLiveData.mutable().onNext(it)
            }
        )
    }

    override fun onSelectUser(userEntity: UserEntity) {
        showUserDetailsLiveData.mutable().onNext(userEntity)
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not mutableLiveData")
    }
}