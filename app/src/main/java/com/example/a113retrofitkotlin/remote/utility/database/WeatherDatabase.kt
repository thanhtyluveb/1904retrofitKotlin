package com.example.a113retrofitkotlin.remote.utility.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [WeatherModel::class], version = 3)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private class PopulateDbAsync internal constructor(db: WeatherDatabase) : AsyncTask<Void, Void, Void>() {

        private val mDao: WeatherDao

        init {
            mDao = db.weatherDao()
        }

        override fun doInBackground(vararg params: Void): Void? {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            //            mDao.deleteAll();
            return null
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase? {
            if (INSTANCE == null) {
                synchronized(WeatherDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            WeatherDatabase::class.java, "weather_database"
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }


        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }
    }
}
