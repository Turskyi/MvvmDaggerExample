package io.github.turskyi.mvvmdaggerexample.utils

import android.view.View
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import io.github.turskyi.mvvmdaggerexample.utils.extension.getParentActivity

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity = view.getParentActivity()
    parentActivity?.let { activity ->
        visibility?.observe(activity, Observer { visibilityState ->
            view.visibility = visibilityState ?: VISIBLE
        })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity = view.getParentActivity()
    parentActivity?.let { activity ->
        text?.observe(activity, Observer { text ->
            view.text = text ?: ""
        })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}