package com.example.saveandrestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saveandrestore.ui.theme.SaveAndRestoreTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    companion object {

        private const val RANDOM_NUMBER = "RANDOM_NUMBER"
    }

    private var randomNumber by mutableStateOf(0)

    private fun generateRandomNumber(): Int {
        return Random.nextInt(0, 1000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SaveAndRestoreTheme {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {

                    Button(
                        onClick = {
                            randomNumber = generateRandomNumber()
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.generate_random_number),
                            fontSize = 18.sp
                        )
                    }

                    Text(
                        text = stringResource(
                            id = R.string.random_number_message,
                            randomNumber
                        ),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RANDOM_NUMBER, randomNumber)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        randomNumber = savedInstanceState.getInt(RANDOM_NUMBER, 0)
    }
}
