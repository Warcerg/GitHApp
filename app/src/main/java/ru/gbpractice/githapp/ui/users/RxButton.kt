package ru.gbpractice.githapp.ui.users

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import io.reactivex.rxjava3.core.Observable

class RxButton: AppCompatButton {

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    fun buttonClickObservable(): Observable<Unit>{
        return  Observable.create { emitter ->
            setOnClickListener {
                emitter.onNext(Unit)
            }
        }
    }

}