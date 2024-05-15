package edu.virginia.cs.helloworldhw.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// telling it this is our Dao object
@Dao
// define the functions we want to use to interact with the database
interface BucketlistDAO {

    // suspend - stop function until insert has finished
    @Insert(onConflict = OnConflictStrategy.REPLACE) // if the same bucket list item is there, replace it
    suspend fun insertItem(bucketlist: Bucketlist)

    @Delete
    suspend fun deleteItem(bucketlist: Bucketlist)

    @Query("SELECT * FROM bucketlist WHERE id = :id")
    suspend fun getItemById(id: Int): Bucketlist?

    // flow - get real time updates
    @Query("SELECT * FROM bucketlist")
    fun getItems(): Flow<List<Bucketlist>>

    @Query("SELECT * FROM bucketlist WHERE isChecked = true ORDER BY dueDate ASC")
    fun getCheckedItems(): Flow<List<Bucketlist>>

    @Query("SELECT * FROM bucketlist WHERE isChecked = false ORDER BY dueDate ASC")
    fun getUncheckedItems(): Flow<List<Bucketlist>>

}