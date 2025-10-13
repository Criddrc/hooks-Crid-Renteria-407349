package com.example.activityresults

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.activityresults.ui.theme.ActivityResultsTheme

class MainActivity : ComponentActivity() {

    companion object {
        // Constantes que usa ColorPickerActivity
        const val RAINBOW_COLOR = "RAINBOW_COLOR"
        const val RAINBOW_COLOR_NAME = "RAINBOW_COLOR_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityResultsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreenComposable()
                }
            }
        }
    }
}

@Composable
fun MainScreenComposable() {
    val context = LocalContext.current

    var selectedColorName by remember { mutableStateOf("None") }
    var selectedColorValue by remember { mutableStateOf(Color.White) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val name = result.data?.getStringExtra(MainActivity.RAINBOW_COLOR_NAME)
            val colorLong = result.data?.getLongExtra(MainActivity.RAINBOW_COLOR, 0x00FFFFFFL)
            name?.let { selectedColorName = it }
            colorLong?.let {
                // convertir Long a Color (si viene 0x00FFFFFFL -> dejar blanco)
                selectedColorValue = if (it == 0x00FFFFFFL) Color.White else Color(it)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(selectedColorValue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Color seleccionado: $selectedColorName", color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val intent = Intent(context, ColorPickerActivity::class.java)
            launcher.launch(intent)
        }) {
            Text("Elegir color")
        }
    }
}
