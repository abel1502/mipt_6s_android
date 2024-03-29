package com.abel.mipt_6s_test_app.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abel.mipt_6s_test_app.R
import com.abel.mipt_6s_test_app.ui.theme.GreenBright
import com.abel.mipt_6s_test_app.ui.theme.GreenGradientBrush
import com.abel.mipt_6s_test_app.ui.theme.TestAppTheme


@Composable
fun SignUpScreen(viewModel: SignUpViewModel, navController: NavController) {
    val viewState by viewModel.viewState.collectAsState()
    val localContext = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.setNavController(navController)
    }

    LaunchedEffect(viewState.popupMessage) handler@{
        if (viewState.popupMessage == null)
            return@handler

        Toast.makeText(localContext, viewState.popupMessage, Toast.LENGTH_SHORT).show()

        viewModel.obtainEvent(SignUpEvent.PopupShown)
    }

    SignUpView(
        viewState,
        eventHandler = { viewModel.obtainEvent(it) },
    )
}

// region Helpers
@Composable
private fun SignUpView(
    viewState: SignUpViewState,
    eventHandler: (SignUpEvent) -> Unit,
) {
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
            SignUpLogo()
            SignUpTitle()
            Spacer(
                modifier = Modifier
                    .weight(3.0f)
            )
            SignUpFields(viewState, eventHandler)
            Spacer(
                modifier = Modifier
                    .weight(1.0f)
            )
            SignUpSubmit(eventHandler)
        }
        SignUpBackground()
    }
}

@Composable
private fun SignUpBackground() {
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
private fun SignUpLogo() {
    Image(
        modifier = Modifier
            .size(width = 179.dp, height = 135.dp),
        painter = painterResource(id = R.drawable.task1_logo),
        contentDescription = "Logo",
        alignment = Alignment.TopCenter,
    )
}

@Composable
private fun SignUpTitle() {
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
private fun SignUpFields(
    viewState: SignUpViewState,
    eventHandler: (SignUpEvent) -> Unit,
) {
    val colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color(0xFFFFFFFF),
        placeholderColor = Color(0x663B3B3B),
    )

    Text(
        modifier = Modifier,
        text = stringResource(R.string.sign_up_for_free),
        fontFamily = FontFamily(Font(R.font.benton_sans_bold, FontWeight.Bold)),
        fontSize = 20.sp,
        fontWeight = FontWeight(400),
        lineHeight = 26.2.sp,
    )

    // region TextFields
    TextField(
        modifier = Modifier
            .padding(top = 40.dp)
            .size(width = 325.dp, height = 57.dp),
        value = viewState.login,
        onValueChange = { eventHandler(SignUpEvent.LoginChanged(it)) },
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
        value = viewState.email,
        onValueChange = { eventHandler(SignUpEvent.EmailChanged(it)) },
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
        value = viewState.password,
        onValueChange = { eventHandler(SignUpEvent.PasswordChanged(it)) },
        placeholder = { Text(stringResource(R.string.placeholder_password)) },
        colors = colors,
        visualTransformation = if (viewState.passwordShown) VisualTransformation.None
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
            val image = if (viewState.passwordShown) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

            val description = if (viewState.passwordShown) "Hide password"
                else "Show password"

            IconButton(
                onClick = { eventHandler(SignUpEvent.PasswordShownChanged) }
            ){
                Icon(
                    imageVector = image,
                    contentDescription = description,
                )
            }
        },
    )
    // endregion TextFields

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
                checked = viewState.keepSignedIn,
                onCheckedChange = { eventHandler(SignUpEvent.KeepSignedInChanged(it)) },
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
                checked = viewState.emailMe,
                onCheckedChange = { eventHandler(SignUpEvent.EmailMeChanged(it)) },
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
    // endregion Checkboxes
}

@Composable
private fun SignUpSubmit(
    eventHandler: (SignUpEvent) -> Unit,
) {
    Button(
        modifier = Modifier
            .size(width = 175.dp, height = 57.dp),
        onClick = { eventHandler(SignUpEvent.Submit) },
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
            .clickable { eventHandler(SignUpEvent.AlreadyHaveAccount) },
        text = stringResource(R.string.already_have_an_account),
        fontFamily = FontFamily(Font(R.font.benton_sans_bold, FontWeight.Bold)),
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        lineHeight = 19.98.sp,
        color = GreenBright,
        textDecoration = TextDecoration.Underline,
    )
}
// endregion Helpers

@Preview(showBackground = true)
@Composable
fun Task1Preview() {
    TestAppTheme {
        SignUpView(
            viewState = SignUpViewState(),
            eventHandler = {},
        )
    }
}
