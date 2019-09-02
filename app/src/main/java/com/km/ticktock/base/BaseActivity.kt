package com.km.ticktock.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutRes: Int
    abstract val isUseDatabinding: Boolean

    inline fun <reified T : Activity>
            Context.startActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (layoutRes != 0) {
            if (!isUseDatabinding)
                setContentView(layoutRes)
            else
                onDataBinding()
        }

        setupViews()
    }

    open fun onDataBinding() {

    }

    // 뷰 컴포넌트 설정
    abstract fun setupViews()
}