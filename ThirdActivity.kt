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

data class PlayerLvl3(val name: String, var health: Int, var attack: Int, val weaponName: String, var level: Int, var isAlive: Boolean)
data class EnemyLvl3(val name: String, var health: Int, var attack: Int, var level: Int, var isAlive: Boolean, val warcry: String)
val myPlayerLvl3 = PlayerLvl3("Sky", 25, 8, "Dagger", 3, true)
val thirdEnemy = EnemyLvl3("Skeleton King", 20, 7, 3, true, "Heh heh heh...")

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)


        enemyNameViewF.text = thirdEnemy.name
        enemyHealthViewF.text = thirdEnemy.health.toString()
        enemyActionsViewF.text =
            "${thirdEnemy.name} approaches you with a \n '${thirdEnemy.warcry}'"


        playerHealthViewF.text = myPlayerLvl3.health.toString()
        playerAttackViewF.text = "0 - ${myPlayerLvl3.attack - 1}"
        playerActionsViewF.text = "You wait while staying alert..."
    }


    fun attackButtonTapped(view: View) {
        // have our character attack
        val ourAttack = Random.nextInt(myPlayerLvl3.attack)
        thirdEnemy.health -= ourAttack
        // enemy attacks our character
        val theirAttack = Random.nextInt(thirdEnemy.attack)
        myPlayerLvl3.health -= theirAttack
        // subtract attack from our enemies health
        playerHealthViewF.text = myPlayerLvl3.health.toString()
        enemyHealthViewF.text = thirdEnemy.health.toString()
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
        if (thirdEnemy.health <= 0) {
            dialogBuilder.setMessage("${thirdEnemy.name} has been defeated! \n Your health has been replenished. \n You leveled up! \n Congratulations! Floor 1 is clear.")
                .setCancelable(false)
                .setPositiveButton("Continue", DialogInterface.OnClickListener
                { dialog, id ->
                    dialog.cancel()
                    val whereAmIGoing = Intent(this, FourthActivity::class.java)
                    startActivity(whereAmIGoing)
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Defeat!")
            alert.show()
            if (myPlayerLvl3.health <= 0) {
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
        fun defendButtonTapped(view: View) {
            // have our character attack
            val ourAttack = Random.nextInt(myPlayerLvl3.attack) / 2
            thirdEnemy.health -= ourAttack
            // enemy attacks our character
            val theirAttack = Random.nextInt(thirdEnemy.attack) / 2
            myPlayerLvl3.health -= theirAttack
            // subtract attack from our enemies health
            playerHealthViewF.text = myPlayerLvl3.health.toString()
            enemyHealthViewF.text = thirdEnemy.health.toString()
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
            if (thirdEnemy.health <= 0) {
                dialogBuilder.setMessage("${thirdEnemy.name} has been defeated! \n Your health has been replenished. \n You leveled up! \n Congratulations! Floor 1 is clear.")
                    .setCancelable(false)
                    .setPositiveButton("Continue", DialogInterface.OnClickListener
                    { dialog, id ->
                        dialog.cancel()
                        val whereAmIGoing = Intent(this, FourthActivity::class.java)
                        startActivity(whereAmIGoing)
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Defeat!")
                alert.show()
            }
            if (myPlayerLvl3.health <= 0) {
                dialogBuilder.setMessage("${myPlayerLvl3.name} has been defeated! \n You'll have to start over.")
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