package com.example.neostore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

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
