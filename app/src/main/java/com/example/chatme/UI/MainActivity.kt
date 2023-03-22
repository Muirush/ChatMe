package com.example.chatme.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.chatme.Constants
import com.example.chatme.R
import com.example.chatme.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var url = "https://api.openai.com/v1/completions"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edQ = binding.idEdQ
        edQ.setOnEditorActionListener(TextView.OnEditorActionListener{v,actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND){
                binding.Answer.text ="Please wait..."
                if (edQ.text.toString().length>0){
                    getResponse(edQ.text.toString())
                }else{
                    Toast.makeText(this, "Please enter your question", Toast.LENGTH_LONG).show()
                }
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun getResponse(query: String) {
        var questionTV = binding.Question
        questionTV.text = query
        binding.idEdQ.setText("")
        val queue:RequestQueue = Volley.newRequestQueue(applicationContext)
        val jsonObject :JSONObject?= JSONObject()

        jsonObject?.put("model", "text-davinci-003")
        jsonObject?.put("prompt", query)
        jsonObject?.put("temperature", 0)
        jsonObject?.put("max_tokens", 100)
        jsonObject?.put("top_p", 1)
        jsonObject?.put("frequency_penalty", 0.0)
        jsonObject?.put("presence_penalty", 0.0)



    }
}