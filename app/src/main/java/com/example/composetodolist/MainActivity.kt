package com.example.composetodolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetodolist.ui.theme.ComposeToDoListTheme
import com.example.composetodolist.view.SaveTask
import com.example.composetodolist.view.ToDoList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeToDoListTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "toDoList") {
                    composable(
                        route = "toDoList"
                    ){
                        ToDoList(navController)
                    }

                    composable(
                        route = "saveTask"
                    ){
                        SaveTask(navController)
                    }
                }

            }
        }
    }
}

