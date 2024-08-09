package com.example.test_xpayback.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.example.test_xpayback.R


import com.example.test_xpayback.fragment.UserListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UserListFragment())
                .commit()
        }
    }
}
