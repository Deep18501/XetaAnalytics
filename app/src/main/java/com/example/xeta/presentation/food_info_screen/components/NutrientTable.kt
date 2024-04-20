package com.example.xeta.presentation.food_info_screen.components

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xeta.R
import com.example.xeta.data.model.food_info_data.NutritionInfoScaled
import com.example.xeta.data.model.food_info_data.ServingSize
import com.example.xeta.ui.theme.Orange02

@Composable
fun NutrientTable(perServe: List<NutritionInfoScaled>?, per250: List<NutritionInfoScaled>?) {
    var perServeData: List<NutritionInfoScaled>? = null
    var perServe250: List<NutritionInfoScaled>? = null
    if (perServe == null) {
        perServeData = listOf(
            NutritionInfoScaled("Energy", "J", 415.toDouble()),
            NutritionInfoScaled("Protein", "g", 2.toDouble()),
            NutritionInfoScaled("Trans Fat", "g", .5),
            NutritionInfoScaled("Saturated Fat", "g", .8),
            NutritionInfoScaled("Carbohydrates", "g", 3.toDouble()),
            NutritionInfoScaled("Fiber", "g", 4.toDouble()),
        )
        perServe250 = listOf(
            NutritionInfoScaled("Energy", "J", 600.toDouble()),
            NutritionInfoScaled("Protein", "g", 6.toDouble()),
            NutritionInfoScaled("Trans Fat", "g", 1.toDouble()),
            NutritionInfoScaled("Saturated Fat", "g", 0.8),
            NutritionInfoScaled("Carbohydrates", "g", 5.5),
            NutritionInfoScaled("Fiber", "g", 7.2),
        )
    } else {
        perServeData = perServe
        perServe250 = per250
    }

    val items = perServeData.size

    Column(
        modifier = Modifier
            .padding(horizontal = 60.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(border = BorderStroke(0.5.dp, Color.Black), shape = RoundedCornerShape(8.dp))
            .background(Orange02)
    ) {
        Divider(thickness = 1.dp, modifier = Modifier.offset(y=30.dp))

        Row (modifier= Modifier.padding(8.dp)){

            Column {
                Text(text = "", fontSize = 10.sp)
                Spacer(modifier = Modifier.height(12.dp))
                for (item in 0..items - 1) {
                    Text(
                        text = perServeData[item].name, fontSize = 10.sp, fontFamily = FontFamily(
                            Font(
                                R.font.inter
                            )
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(
                    text = "Per Serve", fontSize = 10.sp, fontFamily = FontFamily(
                        Font(
                            R.font.inter
                        )
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                for (item in 0..items - 1) {
                    var value = perServeData[item].value.toString()
                    value += perServeData[item].units
                    Text(
                        text = value, fontSize = 10.sp, fontFamily = FontFamily(
                            Font(
                                R.font.inter
                            )
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(
                    text = "Per 250gm", fontSize = 10.sp, fontFamily = FontFamily(
                        Font(
                            R.font.inter
                        )
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                for (item in 0..items - 1) {
                    val value = perServe250?.get(item)?.value
                    var formattedNumber = "%.1f".format(value).toString()
                    formattedNumber += perServe250?.get(item)?.units ?: "g"
                    Text(
                        text = formattedNumber, fontSize = 10.sp, fontFamily = FontFamily(
                            Font(
                                R.font.inter
                            )
                        )
                    )
                }
            }

        }
    }

}