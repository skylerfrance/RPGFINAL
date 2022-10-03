package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_fourth.*

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        textView.text = "After going through various floors and continuing to level up... \n You finally stumble across the deepest end of the cave." +
                "\n A dark figure with glowing eyes stares back. This might be the cause of the monsters around town."
    }
    fun buttonTapped(view: View){
        val whereAmIGoing = Intent(this, FinalActivity::class.java)
        startActivity(whereAmIGoing)
    }
}