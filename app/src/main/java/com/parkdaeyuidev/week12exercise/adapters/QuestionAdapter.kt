package com.parkdaeyuidev.week12exercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.parkdaeyuidev.week12exercise.R
import com.parkdaeyuidev.week12exercise.models.QuestionData

class QuestionAdapter(
    val mContext : Context,
    val resId : Int,
    val mList : ArrayList<QuestionData>
) : ArrayAdapter<QuestionData>(mContext, resId, mList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if (tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.question_list_item, null)
        }

        val row = tempRow!!

        val inputTxt = row.findViewById<TextView>(R.id.inputTxt)
        val outputTxt = row.findViewById<TextView>(R.id.outputTxt)

        inputTxt.text = mList[position].inputNum.toString()
        outputTxt.text = mList[position].outputTxt
        return row
    }
}