package com.example.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userManager = UserManager(this)

        initListeners()

        readDataUser()
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener { saveDataUser() }
    }

    private fun saveDataUser() {

        val name = binding.edtName.text.toString()
        val age = binding.edtAge.text.toString().toInt()
        val authenticated = binding.cbAuthenticated.isChecked

        lifecycleScope.launch { userManager.saveDataUser(name, age, authenticated) }
    }

    private fun readDataUser() {
        lifecycleScope.launch {
            val user = userManager.readDataUser()

            binding.edtName.setText(user.name)
            if(user.age > 0) binding.edtAge.setText(user.age.toString())
            binding.cbAuthenticated.isChecked = user.authenticated
        }
    }

}