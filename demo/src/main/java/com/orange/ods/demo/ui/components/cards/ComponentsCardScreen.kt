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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.orange.ods.compose.theme.Grey300
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.ComponentsNavigationItem

@ExperimentalMaterialApi
@Composable
fun ComponentsCardScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.picture_component_cards),
            modifier = Modifier.fillMaxWidth(),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.component_card_description),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.body1
        )
        Column(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            TextAndDivider(
                text = stringResource(id = R.string.component_image_first_card_view),
                onClick = {
                    navController.navigate(ComponentsNavigationItem.ImageFirstCardView.route)
                }
            )
            TextAndDivider(
                text = stringResource(id = R.string.component_small_card_view),
                onClick = {
                    navController.navigate(ComponentsNavigationItem.SmallCardView.route)
                }
            )
        }
    }
}

@Composable
private fun TextAndDivider(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            text = text
        )
    }
    Divider(
        modifier = Modifier.padding(start = 16.dp, end = 10.dp),
        color = Grey300,
        thickness = 1.dp
    )
}