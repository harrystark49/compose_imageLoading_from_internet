package com.example.compose_imageloading_from_internet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.example.compose_imageloading_from_internet.ui.theme.Compose_imageLoading_from_internetTheme
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_imageLoading_from_internetTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
//need to approve the internet permissions
//in gradle in application give  android:usesCleartextTraffic="true"
//need to add implemention implementation("io.coil-kt:coil-compose:1.3.1")
fun Greeting(name: String) {
    Box (
        androidx.compose.ui.Modifier
            .height(100.dp)
            .width(100.dp),
    contentAlignment = Alignment.Center){

        var painter= rememberImagePainter(data = "https://1.bp.blogspot.com/-LgTa-xDiknI/X4EflN56boI/AAAAAAAAPuk/24YyKnqiGkwRS9-_9suPKkfsAwO4wHYEgCLcBGAsYHQ/s0/image9.png"
            , builder = {
                placeholder(R.drawable.ic_launcher_background)
                crossfade(1000)
                error(R.drawable.ic_launcher_foreground)
                //for gray color
                transformations(
                    GrayscaleTransformation(),
                    CircleCropTransformation(),
                    BlurTransformation(LocalContext.current),
                    RoundedCornersTransformation(50f),
                )
        } )
        var painterstate=painter.state
        Image(painter=painter,contentDescription = "image",androidx.compose.ui.Modifier
            .height(50.dp)
            .width(50.dp))

        if(painterstate is ImagePainter.State.Loading){
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_imageLoading_from_internetTheme {
        Greeting("Android")

    }
}