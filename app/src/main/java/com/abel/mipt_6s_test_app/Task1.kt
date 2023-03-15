package com.abel.mipt_6s_test_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abel.mipt_6s_test_app.ui.theme.TestAppTheme
import java.time.format.TextStyle

@Composable
fun Task1() {
    Card(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 49.dp)
                    .size(width = 179.dp, height = 135.dp),
                painter = painterResource(id = R.drawable.task1_logo),
                contentDescription = "Logo",
                alignment = Alignment.TopCenter,
            )
            Task1Title()
            Text(
                modifier = Modifier
                    .padding(top = 65.dp),
                text = "Sign Up For Free",
                fontFamily = FontFamily(Font(R.font.benton_sans_bold, FontWeight.Bold)),
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                lineHeight = 26.2.sp,
            )
            Task1Fields()
        }
        Task1Background()
    }
}

@Composable
fun Task1Background() {
    Image(
        modifier = Modifier
            .graphicsLayer { alpha = 0.99f }
            .drawWithContent {
                drawContent()
                drawRect(
                    brush = Brush.verticalGradient(
                        0f to Color.Black,
                        0.4f to Color.Transparent,
                        startY = 0f,
                        endY = size.height,
                    ),
                    blendMode = BlendMode.DstIn,
                )
            },
        painter = painterResource(id = R.drawable.task1_bg_pattern),
        contentDescription = "Background pattern",
        alignment = Alignment.TopCenter,
    )
}

@Composable
fun Task1Title() {
    Text(
        modifier = Modifier
            .drawWithContent {
                drawContent()
                drawRect(
                    brush = Brush.horizontalGradient(
                        0f to Color(0xFF53E88B),
                        1f to Color(0xFF15BE77),
                    ),
                    blendMode = BlendMode.Lighten,
                )
            },
        text = "FoodNinja",
        fontFamily = FontFamily(Font(R.font.viga)),
        fontSize = 40.sp,
        // fontWeight = FontWeight(400),
        color = Color.Black,
        lineHeight = 53.76.sp,
        letterSpacing = 0.5.sp,
    )
    Text(
        modifier = Modifier
            .offset(y = (-6).dp),
        text = "Deliver Favorite Food",
        fontFamily = FontFamily(Font(R.font.inter)),
        fontSize = 13.sp,
        fontWeight = FontWeight(600),
        lineHeight = 15.73.sp,
        letterSpacing = 1.sp,
    )
}

@Composable
fun Task1Fields() {
//    var strLogin by remember { mutableStateOf("") };
//
//    TextField(
//
//    )
}

@Preview(showBackground = true)
@Composable
fun Task1Preview() {
    TestAppTheme {
        Task1()
    }
}