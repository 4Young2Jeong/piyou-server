package com.example.watchapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.watchapp.R

// Splash 화면 만들기
// handler -> 3초 뒤에 다른 액티비티로 이동
class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }
}