package com.example.ortegatec.viewmodel

import androidx.lifecycle.*
import com.example.ortegatec.data.repository
import com.example.ortegatec.model.Laboratorio
import kotlinx.coroutines.launch

class LabViewModel(private val repository: repository) : ViewModel() {

    val allLaboratorios: LiveData<List<Laboratorio>> = repository.laboratorios.asLiveData()

    fun insert(laboratorio: Laboratorio) = viewModelScope.launch {
        repository.insertarLaboratorio(laboratorio)
    }

    private val _syncStatus = MutableLiveData<Boolean?>()
    val syncStatus: LiveData<Boolean?> = _syncStatus

    fun sincronizar() = viewModelScope.launch {
        try {
            val response = repository.sincronizarConNube()
            _syncStatus.postValue(response.isSuccessful)
        } catch (e: Exception) {
            _syncStatus.postValue(false)
        }
    }

    fun resetSyncStatus() {
        _syncStatus.value = null
    }
}

class LabViewModelFactory(private val repository: repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LabViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LabViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
