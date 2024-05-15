package edu.virginia.cs.helloworldhw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import edu.virginia.cs.helloworldhw.ui.theme.HelloWorldHWTheme
import edu.virginia.cs.helloworldhw.ui.theme.add_item.AddItemScreen
import edu.virginia.cs.helloworldhw.ui.theme.bucketlist_list.BucketlistScreen
import edu.virginia.cs.helloworldhw.util.Routes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldHWTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = Routes.VIEW_LIST) {
                    composable(Routes.VIEW_LIST) {
                        BucketlistScreen(onNavigate = {
                            navController.navigate(it.route)
                            }
                        )
                    }
                    composable(
                        route = Routes.ADD_EDIT_LIST_ITEM + "?itemId={itemId}",
                        arguments = listOf(
                            navArgument(
                                name = "itemId" ) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddItemScreen(onPopBackStack = { navController.popBackStack() })
                    }
            }
        }
    }
}
}
