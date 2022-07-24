package ru.gbpractice.githapp.ui.details

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo



class UserDetailsViewModel(
    private val userDetailsRepo: UserDetailsRepo
) : DetailsContract.ViewModel, ViewModel() {

    override val userLiveData: Observable<UserEntity> = BehaviorSubject.create()
    override val userDetailsLiveData: Observable<UserDetailsEntity> = BehaviorSubject.create()
    override val userRepoListLiveData: Observable<List<UserRepoEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val loadingLiveData: Observable<Boolean> = BehaviorSubject.create()


    override fun provideUserData(userEntity: UserEntity) {
        userLiveData.mutable().onNext(userEntity)
        provideUserDetails(userEntity)
        provideRepoList(userEntity)
    }

    private fun provideUserDetails(userEntity: UserEntity) {
        userDetailsRepo.getUserDetails(userEntity.login)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    userDetailsLiveData.mutable().onNext(it)
                },
                onError = {
                    errorLiveData.mutable().onNext(it)
                }
            )
    }

    private fun provideRepoList(userEntity: UserEntity) {
        loadingLiveData.mutable().onNext(true)
        userDetailsRepo.getUserRepoList(userEntity.login)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    loadingLiveData.mutable().onNext(false)
                    userRepoListLiveData.mutable().onNext(it)
                },
                onError = {
                    loadingLiveData.mutable().onNext(false)
                    errorLiveData.mutable().onNext(it)
                }
            )
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not mutableLiveData")
    }
}