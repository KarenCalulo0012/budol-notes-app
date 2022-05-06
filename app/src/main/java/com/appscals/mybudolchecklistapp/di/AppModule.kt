package com.appscals.mybudolchecklistapp.di

import android.app.Application
import androidx.room.Room
import com.appscals.mybudolchecklistapp.data.local.ItemsDB
import com.appscals.mybudolchecklistapp.data.repository.DataRepositoryImpl
import com.appscals.mybudolchecklistapp.dataUtils.DATABASE_NAME
import com.appscals.mybudolchecklistapp.domain.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemDatabase(app: Application) = Room.databaseBuilder(
        app,
        ItemsDB::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRepository(db: ItemsDB): DataRepository {
        return DataRepositoryImpl(db.itemsDao)
    }
}