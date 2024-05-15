package edu.virginia.cs.helloworldhw.ui.theme.bucketlist_list

import edu.virginia.cs.helloworldhw.data.Bucketlist

sealed class BucketlistEvent {
    data class DeleteListItem(val bucketlist: Bucketlist): BucketlistEvent()
    data class AfterChange(val bucketlist: Bucketlist, val isChecked: Boolean, val dateCompleted: String): BucketlistEvent()
    data class OnListItemClick(val bucketlist: Bucketlist): BucketlistEvent()
    object OnAddListItem: BucketlistEvent()

}