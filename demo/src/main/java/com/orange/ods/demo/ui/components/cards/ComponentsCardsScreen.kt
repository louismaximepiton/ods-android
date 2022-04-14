/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.cards

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.ComponentsNavigationItem
import com.orange.ods.demo.ui.components.utilities.ComponentHeader

private data class CardMenuItem(@StringRes val titleRes: Int, val route: String)

private val cardMenuItems = listOf(
    CardMenuItem(R.string.component_card_image_first, ComponentsNavigationItem.CardImageFirst.route),
    CardMenuItem(R.string.component_card_title_first, ComponentsNavigationItem.CardTitleFirst.route),
    CardMenuItem(R.string.component_card_small, ComponentsNavigationItem.CardSmall.route)
)

@ExperimentalMaterialApi
@Composable
fun ComponentsCardsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        ComponentHeader(
            imageRes = R.drawable.picture_component_cards,
            description = R.string.component_card_description
        )

        Column(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s))
        ) {
            for (cardMenuItem in cardMenuItems) {
                OdsListItem(text = stringResource(id = cardMenuItem.titleRes), modifier = Modifier.clickable {
                    navController.navigate(cardMenuItem.route)
                })
            }
        }
    }
}