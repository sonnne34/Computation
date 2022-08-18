package com.sonne.computation.domian.repository

import com.sonne.computation.domian.entity.GameSettings
import com.sonne.computation.domian.entity.Level
import com.sonne.computation.domian.entity.Question
import java.lang.Integer.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSetting(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    30,
                    30
                )
            }
            Level.EASY -> {
                GameSettings(
                    10,
                    6,
                    60,
                    60
                )
            }
            Level.NORMAL -> {
                GameSettings(
                    10,
                    20,
                    80,
                    40
                )
            }
            Level.HARD -> {
                GameSettings(
                    100,
                    10,
                    70,
                    60
                )
            }
        }
    }
}