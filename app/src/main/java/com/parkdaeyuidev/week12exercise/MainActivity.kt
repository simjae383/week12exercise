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
    var inputNum = 0
    var tempNum = 0
    var isRestart = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mQuestAdapter = QuestionAdapter(this, R.layout.question_list_item, mQuestionList)
        questionListView.adapter = mQuestAdapter
        setupEvents()
    }

    fun setupEvents(){
        startBtn.setOnClickListener {
            if(isRestart == true){
                repeatQuest = 0
                correctNums.clear()
                mQuestionList.clear()
                mQuestAdapter.notifyDataSetChanged()
            }
            repeatTxt.text = "질문 횟수 : $repeatQuest"
            myNums = arrayListOf(0,0,0)
            startBtn.isEnabled = false
            inputNumEdt.isEnabled = true
            answerBtn.isEnabled = true
            activeTxt.text = "게임 시작"
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
                if(!isRepeatOk){
                    correctNums.add(randomNum)
                    break;
                }
            }
        }
    }

    fun userInput(){
        inputNum = inputNumEdt.text.toString().toInt()
        inputNumEdt.text.clear()
        tempNum = inputNum
        if(inputNum in 111..999){
            for(i in 0..2){
                myNums[i] = inputNum % 10;
                inputNum /= 10;
                if (inputNum == 0){
                    break
                }
            }
        } else {
            Toast.makeText(this, "잘못된 입력입니다.", Toast.LENGTH_SHORT).show()
        }
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
            goodJob()
        } else {
            resultMsg = "틀렸습니다. $strike 스트라이크, $balls 볼입니다."
            repeatQuest++
        }
        Toast.makeText(this, resultMsg, Toast.LENGTH_SHORT).show()
        repeatTxt.text = "질문 횟수 : $repeatQuest"
        mQuestionList.add(QuestionData(tempNum, resultMsg))
        mQuestAdapter.notifyDataSetChanged()
    }

    private fun goodJob(){
        activeTxt.text = "정답입니다."
        answerBtn.isEnabled = false
        startBtn.isEnabled = true
        inputNumEdt.isEnabled = false
        isRestart = true
    }
}