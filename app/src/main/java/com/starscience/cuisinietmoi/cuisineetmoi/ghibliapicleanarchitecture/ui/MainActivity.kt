package com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.noconent.emptyreclerviewindicator.EmptyViewIndicator
import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.R
import com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.model.GhibliState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val adapter: Adapter by inject()
    val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBrowseRecycler()

        viewModel.getGhiblis().observe(this, Observer { data -> if (data != null) ghibliStates(data) })
        viewModel.fetchGhiblis()
    }

    private fun setupBrowseRecycler() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    private fun ghibliStates(states: GhibliState){
        when(states){
            is GhibliState.Loading -> setupLoadingScreen()
            is GhibliState.Error -> setupErrorScreen(states.errorMessage)
            is GhibliState.Success -> setupSuccessScreen(states.data)
        }
    }

    private fun setupLoadingScreen(){
        progress_bar.visibility = View.VISIBLE
        recyclerview.visibility = View.GONE
    }

    private fun setupSuccessScreen(data: List<Ghibli>?){
        progress_bar.visibility = View.GONE
        recyclerview.visibility = View.VISIBLE
        if (!data.isNullOrEmpty()){
            Timber.w("########## HAMZA LE SINGE ##########")
            updateRecyclerView(data)
        }
        else{
            Timber.w("########## HAMZA LE BOSS ##########")
            setupEmptyRecyclerView()
        }
    }

    private fun setupErrorScreen(message: String?){
        progress_bar.visibility = View.GONE
        recyclerview.visibility = View.GONE

        val errorSnack = Snackbar.make(recyclerview, message.toString(), Snackbar.LENGTH_SHORT)
        errorSnack.setAction(R.string.retry_error) { viewModel.fetchGhiblis() }
        errorSnack.show()
    }

    private fun updateRecyclerView(data: List<Ghibli>){
        adapter.ghiblis = data
        adapter.notifyDataSetChanged()
    }

    private fun setupEmptyRecyclerView(){
        val emptyIndicator = EmptyViewIndicator.instance()!!.build()
        emptyIndicator.show(this, recyclerview)
    }
}