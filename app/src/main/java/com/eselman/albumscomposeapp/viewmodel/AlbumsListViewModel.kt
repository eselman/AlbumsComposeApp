package com.eselman.albumscomposeapp.viewmodel

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
class AlbumsListViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
): ViewModel() {

    val isLoading = MutableStateFlow(false)
    val isError = MutableStateFlow(false)
    val isRefreshing = MutableStateFlow(false)
    val albums = MutableStateFlow<List<Album>>(mutableListOf())

    init {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.value = true
            isError.value = false
            val albumState = albumRepository.getAlbums()
            if (albumState.isError) {
                isError.value = true
                isLoading.value = false
                albums.value = mutableListOf()
            } else {
                albums.value = albumState.albums ?: mutableListOf()
                isError.value = false
                isLoading.value = false
            }
        }
    }

    fun refreshAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            isRefreshing.value = true
            isError.value = false
            isLoading.value = false
            val albumState = albumRepository.refreshAlbums()
            if (albumState.isError) {
                isError.value = true
                isLoading.value = false
                isRefreshing.value = false
                albums.value = mutableListOf()
            } else {
                albums.value = albumState.albums ?: mutableListOf()
                isError.value = false
                isLoading.value = false
                isRefreshing.value = false
            }
        }
    }
}
