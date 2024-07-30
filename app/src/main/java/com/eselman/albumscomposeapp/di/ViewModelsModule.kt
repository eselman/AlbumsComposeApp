package com.eselman.albumscomposeapp.di

import com.eselman.albumscomposeapp.database.dao.AlbumDao
import com.eselman.albumscomposeapp.network.services.AlbumsService
import com.eselman.albumscomposeapp.repository.AlbumRepository
import com.eselman.albumscomposeapp.repository.AlbumRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelsModule {
    @Provides
    fun provideAlbumRepositoryImpl(
        albumDao: AlbumDao,
        albumsService: AlbumsService
    ): AlbumRepository =
        AlbumRepositoryImpl(albumDao, albumsService)
}
