package com.proyecto.appejemplomascotas

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.RelativeLayout

class LoginActivity: Activity() {
    lateinit var relativeLayout: RelativeLayout
    lateinit var animationDrawable: AnimationDrawable
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}