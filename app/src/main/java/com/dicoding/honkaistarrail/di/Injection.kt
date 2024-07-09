package com.dicoding.honkaistarrail.di

import com.dicoding.honkaistarrail.data.HonkaiStarRailRepository

object Injection {
    fun provideRepository(): HonkaiStarRailRepository {
        return HonkaiStarRailRepository.getInstance()
    }
}