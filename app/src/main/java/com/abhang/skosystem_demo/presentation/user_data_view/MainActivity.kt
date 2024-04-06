package com.abhang.skosystem_demo.presentation.user_data_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhang.skosystem_demo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.abhang.skosystem_demo.domain.models.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var userAdapter : UserAdapter
    private val userViewModel : UserViewModel by viewModels()
    private var mList : ArrayList<UserData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        userViewModel.getUSerData()
        setupRecyclerview()
        observeData()
    }

    private fun setupRecyclerview(){
        userAdapter = UserAdapter(this) {

        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if ((layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == mList.count()-1){
                        binding.progressBar.isVisible = true
                        userViewModel.getUSerData()
                    }
                }
            })
        }

    }

    private fun observeData(){
        CoroutineScope(Dispatchers.IO).launch {
            userViewModel.userValue.collectLatest {data->
                withContext(Dispatchers.Main){
                    when{
                        data.isLoading-> binding.progressBar.isVisible = true
                        else ->{
                            binding.progressBar.isVisible = false
                            if(data.data?.isNotEmpty() == true) {
                                mList.addAll(data.data as ArrayList<UserData>)
                                userAdapter.submitList(mList.toMutableList())
                            }
                        }
                    }
                }
            }
        }
    }
}