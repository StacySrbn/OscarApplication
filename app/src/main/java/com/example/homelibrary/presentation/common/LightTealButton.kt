package com.example.homelibrary.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.MediumPadding

@Composable
fun LightTealButton(
    onClick: () -> Unit,
    label: String
){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = MediumPadding)
            .clip(RoundedCornerShape(48.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.teal_light),
            contentColor = colorResource(id = R.color.teal_main)
        ),
        onClick = onClick
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}