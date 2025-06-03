package com.example.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.game.ComponentType.ONE_BUTTON
import com.example.game.ComponentType.QUESTION
import com.example.game.ComponentType.TWO_BUTTONS
import com.example.game.ui.theme.GameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DrazdakGame(innerPadding)
                }
            }
        }
    }
}

@Composable
fun DrazdakGame(innerPadding: PaddingValues) {
    val viewModel: GameDataViewModel = viewModel()
    MyApp(viewModel)
}

@Composable
private fun MyApp(viewModel: GameDataViewModel) {
    Column(modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.forrester2), contentScale = ContentScale.Crop),
           verticalArrangement = Arrangement.Bottom,
           horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = viewModel.getCurrentDataHolder().displayText,
                  onValueChange = {},
                  modifier = Modifier.padding(vertical = 4.dp),
                  shape = RoundedCornerShape(8.dp),
                  enabled = true,
                  readOnly = true,
                  textStyle = TextStyle.Default.copy(fontSize = viewModel.getCurrentDataHolder().fontSize),
                  colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color(0xffd8e6ff),
                                                            focusedContainerColor = Color(0xffd8e6ff)))

        when (viewModel.getCurrentDataHolder().componentType) {
            ONE_BUTTON -> OneButton({ viewModel.incrementIndex() }, viewModel.getCurrentDataHolder().firstButtonText)
            TWO_BUTTONS -> TwoButtons(viewModel.index.value > 0,
                                      { viewModel.decrementIndex() },
                                      viewModel.getCurrentDataHolder().firstButtonText,
                                      viewModel.index.value < viewModel.getHoldersListSize() - 1,
                                      { viewModel.incrementIndex() },
                                      viewModel.getCurrentDataHolder().secondButtonText)
            QUESTION -> QuestionWithAnswers(answers = viewModel.getCurrentDataHolder().answers,
                                            onCorrectChoice = { viewModel.incrementIndex() })
        }
    }
}

@Composable
private fun OneButton(buttonOnClick: () -> Unit, buttonText: String) {
    Button(onClick = buttonOnClick) {
        Text(text = buttonText)
    }
}

@Composable
private fun TwoButtons(backButtonEnabled: Boolean,
                       backButtonOnClick: () -> Unit,
                       backButtonText: String,
                       nextButtonEnabled: Boolean,
                       nextButtonOnClick: () -> Unit,
                       nextButtonText: String) {
    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Button(enabled = backButtonEnabled, onClick = backButtonOnClick) {
            Text(text = backButtonText)
        }
        Spacer(modifier = Modifier.padding(25.dp))
        Button(enabled = nextButtonEnabled, onClick = nextButtonOnClick) {
            Text(text = nextButtonText)
        }
    }
}

@Composable
private fun QuestionWithAnswers(answers: List<Answer>, onCorrectChoice: () -> Unit) {
    val showAlertMessage = remember { mutableStateOf(false) }
    if (showAlertMessage.value) {
        MyAlertDialog(showAlertMessage)
    }
    answers.forEach { action ->
        Button(
            onClick = {
                if (action.valid) {
                    onCorrectChoice.invoke()
                }
                else {
                    showAlertMessage.value = true
                }
            },
            modifier = Modifier.width(256.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = true) {
            Text(text = action.answer)
        }
    }
}

@Composable
private fun MyAlertDialog(showAlertMessage: MutableState<Boolean>) {
    AlertDialog(
        icon = {
            Icon(Icons.Default.Warning,
                 contentDescription = "Example Icon")
        },
        title = {
            Text(text = "Zlá odpoveď")
        },
        text = {
            Text(text = "Skontroluj si poriadne svoju odpoveď")
        },
        onDismissRequest = {
            showAlertMessage.value = false
        },
        confirmButton = {
            TextButton(onClick = { showAlertMessage.value = false })
            { Text("OK") }
        }
    )
}