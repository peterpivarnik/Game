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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    val h1 = TextHolder("Lesnícka palica Gejzu Dražďáka", "Štart", 26.sp, true)
    val h2 = TextHolder(
            "Kde bolo, tam bolo, žil raz v petržalských lesoch jeden lesník. " +
                    "Tento lesník sa volal Gejza Dražďák a mal na starosti lesy na petržalskej strane Dunaja. " +
                    "Pomocou svojej lesníckej palice dokázal udržiavať poriadok v lese. ")
    val h3 = TextHolder(
            "Táto palica bola totiž kúzelná. Pomáhala mu rozprávať sa so zvieratami a tak hneď vedel, či je v lese všetko v poriadku.")
    val h4 = TextHolder(
            "Táto lesnícka palica mu dokonca dala moc rozumieť zvieratám aj keď ju práve nemal pri sebe." +
                    " Stačilo, že ju ráno chytil do ruky a hneď rozumel všetkým zvieratkám celý deň.")
    val h5 =
        TextHolder("Raz sa stalo, že sa mu táto lesnícka palica stratila. Gejza netušil kde sa jeho lesnícka palica nachádza. " +
                           "Bez toho aby vedel kde je jeho kúzelná palica sa nemohol vybrať do lesa.")

    val h6 = TextHolder("Chcel by si pomôcť Gejzovi nájsť stratenú lesnícku palicu?", "Áno", 16.sp, true)
    val h7 = TextHolder("Poďme na to:")

    val myTexts = listOf(h1, h2, h3, h4, h5, h6, h7)

    MyApp(myTexts)
}

@Composable
private fun MyApp(myTexts: List<TextHolder>) {
    var index by remember { mutableStateOf(0) }
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
        if (myTexts[index].oneButton) {
            Button(onClick = { index++ }) {
                Text(text = myTexts[index].firstButtonText)
            }
        }
        else {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Button(enabled = index > 0, onClick = { index-- }) {
                    Text(text = "Späť")
                }
                Spacer(modifier = Modifier.padding(25.dp))
                Button(enabled = index < myTexts.size - 1, onClick = { index++ }) {
                    Text(text = "Pokračovať")
                }
            }
        }
    }
}

