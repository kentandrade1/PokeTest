package com.example.pokemonapi.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.data.AbilityRepository
import com.example.pokemonapi.models.AbilityDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AbilityDetailViewModel @Inject constructor(
    private val repo: AbilityRepository
) : ViewModel() {

    private val _data = MutableStateFlow<AbilityDetailResponse?>(null)
    val data: StateFlow<AbilityDetailResponse?> = _data

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun load(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                _data.value = repo.getAbilityDetail(id)
            } catch (e: Exception) {
                _error.value = "No se pudo cargar la habilidad."
            }

            _loading.value = false
        }
    }
}
