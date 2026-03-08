package com.example.ortegatec.viewmodel

import androidx.lifecycle.*
import com.example.ortegatec.data.repository
import com.example.ortegatec.model.Equipo
import kotlinx.coroutines.launch

class EquipViewModel(private val repository: repository) : ViewModel() {

    fun getEquiposByLab(labId: Int): LiveData<List<Equipo>> = 
        repository.obtenerEquiposPorLaboratorio(labId).asLiveData()

    fun insert(equipo: Equipo) = viewModelScope.launch {
        repository.insertarEquipo(equipo)
    }

    fun update(equipo: Equipo) = viewModelScope.launch {
        repository.actualizarEquipo(equipo)
    }

    fun delete(equipo: Equipo) = viewModelScope.launch {
        repository.eliminarEquipo(equipo)
    }
}

class EquipViewModelFactory(private val repository: repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EquipViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EquipViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
