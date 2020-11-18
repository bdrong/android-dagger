package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.storage.FakeStorage
import com.example.android.dagger.storage.SharedPreferencesStorage
import com.example.android.dagger.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.Provides

// Overrides StorageModule in android tests
// Because of @Binds, StorageModule needs to be an abstract class
@Module
class TestStorageModule {

    // Makes Dagger provide FakeStorage when a Storage type is requested
//    @Binds
//    abstract fun provideStorage(storage: FakeStorage): Storage

    @RegistrationStorage
    @Provides
    fun provideRegistrationStorage(context: Context): Storage {
        return SharedPreferencesStorage("registration", context)
    }

    @LoginStorage
    @Provides
    fun provideLoginStorage(context: Context): Storage {
        return SharedPreferencesStorage("login", context)
    }
}