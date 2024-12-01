package com.example.homelibrary.presentation.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.ExtraSmallPadding

@Composable
fun TopButtonsSection(
    navController: NavHostController
){

    var isFavorite by remember { mutableStateOf(false) }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .size(30.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp),
                imageVector = Icons.Rounded.ArrowBackIosNew,
                contentDescription = stringResource(R.string.back),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        // TODO save the choice and color
        //  add to the list of favorite to display it
        //  on the favorite screen
        IconButton(
            onClick = { isFavorite = !isFavorite },
            modifier = Modifier
                .size(45.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = ExtraSmallPadding),
                imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = stringResource(R.string.favorite),
                tint = if (isFavorite) colorResource(id = R.color.hot_orange) else MaterialTheme.colorScheme.onBackground
            )
        }
    }
}