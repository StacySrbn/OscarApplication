package com.example.homelibrary.presentation.get_started

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.presentation.common.buttons.TealButton
import com.example.homelibrary.presentation.get_started.components.SuccessSection

@Composable
fun GetStartedScreen(){

    val title = stringResource(id = R.string.get_started_label)
    val subTitle = stringResource(id = R.string.get_started_subtitle)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SuccessSection(title = title, subTitle = subTitle)

        Spacer(modifier = Modifier.height(40.dp))

        val label = stringResource(id = R.string.get_started_label)
        TealButton(
            label = label,
            onClick = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GetStartedScreenPreview() {
    GetStartedScreen()
}