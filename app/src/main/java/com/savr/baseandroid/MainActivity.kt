package com.savr.baseandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.savr.baseandroid.utils.ProgressLoading
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getMovies()

        observer()
        initView()
    }

    private fun observer() {
        mainViewModel.listMovie.observe(this, Observer {
            if (it.isEmpty()) {
                rv_movie.visibility = View.GONE
                layout_status.visibility = View.VISIBLE

                iv_status.setImageResource(R.drawable.ic_no_data)
                tv_status.text = "Not found"

            } else {
                rv_movie.visibility = View.VISIBLE
                layout_status.visibility = View.GONE

                val adapter = MovieAdapter(it)
                rv_movie.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })

        mainViewModel.progress.observe(this, Observer {
            when(it) {
                ProgressLoading.LOADING -> {
                    rv_movie.visibility = View.GONE
                    layout_status.visibility = View.GONE
                    shimmer_loading.visibility = View.VISIBLE
                }
                ProgressLoading.DONE -> {
                    shimmer_loading.visibility = View.GONE
                }
                ProgressLoading.ERROR -> {
                    rv_movie.visibility = View.GONE
                    shimmer_loading.visibility = View.GONE
                    layout_status.visibility = View.VISIBLE

                    iv_status.setImageResource(R.drawable.ic_connection_error)
                    tv_status.text = "No Internet connection"
                }
            }
        })
    }

    private fun initView() {
        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false

            val textSearch = edit_search.text.toString()

            if (textSearch.isNotEmpty()) {
                mainViewModel.searchMovie(textSearch)
            } else {
                mainViewModel.getMovies()
            }
        }

        edit_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        mainViewModel.searchMovie("$s")
                    }, 1500)
                } else {
                    mainViewModel.getMovies()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }
}
