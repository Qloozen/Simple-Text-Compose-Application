package com.example.simpletextcomposeapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.simpletextcomposeapplication.ui.theme.SimpleTextComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = MainViewModel()) {
    // When states changes will cause recomposition of the composable, so the changes will be displayed for dynamic content.
    val newNameStateContent = viewModel.textFieldState.observeAsState("")

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        GreetingMessage(
            newNameStateContent.value
        ) { newName -> viewModel.onTextChanged(newName) }
    }
}

@Composable
fun GreetingMessage(
                 textFieldValue: String,
                 textFieldUpdate: (newName: String) -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "TypedText: $textFieldValue" , style = MaterialTheme.typography.h5)

        TextField(value = textFieldValue, onValueChange = textFieldUpdate)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleTextComposeApplicationTheme {
        MainScreen()
    }
}