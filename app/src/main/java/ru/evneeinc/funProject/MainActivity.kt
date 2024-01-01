package ru.evneeinc.funProject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.evneeinc.ui.HomeFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transactionManager = supportFragmentManager.beginTransaction()
        transactionManager.add(R.id.fragment_container, HomeFragment())
        transactionManager.commit()
    }
}
