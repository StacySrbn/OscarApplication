package com.example.homelibrary.presentation.common

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.MediumPadding

@Composable
fun ErrorLoadingSign(
    modifier: Modifier = Modifier,
){
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                modifier = Modifier
                    .size(40.dp),
                imageVector = Icons.Rounded.Error,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
            Spacer(modifier = Modifier.height(MediumPadding))
            Text(
                fontSize = 18.sp,
                color = Color.Gray,
                text = stringResource(R.string.error_while_loading)
            )
            Text(
                fontSize = 18.sp,
                color = Color.Gray,
                text = stringResource(R.string.tap_to_try_again)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview(){
    ErrorLoadingSign(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
            .clickable {  }
    )
}