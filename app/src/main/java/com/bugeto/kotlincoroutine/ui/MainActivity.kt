package com.bugeto.kotlincoroutine.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bugeto.kotlincoroutine.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ListViewModel
    private val userListAdapter = UserListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this@MainActivity)[ListViewModel::class.java]
        viewModel.refresh()
        rv_usersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }
        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.users.observe(this, Observer { data ->
            data?.let {
                rv_usersList.visibility = View.VISIBLE
                userListAdapter.updateData(it)
            }
        })
        viewModel.userLoadError.observe(this, Observer { isError ->
            listError.visibility = if (isError == "") View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    listError.visibility = View.GONE
                    rv_usersList.visibility = View.GONE
                }
            }
        })

    }

}