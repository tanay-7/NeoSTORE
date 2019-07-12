package com.example.neostore

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.neostore.ui.base.BaseActivity
import com.example.neostore.ui.mvp.login.LoginActivity

class StartupSplashActivity : BaseActivity() {
    override var getLayout = R.layout.activity_startup_splash

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 4000

    val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun init() {
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }


}
