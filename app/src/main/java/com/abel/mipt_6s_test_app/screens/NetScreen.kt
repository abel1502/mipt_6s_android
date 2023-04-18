package com.abel.mipt_6s_test_app.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.abel.mipt_6s_test_app.R
import com.abel.mipt_6s_test_app.ui.theme.TestAppTheme


@Composable
fun NetScreen(
    viewModel: NetViewModel,
    navController: NavController,
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.setNavController(navController)
    }

    NetView(
        viewState,
        eventHandler = { viewModel.obtainEvent(it) },
    )
}

// region Helpers
@Composable
fun NetView(
    viewState: NetViewState,
    eventHandler: (NetEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (viewState.commercial != null) {
            NetLoadedImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 16.dp)
                    .clickable { eventHandler(NetEvent.CommercialClicked(viewState.commercial.url)) },
                url = viewState.commercial.picture,
                contentDescription = "Commercial",
            )
        }

        Row(
            modifier = Modifier
                .padding(vertical=8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Switch(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                checked = viewState.curSection == NetViewState.Section.POPULAR,
                onCheckedChange = { eventHandler(NetEvent.SwitchSection(
                    if (it) NetViewState.Section.POPULAR else NetViewState.Section.NEAREST
                )) },
            )
            Text(
                modifier = Modifier,
                text = if (viewState.curSection == NetViewState.Section.POPULAR)
                    stringResource(id = R.string.restaurants_popular)
                else
                    stringResource(id = R.string.restaurants_nearest),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 1,
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            itemsIndexed(viewState.getRestaurants()) { _, restaurant ->
                NetRestaurantCard(
                    model = restaurant,
                    viewState = viewState,
                    eventHandler = eventHandler,
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
            }
        }
    }
}

@Composable
fun NetRestaurantCard(
    model: Restaurant,
    viewState: NetViewState,
    eventHandler: (NetEvent) -> Unit,
) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .clickable { eventHandler(NetEvent.RestaurantClicked(model)) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NetLoadedImage(
                modifier = Modifier
                    .fillMaxHeight(),
                url = model.image,
                contentDescription = "Restaurant image",
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp),
            ) {
                Text(
                    modifier = Modifier,
                    text = model.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    modifier = Modifier,
                    text = model.deliveryTime,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun NetLoadedImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String,
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = contentDescription,
    )
}
// endregion Helpers

@Preview
@Composable
fun NetScreenPreview() {
    TestAppTheme {
        NetView(
            viewState = NetViewState(),
            eventHandler = {},
        )
    }
}