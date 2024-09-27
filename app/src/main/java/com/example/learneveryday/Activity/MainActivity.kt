package com.example.learneveryday.Activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learneveryday.Logic.Model.Response.News
import com.example.learneveryday.R
import com.example.learneveryday.UI.Tool.NewsAdapter
import com.example.learneveryday.UI.ViewModel.NewsViewModel
import com.example.learneveryday.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var actionBar: ActionBar
    lateinit var viewModel :NewsViewModel
    lateinit var newsAdapter : NewsAdapter
    lateinit var newsLayoutManger : LinearLayoutManager
    lateinit var animatorSetList1: ArrayList<AnimatorSet>
    lateinit var animatorSetList11: ArrayList<AnimatorSet>
    lateinit var floatList: List<FloatingActionButton>
    var radius = 300
    var isExpansion = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidget()
    }
    fun initWidget(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.root.findViewById(R.id.toolbar))
        actionBar = supportActionBar!!
        actionBar.setHomeAsUpIndicator(R.drawable.home)
        actionBar.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsAdapter = NewsAdapter(this,viewModel.newsList)
        newsLayoutManger = LinearLayoutManager(this)

        binding.recycler.apply {
            adapter = newsAdapter
            layoutManager = newsLayoutManger
        }

        viewModel.newsLiveData.observe(this){
            Log.d("NewsList0", "initWidget: num = " + it.size)
            newsAdapter.newsList = it as ArrayList<News>
            newsAdapter.notifyDataSetChanged()
        }
        viewModel.applyNews()

        setOnClick()
        initFloatAnimator()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            //android.R.id.home：这是 Android 系统提供的一个资源 ID，用于表示 ActionBar 上的 Home/Up 按钮。它是系统级别的 ID，可以在任何 Android 项目中使用。
            binding.drawer.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun setOnClick(){
        Log.d("Float", "setOnClick: ")

        binding.navigationMain.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.word_book ->{
                    var intent = Intent(this,WordBookActivity::class.java)
                    startActivity(intent)
                }
                R.id.history ->{
                    var intent = Intent(this,NewsHistoryRecordActivity::class.java)
                    startActivity(intent)
                }
                R.id.news_collect ->{

                }
                R.id.en_sentence ->{

                }
                R.id.sentence ->{

                }
            }
            binding.drawer.closeDrawers()
            true
        }

        binding.onclickListener = View.OnClickListener { view ->
            when(view.id){
                R.id.floating_button ->{
                    Log.d("Float", "setOnClick: ")
                    if (isExpansion){
                        FloatFold()
                        isExpansion = false
                    } else{
                        FloatExpansion()
                        isExpansion = true
                    }
                }
                R.id.floating_menu1 ->{
                    var intent = Intent(this,HotTopicActivity::class.java)
                    startActivity(intent)
                }
                R.id.floating_menu2 ->{
                    var intent = Intent(this,DailySentenceActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }
    fun initFloatAnimator(){
        Log.d("Float", "initFloatAnimator: ")
        animatorSetList1 = ArrayList<AnimatorSet>()
        animatorSetList11 = ArrayList<AnimatorSet>()
        floatList = ArrayList<FloatingActionButton>().apply {
            add(binding.floatingMenu3)
            add(binding.floatingMenu2)
            add(binding.floatingMenu1)
        }

        for (i in floatList.size - 1 downTo 0){
            var avgAngle : Int = (90 / (floatList.size - 1))
            var angle : Int = avgAngle * i
            var x = (-Math.cos(angle * (Math.PI / 180)) * radius).toFloat()
            var y = (-Math.sin(angle * (Math.PI / 180)) * radius).toFloat()

            var animatorX = ObjectAnimator.ofFloat(floatList.get(i),"translationX", 0f, x)
            var animatorY = ObjectAnimator.ofFloat(floatList.get(i),"translationY", 0f, y)
            var animatorSet = AnimatorSet()
            animatorSet.play(animatorX).with(animatorY)
            animatorSetList1.add(animatorSet)
        }
        for (i in  0..floatList.size - 1){
            var avgAngle : Int = (90 / (floatList.size - 1))
            var angle : Int = avgAngle * i
            var x = (-Math.cos(angle * (Math.PI / 180)) * radius).toFloat()
            var y = (-Math.sin(angle * (Math.PI / 180)) * radius).toFloat()

            var animatorX = ObjectAnimator.ofFloat(floatList.get(i),"translationX",x, 0f)
            var animatorY = ObjectAnimator.ofFloat(floatList.get(i),"translationY",y, 0f)

            var animatorSet = AnimatorSet()
            animatorSet.play(animatorX).with(animatorY)
            animatorSetList11.add(animatorSet)
        }
    }

    fun FloatExpansion(){
        Log.d("Float", "FloatExpansion: expansion")
        for (i in 0..animatorSetList1.size - 1)
            animatorSetList1.get(i).start()
    }
    fun FloatFold(){
        Log.d("Float", "FloatExpansion: fold")
        for (i in 0..animatorSetList1.size - 1)
            animatorSetList11.get(i).start()
    }

}

