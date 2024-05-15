package edu.virginia.cs.helloworldhw.data

import kotlinx.coroutines.flow.Flow

class BucketlistRepositoryImpl(
    // getting access to our database
    private val dao: BucketlistDAO
) : BucketlistRepository {
    override suspend fun insertItem(bucketlist: Bucketlist) {
        dao.insertItem(bucketlist)
    }

    override suspend fun deleteItem(bucketlist: Bucketlist) {
        dao.deleteItem(bucketlist)
    }

    override suspend fun getItemById(id: Int): Bucketlist? {
        return dao.getItemById(id)
    }

    override fun getItems(): Flow<List<Bucketlist>> {
        return dao.getItems()
    }

    override fun getCheckedItems(): Flow<List<Bucketlist>> {
        return dao.getCheckedItems()
    }

    override fun getUncheckedItems(): Flow<List<Bucketlist>> {
        return dao.getUncheckedItems()
    }
}