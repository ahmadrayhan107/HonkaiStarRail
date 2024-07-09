package com.dicoding.honkaistarrail.data

import com.dicoding.honkaistarrail.model.HonkaiStarRailCharacters
import com.dicoding.honkaistarrail.model.HonkaiStarRailData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class HonkaiStarRailRepository {
    private val honkaiStarRailCharacters = mutableListOf<HonkaiStarRailCharacters>()

    init {
        if (honkaiStarRailCharacters.isEmpty()) {
            HonkaiStarRailData.dummyData.forEach {
                honkaiStarRailCharacters.add(HonkaiStarRailCharacters(it, 0))
            }
        }
    }

    fun getAllCharacters(): Flow<List<HonkaiStarRailCharacters>> {
        return flowOf(honkaiStarRailCharacters)
    }

    fun getDetailCharacter(characterId: String): HonkaiStarRailCharacters {
        return honkaiStarRailCharacters.first {
            it.honkaiStarRail.id == characterId
        }
    }

    fun searchCharacters(query: String): Flow<List<HonkaiStarRailCharacters>> {
        return getAllCharacters().map { data ->
            data.filter { it.honkaiStarRail.name.contains(query, ignoreCase = true) }
        }
    }

    companion object {
        @Volatile
        private var instance: HonkaiStarRailRepository? = null

        fun getInstance(): HonkaiStarRailRepository =
            instance ?: synchronized(this) {
                HonkaiStarRailRepository().apply {
                    instance = this
                }
            }
    }
}