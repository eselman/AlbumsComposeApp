package com.eselman.albumscomposeapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eselman.albumscomposeapp.model.Album
import com.eselman.albumscomposeapp.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val albumRepository: AlbumRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val album = MutableStateFlow<Album?>(null)

    init {
        val albumId = savedStateHandle.get<String>("albumId") ?: ""
        viewModelScope.launch(Dispatchers.IO) {
            album.value = albumRepository.getAlbumById(albumId)
        }
    }
}
