package com.goforer.singlesharedsample.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.goforer.base.utility.keyboard.KeyboardObserver
import com.goforer.singlesharedsample.R
import com.goforer.singlesharedsample.databinding.ActivityMainBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    internal var onKeyboardChange: ((status: Int) -> Unit) = {}

    private lateinit var keyboardObserver: KeyboardObserver

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKeyboardListener()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
            || super.onSupportNavigateUp()
    }

    internal fun addKeyboardListener() {
        keyboardObserver.addListener { status ->
            onKeyboardChange(status)
        }
    }

    internal fun removeKeyboardListener() {
        keyboardObserver.removeListener()
    }

    internal fun hideKeyboard() {
        val inputManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        currentFocus?.windowToken?.let {
            inputManager.hideSoftInputFromWindow(it, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    private fun initKeyboardListener() {
        keyboardObserver = KeyboardObserver(this)
    }
}