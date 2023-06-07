package com.implementing.pscomposediary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.firebase.FirebaseApp
import com.implementing.pscomposediary.navigation.AppNavigation
import com.implementing.pscomposediary.ui.theme.PSDiaryTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize FirebaseApp if not already initialized

        FirebaseApp.initializeApp(this)
        println("Firebase Established")


        setContent {
            PSDiaryTheme {
                // A surface container using the 'background' color from the theme
    //                    ConfettiCenterView()
                    AppNavigation()
            }
        }
    }
}



