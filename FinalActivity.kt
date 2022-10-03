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
import kotlinx.android.synthetic.main.activity_third.*
import kotlin.random.Random

data class PlayerLvl10(val name: String, var health: Int, var attack: Int, val weaponName: String, var level: Int, var isAlive: Boolean)
data class EnemyBoss(val name: String, var health: Int, var attack: Int, var level: Int, var isAlive: Boolean, val warcry: String)
val myPlayerLvl10 = PlayerLvl10("Sky", 50, 20, "Dagger", 10, true)
val bossEnemy = EnemyBoss("Spawner", 60, 18, 12, true, "Hmph...")

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)


        enemyNameViewF.text = bossEnemy.name
        enemyHealthViewF.text = bossEnemy.health.toString()
        enemyActionsViewF.text = "${bossEnemy.name} approaches you with a \n '${bossEnemy.warcry}'"


        playerHealthViewF.text = myPlayerLvl10.health.toString()
        playerAttackViewF.text = "0 - ${myPlayerLvl10.attack - 1}"
        playerActionsViewF.text = "You wait while staying alert..."
    }


    fun attackButtonTapped(view: View) {
        // have our character attack
        val ourAttack = Random.nextInt(myPlayerLvl10.attack)
        bossEnemy.health -= ourAttack
        // enemy attacks our character
        val theirAttack = Random.nextInt(bossEnemy.attack)
        myPlayerLvl10.health -= theirAttack
        // subtract attack from our enemies health
        playerHealthViewF.text = myPlayerLvl10.health.toString()
        enemyHealthViewF.text = bossEnemy.health.toString()
        if (ourAttack == 0) {
            playerActionsViewF.text = "You attacked and missed."
        }
        if (theirAttack == 0) {
            enemyActionsViewF.text = "They attacked and missed."
        }
        playerActionsViewF.text = "You attacked and dealt $ourAttack damage"
        enemyActionsViewF.text = "They attacked and dealt $theirAttack damage"
        // defeated enemy, move on
        val dialogBuilder = AlertDialog.Builder(this)
        if (bossEnemy.health <= 0) {
            dialogBuilder.setMessage("${bossEnemy.name} has been defeated! \n Your health has been replenished. \n You leveled up! \n Congratulations! You saved the village!")
                .setCancelable(false)
                .setPositiveButton("Continue", DialogInterface.OnClickListener
                { dialog, id ->
                    dialog.cancel()
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Defeat!")
            alert.show()
        }
        if (myPlayerLvl10.health <= 0) {
            dialogBuilder.setMessage("${myPlayerLvl10.name} has been defeated! \n You'll have to start over.")
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
            val ourAttack = Random.nextInt(myPlayerLvl10.attack) / 2
            bossEnemy.health -= ourAttack
            // enemy attacks our character
            val theirAttack = Random.nextInt(bossEnemy.attack) / 2
            myPlayerLvl10.health -= theirAttack
            // subtract attack from our enemies health
            playerHealthViewF.text = myPlayerLvl10.health.toString()
            enemyHealthViewF.text = bossEnemy.health.toString()
            if (ourAttack == 0) {
                playerActionsViewF.text = "You attacked and missed."
            }
            if (theirAttack == 0) {
                enemyActionsViewF.text = "They attacked and missed."
            }
            playerActionsViewF.text = "You attacked while defending and dealt $ourAttack damage"
            enemyActionsViewF.text = "They attacked you defending and dealt $theirAttack damage"
            // defeated enemy, move on
            val dialogBuilder = AlertDialog.Builder(this)
            if (bossEnemy.health <= 0) {
                dialogBuilder.setMessage("${bossEnemy.name} has been defeated! \n Your health has been replenished. \n You leveled up! \n Congratulations! You saved the village!")
                    .setCancelable(false)
                    .setPositiveButton("Continue", DialogInterface.OnClickListener
                    { dialog, id ->
                        dialog.cancel()
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Defeat!")
                alert.show()
            }
            if (myPlayerLvl10.health <= 0) {
                dialogBuilder.setMessage("${myPlayerLvl10.name} has been defeated! \n You'll have to start over.")
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