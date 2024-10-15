package com.example.homelibrary.presentation.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homelibrary.presentation.Dimens
import com.example.homelibrary.presentation.Dimens.AHundred
import com.example.homelibrary.presentation.Dimens.SmallPadding
import com.example.homelibrary.ui.theme.HomeLibraryTheme

@Composable
fun TopSectionDetails(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(AHundred)
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .clickable { },
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = null
        )

        Row (
            modifier = Modifier
                .width(120.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { },
                imageVector = Icons.Rounded.Search,
                contentDescription = null
            )
            Row (
                modifier = Modifier.width(65.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { },
                    imageVector = Icons.Rounded.Send,
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { },
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = null
                )
            }

        }



    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopSectionDetails(){
    HomeLibraryTheme {
        TopSectionDetails()
    }
}
