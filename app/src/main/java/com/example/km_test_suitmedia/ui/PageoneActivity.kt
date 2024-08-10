package com.example.km_test_suitmedia.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.km_test_suitmedia.databinding.ActivityPageoneBinding
import com.example.km_test_suitmedia.ui.customview.PalidromeEditText

class PageoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPageoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPageoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameEditText: EditText = binding.name
        val palidromeEditText: PalidromeEditText = binding.palindrome
        val checkPalidrome: TextView = binding.checkPalidrome
        val checkButton: Button = binding.buttoncheck
        val nextButton: Button = binding.buttonnext


        palidromeEditText.bindTextView(checkPalidrome)
        checkButton.setOnClickListener {
            val text = palidromeEditText.text.toString()
            val message = if (palidromeEditText.isPalindrome(text)) {
                "isPalindrome"
            } else {
                "not palindrome"
            }
            showDialog(message)
        }

        nextButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val intent = Intent(this, PagetwoActivity::class.java).apply {
                putExtra("name", name)
            }
            startActivity(intent)
        }

        palidromeEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Palindrome Check")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

}
