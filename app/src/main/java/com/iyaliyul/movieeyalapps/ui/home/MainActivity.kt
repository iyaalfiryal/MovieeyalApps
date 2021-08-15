package com.iyaliyul.movieeyalapps.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.iyaliyul.movieeyalapps.R
import com.iyaliyul.movieeyalapps.databinding.ActivityMainBinding
import com.iyaliyul.movieeyalapps.ui.detail.DetailActivity
import com.iyaliyul.movieeyalapps.utils.Resource
import com.iyaliyul.movieeyalapps.utils.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private val movieAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up si recycler view
        with(binding.rvMovie){
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
            setHasFixedSize(true)
        }

        //observe state
        viewModel.movieData.observe(this){ response ->
            when(response){
                is Resource.Success -> {
                    binding.progressCircular.isVisible = false
                    response.data.results.map { movie ->
                        movieAdapter.add(MainItem(movie){
                            startActivity(Intent(this, DetailActivity::class.java)
                                .putExtra(DetailActivity.DETAIL_EXTRA, it))

                        })
                    }
                }
                is Resource.Loading -> {
                    binding.progressCircular.isVisible = true
                }

                is Resource.Error -> {
                    binding.progressCircular.isVisible = false
                    showToast(response.message)
                }

                is Resource.Empty -> {
                    binding.progressCircular.isVisible = false
                    showToast("Data is empty")
                }

            }

        }
    }
}