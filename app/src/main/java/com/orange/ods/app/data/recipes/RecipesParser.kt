/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.data.recipes

import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.Food
import com.orange.ods.app.domain.recipes.Ingredient
import com.orange.ods.app.domain.recipes.Recipe
import org.json.JSONException
import org.json.JSONObject

class RecipesParser {

    companion object {

        private const val Recipes = "recipes"
        private const val Title = "title"
        private const val Subtitle = "subtitle"
        private const val Description = "description"
        private const val Url = "url"
        private const val IconName = "iconName"
        private const val Ingredients = "ingredients"
        private const val FoodId = "foodId"
        private const val Quantity = "quantity"
        private const val Cafe = "Cafe"
        private const val CookingPot = "CookingPot"
        private const val IceCream = "IceCream"
        private const val Restaurant = "Restaurant"
        private const val Foods = "foods"
        private const val Id = "id"
        private const val Name = "name"
        private const val Image = "image"
    }

    @Throws(JSONException::class, NoSuchElementException::class)
    fun parseRecipes(jsonString: String): List<Recipe> {
        val jsonObject = JSONObject(jsonString)
        val jsonFoods = jsonObject.getJSONArray(Foods)
        val foods = List(jsonFoods.length()) { parseFood(jsonFoods.getJSONObject(it)) }
        val jsonRecipes = jsonObject.getJSONArray(Recipes)

        return List(jsonRecipes.length()) { parseRecipe(jsonRecipes.getJSONObject(it), foods) }
    }

    @Throws(JSONException::class)
    fun parseFood(jsonFood: JSONObject): Food {
        val id = jsonFood.getInt(Id)
        val name = jsonFood.getString(Name)
        val image = jsonFood.getString(Image)

        return Food(id, name, image)
    }

    @Throws(JSONException::class, NoSuchElementException::class)
    private fun parseRecipe(jsonRecipe: JSONObject, foods: List<Food>): Recipe {
        val title = jsonRecipe.getString(Title)
        val subtitle = jsonRecipe.getString(Subtitle)
        val description = jsonRecipe.getString(Description)
        val url = jsonRecipe.getString(Url)
        val iconName = jsonRecipe.getString(IconName)
        val iconResId = getIconResId(iconName)
        val jsonIngredients = jsonRecipe.getJSONArray(Ingredients)
        val ingredients = List(jsonIngredients.length()) { parseIngredient(jsonIngredients.getJSONObject(it), foods) }

        return Recipe(title, subtitle, ingredients, description, url, iconResId)
    }

    @Throws(JSONException::class, NoSuchElementException::class)
    private fun parseIngredient(jsonIngredient: JSONObject, foods: List<Food>): Ingredient {
        val foodId = jsonIngredient.getInt(FoodId)
        val food = foods.first { it.id == foodId }
        val quantity = jsonIngredient.getString(Quantity)

        return Ingredient(food, quantity)
    }

    private fun getIconResId(iconName: String) = when (iconName) {
        Cafe -> R.drawable.ic_coffee
        CookingPot -> R.drawable.ic_cooking_pot
        IceCream -> R.drawable.ic_ice_cream
        Restaurant -> R.drawable.ic_restaurant
        else -> null
    }
}