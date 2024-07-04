package com.example.composetest

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BrandonViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    /**
     *  [Approach 1]
     *  This approach will trigger all recomposition
     */

    data class UiState(
        val testInt: Int = 1,
        val testIntState: State<Int> = mutableIntStateOf(1),
        val testLongState: State<Long> = mutableLongStateOf(1L)
    )

    private val _uiState = mutableStateOf(UiState())
    val uiState: State<UiState> = _uiState

    fun addTestInt() {
        _uiState.value = _uiState.value.copy(
            testInt = _uiState.value.testInt + 1
        )
    }

    fun addTestIntState() {
        _uiState.value = _uiState.value.copy(
            testIntState = mutableIntStateOf(_uiState.value.testIntState.value + 1)
        )
    }

    fun addTestLongState() {
        _uiState.value = _uiState.value.copy(
            testLongState = mutableLongStateOf(_uiState.value.testLongState.value + 1)
        )
    }

    /**
     *  [Approach 2]
     *  This approach will trigger specific recomposition
     */

//    var testInt: Int = 1
//    var testIntState by mutableIntStateOf(1)
//        private set
//    var testLongState by mutableLongStateOf(1L)
//        private set
//
//
//    fun addTestInt() {
//        testInt += 1
//    }
//
//    fun addTestIntState() {
//        testIntState += 1
//    }
//
//    fun addTestLongState() {
//        testLongState += 1
//    }

}
