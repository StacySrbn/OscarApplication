package com.example.homelibrary.presentation.common

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.ExtraSmallPadding
import com.example.homelibrary.util.Dimens.SmallPadding
import com.example.homelibrary.ui.theme.HomeLibraryTheme

@Composable
fun TopSectionBack(
    iconVisibility: Boolean,
    title: String,
    subTitle: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = SmallPadding),
            verticalAlignment = Alignment.Bottom
        ) {
           /* Box(
                modifier = Modifier
                    .padding(ExtraSmallPadding)
            ) {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { },
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null
                )
            }
            */
        }

        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 16.dp, end = 24.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                if (iconVisibility) {
                    Spacer(modifier = Modifier.width(ExtraSmallPadding))
                    Image(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.hello_ic),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            Text(
                text = subTitle,
                color = colorResource(id = R.color.gray),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun TopSectionBackPreview() {
    HomeLibraryTheme {
        TopSectionBack(
            iconVisibility = true,
            title = stringResource(id = R.string.welcome_back_label),
            subTitle = stringResource(id = R.string.sign_to_ur_acc_label)
        )
    }
}