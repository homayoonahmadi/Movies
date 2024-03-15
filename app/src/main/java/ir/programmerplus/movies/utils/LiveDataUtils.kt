package ir.programmerplus.movies.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * This function will observe live data once and then the observer will be removed
 *
 * @param observer observer listener object
 * @param <T>      type of live data object
</T> */
fun <T> LiveData<T>.observeOnce(observer: Observer<T>) {
    observeForever(object : Observer<T> {
        override fun onChanged(value: T) {
            removeObserver(this)
            observer.onChanged(value)
        }
    })
}