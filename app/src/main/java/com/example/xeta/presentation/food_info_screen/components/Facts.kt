package com.example.xeta.presentation.food_info_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xeta.R
import com.example.xeta.ui.theme.Orange03


@Composable
fun Facts(facts:List<String>) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .padding(16.dp)
            .horizontalScroll(scrollState,enabled = true)
    ) {
        for (fact in facts) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp).clip(RoundedCornerShape(8.dp )).background(Orange03)
            ) {
                Text(
                    modifier= Modifier.padding(top = 25.dp, start = 56.dp),
                    text = "Did you know?",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.inter))
                )
                Spacer(modifier = Modifier.height(4.dp)) // Add some space between the title and the fact
                Text(
                    modifier= Modifier.width(343.dp).padding(16.dp),
                    text = fact,
                    color = Color.White,
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.inter))
                )
            }
        }

    }
}
