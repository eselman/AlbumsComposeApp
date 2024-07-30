package com.eselman.albumscomposeapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AlbumPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val albumUrl = MutableStateFlow<String?>(null)

    init {
        albumUrl.value = savedStateHandle.get<String>("albumUrl")
    }
}