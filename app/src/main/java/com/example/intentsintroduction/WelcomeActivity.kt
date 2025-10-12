package com.example.intentsintroduction

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intentsintroduction.ui.theme.IntentsIntroductionTheme
import com.example.intentsintroduction.MainActivity.Companion.FULL_NAME_KEY

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsIntroductionTheme {
                WelcomeScreen(intent)
            }
        }
    }
}

@Composable
private fun WelcomeScreen(intent: Intent) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            val fullName = intent.getStringExtra(FULL_NAME_KEY) ?: ""
            val welcomeText = stringResource(R.string.welcome_text, fullName)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = welcomeText,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )

                Button(
                    onClick = { (context as ComponentActivity).finish() },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Volver")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    val intent = Intent().apply {
        putExtra(FULL_NAME_KEY, "Dahiana Castillo")
    }
    IntentsIntroductionTheme {
        WelcomeScreen(intent = intent)
    }
}
