package com.example.homelibrary.presentation.core.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.ExtraSmallPadding
import com.example.homelibrary.util.Dimens.SmallPadding

@Composable
fun TopBar(){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SmallPadding)
                .height(100.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Box(modifier = Modifier.padding(ExtraSmallPadding)){
                Image(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null
                )
            }

            Box(modifier = Modifier.padding(ExtraSmallPadding)){
                Text(
                    text = stringResource(id = R.string.home_label),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }



            Box(modifier = Modifier.padding(ExtraSmallPadding)){
                Image(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                    
                )
            }

        }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}