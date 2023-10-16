package com.watchapp.piyou.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.watchapp.piyou.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //바인딩 객체 선언
    private var mBinding: ActivityMainBinding ?= null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //바인딩 초기화
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        // 생성된 뷰 액티비티에 표시시
        setContentView(binding.root)


        binding.btnA.setOnClickListener {

            //다음화면으로 이동하기 위한 인텐트 객체 생성
            val intent = Intent(this, ConfirmActivity::class.java)

            //HelloWorld라는 텍스트 값을 담음
            intent.putExtra("msg", binding.tvSendMsg.text.toString())
            startActivity(intent)   //intent에 저장되어 있는 엑티비티 쪽으로 이동한다
            finish() //자기 자신 액티비티 파괴

        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}