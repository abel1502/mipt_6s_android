package com.abel.mipt_6s_test_app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.abel.mipt_6s_test_app.ui.theme.TestAppTheme

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    navController: NavController,
    restaurantId: Int,
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.setNavController(navController)
        viewModel.obtainEvent(DetailsEvent.ProvideRestaurant(restaurantId))
    }

    DetailsView(
        viewState,
        eventHandler = { viewModel.obtainEvent(it) },
    )
}

// region Helpers
@Composable
fun DetailsView(
    viewState: DetailsViewState,
    eventHandler: (DetailsEvent) -> Unit,
) {
    val model = viewState.restaurant

    if (model == null) {
        Text(text = "Missing restaurant")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        NetLoadedImage(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .align(Alignment.CenterHorizontally),
            url = model.image,
            contentDescription = "Restaurant image",
        )

        Text(
            modifier = Modifier,
            text = model.name,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            modifier = Modifier,
            text = model.deliveryTime,
            fontWeight = FontWeight.Light,
            fontSize = 24.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
// endregion

@Preview
@Composable
fun DetailsScreenPreview() {
    TestAppTheme {
        DetailsView(
            viewState = DetailsViewState(),
            eventHandler = {},
        )
    }
}