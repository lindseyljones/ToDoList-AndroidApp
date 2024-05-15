package edu.virginia.cs.helloworldhw.data
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity // we this as a table in our database
data class Bucketlist( // what do we want to save with our bucketlist item
    val name: String,
    val dueDate: String,
    val isChecked: Boolean,
    val dateCompleted: String? = null,
    @PrimaryKey val id: Int? = null,
    // var dateDue: Date? = null,
    // var dateCompleted: Date? = null
)
