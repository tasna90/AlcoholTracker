package com.example.alcoholTracker.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.alcoholTracker.common.Constants.DATABASE_NAME
import com.example.alcoholTracker.database.AlcoholTrackerDatabase
import com.example.alcoholTracker.domain.RepositoryImpl
import com.example.alcoholTracker.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val PREFERENCES_DATA_STORE = "alcohol_tracker_user_pref_data_store"

val Context.dataStore by preferencesDataStore(name = PREFERENCES_DATA_STORE)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*@Provides
    @Singleton
    fun provideRealm(): RealmConfiguration {
        return RealmConfiguration.create(
            schema = setOf(User::class)
        )
    }

    @Provides
    @Singleton
    fun provideRepository(configuration: RealmConfiguration): Repository {
        return RepositoryImpl(configuration)
    }*/

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return appContext.dataStore
    }

    @Provides
    @Singleton
    fun provideStopAlcoholDatabase(@ApplicationContext appContext: Context): AlcoholTrackerDatabase {
        return Room.databaseBuilder(
            appContext,
            AlcoholTrackerDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(database: AlcoholTrackerDatabase): Repository {
        return RepositoryImpl(database)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("alcohol_tracker_shared_pref", MODE_PRIVATE)
    }
}