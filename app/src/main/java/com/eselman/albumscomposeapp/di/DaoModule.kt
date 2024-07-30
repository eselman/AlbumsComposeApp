package com.eselman.albumscomposeapp.di

import com.eselman.albumscomposeapp.database.dao.AlbumDao
import com.eselman.albumscomposeapp.database.dao.AlbumDaoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DaoModule {
    @Binds
    abstract fun bindAlbumDao(impl: AlbumDaoImpl): AlbumDao
}
