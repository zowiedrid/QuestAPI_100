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


        fun updateUiState(detailSiswa: DetailSiswa) {
            uiStateSiswa =
                UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))

        }

        private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa ): Boolean {
            return with(uiState) {
                nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
            }
        }

        suspend fun updateSiswa(){
        if (validasiInput()){}
            repositorySiswa.updateSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
    }
