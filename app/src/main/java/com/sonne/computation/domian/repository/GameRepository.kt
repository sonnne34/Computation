package com.sonne.computation.domian.repository

import com.sonne.computation.domian.entity.GameSettings
import com.sonne.computation.domian.entity.Level
import com.sonne.computation.domian.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSetting(level: Level): GameSettings
}