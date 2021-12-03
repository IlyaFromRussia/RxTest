package com.lobovindustries.rxtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.lobovindustries.rxtest.viewmodel.MainViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = MainViewModel()
        val btn = findViewById<Button>(R.id.btn)

        viewModel?.liveData?.observe(ViewTreeLifecycleOwner.get(btn)!!)
        {
            Toast.makeText(this, "${it[0]} - ${it[1]}", Toast.LENGTH_LONG).show()
        }

        btn.setOnClickListener {
            viewModel?.subscribeWithoutSubscribers()
        }

    }
}