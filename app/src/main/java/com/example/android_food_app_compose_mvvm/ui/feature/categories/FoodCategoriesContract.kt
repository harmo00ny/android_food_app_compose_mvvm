package com.example.android_food_app_compose_mvvm.ui.feature.categories

import com.example.android_food_app_compose_mvvm.base.ViewEvent
import com.example.android_food_app_compose_mvvm.base.ViewSideEffect
import com.example.android_food_app_compose_mvvm.base.ViewState
import com.example.android_food_app_compose_mvvm.model.FoodItem

class FoodCategoriesContract {
    sealed class Event: ViewEvent {
        data class CategorySelection(val categoryName: String): Event()
    }

    data class State(val categories: List<FoodItem> = listOf(), val isLoading: Boolean = false) :
        ViewState

    sealed class Effect: ViewSideEffect {
        object DataWasLoaded: Effect()

        sealed class Navigation: Effect() {
            data class ToCategoryDetails(val categoryName: String): Navigation()
        }
    }
}