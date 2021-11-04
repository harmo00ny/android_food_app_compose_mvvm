package com.example.android_food_app_compose_mvvm.ui.feature.entry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_food_app_compose_mvvm.ui.feature.categories.FoodCategoriesContract
import com.example.android_food_app_compose_mvvm.ui.feature.categories.FoodCategoriesScreen
import com.example.android_food_app_compose_mvvm.ui.feature.categories.FoodCategoriesViewModel
import com.example.android_food_app_compose_mvvm.ui.feature.entry.NavigationKeys.Arg.FOOD_CATEGORY_ID
import com.example.android_food_app_compose_mvvm.ui.theme.FoodAppTheme
import dagger.hilt.android.AndroidEntryPoint

// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                FoodApp()
            }
        }
    }
}

@Composable
private fun FoodApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.FOOD_CATEGORIES_LIST) {
        composable(route = NavigationKeys.Route.FOOD_CATEGORIES_LIST) {
            FoodCategoriesDestination(navController)
        }
        composable(
            route = NavigationKeys.Route.FOOD_CATEGORY_DETAILS,
            argument = listOf(navArgument(FOOD_CATEGORY_ID) {
                type = NavType.StringType
            })
        ) {
        }
    }
}

@Composable
private fun FoodCategoriesDestination(navController: NavHostController) {
    val viewModel: FoodCategoriesViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    FoodCategoriesScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is FoodCategoriesContract.Effect.Navigation.ToCategoryDetails) {
                navController.navigate("${NavigationKeys.Route.FOOD_CATEGORIES_LIST}/${navigationEffect.categoryName}")
            }
        }
    )
}

object NavigationKeys {
    object Arg {
        const val FOOD_CATEGORY_ID = "foodCategoryName"
    }

    object Route {
        const val FOOD_CATEGORIES_LIST = "food_categories_list"
        const val FOOD_CATEGORY_DETAILS = "$FOOD_CATEGORIES_LIST/{$FOOD_CATEGORY_ID}"
    }
}