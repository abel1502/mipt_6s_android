// Task 1

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abel.mipt_6s_test_app.ui.theme.GreenBright
import com.abel.mipt_6s_test_app.ui.theme.GreenGradientBrush
import com.abel.mipt_6s_test_app.ui.theme.TestAppTheme


@Composable
fun SignUpScreen() {
    Card(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 49.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .size(width = 179.dp, height = 135.dp),
                painter = painterResource(id = R.drawable.task1_logo),
                contentDescription = "Logo",
                alignment = Alignment.TopCenter,
            )
            HelperTitle()
            Spacer(
                modifier = Modifier
                    .weight(3.0f)
            )
            Text(
                modifier = Modifier,
                text = stringResource(R.string.sign_up_for_free),
                fontFamily = FontFamily(Font(R.font.benton_sans_bold, FontWeight.Bold)),
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                lineHeight = 26.2.sp,
            )
            HelperFields()
            Spacer(
                modifier = Modifier
                    .weight(1.0f)
            )
            Button(
                modifier = Modifier
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
                            brush = GreenGradientBrush
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.create_account),
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
                text = stringResource(R.string.already_have_an_account),
                fontFamily = FontFamily(Font(R.font.benton_sans_bold, FontWeight.Bold)),
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                lineHeight = 19.98.sp,
                color = GreenBright,
                textDecoration = TextDecoration.Underline,
            )
        }
        HelperBackground()
    }
}

// region Helpers
@Composable
private fun HelperBackground() {
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
private fun HelperTitle() {
    Text(
        modifier = Modifier
            .drawWithContent {
                drawContent()
                drawRect(
                    brush = GreenGradientBrush,
                    blendMode = BlendMode.Lighten,
                )
            },
        text = stringResource(R.string.app_name),
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
        text = stringResource(R.string.app_motto),
        fontFamily = FontFamily(Font(R.font.inter)),
        fontSize = 13.sp,
        fontWeight = FontWeight(600),
        lineHeight = 15.73.sp,
        letterSpacing = 1.sp,
    )
}

@Composable
private fun HelperFields() {
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

    // region TextFields
    TextField(
        modifier = Modifier
            .padding(top = 40.dp)
            .size(width = 325.dp, height = 57.dp),
        value = nameValue,
        onValueChange = { nameValue = it },
        placeholder = { Text(stringResource(R.string.placeholder_name)) },
        colors = colors,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Person icon",
                tint = GreenBright,
            )
        },
    )
    TextField(
        modifier = Modifier
            .padding(top = 12.dp)
            .size(width = 325.dp, height = 57.dp),
        value = emailValue,
        onValueChange = { emailValue = it },
        placeholder = { Text(stringResource(R.string.placeholder_email)) },
        colors = colors,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email icon",
                tint = GreenBright,
            )
        },
    )
    TextField(
        modifier = Modifier
            .padding(top = 12.dp)
            .size(width = 325.dp, height = 57.dp),
        value = passwordValue,
        onValueChange = { passwordValue = it },
        placeholder = { Text(stringResource(R.string.placeholder_password)) },
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
                tint = GreenBright,
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
                    checkedColor = GreenBright,
                ),
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = stringResource(R.string.keep_me_signed_in),
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
                    checkedColor = GreenBright,
                ),
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = stringResource(R.string.email_me),
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
// endregion Helpers

@Preview(showBackground = true)
@Composable
fun Task1Preview() {
    TestAppTheme {
        SignUpScreen()
    }
}
