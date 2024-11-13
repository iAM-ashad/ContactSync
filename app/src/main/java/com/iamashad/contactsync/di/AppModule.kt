package com.iamashad.contactsync.di

import com.google.firebase.firestore.FirebaseFirestore
import com.iamashad.contactsync.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
    @Singleton
    @Provides
    fun provideContactRepository(firestore: FirebaseFirestore): ContactRepository {
        return ContactRepository(firestore)
    }
}