package com.example.gitproject.view.baseActivity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gitproject.R
import com.example.gitproject.util.Constants

/*
* Abstract class for using the common code and app permission
* */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }


    abstract fun getLayoutId(): Int
    abstract fun init()

    /*
    * Extension function of adding a fragment
    * */
    public fun AppCompatActivity.addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainContainer, fragment, fragment::class.java.simpleName).commit()
    }

    /*
    * Extension function of adding a fragment and keep in backstack
    * */
    public fun AppCompatActivity.addFragment(fragment: Fragment, backstack: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
            .add(R.id.mainContainer, fragment, fragment::class.java.simpleName)
            .addToBackStack(backstack).commit()
    }


    override fun onSupportNavigateUp(): Boolean {

        if (Constants.TOOLBAR_BUTTON_CLICK) {
            onBackPressed()
        }

        return true
    }


}