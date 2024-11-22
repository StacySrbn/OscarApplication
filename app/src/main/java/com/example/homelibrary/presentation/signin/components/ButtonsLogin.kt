package com.example.homelibrary.presentation.signin.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.ExtraSmallPadding
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.presentation.common.buttons.TealButton
import com.example.homelibrary.presentation.navgraph.Screen
import com.example.homelibrary.ui.theme.HomeLibraryTheme

@Composable
fun ButtonsLogin(
    onGoogleSignInClick:() -> Unit,
    onSignInClick:() -> Unit,
    navigate: (Screen) -> Unit
){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val label = stringResource(id = R.string.login_label)
        TealButton(
            label = label,
            onClick = onSignInClick
        )

        Spacer(modifier = Modifier.height(MediumPadding))

        val signupText = buildAnnotatedString {
            append(stringResource(id = R.string.dont_have_account_label))
            withStyle(style = SpanStyle(color = Color(0xFF118A7E))) {
                append(stringResource(id = R.string.signup_label))
            }
        }

        Text(
            modifier = Modifier.clickable {
                navigate(Screen.SignUpScreen)
            },
            text = signupText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray)
        )
        Spacer(modifier =  Modifier.height(MediumPadding))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding),
            verticalAlignment = Alignment.CenterVertically
        ){
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(color = colorResource(id = R.color.gray))
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = ExtraSmallPadding),
                text = stringResource(id = R.string.or_with_label),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.gray)
            )
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(color = colorResource(id = R.color.gray))
            )
        }
        Spacer(modifier = Modifier.height(MediumPadding))


        val googleLabel = stringResource(id = R.string.signin_google_label)
        val googleIcon: Painter = painterResource(id = R.drawable.google_ic)
        SignInWithButton(
            onClick = onGoogleSignInClick,
            icon = googleIcon,
            label = googleLabel
        )

        Spacer(modifier = Modifier.height(ExtraSmallPadding))

        val facebookLabel = stringResource(id = R.string.signin_facebook_label)
        val facebookIcon: Painter = painterResource(id = R.drawable.facebook_ic)
        SignInWithButton(
            onClick = {},
            icon = facebookIcon,
            label = facebookLabel
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsLoginPreview(){
    HomeLibraryTheme {
        ButtonsLogin(
            onGoogleSignInClick = {},
            navigate = {},
            onSignInClick = {}
        )
    }
}