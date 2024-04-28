package com.example.composetodolist.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetodolist.ItemList.taskItem
import com.example.composetodolist.R
import com.example.composetodolist.repository.RepositoryTasks
import com.example.composetodolist.ui.theme.Light_Black
import com.example.composetodolist.ui.theme.Gray
import com.example.composetodolist.ui.theme.Selected_Green_Radio_Button
import com.example.composetodolist.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ToDoList(navController: NavController) {

    val repositoryTasks = RepositoryTasks()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        "To do list",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.primary,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("saveTask")
                },
                containerColor = Selected_Green_Radio_Button,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    tint = MaterialTheme.colorScheme.onSecondary,
                    contentDescription = "Icon to add task."
                )
            }
        }
    ) {

        Surface(modifier = Modifier
            .padding(0.dp, 70.dp, 0.dp, 0.dp )
        )

        {
            val systemUiController = rememberSystemUiController()
            val systemBarColor = MaterialTheme.colorScheme.primary
            SideEffect {
                systemUiController.setSystemBarsColor(systemBarColor)
            }

            val todoList = repositoryTasks.rescueTasks().collectAsState(mutableListOf()).value

            LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
                itemsIndexed(todoList){position, _, ->
                    taskItem(position = position, toDoList = todoList, context, navController)
                }
            }
        }
    }
}
