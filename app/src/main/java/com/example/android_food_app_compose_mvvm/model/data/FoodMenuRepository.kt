package com.example.android_food_app_compose_mvvm.model.data

import com.example.android_food_app_compose_mvvm.model.FoodItem
import com.example.android_food_app_compose_mvvm.model.response.FoodCategoriesResponse
import com.example.android_food_app_compose_mvvm.model.response.MealsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodMenuRepository @Inject constructor(private val foodMenuApi: FoodMenuApi) {
    private var cachedCategories: List<FoodItem>? = null

    suspend fun getFoodCategories(): List<FoodItem> {
        var cachedCategories = cachedCategories
        if (cachedCategories == null) {
            cachedCategories = foodMenuApi.getFoodCategories().mapCategoriesToItems()
            this.cachedCategories = cachedCategories
        }
        return cachedCategories
    }

    private fun FoodCategoriesResponse.mapCategoriesToItems(): List<FoodItem> {
        return this.categories.map { category ->
            FoodItem(
                id = category.id,
                name = category.name,
                description = category.description,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }

    suspend fun getMealsByCategory(categoryId: String): List<FoodItem> {
        val categoryName = getFoodCategories().first { it.id == categoryId }.name
        return foodMenuApi.getMealsByCategory(categoryName).mapMealsToItems()
    }

    private fun MealsResponse.mapMealsToItems(): List<FoodItem> {
        return this.meals.map { category ->
            FoodItem(
                id = category.id,
                name = category.name,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }
}