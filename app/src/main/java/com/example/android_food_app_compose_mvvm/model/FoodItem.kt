package com.example.android_food_app_compose_mvvm.model

data class FoodItem(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String = ""
)