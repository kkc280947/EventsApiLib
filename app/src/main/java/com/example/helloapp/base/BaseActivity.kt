package com.example.helloapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity: AppCompatActivity(){

    protected fun swapFragment(
        fragment: Fragment, addToBackStack: Boolean
    ) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()

        fragmentTransaction.replace(getContainerViewId(), fragment)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    abstract fun getContainerViewId(): Int
}