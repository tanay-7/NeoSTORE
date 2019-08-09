package com.example.neostore.ui.mvvm.addaddress

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
    entities = [AddressEntity::class],
    version = 1
)
abstract class AddressDatabase : RoomDatabase() {
    companion object {
        private lateinit var mInstance: AddressDatabase

        @Synchronized
        fun getInstance(context: Context): AddressDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AddressDatabase::class.java,
                    "address"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance
        }
    }

    abstract fun addressDao(): AddressDao
}