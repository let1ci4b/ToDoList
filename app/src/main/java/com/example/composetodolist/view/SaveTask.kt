package com.example.composetodolist.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetodolist.components.TextBox
import com.example.composetodolist.components.saveButton
import com.example.composetodolist.ui.theme.Disabled_Green_Radio_Button
import com.example.composetodolist.ui.theme.Disabled_Red_Radio_Button
import com.example.composetodolist.ui.theme.Disabled_Yellow_Radio_Button
import com.example.composetodolist.ui.theme.Green
import com.example.composetodolist.ui.theme.Selected_Green_Radio_Button
import com.example.composetodolist.ui.theme.Selected_Red_Radio_Button
import com.example.composetodolist.ui.theme.Selected_Yellow_Radio_Button
import com.example.composetodolist.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveTask(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green
                ),
                title = {
                    Text(
                        "Save task",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = White
                    )
                }
            )
        },
        containerColor = White,
    ) {

        var taskTitle by remember {
            mutableStateOf("")
        }

        var taskDescription by remember {
            mutableStateOf("")
        }

        var noPriorityTask by remember {
            mutableStateOf(false)
        }

        var lowPriorityTask by remember {
            mutableStateOf(false)
        }

        var mediumPriorityTask by remember {
            mutableStateOf(false)
        }

        var highPriorityTask by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TextBox(
                value = taskTitle,
                onValueChange = {
                    if (it.length <= 30) taskTitle = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 90.dp, 20.dp, 0.dp),
                label = "Task title",
                maxLines = 1,
                keyboardType = KeyboardType.Text,
                supportingText = taskTitle,
                maxChar = 25
            );
            TextBox(
                value = taskDescription,
                onValueChange = {
                    if (it.length <= 160 && (it.count { it == '\n' } + 1) <= 3) taskDescription = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 15.dp, 20.dp, 0.dp),
                label = "Description",
                maxLines = 3,
                keyboardType = KeyboardType.Text,
                supportingText = taskDescription,
                maxChar = 160
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp, 15.dp, 20.dp, 0.dp)
            ) {
                Text(
                    text = "Priority level:",
                    modifier = Modifier.padding(0.dp, 0.dp, 15.dp, 0.dp))
                RadioButton(
                    selected = lowPriorityTask,
                    onClick = {
                        lowPriorityTask = !lowPriorityTask
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = Disabled_Green_Radio_Button,
                        selectedColor = Selected_Green_Radio_Button
                    )
                )
                RadioButton(
                    selected = mediumPriorityTask,
                    onClick = {
                        mediumPriorityTask = !mediumPriorityTask
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = Disabled_Yellow_Radio_Button,
                        selectedColor = Selected_Yellow_Radio_Button
                    )
                )
                RadioButton(
                    selected = highPriorityTask,
                    onClick = {
                        highPriorityTask = !highPriorityTask
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = Disabled_Red_Radio_Button,
                        selectedColor = Selected_Red_Radio_Button
                    )
                )
            }

            saveButton(
                onClick = {

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                text = "Save"
            )

        }
    }
}
