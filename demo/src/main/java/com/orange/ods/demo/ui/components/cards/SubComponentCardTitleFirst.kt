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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.card.OdsCardTitleFirst
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.LabelledCheckbox

@ExperimentalMaterialApi
@Composable
fun CardTitleFirstContent() {
    val context = LocalContext.current
    val thumbnailIsChecked = rememberSaveable { mutableStateOf(true) }
    val textIsChecked = rememberSaveable { mutableStateOf(true) }
    val subtitleIsChecked = rememberSaveable { mutableStateOf(true) }
    val button1IsChecked = rememberSaveable { mutableStateOf(true) }
    val button2IsChecked = rememberSaveable { mutableStateOf(true) }

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetPeekHeight = 56.dp,
        sheetContent = {
            CardBottomSheetContentHeader()
            LabelledCheckbox(label = stringResource(id = R.string.component_element_thumbnail), checked = thumbnailIsChecked)
            LabelledCheckbox(label = stringResource(id = R.string.component_element_subtitle), checked = subtitleIsChecked)
            LabelledCheckbox(label = stringResource(id = R.string.component_element_text), checked = textIsChecked)
            LabelledCheckbox(label = stringResource(id = R.string.component_element_button1), checked = button1IsChecked)
            LabelledCheckbox(label = stringResource(id = R.string.component_element_button2), checked = button2IsChecked)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.ods_spacing_s))
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.ods_spacing_s))
            ) {
                val button1Text = stringResource(id = R.string.component_element_button1)
                val button2Text = stringResource(id = R.string.component_element_button2)
                val cardContainerText = stringResource(id = R.string.component_card_element_container)

                OdsCardTitleFirst(
                    thumbnailRes = if (thumbnailIsChecked.value) R.drawable.placeholder else null,
                    imageRes = R.drawable.placeholder,
                    title = stringResource(id = R.string.component_element_title),
                    subtitle = if (subtitleIsChecked.value) stringResource(id = R.string.component_element_subtitle) else null,
                    text = if (textIsChecked.value) stringResource(id = R.string.component_element_text_value) else null,
                    onCardClick = { clickOnElement(context, cardContainerText) },
                    button1Text = if (button1IsChecked.value) button1Text else null,
                    onButton1Click = { clickOnElement(context, button1Text) },
                    button2Text = if (button2IsChecked.value) button2Text else null,
                    onButton2Click = { clickOnElement(context, button2Text) }
                )
            }
        }
    }
}