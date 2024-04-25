package com.example.composetodolist.view

import android.annotation.SuppressLint
import android.view.WindowInsets.Side
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.invalidateGroupsWithKey
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetodolist.ItemList.taskItem
import com.example.composetodolist.R
import com.example.composetodolist.model.Task
import com.example.composetodolist.repository.RepositoryTasks
import com.example.composetodolist.ui.theme.Dark_Green
import com.example.composetodolist.ui.theme.Green
import com.example.composetodolist.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.Firebase

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
                    containerColor = Green
                ),
                title = {
                    Text(
                        "To do list",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = White
                    )
                }
            )
        },
        containerColor = Dark_Green,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("saveTask")
                },
                containerColor = Green,
                shape = CircleShape
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
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
            SideEffect {
                systemUiController.setSystemBarsColor(Green)
            }

            val todoList = repositoryTasks.rescueTasks().collectAsState(mutableListOf()).value

            LazyColumn {
                itemsIndexed(todoList){position, _, ->
                    taskItem(position = position, toDoList = todoList, context, navController)
                }
            }
        }
    }
}
