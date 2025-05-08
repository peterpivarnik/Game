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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game.ComponentType.QUESTION
import com.example.game.ComponentType.ONE_BUTTON
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
    val h1 = DataHolder("Lesnícka palica Gejzu Dražďáka", "Štart", fontSize = 26.sp, componentType = ONE_BUTTON)
    val h2 = DataHolder("Kde bolo, tam bolo, žil raz v petržalských lesoch jeden lesník. " +
                        "Tento lesník sa volal Gejza Dražďák a mal na starosti lesy na petržalskej strane Dunaja. " +
                        "Pomocou svojej lesníckej palice dokázal udržiavať poriadok v lese. ")
    val h3 = DataHolder("Táto palica bola totiž kúzelná. Pomáhala mu rozprávať sa so zvieratami a tak hneď vedel, " +
                        "či je v lese všetko v poriadku.")
    val h4 = DataHolder("Táto lesnícka palica mu dokonca dala moc rozumieť zvieratám aj keď ju práve nemal pri sebe." +
                        " Stačilo, že ju ráno chytil do ruky a hneď rozumel všetkým zvieratkám celý deň.")
    val h5 = DataHolder("Raz sa stalo, že sa mu táto lesnícka palica stratila. " +
                        "Gejza netušil kde sa jeho lesnícka palica nachádza. " +
                        "Bez toho aby vedel kde je jeho kúzelná palica sa nemohol vybrať do lesa.")
    val h6 = DataHolder("Chcel by si pomôcť Gejzovi nájsť stratenú lesnícku palicu?",
                        "Áno",
                        componentType = ONE_BUTTON)
    val h7 = DataHolder("Poďme na to:")
    val h8 = DataHolder("Ako prvu vec potrebujem aby si zistil kedy bol postaveny tento dom. Bolo to:",
                        componentType = QUESTION,
                        answers = listOf(Answer("1981", false),
                                         Answer("1982", false),
                                         Answer("1983", true),
                                         Answer("1984", false)))
    val h9 = DataHolder("Správne! Teraz musíš ísť za ježkom možno ti poradí ako ďalej")
    val h10 = DataHolder("Ahoj pomocník Ja som ježko a bývam tu na tejto lúke. Pomáhaš nájsť lesnícku palicu?",
                         "Áno",
                         componentType = ONE_BUTTON)
    val h11 = DataHolder("Tak poď za mnou. Dúfam že mi budeš stíhať, ja som totiž celkom rýchly.")
    val h12 = DataHolder("Povedz mi kde bol vyrobený tento maličký most, lebo sa mi veľmi páči",
                         componentType = QUESTION,
                         answers = listOf(Answer("Podbrezová", false),
                                          Answer("Hronec", true),
                                          Answer("Košice", false),
                                          Answer("Bratislava", false)))
    val h13 = DataHolder("Ďakujem za informáciu. Bohužiaľ ja neviem kde je lesníková palica. Skús sa spýtať vlka. " +
                         "Je tu neďaleko za týmto jazerom.")
    val myTexts = listOf(h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13)

    MyApp(myTexts)
}

@Composable
private fun MyApp(myTexts: List<DataHolder>) {
    var index by remember { mutableIntStateOf(0) }
    Column(modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.forrester2), contentScale = ContentScale.Crop),
           verticalArrangement = Arrangement.Bottom,
           horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = myTexts[index].displayText,
                  onValueChange = {},
                  modifier = Modifier.padding(vertical = 4.dp),
                  shape = RoundedCornerShape(8.dp),
                  enabled = true,
                  readOnly = true,
                  textStyle = TextStyle.Default.copy(fontSize = myTexts[index].fontSize),
                  colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color(0xffd8e6ff),
                                                            focusedContainerColor = Color(0xffd8e6ff)))

        when (myTexts[index].componentType) {
            ONE_BUTTON -> OneButton({ index++ }, myTexts[index].firstButtonText)
            TWO_BUTTONS -> TwoButtons(index > 0,
                                      { index-- },
                                      myTexts[index].firstButtonText,
                                      index < myTexts.size - 1,
                                      { index++ },
                                      myTexts[index].secondButtonText)
            QUESTION -> QuestionWithAnswers(answers = myTexts[index].answers, onCorrectChoice = { index++ })
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
        Button(enabled = nextButtonEnabled,
               onClick = nextButtonOnClick) {
            Text(text = nextButtonText)
        }
    }
}

@Composable
private fun QuestionWithAnswers(answers: List<Answer>, onCorrectChoice: () -> Unit) {
    val showAlertMessage = remember { mutableStateOf(false) }
    if (showAlertMessage.value) {
        AlertDialog(
                icon = {
                    Icon(Icons.Default.Warning, contentDescription = "Example Icon")
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
                    TextButton(onClick = {
                        showAlertMessage.value = false
                    }) {
                        Text("OK")
                    }
                }
        )
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