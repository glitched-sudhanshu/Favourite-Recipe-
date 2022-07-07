package com.example.favouritedish.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.favouritedish.R
import com.example.favouritedish.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * In manifest file of this activity, under activity tag, added
         * android:theme="@style/Theme.FavouriteDish.NoActionBar">
         * Theme.FavouriteDish.NoActionBar
         * is a custom theme made for this activity
         * this piece of code to remove action bar from a specific activity.
         */

        val splashBinding: ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        //to hide status and system bars
        hideSystemBars()

        //animation for text view of splash screen
        val splashAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash)
        splashBinding.tvSplashTitle.animation = splashAnimation
        splashAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                //TODO("Not yet implemented")
            }

            override fun onAnimationEnd(p0: Animation?) {
                /**
                 * Handler class offers us to handle things with a delay
                 */
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }, 700)
            }

            override fun onAnimationRepeat(p0: Animation?) {
                //TODO("Not yet implemented")
            }

        })

    }

    private fun hideSystemBars() {
        /**
         * * did not use this piece of code because
         * 1. else statement is deprecated
         * 2. once we interact with the status bar it will not disappear again
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else
        {
        @Suppress("DEPRECATION")
        window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )
        }
         **/

        /**IMMERSIVE MODE**/
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}