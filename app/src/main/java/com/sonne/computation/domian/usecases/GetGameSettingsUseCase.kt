package com.sonne.computation.domian.usecases

import com.sonne.computation.domian.entity.GameSettings
import com.sonne.computation.domian.entity.Level
import com.sonne.computation.domian.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSetting(level)
    }
}