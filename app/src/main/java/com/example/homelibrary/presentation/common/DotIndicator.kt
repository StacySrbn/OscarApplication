package com.example.homelibrary.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.SelectedIndicatorSize
import com.example.homelibrary.util.Dimens.UnselectedIndicatorSize

@Composable
fun DotIndicator(
    modifier: Modifier = Modifier,
    itemsSize: Int,
    selectedItem: Int,
    selectedColor: Color = colorResource(id = R.color.teal_main),
    unselectedColor: Color = colorResource(id = R.color.teal_light)
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        repeat(times = itemsSize){ item->
            Box(
                modifier = Modifier
                    .size(if (item == selectedItem) SelectedIndicatorSize else UnselectedIndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (item == selectedItem) selectedColor else unselectedColor)

            )
        }
    }
}
