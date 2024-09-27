package com.example.learneveryday.Activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.text.set
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learneveryday.Logic.Model.DataBaseEntity.Word
import com.example.learneveryday.Logic.Repository
import com.example.learneveryday.R
import com.example.learneveryday.UI.ViewModel.EnglishSentenceViewModel
import com.example.learneveryday.databinding.ActivityDailySentenceBinding
import com.example.learneveryday.databinding.PopWindowBinding
import java.util.regex.Pattern
import kotlin.math.log

class DailySentenceActivity : AppCompatActivity() {
    lateinit var binding : ActivityDailySentenceBinding
    lateinit var viewModel: EnglishSentenceViewModel
    var pic_list = arrayListOf(R.drawable.pick,R.drawable.pika,R.drawable.jine,R.drawable.miao,R.drawable.xiaohuomiao,R.drawable.geng)
    var color_List = arrayListOf(Color.parseColor("#FFF380"),Color.parseColor("#f8de6f"),
        Color.parseColor("#8EEBEC"),Color.parseColor("#7FFFD4"),Color.parseColor("#E55451"),Color.parseColor("#4E387E"))
    lateinit var displayMetrics : DisplayMetrics
    var pop_width = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidget()
    }

    fun initWidget(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_daily_sentence)
        viewModel = ViewModelProvider(this).get(EnglishSentenceViewModel::class.java)

        displayMetrics = this.resources.displayMetrics
        pop_width = (displayMetrics.widthPixels * 0.8).toInt()

        viewModel.EnglishSentence.observe(this, Observer {
            it?.apply {
                var num = (0..pic_list.size - 1).random()

                var spannableString = it.content?.let { it1 -> viewModel.DealWithText(it1,viewModel) }
                binding.apply {
                    englishContent.text = spannableString
                    englishContent.movementMethod = LinkMovementMethod.getInstance()
                    englishNote.text = it.note
                    englishSentenceLayout.setBackgroundColor(color_List.get(num))
                    sentencePicture.setImageResource(pic_list.get(num))
                }
            }
        })

        viewModel._word.observe(this, Observer {
            it?.let {
                ShowPopWindow()
            }
        })

        viewModel.loadAllWord()
        viewModel.applyEnglishSentence()
    }

//    fun DealWithText(string: String) {
//        val spannableString = SpannableString(string)
//        val pattern = Pattern.compile("\\b\\w+\\b")
//        val matcher = pattern.matcher(string)
//
//        while (matcher.find()) {
//            val start = matcher.start()
//            val end = matcher.end()
//            val word = matcher.group()
//
//            val clickableSpan = object : ClickableSpan() {
//                override fun onClick(view: View) {
//                    viewModel.applyWordTranslate(word)
//                    Log.d("Word", "onClick: $word")
//                }
//
//                override fun updateDrawState(ds: TextPaint) {
//                    super.updateDrawState(ds)
////                    ds.isUnderlineText = true
//                    ds.color = Color.BLACK
//                }
//            }
//            spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
//        }
//
//        binding.englishContent.apply {
//            text = spannableString
//            movementMethod = LinkMovementMethod.getInstance()
////            highlightColor = Color.TRANSPARENT  // 防止点击时出现背景色
//        }
//
//    // 确保父视图不拦截点击事件
//    //binding.englishContent.parent.requestDisallowInterceptTouchEvent(true)
//}

    fun ShowPopWindow(){
        var window_binding = DataBindingUtil
            .inflate<PopWindowBinding>(layoutInflater,R.layout.pop_window,null,false)
        var popWindow = PopupWindow(window_binding.root,pop_width,ViewGroup.LayoutParams.WRAP_CONTENT)

        var word = Word(viewModel.word.value,viewModel._word_translate.value)
        var isCollect = viewModel.list.contains(word)
        if (isCollect) window_binding.wordCollect.setImageResource(R.drawable.collect)

        popWindow.isFocusable = true
        window_binding.word.text = viewModel._word.value
        window_binding.wordTranslate.text = viewModel._word_translate.value

        window_binding.wordCollect.setOnClickListener {
            Log.d("WordBook", "ShowPopWindow: " + isCollect)
            if (!isCollect){
                viewModel.collectWord(word)
                window_binding.wordCollect.setImageResource(R.drawable.collect)
                isCollect = true

            }else{
                viewModel.deleteWord(word)
                window_binding.wordCollect.setImageResource(R.drawable.discollect)
                isCollect = false
            }
        }

        popWindow.showAsDropDown(binding.englishContent)
        binding.englishContent.movementMethod = LinkMovementMethod.getInstance()
    }
}