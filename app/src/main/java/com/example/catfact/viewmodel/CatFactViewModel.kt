package com.example.catfact.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catfact.data.model.CatFact
import com.example.catfact.data.repository.CatFactRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CatFactViewModel(private val catFactRepository: CatFactRepository): ViewModel() {

    private val _catFact = MutableLiveData<CatFact>()
    val catFact: LiveData<CatFact> = _catFact

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchFact() {
        viewModelScope.launch {
            try {
                val response = catFactRepository.getFact()
                if (response.isSuccessful) {
                    _catFact.postValue(response.body())
                } else {
                    _error.postValue("Error: ${response.code()}")
                }
            } catch (e: HttpException) {
                _error.postValue("Network error: ${e.message}")
            } catch (e: Exception) {
                _error.postValue("Unexpected error: ${e.message}")
            }
        }
    }
}
