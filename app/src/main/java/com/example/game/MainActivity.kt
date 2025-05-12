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
    val h2 = DataHolder(
        "Kde bolo, tam bolo, žil raz v petržalských lesoch jeden lesník. " +
        "Tento lesník sa volal Gejza Dražďák a mal na starosti lesy na petržalskej strane Dunaja. " +
        "Pomocou svojej lesníckej palice dokázal udržiavať poriadok v lese. ")
    val h3 = DataHolder(
        "Táto palica bola totiž kúzelná. Pomáhala mu rozprávať sa so zvieratami a tak hneď vedel, " +
        "či je v lese všetko v poriadku.")
    val h4 = DataHolder(
        "Táto lesnícka palica mu dokonca dala moc rozumieť zvieratám aj keď ju práve nemal pri sebe." +
        " Stačilo, že ju ráno chytil do ruky a hneď rozumel všetkým zvieratkám celý deň.")
    val h5 = DataHolder(
        "Raz sa stalo, že sa mu táto lesnícka palica stratila. " +
        "Gejza netušil kde sa jeho lesnícka palica nachádza. " +
        "Bez toho aby vedel kde je jeho kúzelná palica sa nemohol vybrať do lesa.")
    val h6 = DataHolder(
        "Chcel by si pomôcť Gejzovi nájsť stratenú lesnícku palicu?",
        "Áno",
        componentType = ONE_BUTTON)
    val h7 = DataHolder("Poďme na to:")
    val h8 = DataHolder("Ako prvu vec potrebujem aby si zistil kedy bol postaveny tento dom. Bolo to:",
                        componentType = QUESTION,
                        answers = listOf(
                            Answer("1981", false),
                            Answer("1982", false),
                            Answer("1983", true),
                            Answer("1984", false)))
    val h9 = DataHolder("Správne! Teraz musíš ísť za ježkom možno ti poradí ako ďalej")
    val h10 = DataHolder(
        "Ahoj pomocník Ja som ježko a bývam tu na tejto lúke. Pomáhaš nájsť lesnícku palicu?",
        "Áno",
        componentType = ONE_BUTTON)
    val h11 = DataHolder("Tak poď za mnou. Dúfam že mi budeš stíhať, ja som totiž celkom rýchly.")
    val h12 = DataHolder("Povedz mi kde bol vyrobený tento maličký most, lebo sa mi veľmi páči",
                         componentType = QUESTION,
                         answers = listOf(
                             Answer("Podbrezová", false),
                             Answer("Hronec", true),
                             Answer("Košice", false),
                             Answer("Bratislava", false)))
    val h13 = DataHolder("Ďakujem za informáciu. " +
                         "Videl som, že niekto v kapucni beží tam na konci tejto lúky s lesníkovou palicou v ruke. " +
                         "Tam neďaleko býva korytnačka, skú sa jej spýtať či niekoho nevidela.")
    val h14 = DataHolder("Ahoj pomocník, vidím že pomáhaš nájsť lesnícku palicu. " +
                         "Môžem ti pomôcť, ale najprv musíš dokázať že dobre poznáš zvieratká. ")
    val h15 = DataHolder("Vieš medzi aké druhy zvierat patria korytnačky?",
                         componentType = QUESTION,
                         answers = listOf(
                             Answer("Cicavce", false),
                             Answer("Obojživejníky", true),
                             Answer("Ryby", false),
                             Answer("Vtáky", false)))
    val h16 = DataHolder("Výborne, vidím že sa vyznáš. Takže ti pomôžem." +
                         "Videla som niekoho v kapucni. Išiel tadiaľto po ceste. Tam kúsok dalej na poloostrove býva bobor. " +
                         "Skús sa spýtať jeho",
                         "Ďakujem",
                         componentType = ONE_BUTTON)
    val h17 = DataHolder("Čo tu chceš, nevidíš že nemám čas? " +
                         "Jasné, jasné, hľadáš palicu, a prečo ti ja mám pomáhať? ")
    val h18 = DataHolder("Vieš vôbec čo majú bobre najradšej?",
                         componentType = QUESTION,
                         answers = listOf(
                             Answer("Listy zo stromov", false),
                             Answer("Kôru zo stromov", true),
                             Answer("Suchú trávu", false),
                             Answer("Čerstvé mušky", false)))
    val h19 = DataHolder("No tak dobre, pomôžem ti. " +
                         "Videl som niekoho v kapucni utekať týmto smerom. Tam niekde býva vlk, skús sa spýtať jeho. ")
    val h20 = DataHolder("Auuuuuuuu! " +
                         "Čo tu robíš? Vari nevidíš že si na mojom území? " +
                         "Ty sa ma nebojíš? Tak to musíš byť ty, čo hľadáš tú lesníkovu palicu? " +
                         "Už o tebe vie hádam celý les. A tuším chceš pomoc aj odo mňa. " +
                         "Videl som niekoho, kto mal v ruke tú palicu. " +
                         "Ale ak chceš moju pomoc, musíš mi odpovedať na jednu otázku: ")
    val h21 = DataHolder("V ktorej rozprávke nie je vlk v hlavnej úlohe?",
                         componentType = QUESTION,
                         answers = listOf(
                             Answer("Sedem kozliatok", false),
                             Answer("Tri prasiatka", false),
                             Answer("Červená čiapočka", false),
                             Answer("Šípková Ruženka", true)))
    val h22 = DataHolder("Správne. Pozri, už je tu veverička. Tá ti ukáže kadiaľ išiel ten človek s palicou. ")
    val h23 =
            DataHolder("Áno, áno, ja ti to ukážem, ale najprv mi nusíš vysvetliť ako tu mohol vyrásť taký veľý strom. " +
                       "Poď za mnou ukážem ti ho.")
    val h24 = DataHolder("Ach veverička, veď to nie je strom, to je predsa:",
                         componentType = QUESTION,
                         answers = listOf(
                             Answer("Veľký dom", false),
                             Answer("Panelák", false),
                             Answer("Krík", false),
                             Answer("Stožiar", true)))
    val h25 = DataHolder("A na tom stožiari sú antény na príjmanie a vysielanie signálu.")
    val h26 = DataHolder("Antény? No teda. No keď vravíš. " +
                         "No teraz musíš ísť tamto k vode. Skús sa spýtať labute, či ti nebude vedieť poradiť viac.")
    val h27 = DataHolder("Ahoj labuť. Nevidela si tu niekoho s lesníkovou palicou?")
    val h28 = DataHolder("Videla, ale ak chces pomôcť, tak najprv poraď ty mne.")
    val h29 = DataHolder("Vieš ktorá rozprávka je o labuti?",
                         componentType = QUESTION,
                         answers = listOf(
                             Answer("Škaredé mačiatko", false),
                             Answer("Škaredé kuriatko", false),
                             Answer("Škaredé húsatko", false),
                             Answer("Škaredé káčatko", true)))
    val h30 = DataHolder("No dobre, videla som aj ja niekoho ísť s lesníckou palicou, musíš ísť tamto kúsok ďalej. " +
                         "Tam sa zvykne nad hladinu vynárať kapor, počkaj na neho, on určite niečo videl.")
    val h31 = DataHolder("Počkaj na kapra? Ale dokedy tu mám čakať?")
    val h32 = DataHolder("Hmm, niekto ma tu volá? Aha, hľadáš palicu, že? " +
                         "Pomôžem ti ak vieš, ktorá časť kapra sa odkladá pre šťastie.")
    val h33 = DataHolder("Je to:",
                         componentType = QUESTION,
                         answers = listOf(
                             Answer("Kaprie oko", false),
                             Answer("Kapria kosť", false),
                             Answer("Kapria hlava", false),
                             Answer("Kapria šupina", true)))
    val h34 = DataHolder("No dobre, vidím že to poznáš, tak choď týmto smerom. " +
                         "Tam pri lávke býva žaba, hádam ti aj ona pomôže.")
    val h35 = DataHolder("Kváá. Kváá. Vidím ťa. A viem že hľadáš lesníkovú palicu. " +
                         "Ale ak chceš vedieť kadiaľ ďalej, musíš vedieť ako sa volá táto časť Dunaja!")
    val h36 = DataHolder("Je to:",
                         componentType = QUESTION,
                         answers = listOf(
                             Answer("Slovenské rameno", false),
                             Answer("Maďarské rameno", false),
                             Answer("Poľské rameno", false),
                             Answer("Chorvátske rameno", true)))
    val h37 = DataHolder("Výborne! Kváá. " +
                         "Teraz bež ešte tamto do lesa. Tam musíš nájsť strom v tvare písmena \"V\". " +
                         "Na ňom býva stará múdra sova, ona už bude vedieť ako ti poradiť.")
    val h38 = DataHolder("Hu húú, tak ty hľadáš palicu? Tak mám pre teba poslednú úlohu. " +
                         "Povedz mi kam išla sova v obľúbenej detskej pesničke?",
                         componentType = QUESTION,
                         answers = listOf(
                             Answer("do kina", false),
                             Answer("do divadla", false),
                             Answer("na tanec", true),
                             Answer("na disco", false)))
    val h39 = DataHolder("Super. Zvládol si všetky úlohy. Teraz už musíš len zistiť kde je lesníková palica. " +
                         "Ale ani to pre teba nebude problém. Stačí ak nazrieš do Záhrady Gejzu Dražďáka.")
    val h40 = DataHolder("V záhrade: Veď tá palica je tu. A má ju v rukách lesníkov syn. " +
                         "Tak tá palica sa vôbec nestratila, ale lesníkov syn spravil všetku prácu za svojho otca! ")
    val myTexts = listOf(h1,
                         h2,
                         h3,
                         h4,
                         h5,
                         h6,
                         h7,
                         h8,
                         h9,
                         h10,
                         h11,
                         h12,
                         h13,
                         h14,
                         h15,
                         h16,
                         h16,
                         h17,
                         h18,
                         h19,
                         h20,
                         h21,
                         h22,
                         h23,
                         h24,
                         h25,
                         h26,
                         h27,
                         h28,
                         h29,
                         h30,
                         h31,
                         h32,
                         h33,
                         h34,
                         h35,
                         h36,
                         h37,
                         h38,
                         h39,
                         h40)
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