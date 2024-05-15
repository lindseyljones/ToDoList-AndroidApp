package edu.virginia.cs.helloworldhw.data

import kotlinx.coroutines.flow.Flow

interface BucketlistRepository {
    suspend fun insertItem(bucketlist: Bucketlist)

    suspend fun deleteItem(bucketlist: Bucketlist)

    suspend fun getItemById(id: Int): Bucketlist?

    fun getItems(): Flow<List<Bucketlist>>

    fun getCheckedItems(): Flow<List<Bucketlist>>

    fun getUncheckedItems(): Flow<List<Bucketlist>>
}