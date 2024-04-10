package com.example.composetodolist.ItemList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composetodolist.R
import com.example.composetodolist.model.Task
import com.example.composetodolist.ui.theme.Black
import com.example.composetodolist.ui.theme.Dark_Green
import com.example.composetodolist.ui.theme.Selected_Green_Radio_Button
import com.example.composetodolist.ui.theme.Selected_Red_Radio_Button
import com.example.composetodolist.ui.theme.Selected_Yellow_Radio_Button
import com.example.composetodolist.ui.theme.White
import com.example.composetodolist.ui.theme.shapePriorityCard

@Composable
fun taskItem(position: Int, toDoList: MutableList<Task>){

    val taskTitle = toDoList[position].task
    val taskDescription = toDoList[position].description
    val taskPriority = toDoList[position].priorityLevel

    var priorityLevel: String = when(taskPriority){
        0 -> { "No priority" }
        1 -> { "Low priority" }
        2 -> { "Medium priority" }
        else -> { "High priority" }
    }

    val priorityColor = when(taskPriority){
        0 -> { Dark_Green }
        1 -> { Selected_Green_Radio_Button }
        2 -> { Selected_Yellow_Radio_Button }
        else -> { Selected_Red_Radio_Button }
    }

    Card (
       colors = CardDefaults.cardColors(
           containerColor = White
       ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 5.dp, 10.dp, 5.dp)
    ) {
        ConstraintLayout (
            modifier = Modifier.padding(5.dp)
        ) {
            val(textTitle, textDescription, cardPriority, textPriority, deleteButton) = createRefs()

            Text(
                text = taskTitle.toString(),
                color = Black,
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(textTitle){
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = taskDescription.toString(),
                color = Black,
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(textDescription){
                    top.linkTo(textTitle.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    width = Dimension.preferredWrapContent
                }
            )

            Text(
                text = priorityLevel,
                color = Black,
                fontSize = 12.sp,

                modifier = Modifier.constrainAs(textPriority){
                    top.linkTo(textDescription.bottom)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 5.dp)
                }
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = priorityColor
                ),
                modifier = Modifier
                    .size(15.dp)
                    .constrainAs(cardPriority) {
                        top.linkTo(textDescription.bottom)
                        start.linkTo(textPriority.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 5.dp)
                    },
                shape = shapePriorityCard.large
            ) { }

            IconButton(
                onClick = { },
                modifier = Modifier.constrainAs(deleteButton){
                    top.linkTo(textDescription.bottom)
                    end.linkTo(parent.end, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 5.dp)
                }
           ) {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = "Icon to delete task.")
            }
        }
    }
}