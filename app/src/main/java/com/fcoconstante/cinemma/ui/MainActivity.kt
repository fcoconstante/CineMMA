package com.fcoconstante.cinemma.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.fcoconstante.cinemma.R
import com.fcoconstante.cinemma.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val SPLASH_LENGTH = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val anim = AnimationUtils.loadAnimation(this,R.anim.anim)
        imgSplash.startAnimation(anim)

        Handler().postDelayed({
          Intent(this,HomeActivity::class.java).also {
              it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
              startActivity(it)
          }
        }, SPLASH_LENGTH.toLong())
    }
}
