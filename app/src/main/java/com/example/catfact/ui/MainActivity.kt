package com.example.catfact.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.catfact.R
import com.example.catfact.data.remote.RetrofitClient
import com.example.catfact.data.repository.CatFactRepository
import com.example.catfact.viewmodel.CatFactViewModel
import com.example.catfact.viewmodel.CatFactViewModelFactory

class MainActivity : AppCompatActivity() {

    private val repository by lazy { CatFactRepository(RetrofitClient.instance) }
    private val viewModel: CatFactViewModel by viewModels { CatFactViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factTextView: TextView = findViewById(R.id.fact_txt)
        val generateButton: Button = findViewById(R.id.generate_btn)

        // Observe for new facts
        viewModel.catFact.observe(this, Observer { fact ->
            factTextView.text = fact?.fact
        })

        // Observe for errors
        viewModel.error.observe(this, Observer { error ->
            factTextView.text = error
        })

        // Fetch fact when button is clicked
        generateButton.setOnClickListener {
            viewModel.fetchFact()
        }
    }
}
