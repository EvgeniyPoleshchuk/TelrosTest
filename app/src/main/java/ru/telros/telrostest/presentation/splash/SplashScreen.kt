package ru.telros.telrostest.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import ru.telros.telrostest.R

@Composable
fun SplashScreen(navController: () -> Unit) {
    val viewModel: SplashVM = hiltViewModel()
    val initDb = viewModel.isDatabaseInitialized.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.password_lotti))
        val progression by animateLottieCompositionAsState(composition, speed = 2f)
        LottieAnimation(
            composition = composition,
            progress = { progression },

        )
        if (progression == 1f && initDb.value) {
            navController()
        }
    }
}