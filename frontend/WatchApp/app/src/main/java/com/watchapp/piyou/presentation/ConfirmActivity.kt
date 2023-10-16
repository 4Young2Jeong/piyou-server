package com.watchapp.piyou.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.watchapp.piyou.databinding.ActivityConfirmBinding

class ConfirmActivity: AppCompatActivity() {

    private var mBinding: ActivityConfirmBinding ?= null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sub)
        //바인딩 초기화
        mBinding = ActivityConfirmBinding.inflate(layoutInflater)

        // 생성된 뷰 액티비티에 표시시
        setContentView(binding.root)

        //intent에 넘겨진 객체중에 msg가 있다면
        if(intent.hasExtra("msg")) {
            //서브 액티비티의 존재하는 텍스트뷰에 main에 있는 메세지가 넘겨져 옴
            //binding.tvGetMsg.text = intent.getStringExtra("msg")
        }
    }
}