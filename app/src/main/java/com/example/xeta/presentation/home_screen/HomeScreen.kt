package com.example.xeta.presentation.home_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import com.example.xeta.data.model.home_data.HomePageModel
import com.example.xeta.presentation.Screens
import com.example.xeta.presentation.home_screen.components.BottomBar
import com.example.xeta.presentation.home_screen.components.CircularProgressBar
import com.example.xeta.presentation.home_screen.components.ExploreCard
import com.example.xeta.presentation.home_screen.components.TopBar
import com.example.xeta.ui.theme.Lavender01
import com.example.xeta.ui.theme.Orange01
import com.example.xeta.ui.theme.Purple01
import com.example.xeta.ui.theme.Skin01
import com.example.xeta.ui.theme.White01
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var data = remember{
        mutableStateOf(HomePageModel(null, null, null, null))
    }

    LaunchedEffect(data) {
        viewModel.homeData.collect { updated ->
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
        }, topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar()
        }
    ) { paddingval ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingval.calculateTopPadding(),
                    bottom = paddingval.calculateBottomPadding()
                )
                .verticalScroll(scrollState, enabled = true)
                .clickable {
                    navController.navigate(Screens.FoodInfoScreen.route)
                }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val currentDateTime = LocalDateTime.now()

                // Format the date and time
                val formatter = DateTimeFormatter.ofPattern(
                    "EEEE, d'${getDayOfMonthSuffix(currentDateTime.dayOfMonth)}' MMMM",
                    Locale.ENGLISH
                )
                val formattedDateTime = currentDateTime.format(formatter)

                Text(
                    text = "Today",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.inter))
                )
                Text(
                    text = formattedDateTime,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Gray,
                    fontFamily = FontFamily(Font(R.font.inter))
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressBar(
                    percent = .3f,
                    bgcolor = Skin01,
                    color = Orange01,
                    strokeWidth = 9.dp,
                    radius = 110.dp
                ) {}

                CircularProgressBar(
                    percent = .4f,
                    bgcolor = Lavender01,
                    color = Purple01,
                    strokeWidth = 9.dp,
                    radius = 88.dp
                ) {}

                Text(
                    text = "SET GOAL!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = FontFamily(Font(R.font.inter))
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.height(21.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.path_3065),
                        contentDescription = "Diet Points"
                    )
                    Text(
                        text = "Diet Pts",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_fitness_center),
                        contentDescription = "Exercise Points"
                    )
                    Text(
                        text = "Exercise Pts",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    var cal = 1500
                    if (data.value.section_2 != null) {
                        try {
                            cal = data.value.section_2!!.calories_burned
                        } catch (e: Exception) {
                            Log.d("ExceptionFind5", e.toString())
                        }
                    }
                    Text(
                        text = cal.toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight(400),
                        color = Orange01,
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                    Text(
                        text = "Cal",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "3/5",
                        fontSize = 15.sp,
                        fontWeight = FontWeight(400),
                        color = Orange01,
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                    Text(
                        text = "Days",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "88",
                        fontSize = 15.sp,
                        fontWeight = FontWeight(400),
                        color = Orange01,
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                    Text(
                        text = "Health Score",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                }
            }
            Spacer(modifier = Modifier.height(33.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(10.dp)
                        .width(10.dp)
                        .clip(CircleShape)
                        .background(Orange01)
                )
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(10.dp)
                        .width(10.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
            }


            Text(
                modifier = Modifier.padding(14.dp),
                text = "Your Goals",
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                fontFamily = FontFamily(Font(R.font.inter))
            )
            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shadow(.2.dp, shape = RoundedCornerShape(10.dp))
                    .background(White01),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding(9.dp),
                    painter = painterResource(id = R.drawable.img_training_plan),
                    contentDescription = ""
                )
                Column (modifier= Modifier.width(195.dp)){
                    Text(
                        text = data.value.section_1?.plan_name ?: "Keto Weight Loss plan",
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                    Text(
                        text = "Current Mission Goal",
                        color = Color.Gray,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                }
                Box(modifier = Modifier.padding(15.dp), contentAlignment = Alignment.Center) {
                    val progress= data.value.section_1?.progress?.replace("%","")?.toFloat()?:73f
                    println(progress)
                    CircularProgressBar(
                        percent = progress / 100,
                        radius = 21.dp,
                        bgcolor = Color.Transparent,
                        color = Orange01,
                        startAngle = 90f,
                        strokeWidth = 6.dp
                    ) {
                    }
                    Text(
                        text = progress.toInt().toString() + "%",
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.inter))
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(14.dp),
                text = "Explore",
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                fontFamily = FontFamily(Font(R.font.inter))
            )
            Spacer(modifier = Modifier.height(15.dp))

            ExploreCard(
                image = R.drawable.img_explore_1,
                title = "Find Diets",
                desc = "Find premade diets according to your cuisine"
            )
            ExploreCard(
                image = R.drawable.img_explore_2,
                title = "Find Nutritionist",
                desc = "Get customized diets to achieve your health \n" +
                        "goal"
            )
        }
    }

}


fun getDayOfMonthSuffix(day: Int): String {
    if (day !in 1..31) {
        throw IllegalArgumentException("Invalid day of month")
    }
    return when (day) {
        1, 21, 31 -> "st"
        2, 22 -> "nd"
        3, 23 -> "rd"
        else -> "th"
    }
}