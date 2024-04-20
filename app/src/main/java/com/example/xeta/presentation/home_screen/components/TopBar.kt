package com.example.xeta.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xeta.R
import com.example.xeta.ui.theme.Gold01

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(1.2f).height(67.dp).shadow(elevation = .3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

    ) {

        Text(modifier = Modifier.padding(start = 21.dp),
            text = "Dietsnap",
            fontWeight = FontWeight(500),
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.inter)
            ),
            color = Gold01
        )

        Row {
            Image(painter = painterResource(id = R.drawable.ic_bell), contentDescription = "icons")
            Spacer(modifier = Modifier.width(20.dp))
            Image(painter = painterResource(id = R.drawable.ic_achivement), contentDescription = "icons")
            Spacer(modifier = Modifier.width(20.dp))
            Image(painter = painterResource(id = R.drawable.ic_message), contentDescription = "icons")
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}