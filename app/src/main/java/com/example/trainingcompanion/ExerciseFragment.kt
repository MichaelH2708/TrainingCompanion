package com.example.trainingcompanion

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.trainingcompanion.databinding.FragmentExerciseBinding
import kotlinx.android.synthetic.main.fragment_exercise.*

class ExerciseFragment : Fragment() {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExerciseBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_exercise,
            container, false
        )
        exerciseList=Constants.defaultExerciseList()
        setupRestView()


        return binding.root
    }

    override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        super.onDestroy()
    }


    private fun setupRestView() {

        llRestView?.visibility = View.VISIBLE
        llExerciseView?.visibility = View.GONE


        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition+1].getName()

        setRestProgressBar()
    }


    private fun setRestProgressBar() {

        progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 10 - restProgress
                tvTimer.text =
                    (10 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++

                setupExerciseView()
            }
        }.start()
    }


    private fun setupExerciseView() {

        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE


        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.text = exerciseList!![currentExercisePosition].getName()

        setExerciseProgressBar()
    }

    private fun setExerciseProgressBar() {

        progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBarExercise.progress = 30 - exerciseProgress
                tvExerciseTimer.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition < 11) {
                    setupRestView()
                } else {
                }
            }
        }.start()
    }
}