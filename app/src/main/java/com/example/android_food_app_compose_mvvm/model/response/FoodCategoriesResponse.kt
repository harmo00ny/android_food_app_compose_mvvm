package com.example.android_food_app_compose_mvvm.model.response

import com.google.gson.annotations.SerializedName

data class FoodCategoriesResponse(val categories: List<FoodCategoryResponse>)

data class FoodCategoryResponse(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("setCategoryThumb") val thumbnailUrl: String,
    @SerializedName("strCategoryDescription") val description: String = ""
)