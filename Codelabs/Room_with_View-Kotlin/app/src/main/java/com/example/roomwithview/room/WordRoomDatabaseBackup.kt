package com.example.roomwithview.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomwithview.data.Word

/*
    You annotate the class to be a Room database with @Database and use the annotation parameters to declare the entities that belong
    in the database and set the version number.

    "Each entity corresponds to a table that will be created in the database."

    Database migrations are beyond the scope of this codelab, so we set exportSchema to false here to avoid a build warning.
    In a real app, you should consider setting a directory for Room to use to export the schema so you can check the current
    schema into your version control system.
 */
//TODO 3 : Created the database structure
@Database(entities = arrayOf(Word::class), version=1, exportSchema = false)
//The database class for Room must be abstract and extend RoomDatabase
public abstract class WordRoomDatabase : RoomDatabase(){
    // Singleton prevents multiple instances of database opening at the
    // same time.
    @Volatile
    private var INSTANCE: WordRoomDatabase? = null

    /*
        getDatabase returns the singleton. It'll create the database the first time it's accessed,
        using Room's database builder to create a RoomDatabase object in the application context from
        the WordRoomDatabase class and names it "word_database".
     */
    fun getDatabase(context: Context): WordRoomDatabase {
        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                WordRoomDatabase::class.java,
                "word_database"
            ).build()
            INSTANCE = instance
            // return instance
            instance
        }
    }
    /*
    Note: When you modify the database schema, you'll need to update the version number and define a migration strategy.

    For an example, a destroy and re-create strategy can be sufficient. But for a real app,
    you must implement a migration strategy. See Understanding migrations with Room.

    --------------------

    In Android Studio, if you get errors when you paste code or during the build process, select Build >Clean Project.
    Then select Build > Rebuild Project, and then build again. If you use the provided code, there should be no errors at
    the points where you are instructed to build the app, but there may be errors in-between.
     */


}