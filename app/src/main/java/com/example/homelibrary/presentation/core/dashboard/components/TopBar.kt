package com.example.homelibrary.presentation.core.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.util.Dimens.SmallPadding

@Composable
fun TopBar(){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding)
                .height(60.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton(
                onClick = { /* Add click logic here */ },
                modifier = Modifier.size(35.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(35.dp),
                    tint = Color.Black
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

            IconButton(
                onClick = { /* Add click logic here */ },
                modifier = Modifier.size(35.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Notification",
                    modifier = Modifier.size(35.dp),
                    tint = Color.Black
                )
            }

        }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}