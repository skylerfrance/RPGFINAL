package com.example.myapplication

//big data classes => player, enemies, might have some for weapons
//randomly generate enemies of certain types
//progression system? when a player wins a fight, do they get health/upgrades?
//difficulty progression, enemies get harder as the game goes on
//way to keep track of how many enemies are defeated

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

data class Player(val name: String, var health: Int, var attack: Int, val weaponName: String, var level: Int, var isAlive: Boolean)
data class Enemy(val name: String, var health: Int, var attack: Int, var level: Int, var isAlive: Boolean, val warcry: String)
val myPlayer = Player("Sky", 20, 6, "Dagger", 1, true)
val firstEnemy = Enemy("Cave Bat", 5, 3, 1, true, "SQUEAK")

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        enemyNameView.text = firstEnemy.name
        enemyHealthView.text = firstEnemy.health.toString()
        enemyActionsView.text = "${firstEnemy.name} approaches you with a \n '${firstEnemy.warcry}'"


        playerHealthView.text = myPlayer.health.toString()
        playerAttackView.text = "0 - ${myPlayer.attack-1}"
        playerActionsView.text = "You wait while staying alert..."
    }


    // easiest enemy, no need for game over

    fun attackButtonTapped(view: View) {
        // have our character attack
        val ourAttack = Random.nextInt(myPlayer.attack)
        firstEnemy.health -= ourAttack
        // enemy attacks our character
        val theirAttack = Random.nextInt(firstEnemy.attack)
        myPlayer.health -= theirAttack
        // subtract attack from our enemies health
        playerHealthView.text = myPlayer.health.toString()
        enemyHealthView.text = firstEnemy.health.toString()
        if (ourAttack == 0) {
            playerActionsView.text = "You attacked and missed."
        }
        if (theirAttack == 0) {
            enemyActionsView.text = "They attacked and missed."
        }
        playerActionsView.text = "You attacked and dealt $ourAttack damage"
        enemyActionsView.text = "They attacked and dealt $theirAttack damage"
        // defeated enemy, move on
        val dialogBuilder = AlertDialog.Builder(this)
        if (firstEnemy.health <= 0) {
            dialogBuilder.setMessage("${firstEnemy.name} has been defeated! \n Your health has been replenished. \n You leveled up!")
                .setCancelable(false)
                .setPositiveButton("Continue", DialogInterface.OnClickListener
                { dialog, id -> dialog.cancel()
                    val whereAmIGoing = Intent(this, SecondActivity::class.java)
                    startActivity(whereAmIGoing)
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Defeat!")
            alert.show()
        }
    }
    fun defendButtonTapped(view: View){
        // have our character attack
        val ourAttack = Random.nextInt(myPlayer.attack) / 2
        firstEnemy.health -= ourAttack
        // enemy attacks our character
        val theirAttack = Random.nextInt(firstEnemy.attack) / 2
        myPlayer.health -= theirAttack
        // subtract attack from our enemies health
        playerHealthView.text = myPlayer.health.toString()
        enemyHealthView.text = firstEnemy.health.toString()
        if (ourAttack == 0) {
            playerActionsView.text = "You attacked and missed."
        }
        if (theirAttack == 0) {
            enemyActionsView.text = "They attacked and missed."
        }
        playerActionsView.text = "You attacked while defending and dealt $ourAttack damage"
        enemyActionsView.text = "They attacked you defending and dealt $theirAttack damage"
        // defeated enemy, move on
        val dialogBuilder = AlertDialog.Builder(this)
        if (firstEnemy.health <= 0) {
            dialogBuilder.setMessage("${firstEnemy.name} has been defeated! \n Your health has been replenished. \n You leveled up!")
                .setCancelable(false)
                .setPositiveButton("Continue", DialogInterface.OnClickListener
                { dialog, id -> dialog.cancel()
                    val whereAmIGoing = Intent(this, SecondActivity::class.java)
                    startActivity(whereAmIGoing)
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Defeat!")
            alert.show()
        }
    }

}