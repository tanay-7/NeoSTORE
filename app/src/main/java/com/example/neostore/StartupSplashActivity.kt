package com.example.neostore

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.neostore.features.base.BaseActivity
import com.example.neostore.features.login.LoginActivity

class StartupSplashActivity : BaseActivity() {

    override var getLayout = R.layout.activity_startup_splash

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 4000

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }
}
