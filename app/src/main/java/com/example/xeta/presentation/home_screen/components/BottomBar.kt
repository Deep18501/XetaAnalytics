package com.example.xeta.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xeta.R

@Composable
fun BottomBar() {
    Divider(color = Color.Gray, thickness = .3.dp)
    Row (Modifier.fillMaxWidth().padding(top = 7.dp), horizontalArrangement = Arrangement.SpaceEvenly,){
        BottomItem(image = R.drawable.ic_activity, text = "Activity")
        BottomItem(image =R.drawable.ic_goals , text = "Goals")
        BottomItem(image = R.drawable.ic_camera, text ="Camera" )
        BottomItem(image = R.drawable.ic_feed, text ="Feed" )
        BottomItem(image = R.drawable.ic_profile, text = "Profile")
    }
}

@Composable
fun BottomItem(image:Int,text:String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = image), contentDescription =null )
        Text(text = text, fontSize = 10.sp, fontFamily = FontFamily(Font(R.font.inter)), color = Color.Gray)
    }
}