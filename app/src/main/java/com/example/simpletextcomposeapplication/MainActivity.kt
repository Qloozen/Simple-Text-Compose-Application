package com.example.simpletextcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simpletextcomposeapplication.ui.theme.SimpleTextComposeApplicationTheme

val greetingList : ArrayList<String> = arrayListOf(
    "Name1",
    "Name2",
    "Name3",
    "Name4"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    // When states changes will cause recomposition of the composable, so the changes will be displayed for dynamic content.
    val greetingListState = remember { mutableStateListOf<String>("John")}
    val newNameStateContent = remember { mutableStateOf("")}

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        GreetingList(
            greetingListState,
            { greetingListState.add(newNameStateContent.value) },
            newNameStateContent.value,
            { newName -> newNameStateContent.value = newName})
    }
}

@Composable
fun GreetingList(greetingList: List<String>,
                 buttonClick: () -> Unit,
                 textFieldValue: String,
                 textFieldUpdate: (newName: String) -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for(name in greetingList) {
            Greeting(name = name)
        }

        // Creating a simple TextField.
        // 'value' holds the input value stored in the state.
        // 'onValueChange' is a lambda where the input in the state is getting replaced.
        // All state changes should not be handled by the composable itself, therefore delegate it to the parent. */
        TextField(value = textFieldValue, onValueChange = textFieldUpdate)

        Button(onClick = buttonClick) {
            Text("Add new name")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name",
        style = MaterialTheme.typography.h5
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleTextComposeApplicationTheme {
        MainScreen()
    }
}