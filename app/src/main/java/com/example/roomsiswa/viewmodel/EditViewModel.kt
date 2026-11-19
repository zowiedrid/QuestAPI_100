package com.example.roomsiswa.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsiswa.repositori.OfflineRepositorySiswa
import com.example.roomsiswa.view.route.DestinasiEditSiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: OfflineRepositorySiswa): ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiEditSiswa.itemIdArg]) {
        init{
            viewModelScope.launch {
              uiStateSiswa = repositorySiswa.getSiswaStream(idSiswa)
                  .filterNotNull()
                  .first()
                  .toUiStateSiswa(isEntryValid = true)
            }
        }



        }
    }
