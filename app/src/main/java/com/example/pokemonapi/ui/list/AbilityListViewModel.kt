package com.example.pokemonapi.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.data.AbilityRepository
import com.example.pokemonapi.models.AbilityItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AbilityListViewModel @Inject constructor(
    private val repo: AbilityRepository
) : ViewModel() {

    private val _abilities = MutableStateFlow<List<AbilityItem>>(emptyList())
    val abilities: StateFlow<List<AbilityItem>> = _abilities

    private var limit = 10
    private var offset = 0

    fun setLimit(newLimit: Int) {
        limit = newLimit
        offset = 0
        fetch()
    }

    fun nextPage() {
        offset += limit
        fetch()
    }

    fun prevPage() {
        if (offset - limit >= 0) offset -= limit
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            try {
                val res = repo.getAbilities(limit, offset)
                _abilities.value = res.results
            } catch (e: Exception) { e.printStackTrace() }
        }
    }
}
