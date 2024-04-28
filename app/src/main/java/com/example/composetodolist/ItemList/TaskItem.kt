package com.example.composetodolist.ItemList

import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.composetodolist.model.Task
import com.example.composetodolist.repository.RepositoryTasks
import com.example.composetodolist.ui.theme.Black
import com.example.composetodolist.ui.theme.Light_Black
import com.example.composetodolist.ui.theme.Selected_Green_Radio_Button
import com.example.composetodolist.ui.theme.Selected_Red_Radio_Button
import com.example.composetodolist.ui.theme.Selected_Yellow_Radio_Button
import com.example.composetodolist.ui.theme.White
import com.example.composetodolist.ui.theme.shapePriorityCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun taskItem(position: Int,
             toDoList: MutableList<Task>,
             context: android.content.Context,
             navController: NavController,
             /*isEnabled: Boolean,
             selectedItem: Boolean,
             onClick: () -> Unit,
             onEnableChange: (Boolean) -> Unit*/){

    val taskTitle = toDoList[position].task
    val taskDescription = toDoList[position].description
    val taskPriority = toDoList[position].priorityLevel

    val scope = rememberCoroutineScope()
    val repositoryTasks = RepositoryTasks()

    fun deletionDialog(){

        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Delete Task")
            .setMessage("Do you really want to delete this task?")
            .setPositiveButton("Yes"){_, _ ->

                repositoryTasks.deleteTask(taskTitle.toString())

                scope.launch(Dispatchers.Main){
                    toDoList.removeAt(position)
                    navController.navigate("ToDoList") // TODO find a better way to refresh screen
                    Toast.makeText(context, "Task successfully deleted!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("No"){_, _ ->

            }
            .show()
    }

    var priorityLevel: String = when(taskPriority){
        0 -> { "No priority" }
        1 -> { "Low priority" }
        2 -> { "Medium priority" }
        else -> { "High priority" }
    }

    val priorityColor = when(taskPriority){
        0 -> { MaterialTheme.colorScheme.onSecondary }
        1 -> { Selected_Green_Radio_Button }
        2 -> { Selected_Yellow_Radio_Button }
        else -> { Selected_Red_Radio_Button }
    }

    Card (
       colors = CardDefaults.cardColors(
           containerColor = MaterialTheme.colorScheme.onPrimary
       ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 5.dp, 10.dp, 5.dp)
            .background(MaterialTheme.colorScheme.primary)
            /*.combinedClickable(onLongClick = {
                onEnableChange(true)
            }, onClick = onClick )*/
    ) {
        /*if (isEnabled) {
            Checkbox(
                checked = selectedItem, onCheckedChange = null, modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.End)
            )
        }*/
        ConstraintLayout (
            modifier = Modifier.padding(5.dp)
        ) {
            val(textTitle, textDescription, cardPriority, textPriority, deleteButton) = createRefs()

            Text(
                text = taskTitle.toString(),
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(textTitle){
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            /// TODO revise card space when a task hasn't a description
            Text(
                text = taskDescription.toString(),
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(textDescription){
                    top.linkTo(textTitle.bottom, margin = 5.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(textPriority.top, margin = 5.dp)
                }
            )
            /// TODO discover priority level error
            Text(
                text = priorityLevel,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 12.sp,

                modifier = Modifier.constrainAs(textPriority){
                    top.linkTo(textDescription.bottom)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
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
            /// TODO delete in onlongclick
            /*IconButton(
                onClick = {
                    deletionDialog()
                },
                modifier = Modifier.constrainAs(deleteButton){
                    top.linkTo(textDescription.bottom)
                    end.linkTo(parent.end, margin = 0.dp)
                }
           ) {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = "Icon to delete task.")
            }*/
        }
    }

    @Composable
    fun EachRow(isEnabled: Any, title: String, selectedItem: Any, onClick: () -> Unit, onEnableChange: Any) {

    }

    /*@Composable
    fun selectItems() {

        var isEnabled by remember { mutableStateOf(false) }
        var selectedItems by remember { mutableStateOf<Set<Int>>(emptySet()) }
        var selectAll by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = selectAll) {
            selectedItems = if (selectAll)
                selectedItems.plus(0..10)
            else
                selectedItems.minus(0..10)
        }

        Column {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .toggleable(
                        value = selectAll,
                        onValueChange = {
                            selectAll = it
                            isEnabled = it
                        },
                        role = Role.Checkbox
                    )
            ) {
                Text(text = stringResource(R.string.select_all), modifier = Modifier.weight(1f))
                Checkbox(checked = selectAll, onCheckedChange = null)
            }

            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(10) { index ->
                    EachRow(
                        isEnabled = isEnabled,
                        title = "Item $index",
                        selectedItem = selectedItems.contains(index),
                        onClick = {
                            selectedItems =
                                if (selectedItems.contains(index)) selectedItems.minus(index)
                                else selectedItems.plus(index)
                        },
                        onEnableChange = { value: Boolean ->
                            isEnabled = value
                        },
                    )
                }
            }

        }

    }*/
}