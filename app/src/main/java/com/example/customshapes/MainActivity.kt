package com.example.customshapes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.customshapes.ui.CustomShapesTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomShapesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App(){
    Column(modifier = Modifier.fillMaxSize(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        MyCircle()
        Spacer(modifier = Modifier.size(20.dp))
        MyRect()
        Spacer(modifier = Modifier.size(20.dp))
        MyTriangle()
        Spacer(modifier = Modifier.size(20.dp))
        MyStarTrek()
        Spacer(modifier = Modifier.size(20.dp))
        MyMouse()
    }
}

@Composable
fun MyCircle(){
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawCircle(color = Color.Red)
    })
}

@Composable
fun MyRect(){
    val density = AmbientDensity.current.density

    val w = remember { 100f * density }
    val h = remember { 33f * density }

    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawRect(color = Color.Black, size = Size(w, h), topLeft = Offset.Zero)
        drawRect(color = Color.Red, size = Size(w, h), topLeft = Offset(0f, h))
        drawRect(color = Color.Yellow, size = Size(w, h), topLeft = Offset(0f, h * 2))
    })
}

@Composable
fun MyTriangle(){
    val density = AmbientDensity.current.density

    val path = Path().apply {
        moveTo(50f * density, 0f)
        lineTo(100f * density, 100f * density)
        lineTo(0f, 100f * density)
        close()
    }

    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawPath(path, Color.Blue)
    })
}


@Composable
fun MyStarTrek(){
    val myStarTrekShape = GenericShape { size ->
        moveTo(size.width / 2f, 0f)
        lineTo(size.width, size.height)
        quadraticBezierTo(
            size.width * 0.6f,
            size.height * 0.4f,
            0f,
            size.height
        )
        close()
    }

    Surface(
        shape = myStarTrekShape,
        color = Color.Yellow,
        border = BorderStroke(3.dp, Color.Black),
        modifier = Modifier.size(100.dp)
    ) { }
}

@Composable
fun MyMouse(){
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawCircle(Color.Black, radius = 70f, center = Offset(size.width / 2f, size.height / 2f))
        drawCircle(Color.Black, radius = 50f, center = Offset(size.width * 0.25f, size.height * 0.25f))
        drawCircle(Color.Black, radius = 50f, center = Offset(size.width * 0.75f, size.height * 0.25f))
    })
}
