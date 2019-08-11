package com.example.neostore.ui.mvvm.address

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

@Database(
    entities = [AddressEntity::class],
    version = 1
)
abstract class AddressDatabase : RoomDatabase() {
    companion object {
        private var mInstance: AddressDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AddressDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AddressDatabase::class.java,
                    "address"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

                //roomCallback will populate our table with two rows inserted while creation of it.
            }
            return mInstance!!
        }

        private val roomCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                mInstance?.let { PopulateDbAsyncTask(it).execute() }
            }
        }

        //Table with already two rows inserted.
        private class PopulateDbAsyncTask(db: AddressDatabase) : AsyncTask<Unit, Unit, Unit>() {

            private val addressDao: AddressDao = db.addressDao()

            override fun doInBackground(vararg params: Unit?) {
                addressDao.insert(
                    AddressEntity(
                        "Green Valley, Devad",
                        "D.D.Vispute College",
                        "Panvel",
                        "MH",
                        "450000",
                        "INDIA"
                    )
                )

                addressDao.insert(
                    AddressEntity(
                        "The Ruby",
                        "Railway Station",
                        "Dadar",
                        "MH",
                        "120000",
                        "INDIA"
                    )
                )
            }
        }
    }

    abstract fun addressDao(): AddressDao
}