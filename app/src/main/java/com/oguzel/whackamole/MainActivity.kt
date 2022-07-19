package com.oguzel.whackamole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.oguzel.whackamole.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0
    var timeString : String = ""
    var scoreString : String = ""
    var endTime : String = "Time: 0"
    var handler = Handler()
    var runnable = Runnable {  }
    var invisTimer = 1000.00




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        var moleList = arrayListOf(
            binding.moleImage11,binding.moleImage12,binding.moleImage13,binding.moleImage14,
            binding.moleImage21,binding.moleImage22,binding.moleImage23,binding.moleImage24,
            binding.moleImage31,binding.moleImage32, binding.moleImage33,binding.moleImage34,
            binding.moleImage41,binding.moleImage42,binding.moleImage43,binding.moleImage44)

        fun hideMoles(){

            runnable = object : Runnable{
                override fun run() {
                    for (image in moleList){
                        image.visibility= View.INVISIBLE
                    }

                    val random = Random()
                    val randomIndex = random.nextInt(16)

                    moleList[randomIndex].visibility=View.VISIBLE
//                    handler.postDelayed(runnable,500)
                    if(invisTimer>500){
                        handler.postDelayed(runnable, invisTimer.toLong())
                        invisTimer *= (0.95)
                    }
                    else{
                        handler.postDelayed(runnable, 500)

                    }
                }
            }
            handler.post(runnable)


        }

        fun increaseScore(){
            score++
            scoreString="Score: ${score}"
            binding.scoreText.text=scoreString
        }

        hideMoles()

        for(i in moleList){
            i.setOnClickListener { increaseScore();i.visibility=View.INVISIBLE }
        }



        object : CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                timeString="Time: ${p0/1000}"
                binding.timeText.text= timeString
            }

            override fun onFinish() {
                binding.timeText.text=endTime

                handler.removeCallbacks(runnable)

                for(i in moleList){
                    i.visibility=View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setCancelable(false)
                alert.setMessage("Your score is ${score}. Do you want to play again?")
                alert.setPositiveButton("Yes") { dialogInterface, i ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") { dialogInterface, i ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext,WelcomeActivity::class.java)
                    finish()
                    startActivity(intent)
                }
                alert.show()
            }

        }.start()



    }




}