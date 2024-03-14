package com.ft.tasbhi_widget.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

typealias InflateActivity<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB : ViewBinding>(
    private var inflate: InflateActivity<VB>
) : AppCompatActivity() {

    protected lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate.invoke(layoutInflater)
        setContentView(binding.root)
        onAfterCreate()
    }

    override fun onResume() {
        super.onResume()
        onAfterResume()
    }

    override fun onStop() {
        super.onStop()
        onAfterStop()
    }
    protected open fun onAfterCreate() {}
    protected open fun onAfterResume() {}
    protected open fun onAfterStop() {}

}