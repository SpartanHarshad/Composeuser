package io.harshad.compuser.composables

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import io.harshad.compuser.R
import io.harshad.compuser.util.navigation.NavigationAction

@Composable
fun SplashScreen(navAction: NavigationAction) {
    val scale = remember { Animatable(0f) }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (brand) = createRefs()
        LaunchedEffect(Unit) {
            scale.animateTo(
                targetValue = 1.0f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    }
                )
            )
            navAction.navigateToUserScreen()
        }

        Image(
            painter = painterResource(id = R.drawable.eka_logo),
            contentDescription = "brand",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .constrainAs(brand) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(.8f)
                .fillMaxHeight(.7f)
                .scale(scale.value)
        )
    }
}