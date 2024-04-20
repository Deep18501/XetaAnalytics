package com.example.xeta.presentation.food_info_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xeta.R
import com.example.xeta.data.model.food_info_data.SimilarItem

@Composable
fun SimilarItems(list: List<String>) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(16.dp)
            .horizontalScroll(scrollState, enabled = true)
    ) {
        var no = 0
        var image = R.drawable.similar_item_1
        for (item in list) {
            image = if (no % 2 == 0) {
                R.drawable.similar_item_1
            } else {
                R.drawable.similar_item_2
            }
            no+=1
            Box(
                modifier = Modifier
                    .height(113.dp)
                    .width(157.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp)), contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(image),
                    contentDescription = null
                )
                Text(
                    text = item,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(800),
                    fontFamily = FontFamily(
                        Font(R.font.inter)
                    )
                )
            }

        }
    }

}