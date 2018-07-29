package com.pt29.crypto.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent



/**
 * Created by parham on 2/23/2018.
 */

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()

    }
}
