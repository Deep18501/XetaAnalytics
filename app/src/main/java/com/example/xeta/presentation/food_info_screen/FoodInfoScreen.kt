package com.example.xeta.presentation.food_info_screen

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.xeta.R
import com.example.xeta.common.UiEvents
import com.example.xeta.data.model.food_info_data.FoodInfoModel
import com.example.xeta.data.model.food_info_data.NutritionInfoScaled
import com.example.xeta.data.model.home_data.HomePageModel
import com.example.xeta.presentation.Screens
import com.example.xeta.presentation.food_info_screen.components.Facts
import com.example.xeta.presentation.food_info_screen.components.NutrientTable
import com.example.xeta.presentation.food_info_screen.components.SimilarItems
import com.example.xeta.presentation.food_info_screen.components.TopBarFood
import com.example.xeta.presentation.home_screen.HomeScreenViewModel
import com.example.xeta.presentation.home_screen.components.BottomBar
import com.example.xeta.presentation.home_screen.components.TopBar
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun FoodInfoScreen(navController: NavController, viewModel: FoodScreenViewModel = hiltViewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var data = remember {
        mutableStateOf<FoodInfoModel?>(null)
    }
    LaunchedEffect(viewModel.foodScreenData, data.value) {
        viewModel.foodScreenData?.collect { updated ->
            println(updated)
            data.value = updated
        }
    }
    LaunchedEffect(key1 = viewModel.eventFlow) {
        Log.d("AuthViewModel", "Login Started ${viewModel.eventFlow}")

        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            duration = SnackbarDuration.Short
                        )
                    }
                }

                is UiEvents.NavigateEvent -> {
                    navController.navigate(
                        event.route
                    )
                }
            }
        }
    }
    val scrollState = rememberScrollState()
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopBarFood() {
                navController.navigate(Screens.HomeScreen.route)
            }
        },

        ) { paddingval ->
        println(paddingval)

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState, enabled = true)
        ) {
            println("Current data" + data.value)
            Box(
                modifier = Modifier
                    .height(320.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(id = R.drawable.chicken_fried_resized),
                    contentDescription = null
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = "Food Information",
                            fontSize = 15.sp,
                            color = Color.White,
                            fontWeight = FontWeight(700),
                            fontFamily = FontFamily(Font(R.font.inter))
                        )

                        Text(
                            text = data.value?.name ?: "Fried Chicken",
                            fontSize = 28.sp,
                            color = Color.White,
                            fontWeight = FontWeight(700),
                            fontFamily = FontFamily(Font(R.font.inter))
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(77.dp)
                            .offset(x = (-16).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(70.dp)
                                .height(77.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .alpha(.4f)
                                .background(Color.Gray)
                        )
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val rating = data.value?.health_rating ?: 71
                            Text(
                                text = rating.toString(),
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight(700),
                                fontFamily = FontFamily(Font(R.font.inter))
                            )

                            Text(
                                text = "out of 100",
                                fontSize = 8.sp,
                                color = Color.White,
                                fontWeight = FontWeight(700),
                                fontFamily = FontFamily(Font(R.font.inter))
                            )
                        }
                    }
                }
            }

            Titles(text = "Description")
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = data.value?.description
                    ?: "Fried chicken is a dish consisting of chicken pieces usually from broiler chickens which have been floured or battered and then pan-fried, deep fried, or pressure fried.",
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.inter))
            )
            Spacer(modifier = Modifier.height(9.dp))

            Titles(text = "Macro Nutrients")
            Spacer(modifier = Modifier.height(22.dp))

            val validJson = data.value?.nutrition_info?.replace("'", "\"")
            println("json= $validJson")
            // Use a library like Gson or kotlinx.serialization to parse JSON into objects
            val foodInfoList: List<NutritionInfoScaled>? = parseJson(validJson)

            NutrientTable(perServe = foodInfoList, per250 = data.value?.nutrition_info_scaled)
            Spacer(modifier = Modifier.height(16.dp))

            Titles(text = "Facts")
            Spacer(modifier = Modifier.height(16.dp))

            var similar = mutableListOf<String>()
            data.value?.let {

                for (itms in it.similar_items) {
                    similar.add(itms.name)
                }
                Facts(
                    facts = it.generic_facts
                )
            }
            Spacer(modifier = Modifier.height(26.dp))

            Titles(text = "Similar Items")
            Spacer(modifier = Modifier.height(16.dp))

            SimilarItems(list = similar)
        }

    }

}

fun parseJson(json: String?): List<NutritionInfoScaled>? {
    val gson = Gson()
    if (json != null) {
        return gson.fromJson(json, Array<NutritionInfoScaled>::class.java).toList() ?: null
    } else
        return null
}

@Composable
fun Titles(text: String) {
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = text,
        fontSize = 22.sp,
        fontWeight = FontWeight(700),
        fontFamily = FontFamily(Font(R.font.inter))
    )
}