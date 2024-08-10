package com.example.km_test_suitmedia.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.km_test_suitmedia.databinding.ActivityPagetwoBinding

class PagetwoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagetwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPagetwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nametext: TextView = binding.namepagetwo
        val buttonChoose : Button = binding.buttonchoose
        val UserName = intent.getStringExtra("name")
        binding.namepagetwo.text = UserName
        val UsernameChoosen = intent.getStringExtra("namechoosen")
        binding.selectusername.text = UsernameChoosen

        buttonChoose.setOnClickListener {
            val name = nametext.text.toString()
            val intent = Intent(this, PagethreeActivity::class.java).apply {
                putExtra("name", name)
            }
            startActivity(intent)
        }

    }

    fun onBackButtonClicked(view: View) {
        val intent = Intent(this, PageoneActivity::class.java)
        startActivity(intent)
    }
}
