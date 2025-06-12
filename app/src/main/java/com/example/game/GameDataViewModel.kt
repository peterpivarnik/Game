package com.example.game

import androidx.lifecycle.ViewModel

class GameDataViewModel() : ViewModel() {

    private val dataRepository: DataRepository = DataRepository()

    fun incrementIndex() {
        dataRepository.incrementIndex()
    }

    fun decrementIndex() {
        dataRepository.decrementIndex()
    }

    fun getCurrentDataHolder(): DataHolder {
        return dataRepository.getCurrentDataHolder()
    }

    fun getHoldersListSize(): Int {
        return dataRepository.getHoldersListSize()
    }

    fun getCurrentIndex(): Int {
        return dataRepository.getCurrentIndex()
    }

}