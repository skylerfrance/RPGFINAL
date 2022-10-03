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
import kotlinx.android.synthetic.main.activity_second2.*
import kotlin.random.Random

data class PlayerLvl2(val name: String, var health: Int, var attack: Int, val weaponName: String, var level: Int, var isAlive: Boolean)
data class EnemyLvl2(val name: String, var health: Int, var attack: Int, var level: Int, var isAlive: Boolean, val warcry: String)
val myPlayerLvl2 = PlayerLvl2("Sky", 22, 7, "Dagger", 2, true)
val secondEnemy = EnemyLvl2("Skeleton", 10, 5, 2, true, "En Garde!")

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second2)


        enemyNameView2.text = secondEnemy.name
        enemyHealthView2.text = secondEnemy.health.toString()
        enemyActionsView2.text =
            "${secondEnemy.name} approaches you with a \n '${secondEnemy.warcry}'"


        playerHealthView2.text = myPlayerLvl2.health.toString()
        playerAttackView2.text = "0 - ${myPlayerLvl2.attack - 1}"
        playerActionsView2.text = "You wait while staying alert..."
    }


    fun attackButtonTapped(view: View) {
        // have our character attack
        val ourAttack = Random.nextInt(myPlayerLvl2.attack)
        secondEnemy.health -= ourAttack
        // enemy attacks our character
        val theirAttack = Random.nextInt(secondEnemy.attack)
        myPlayerLvl2.health -= theirAttack
        // subtract attack from our enemies health
        playerHealthView2.text = myPlayerLvl2.health.toString()
        enemyHealthView2.text = secondEnemy.health.toString()
        if (ourAttack == 0) {
            playerActionsView2.text = "You attacked and missed."
        }
        if (theirAttack == 0) {
            enemyActionsView2.text = "They attacked and missed."
        }
        playerActionsView2.text = "You attacked and dealt $ourAttack damage"
        enemyActionsView2.text = "They attacked and dealt $theirAttack damage"
        // defeated enemy, move on
        val dialogBuilder = AlertDialog.Builder(this)
        if (secondEnemy.health <= 0) {
            dialogBuilder.setMessage("${secondEnemy.name} has been defeated! \n Your health has been replenished. \n You leveled up!")
                .setCancelable(false)
                .setPositiveButton("Continue", DialogInterface.OnClickListener
                { dialog, id ->
                    dialog.cancel()
                    val whereAmIGoing = Intent(this, ThirdActivity::class.java)
                    startActivity(whereAmIGoing)
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Defeat!")
            alert.show()
        }
        if (myPlayerLvl2.health <= 0) {
            dialogBuilder.setMessage("${myPlayerLvl2.name} has been defeated! \n You'll have to start over.")
                .setCancelable(false)
                .setPositiveButton("Restart", DialogInterface.OnClickListener
                { dialog, id ->
                    dialog.cancel()
                    val whereAmIGoing = Intent(this, MainActivity::class.java)
                    startActivity(whereAmIGoing)
                })
            val alert = dialogBuilder.create()
            alert.setTitle("GAME OVER")
            alert.show()
        }
        fun defendButtonTapped(view: View) {
            // have our character attack
            val ourAttack = Random.nextInt(myPlayerLvl2.attack) / 2
            secondEnemy.health -= ourAttack
            // enemy attacks our character
            val theirAttack = Random.nextInt(secondEnemy.attack) / 2
            myPlayerLvl2.health -= theirAttack
            // subtract attack from our enemies health
            playerHealthView2.text = myPlayerLvl2.health.toString()
            enemyHealthView2.text = secondEnemy.health.toString()
            if (ourAttack == 0) {
                playerActionsView2.text = "You attacked and missed."
            }
            if (theirAttack == 0) {
                enemyActionsView2.text = "They attacked and missed."
            }
            playerActionsView2.text = "You attacked while defending and dealt $ourAttack damage"
            enemyActionsView2.text = "They attacked you defending and dealt $theirAttack damage"
            // defeated enemy, move on
            val dialogBuilder = AlertDialog.Builder(this)
            if (secondEnemy.health <= 0) {
                dialogBuilder.setMessage("${secondEnemy.name} has been defeated! \n Your health has been replenished. \n You leveled up!")
                    .setCancelable(false)
                    .setPositiveButton("Continue", DialogInterface.OnClickListener
                    { dialog, id ->
                        dialog.cancel()
                        val whereAmIGoing = Intent(this, ThirdActivity::class.java)
                        startActivity(whereAmIGoing)
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Defeat!")
                alert.show()
            }
            if (myPlayerLvl2.health <= 0) {
                dialogBuilder.setMessage("${myPlayerLvl2.name} has been defeated! \n You'll have to start over.")
                    .setCancelable(false)
                    .setPositiveButton("Restart", DialogInterface.OnClickListener
                    { dialog, id ->
                        dialog.cancel()
                        val whereAmIGoing = Intent(this, MainActivity::class.java)
                        startActivity(whereAmIGoing)
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("GAME OVER")
                alert.show()
            }
        }

    }
}