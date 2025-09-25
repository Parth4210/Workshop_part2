package com.parth.workshop_part2.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parth.workshop_part2.ui.theme.ui.theme.Workshop_part2Theme

class AnimatedBtn2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val counter = remember { mutableStateOf(0) }
            val oldCounter = remember { mutableStateOf(0) }
            Workshop_part2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Row {
                            OutlinedButton(
                                shape = RoundedCornerShape(
                                    10.dp,
                                    0.dp,
                                    0.dp,
                                    10.dp,
                                ),
                                onClick = {
                                    oldCounter.value = counter.value-1
                                    counter.value += 1
                                }) {
                                Text("INCREASE", fontWeight = FontWeight.Bold)
                            }
                            OutlinedButton(
                                shape = RoundedCornerShape(
                                    0.dp,
                                    10.dp,
                                    10.dp,
                                    0.dp,
                                ),
                                onClick = {
                                    oldCounter.value = counter.value-1
                                    counter.value -= 1
                                }) {
                                Text("REDUCE", fontWeight = FontWeight.Bold)
                            }
                        }

                        AnimatedContent(
                            targetState = counter.value,
//                            transitionSpec = { fadeIn() + scaleIn() + slideInHorizontally() togetherWith fadeOut() + scaleOut() + slideOutHorizontally() },
                            transitionSpec = {
                                if (counter.value > oldCounter.value) {
                                    slideInVertically { targetOffset ->
                                        +targetOffset
                                    } togetherWith slideOutVertically { targetOffset ->
                                        -targetOffset
                                    }
                                } else {
                                    slideInVertically { targetOffset ->
                                        -targetOffset
                                    } togetherWith slideOutVertically { targetOffset ->
                                        +targetOffset
                                    }
                                }
                            }
                        ) { content ->
                            Text(content.toString(), fontSize = 40.sp)
                        }
                    }
                }
            }
        }
    }
}