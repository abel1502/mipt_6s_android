package com.abel.mipt_6s_test_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abel.mipt_6s_test_app.ui.theme.TestAppTheme

@Composable
fun Task1() {
    Card(
        modifier = Modifier
            .fillMaxHeight()
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
            Spacer(
                modifier = Modifier
                    .height(65.dp)
            )
            Text(
                modifier = Modifier,
                text = "Sign Up For Free",
                fontFamily = FontFamily(Font(R.font.benton_sans_bold, FontWeight.Bold)),
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                lineHeight = 26.2.sp,
            )
            Task1Fields()
            Button(
                modifier = Modifier
                    .padding(top = 37.dp)
                    .size(width = 175.dp, height = 57.dp),
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(15.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                0f to Color(0xFF53E88B),
                                1f to Color(0xFF15BE77),
                            )
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Create Account",
                        fontFamily = FontFamily(Font(R.font.benton_sans_bold, FontWeight.Bold)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 20.96.sp,
                        color = Color.White,
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .clickable { /* TODO */ },
                text = "already have an account?",
                fontFamily = FontFamily(Font(R.font.benton_sans_bold, FontWeight.Bold)),
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                lineHeight = 19.98.sp,
                color = Color(0xFF53E88B),
                textDecoration = TextDecoration.Underline,
            )
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
    var nameValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var keepSignedInValue by remember { mutableStateOf(true) }
    var emailMeValue by remember { mutableStateOf(true) }

    var passwordShown by remember { mutableStateOf(false) }

    val colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color(0xFFFFFFFF),
        placeholderColor = Color(0x663B3B3B),
    )

    val themeColor = Color(0xFF53E88B)

    // region TextFields
    TextField(
        modifier = Modifier
            .padding(top = 40.dp)
            .size(width = 325.dp, height = 57.dp),
        value = nameValue,
        onValueChange = { nameValue = it },
        placeholder = { Text("Name") },
        colors = colors,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Person icon",
                tint = themeColor,
            )
        },
    )
    TextField(
        modifier = Modifier
            .padding(top = 12.dp)
            .size(width = 325.dp, height = 57.dp),
        value = emailValue,
        onValueChange = { emailValue = it },
        placeholder = { Text("Email") },
        colors = colors,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email icon",
                tint = themeColor,
            )
        },
    )
    TextField(
        modifier = Modifier
            .padding(top = 12.dp)
            .size(width = 325.dp, height = 57.dp),
        value = passwordValue,
        onValueChange = { passwordValue = it },
        placeholder = { Text("Password") },
        colors = colors,
        visualTransformation = if (passwordShown) VisualTransformation.None
            else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Lock icon",
                tint = themeColor,
            )
        },
        trailingIcon = {
            val image = if (passwordShown) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

            val description = if (passwordShown) "Hide password"
                else "Show password"

            IconButton(
                onClick = { passwordShown = !passwordShown}
            ){
                Icon(
                    imageVector = image,
                    contentDescription = description,
                )
            }
        },
    )
    // endregion

    // region Checkboxes
    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .width(325.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            modifier = Modifier
                .height(28.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                modifier = Modifier,
                checked = keepSignedInValue,
                onCheckedChange = { keepSignedInValue = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = themeColor,
                ),
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = "Keep Me Signed In",
                fontFamily = FontFamily(Font(R.font.inter)),
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                lineHeight = 19.98.sp,
                color = Color(0x80000000),
            )
        }
        Row(
            modifier = Modifier
                .height(28.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                modifier = Modifier,
                checked = emailMeValue,
                onCheckedChange = { emailMeValue = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = themeColor,
                ),
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = "Email Me About Special Pricing",
                fontFamily = FontFamily(Font(R.font.inter)),
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                lineHeight = 19.98.sp,
                color = Color(0x80000000),
            )
        }
    }
    // endregion
}

@Preview(showBackground = true)
@Composable
fun Task1Preview() {
    TestAppTheme {
        Task1()
    }
}