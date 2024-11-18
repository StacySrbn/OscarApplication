package com.example.homelibrary.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.util.Dimens.SmallPadding
import com.example.homelibrary.presentation.onboarding.Page
import com.example.homelibrary.ui.theme.HomeLibraryTheme

@Composable
fun OnBoardingPage(
    page: Page
) {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(horizontal = MediumPadding),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(SmallPadding))
        Text(
            modifier = Modifier.padding(horizontal = SmallPadding),
            text = page.title,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.padding(horizontal = SmallPadding),
            text = page.subtitle,
            color = colorResource(id = R.color.gray),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPagePreview(){
    HomeLibraryTheme {
        OnBoardingPage(
            page = Page(
                title = "Now reading books will be easier",
                subtitle = " Discover new worlds, join a vibrant reading community. Start your reading adventure effortlessly with us.",
                image = R.drawable.frame_1
            )
        )
    }
}