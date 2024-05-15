package edu.virginia.cs.helloworldhw.ui.theme.bucketlist_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.virginia.cs.helloworldhw.data.Bucketlist
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun ListItem(
    bucketlist: Bucketlist,
    onEvent: (BucketlistEvent) -> Unit, // doesn't return anything
    modifier: Modifier = Modifier
    ){
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val currentDate = sdf.format(Date())
    currentDate.replace("-","")

    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = bucketlist.name,
                    fontSize = 15.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text( text = (bucketlist.dueDate.substring(4,6)+"-"
                        +bucketlist.dueDate.substring(6,8)+"-"
                        +bucketlist.dueDate.substring(0,4)),
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic)

                Spacer(modifier = Modifier.width(4.dp))
                if(bucketlist.isChecked) {
                    Text(
                        text = "C",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                            .padding(2.dp)
                    )
                }
                if (!bucketlist.isChecked) {
                    Text(
                        text = "IP",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier =
                        Modifier
                            .background(color = Color.LightGray)
                            .padding(2.dp)
                    )
                }
                IconButton(onClick = { onEvent(BucketlistEvent.DeleteListItem(bucketlist)) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Button")
                }
                IconButton(onClick = { onEvent(BucketlistEvent.OnListItemClick(bucketlist)) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Button")
                }
            }

        }
        Checkbox(checked = bucketlist.isChecked, onCheckedChange = { isChecked ->
            onEvent(BucketlistEvent.AfterChange(bucketlist, isChecked, currentDate))
        })
    }

}