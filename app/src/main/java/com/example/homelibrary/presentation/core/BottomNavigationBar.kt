package com.example.homelibrary.presentation.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homelibrary.R
import com.example.homelibrary.presentation.navgraph.Screen

data class BottomItem(
    val title: String,
    val icon: ImageVector
)
@Composable
fun BottomNavigationBar(
    bottomNavController: NavHostController
){

    val items = listOf(
        BottomItem(
            title = stringResource(id = R.string.home_label),
            icon = Icons.Rounded.Home
        ),
        BottomItem(
            title = stringResource(id = R.string.category_label),
            icon = Icons.Rounded.Category
        ),
        BottomItem(
            title = stringResource(id = R.string.favorite_label),
            icon = Icons.Rounded.Favorite
        ),
        BottomItem(
            title = stringResource(id = R.string.profile_label),
            icon = Icons.Rounded.AccountCircle
        )
    )

    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        Row(
            modifier = Modifier
                .background(colorResource(id = R.color.milk_white))
                .border(1.dp, Color.Gray, shape = RectangleShape)
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(
                    selected = selected.intValue == index,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(id = R.color.teal_main),
                        unselectedIconColor = colorResource(id = R.color.milk),
                        selectedTextColor = colorResource(id = R.color.teal_main),
                        unselectedTextColor = colorResource(id = R.color.milk)
                    ),
                    onClick = {
                        selected.intValue = index
                        when (selected.intValue) {
                            0 -> {
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.DashboardScreen.route)
                            }
                            1 -> {
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.CategoryScreen.route)
                            }
                            2 -> {
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.FavoriteScreen.route)
                            }
                            3 -> {
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.ProfileScreen.route)
                            }
                        }
                    },
                    icon = {
                        Icon (
                            imageVector = bottomItem.icon,
                            contentDescription = bottomItem.title,
                            //tint = colorResource(R.color.teal_main)
                        )
                    },
                    label = {
                        Text(
                            text = bottomItem.title,
                            //color = colorResource(R.color.teal_main)
                        )
                    }
                )
            }
        }
    }

}