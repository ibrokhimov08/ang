package com.example.mathgame.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.mathgame.R
import com.example.mathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var level = 0
    private var isCorrect: Boolean? = null
    private var counter = 10
    private var timer: CountDownTimer? = null


    /* private val mProgressBar = ProgressBar(this)
      private val mCountDownTimer = CountDownTimer()
      int i=0;

      mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
      mProgressBar.setProgress(i);
      mCountDownTimer=new CountDownTimer(5000,1000) {

          @Override
          public void onTick(long millisUntilFinished) {
              Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
              i++;
              mProgressBar.setProgress((int)i*100/(5000/1000));

          }

          @Override
          public void onFinish() {
              //Do what you want
              i++;
              mProgressBar.setProgress(100);
          }
      };
      mCountDownTimer.start();*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        onView()
        onActionView()
        binding.progress.max = 10

        timer = object : CountDownTimer((counter * 1000).toLong(), 1000) {
            override fun onTick(l: Long) {
                binding.progress.setProgress((l / 1000).toInt(),true)
               // binding.progress.progress = (l / 1000).toInt()
                Log.d("count11", "onTick: $l")
            }

            override fun onFinish() {

            }


        }.start()


}




private fun onActionView() {
    val selectItemAnim = AnimationUtils.loadAnimation(this, R.anim.answer_anim)

    binding.trueAnswer.setOnClickListener {
        it.startAnimation(selectItemAnim)
        if (isCorrect == true) {
            level++
            counter += 3
        } else {
            level--
            counter -= 5
        }
        onView()
    }

    binding.falseAnser.setOnClickListener {
        it.startAnimation(selectItemAnim)
        if (isCorrect == false) {
            level++
            counter += 3

        } else {
            level--
            counter -= 5
        }
        onView()
    }
}

private fun onView() {
    val number1 = (0..100).random()
    val number2 = (0..100).random()
    val isTrue = (0..1).random()
    var answer = 0
    val falseAnswer = (0..250).random()
    var text = ""
    val amal = (0..3).random()


    when (amal) {
        0 -> {
            answer = number1 + number2
            text = "$number1 + $number2"
        }

        1 -> {
            answer = number1 - number2
            text = "$number1 - $number2"
        }

        2 -> {
            answer = number1 * number2
            text = "$number1 * $number2"
        }

        3 -> {
            answer = number1 / number2
            text = "$number1 / $number2"
        }
    }
    if (isTrue == 0) {
        isCorrect = true
    } else
        isCorrect = false

    if (isCorrect as Boolean) {
        binding.questionText.text = "$text = $answer"
    } else {
        binding.questionText.text = "$text = $falseAnswer"
    }
    binding.level.text = "Ball: $level"
    ObjectAnimator.ofInt(binding.progress, "progress", counter)
        .setDuration(2000)
        .start()
}
}