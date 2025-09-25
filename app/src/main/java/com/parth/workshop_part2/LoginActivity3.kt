package com.parth.workshop_part2

import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parth.workshop_part2.ui.theme.Workshop_part2Theme

class LoginActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val emailState = remember { mutableStateOf("") }
            val passwordState = remember { mutableStateOf("") }
            val username = remember { mutableStateOf("") }

            Workshop_part2Theme {
                var isButtonVisible by remember { mutableStateOf(true) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 30.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            "Welcome, ${username.value}",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        Card {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(
                                        Icons.Rounded.Person,
                                        "",
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                    Box(Modifier.width(10.dp))
                                    Text(
                                        "Login / SignUp",
                                        textAlign = TextAlign.Center,
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                OutlinedTextField(
                                    value = emailState.value,
                                    onValueChange = {
                                        emailState.value = it; username.value =
                                        it.split("@")[0].split(".")[0].replaceFirstChar { it -> it.uppercase() }
                                    },
                                    placeholder = { Text("Email") },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Outlined.Email,
                                            contentDescription = "Email Icon"
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp),
                                    singleLine = true,
                                )

                                OutlinedTextField(
                                    value = passwordState.value,
                                    onValueChange = { passwordState.value = it },
                                    placeholder = { Text("Password") },
                                    leadingIcon = { Icon(Icons.Outlined.Lock, "Password Icon") },
                                    visualTransformation = PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp)
                                ) {
                                    ElevatedButton(
                                        onClick = {}, modifier = Modifier
                                            .weight(1f)
                                            .padding(end = 10.dp)
                                    ) {
                                        Text("Sign Up & Login")
                                    }
                                    Button(
                                        onClick = { isButtonVisible = !isButtonVisible }

                                    ) {
                                        Text(if (isButtonVisible) "Login" else "")
                                    }
                                    AnimatedVisibility(
                                        visible = isButtonVisible,
                                        // You can customize the enter and exit animations
                                        enter = fadeIn() + slideInVertically(),
                                        exit = fadeOut() + slideOutVertically()
                                    ) {
                                        // 3. The Button that will be animated
                                        Button(onClick = { /* Do something */ }) {
                                            Text("I Animate!")
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}