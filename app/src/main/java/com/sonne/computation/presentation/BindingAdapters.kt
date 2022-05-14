package com.sonne.computation.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.sonne.computation.R
import com.sonne.computation.domian.entity.GameResult

interface OnOptionClickListener{
    fun onOptionClick(option: Int)
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, enough: Boolean): Int {
    val colorResId = if (enough) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("sum")
fun bindSum(textView: TextView, sum: Int) {
    textView.text = sum.toString()
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, gameResult: GameResult) {
    imageView.setImageResource(getSmileResId(gameResult))
}

private fun getSmileResId(gameResult: GameResult): Int {
    return if (gameResult.winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percentage
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}
@BindingAdapter("onOptionClockListener")
fun bindOnOptionClockListener(textView: TextView, clickListener: OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}