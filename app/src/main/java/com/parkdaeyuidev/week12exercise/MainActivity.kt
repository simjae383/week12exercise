package com.parkdaeyuidev.week12exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.parkdaeyuidev.week12exercise.adapters.QuestionAdapter
import com.parkdaeyuidev.week12exercise.models.QuestionData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mQuestionList = ArrayList<QuestionData>()

    lateinit var mQuestAdapter : QuestionAdapter

    var myNums = ArrayList<Int>()
    var correctNums = ArrayList<Int>()
    var repeatQuest = 0
    var goodJob = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mQuestionList.add(QuestionData(111,"틀렸습니다."))

        mQuestAdapter = QuestionAdapter(this, R.layout.question_list_item, mQuestionList)
        questionListView.adapter = mQuestAdapter
        //setupEvents()
    }

    fun setupEvents(){
        startBtn.setOnClickListener {
            myNums = arrayListOf(0,0,0)
            startBtn.isEnabled = false
            inputNumEdt.isEnabled =true
            answerBtn.isEnabled = true
            activeTxt.text = "게임 시작되었습니다."
            setCorrectNum()
        }
        answerBtn.setOnClickListener {
            userInput()
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
        Log.d("answer", "정답 ${correctNums[0]} ${correctNums[1]} ${correctNums[2]}")
    }

    fun userInput(){
        var inputNum = inputNumEdt.text.toString().toInt()
        var isRangeOk = false
        if(111 <= inputNum && inputNum <= 999){
            isRangeOk = true
            for(i in 0..2){
                myNums[i] = inputNum % 10;
                inputNum /= 10;
                if (inputNum == 0){
                    break
                }
            }
        } else {

        }
        Toast.makeText(this, "체크", Toast.LENGTH_SHORT).show()
        checkNum()
    }
    fun checkNum(){

        var strike = 0
        var balls = 0
        var resultMsg = ""
        for(i in 0..2){
            for(j in 0..2){
                if(myNums[i] == correctNums[j]){
                    if(i == j)
                        strike++;
                    else
                        balls++
                }
            }
        }
        if (strike == 3){
            resultMsg = "정답입니다. ${repeatQuest}회만에 맞추셨습니다."
            goodJob = true
        } else {
            resultMsg = "틀렸습니다. ${strike} 스트라이크, ${balls} 볼입니다."
        }

    }
}