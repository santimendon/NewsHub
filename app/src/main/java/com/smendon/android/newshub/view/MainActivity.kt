package com.smendon.android.newshub.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.smendon.android.newshub.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewmodel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val newsAdapter = NewsArticleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            binding.recyclerView.adapter = newsAdapter
            viewmodel.latestNews.observe(this@MainActivity, Observer { result ->
                newsAdapter?.submitList(result.data)
            })
        }
    }
}