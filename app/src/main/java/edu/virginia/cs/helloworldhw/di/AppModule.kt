package edu.virginia.cs.helloworldhw.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.virginia.cs.helloworldhw.data.BucketlistDatabase
import edu.virginia.cs.helloworldhw.data.BucketlistRepository
import edu.virginia.cs.helloworldhw.data.BucketlistRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // how long our dependencies are defined here
object AppModule {

    @Provides // provides a dependency
    @Singleton // only be a single instance that exists of it
    fun provideBucketlistDatabase(app: Application): BucketlistDatabase {
        return Room.databaseBuilder(
            app,
            BucketlistDatabase::class.java,
            name="Bucketlist_db"
        ).build()
    }

    // now create the repository instance
    @Provides
    @Singleton
    fun provideBucketlistRepository(db: BucketlistDatabase): BucketlistRepository {
        return BucketlistRepositoryImpl(db.dao)
    }
}