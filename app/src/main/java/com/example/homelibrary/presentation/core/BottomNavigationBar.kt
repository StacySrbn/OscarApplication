package com.example.homelibrary.presentation.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
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
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
@Composable
fun BottomNavigationBar(
    bottomNavController: NavHostController
){

    val items = listOf(
        BottomItem(
            title = stringResource(id = R.string.home_label),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomItem(
            title = stringResource(id = R.string.category_label),
            selectedIcon = Icons.Filled.Category,
            unselectedIcon = Icons.Outlined.Category
        ),
        BottomItem(
            title = stringResource(id = R.string.favorite_label),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder
        ),
        BottomItem(
            title = stringResource(id = R.string.profile_label),
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle
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
                        unselectedIconColor = colorResource(id = R.color.teal_main),
                        selectedTextColor = colorResource(id = R.color.teal_main),
                        unselectedTextColor = colorResource(id = R.color.teal_main),
                        indicatorColor = Color.Transparent
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
                            imageVector = if(index == selected.intValue) {
                                bottomItem.selectedIcon
                            } else bottomItem.unselectedIcon,
                            contentDescription = bottomItem.title,
                        )
                    },
                    label = {
                        Text(
                            text = bottomItem.title,
                        )
                    }
                )
            }
        }
    }

}