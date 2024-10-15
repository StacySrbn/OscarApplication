package com.example.homelibrary.presentation.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.homelibrary.R
import com.example.homelibrary.presentation.Dimens.AHundred
import com.example.homelibrary.presentation.Dimens.BigPadding
import com.example.homelibrary.presentation.Dimens.ExtraBigPadding
import com.example.homelibrary.presentation.Dimens.ExtraSmallPadding
import com.example.homelibrary.presentation.Dimens.SmallPadding
import com.example.homelibrary.presentation.common.DotIndicator
import com.example.homelibrary.presentation.common.LightTealButton
import com.example.homelibrary.presentation.common.TealButton
import com.example.homelibrary.presentation.navgraph.Screen
import com.example.homelibrary.presentation.onboarding.components.OnBoardingPage
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    onEvent: (OnBoardingEvent) -> Unit,
    navigate: (Screen) -> Unit
){

    val pages = listOf(
        Page(
            title = stringResource(id = R.string.onboarding_title_1),
            subtitle = stringResource(id = R.string.onboarding_subtitle_1),
            image = R.drawable.frame_1
        ),
        Page(
            title = stringResource(id = R.string.onboarding_title_2),
            subtitle = stringResource(id = R.string.onboarding_subtitle_2),
            image = R.drawable.frame_2
        ),
        Page(
            title = stringResource(id = R.string.onboarding_title_3),
            subtitle = stringResource(id = R.string.onboarding_subtitle_3),
            image = R.drawable.frame_3
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val pagerState = rememberPagerState(initialPage = 0){
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage){
                    0 -> listOf("","Continue","")
                    1 -> listOf("Back", "Continue", "")
                    2 -> listOf("Back", "Sign Up", "Sign In")
                    else -> listOf("", "", "")
                }
            }
        }
        val scope = rememberCoroutineScope()
        Row (
            modifier = Modifier
                .height(AHundred)
                .fillMaxWidth()
                .padding(start = 28.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ){
            if (buttonState.value[0].isNotEmpty()){
                Text(
                    modifier = Modifier
                        .padding(start = 14.dp, bottom = 20.dp)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - 1
                                )
                            }
                        },
                    text = buttonState.value[0],
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.teal_main)
                )
            }
        }

        HorizontalPager(state = pagerState) {index->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.height(28.dp))

        DotIndicator(
            modifier = Modifier
                .padding(SmallPadding),
            itemsSize = pages.size,
            selectedItem = pagerState.currentPage
        )
        Spacer(modifier = Modifier.height(BigPadding))

        TealButton(
            label = buttonState.value[1],
            onClick = {
                scope.launch {
                    if (pagerState.currentPage==2){
                        onEvent(OnBoardingEvent.SaveAppEntry)
                        navigate(Screen.SignUpScreen)
                    }
                    else {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage+1
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(ExtraSmallPadding))

        if (buttonState.value[2].isNotEmpty()){
            LightTealButton(
                label = buttonState.value[2],
                onClick = {
                    scope.launch {
                        onEvent(OnBoardingEvent.SaveAppEntry)
                        navigate(Screen.SignInScreen)
                    }
                }
            )
        }

        // Bottom Spacer to balance the layout
        Spacer(modifier = Modifier.height(32.dp))
    }
}