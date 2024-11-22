package com.example.homelibrary.presentation.signup.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.presentation.common.buttons.TealButton
import com.example.homelibrary.presentation.navgraph.Screen

@Composable
fun SignUpScreenButtons(
    navigate: (Screen) -> Unit,
    onRegisterClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val btnLabel = stringResource(id = R.string.register_label)
        TealButton(
            onClick = {
                onRegisterClick()
            },
            label = btnLabel
        )
        Spacer(modifier = Modifier.height(MediumPadding))

        val signInText = buildAnnotatedString {
            append(stringResource(id = R.string.have_account_signin_label))
            withStyle(style = SpanStyle(color = Color(0xFF118A7E))) {
                append(stringResource(id = R.string.signin_label))
            }
        }

        Text(
            modifier = Modifier
                .clickable {
                    navigate(Screen.SignInScreen)
                },
            text = signInText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray)
        )


        Spacer(modifier = Modifier.padding(MediumPadding))

        Text(
            text = stringResource(id = R.string.register_agreement_1),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray)
        )
        Text(
            text = stringResource(id = R.string.register_agreement_2),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.teal_main)
        )


    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewSignUpScreenButtons() {
//    SignUpScreenButtons()
//}