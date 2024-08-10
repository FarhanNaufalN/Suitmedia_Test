package com.example.km_test_suitmedia.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.km_test_suitmedia.databinding.ActivitySplashBinding
import com.example.km_test_suitmedia.ui.PageoneActivity


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val image = binding.ivSplashIcon
        image.alpha = 0f

        image.animate().setDuration(2000).alpha(1f).withEndAction {
            val intent = Intent(this, PageoneActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}


