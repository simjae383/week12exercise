package com.parkdaeyuidev.week12exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.parkdaeyuidev.week12exercise.adapters.QuestionAdapter
import com.parkdaeyuidev.week12exercise.models.QuestionData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val mQuestionList = ArrayList<QuestionData>()

    lateinit var mQuestionAdapter : QuestionAdapter

    var myNums = ArrayList<Int>()
    var correctNums = ArrayList<Int>()
    var isRestart = false
    var repeatQuest = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myNums = arrayListOf(0,0,0)

        mQuestionList.add(QuestionData(111, "정답입니다."))

        mQuestionAdapter = QuestionAdapter(this, R.layout.question_list_item, mQuestionList)
        questionListView.adapter = mQuestionAdapter

        setupEvents()
    }

    fun setupEvents(){
        startBtn.setOnClickListener {
            startBtn.isEnabled = false
            answerBtn.isEnabled = true
            activeTxt.text = "게임 시작되었습니다."
            setCorrectNum()
        }
        answerBtn.setOnClickListener {
            checkNum()
         }
    }

    fun setCorrectNum(){
        for (i in 0 .. 2){
            while(true){

                val randomNum = (Math.random()*9+1).toInt()
                var isRepeatOk = false
                for(num in correctNums){
                    if(num == randomNum){
                        isRepeatOk = true;
                        break;
                    }
                }
                if(isRepeatOk == false){
                    correctNums.add(randomNum)
                    break;
                }
            }
        }
        Log.d("answer", "${correctNums[0]} ${correctNums[1]} ${correctNums[2]}")
    }

    fun userInput(){
        val inputNum = inputNumEdt.text
        Toast.makeText(this, inputNum, Toast.LENGTH_SHORT).show()

    }
    fun checkNum(){

        var strike = 0
        var ball = 0
        for(i in 0..2){

        }
    }
}