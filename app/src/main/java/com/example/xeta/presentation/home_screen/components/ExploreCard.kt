package com.example.xeta.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xeta.R

@Composable
fun ExploreCard(image:Int,title:String,desc:String) {
    Row(modifier= Modifier
        .fillMaxWidth()
        .padding( horizontal = 14.dp, vertical = 8.dp)
        .clip(RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
        ) {
        Image(painter = painterResource(id = image),modifier= Modifier
            .padding(7.dp)
            .fillMaxWidth(.3f), contentDescription ="" )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(18.dp)) {
            Text(
                text = title,
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.inter))
            )
            Spacer(modifier= Modifier.height(7.dp))
            Text(
                text = desc,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.inter))
            )
        }
        
    }
}