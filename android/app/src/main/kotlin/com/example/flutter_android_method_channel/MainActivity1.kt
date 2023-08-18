package com.example.flutter_android_method_channel

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flutter_android_method_channel.theme.DemoJackPackComposeTheme

class MainActivity1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoJackPackComposeTheme() {
                DemoApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 48.dp else 16.dp,
        animationSpec = spring(
            dampingRatio = 0.5f,
            stiffness = 1000f
        ), label = ""
    )

    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp))
                .padding(
                    horizontal = 16.dp,
                    vertical = extraPadding,
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Hello $name!",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                ),
            )

            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expand Icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
    }
}


@Composable
fun DemoApp() {
    var onBoardingSeen by remember { mutableStateOf(false) }

    if (onBoardingSeen) {
        HomeScreen()
    } else {
        OnBoardingScreen(onContinueClick = { onBoardingSeen = true })
    }
}


@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, onContinueClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "OnBoarding Screen")
        ElevatedButton(onClick = onContinueClick) {
            Text(text = "Continue")
        }
    }

}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, greetings: List<String> = List(1000) { "$it" }) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(greetings) { greeting ->
            Greeting(name = greeting)
        }
    }

}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 640,
    name = "Light Mode",
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 640,
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun GreetingPreview() {
    DemoJackPackComposeTheme() {
        DemoApp()
    }
}


