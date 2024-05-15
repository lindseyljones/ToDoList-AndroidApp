package edu.virginia.cs.helloworldhw.data

import androidx.room.Database
import androidx.room.RoomDatabase

// declare it a database and define entities and version
@Database(
    entities = [Bucketlist::class],
    version = 1
)
abstract class BucketlistDatabase : RoomDatabase() {

    abstract val dao: BucketlistDAO
}