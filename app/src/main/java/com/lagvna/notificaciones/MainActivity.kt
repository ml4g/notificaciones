package com.lagvna.notificaciones

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.lagvna.notificaciones.ui.theme.NotificacionesTheme
import kotlin.contracts.contract

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var permission by remember {
                mutableStateOf(
                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                )
            }

            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = {
                    granted ->
                    permission = granted
                }
            )

            LaunchedEffect(true) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }

            NotificacionesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotificationView(Modifier.padding(innerPadding))

                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable()
fun NotificationView(modifier: Modifier = Modifier){
    val context = LocalContext.current
    val notificationService = NotificationService(context)

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Notificaciones")

        Button(
            onClick = {notificationService.showBasicNotification()}
        ) {
            Text("Notificación básica")
        }
        Button(
            onClick = {notificationService.showLargeNotification()}
        ) {
            Text("Notificación grande")
        }
        Button(
            onClick = {notificationService.showInboxNotification()}
        ) {
            Text("Notificación Inbox")
        }
        Button(
            onClick = {notificationService.showImageNotification()}
        ) {
            Text("Notificación Imagen")
        }
        Button(
            onClick = { NotificationWorker.releaseNotification(context)}
        ) {
            Text("Notificación por Worker")
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}