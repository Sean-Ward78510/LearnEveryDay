package com.example.learneveryday.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.learneveryday.Logic.Model.DataBaseEntity.RecordItem
import com.example.learneveryday.R
import com.example.learneveryday.UI.ViewModel.ShowWebViewModel
import com.example.learneveryday.databinding.ActivityShowWebViewBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

class ShowWebViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowWebViewBinding
    lateinit var viewModel : ShowWebViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidget()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (binding.webView.canGoBack()){
                binding.webView.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
    fun initWidget(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_web_view)
        viewModel = ViewModelProvider(this).get(ShowWebViewModel::class.java)

        var url = intent.getStringExtra("url").toString()
        var pic_url = intent.getStringExtra("pic_url").toString()
        var title = intent.getStringExtra("title").toString()
        var source = intent.getStringExtra("source").toString()
        var currentime = Date()
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val time = format.format(currentime)

        var record = RecordItem(title, source, url, pic_url, time)
        viewModel.insertRecord(record)

        binding.webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
        }

        Log.d("ShowWeb", "initWidget: " + url)
        url?.let {
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }
    }
}