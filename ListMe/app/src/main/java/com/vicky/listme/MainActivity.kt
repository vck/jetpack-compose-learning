package com.vicky.listme

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicky.listme.ui.theme.ListMeTheme

import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun HomeScreen(navController: NavHostController) {
    val elements = MutableList(100) { it }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.LightGray)
    ){
        items(elements) {
            Row(modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(Color.DarkGray)
                .padding(4.dp),

                verticalAlignment = Alignment.CenterVertically
            ){
               Box(contentAlignment = Alignment.Center){
                   Text(
                       text="Element $it",
                       textAlign = TextAlign.Center
                   )
               }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Profile Screen"
        )
    }
}

@Composable
fun InputScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {

        var text by remember { mutableStateOf(TextFieldValue(""))}

        TextField(value = text, onValueChange = {newText -> text = newText})

        Button(onClick = {  }) {
            Text(text="Add Item")
        }
    }
}

@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Setting Screen"
        )
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home"){
            HomeScreen(navController)
        }

        composable("input"){
            InputScreen()
        }

        composable("profile"){
            ProfileScreen()
        }
    }
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit

) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name
                    )
                }
            )
        }
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                ListMeTheme {
                    val navController = rememberNavController()
                    val items = listOf(
                        BottomNavItem(
                            name = "Home",
                            route = "home",
                            icon = Icons.Default.Home
                        ),
                        BottomNavItem(
                            name = "Input",
                            route = "input",
                            icon = Icons.Default.Create
                        ),
                        BottomNavItem(
                            name = "Profile",
                            route = "profile",
                            icon = Icons.Default.Person
                        ),
                    )

                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = items,
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                })
                        }
                    ) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
*/