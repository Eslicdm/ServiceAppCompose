package com.eslirodrigues.serviceappcompose.management.memberTab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.serviceappcompose.management.memberTab.model.MemberModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface MemberUiState {
    data object Loading : MemberUiState
    data class Loaded(val members: List<MemberModel>) : MemberUiState
    data class Error(val message: String) : MemberUiState
}

class MemberViewModel(private val repository: MemberRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MemberUiState>(MemberUiState.Loading)
    val uiState: StateFlow<MemberUiState> = _uiState.asStateFlow()

    init {
        fetchMembers()
    }

    fun fetchMembers() {
        viewModelScope.launch {
            _uiState.value = MemberUiState.Loading
            try {
                val members = repository.getMembers()
                _uiState.value = MemberUiState.Loaded(members)
            } catch (e: Exception) {
                _uiState.value =
                    MemberUiState.Error("Failed to load members. Please try again later.")
            }
        }
    }

    fun deleteMember(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteMember(id)
                fetchMembers()
            } catch (e: Exception) {
                // Handle delete error if needed
            }
        }
    }

    fun saveMember(member: MemberModel, isEdit: Boolean) {
        viewModelScope.launch {
            try {
                if (isEdit) {
                    repository.updateMember(member.id, member)
                } else {
                    repository.createMember(member)
                }
                fetchMembers()
            } catch (e: Exception) {
                // Handle save error
            }
        }
    }
}