package com.example.homelibrary.presentation.details_actor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homelibrary.R

@Composable
fun NavigationTopRow(
    navController: NavHostController
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
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
    }
}