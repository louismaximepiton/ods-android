/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.listitem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.domain.recipes.Recipe
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsCaptionTrailing
import com.orange.ods.compose.component.list.OdsCheckboxTrailing
import com.orange.ods.compose.component.list.OdsIconTrailing
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconScope
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.OdsListItemTrailing
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.extension.orElse

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentListItem() {
    val listItemCustomizationState = rememberListItemCustomizationState()
    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = listItemCustomizationState.bottomSheetScaffoldState,
        bottomSheetContent = { ComponentListItemBottomSheetContent(listItemCustomizationState) },
        content = { ComponentListItemContent(listItemCustomizationState) }
    )
}

@Composable
private fun ComponentListItemBottomSheetContent(listItemCustomizationState: ListItemCustomizationState) {
    ComponentCountRow(
        modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
        title = stringResource(id = R.string.component_list_item_size),
        count = listItemCustomizationState.lineCount,
        minusIconContentDescription = stringResource(id = R.string.component_list_item_remove_line),
        plusIconContentDescription = stringResource(id = R.string.component_list_item_add_line),
        minCount = ListItemCustomizationState.MinLineCount,
        maxCount = ListItemCustomizationState.MaxLineCount
    )

    Subtitle(textRes = R.string.component_list_leading, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = listItemCustomizationState.selectedLeading,
        modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
        outlinedChips = true
    ) {
        OdsChoiceChip(textRes = R.string.component_list_leading_none, value = ListItemCustomizationState.Leading.None)
        OdsChoiceChip(textRes = R.string.component_list_leading_icon, value = ListItemCustomizationState.Leading.Icon)
        OdsChoiceChip(textRes = R.string.component_list_leading_circular_image, value = ListItemCustomizationState.Leading.CircularImage)
        OdsChoiceChip(textRes = R.string.component_list_leading_square_image, value = ListItemCustomizationState.Leading.SquareImage)
        OdsChoiceChip(textRes = R.string.component_list_leading_wide_image, value = ListItemCustomizationState.Leading.WideImage)
    }

    Subtitle(textRes = R.string.component_list_trailing, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = listItemCustomizationState.selectedTrailing,
        modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
        outlinedChips = true
    ) {
        listItemCustomizationState.trailings.forEach { trailing ->
            OdsChoiceChip(textRes = trailing.textResId, value = trailing)
        }
    }

}

@Composable
private fun ComponentListItemContent(listItemCustomizationState: ListItemCustomizationState) {
    val recipe = LocalRecipes.current.first { it.description.isNotBlank() }
    with(listItemCustomizationState) {
        Column {
            if (!trailings.contains(selectedTrailing.value)) {
                resetTrailing()
            }

            val modifier = Modifier
                .let { modifier ->
                    iconType?.let { modifier.iconType(it) }.orElse { modifier }
                }
            val singleLineSecondaryText = lineCount.value == 2

            val text = recipe.title
            val secondaryText = if (lineCount.value > 1) recipe.description else null
            val icon: @Composable (OdsListItemIconScope.() -> Unit)? =
                listItemCustomizationState.getIconPainter(recipe)?.let { { OdsListItemIcon(painter = it) } }
            trailing?.let { listItemTrailing ->
                OdsListItem(
                    modifier = modifier,
                    text = text,
                    secondaryText = secondaryText,
                    singleLineSecondaryText = singleLineSecondaryText,
                    icon = icon,
                    trailing = listItemTrailing
                )
            }.orElse {
                val context = LocalContext.current
                OdsListItem(
                    modifier = modifier.clickable { clickOnElement(context = context, context.getString(R.string.component_list_item)) },
                    text = text,
                    secondaryText = secondaryText,
                    singleLineSecondaryText = singleLineSecondaryText,
                    icon = icon
                )
            }

            CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))) {
                FunctionCallCode(
                    name = OdsComposable.OdsListItem.name,
                    parameters = {
                        string("text", text)
                        if (secondaryText != null) {
                            string("secondaryText", secondaryText)
                            if (!singleLineSecondaryText) stringRepresentation("singleLineSecondaryText", false)
                        }
                        if (selectedLeading.value != ListItemCustomizationState.Leading.None) {
                            simple("modifier", "Modifier.iconType($iconType)")
                            simple("icon", "{ OdsListItemIcon(<painter>) }")
                        }
                        if (selectedTrailing.value != ListItemCustomizationState.Trailing.None) {
                            val clazz: Class<*>? = when (selectedTrailing.value) {
                                ListItemCustomizationState.Trailing.Checkbox -> OdsCheckboxTrailing::class.java
                                ListItemCustomizationState.Trailing.Switch -> OdsSwitchTrailing::class.java
                                ListItemCustomizationState.Trailing.Icon -> OdsIconTrailing::class.java
                                ListItemCustomizationState.Trailing.Caption -> OdsCaptionTrailing::class.java
                                else -> null
                            }

                            val trailingParamName = "trailing"
                            clazz?.let {
                                classInstance(trailingParamName, it) {}
                            }.orElse {
                                simple(trailingParamName, "<OdsListItemTrailing>")
                            }
                        }
                    }
                )
            }
        }
    }
}

private val ListItemCustomizationState.Trailing.textResId: Int
    get() = when (this) {
        ListItemCustomizationState.Trailing.None -> R.string.component_list_trailing_none
        ListItemCustomizationState.Trailing.Checkbox -> R.string.component_list_trailing_checkbox
        ListItemCustomizationState.Trailing.Switch -> R.string.component_list_trailing_switch
        ListItemCustomizationState.Trailing.Icon -> R.string.component_list_trailing_icon
        ListItemCustomizationState.Trailing.Caption -> R.string.component_list_trailing_caption
    }

private val ListItemCustomizationState.iconType: OdsListItemIconType?
    get() = when (selectedLeading.value) {
        ListItemCustomizationState.Leading.None -> null
        ListItemCustomizationState.Leading.Icon -> OdsListItemIconType.Icon
        ListItemCustomizationState.Leading.CircularImage -> OdsListItemIconType.CircularImage
        ListItemCustomizationState.Leading.SquareImage -> OdsListItemIconType.SquareImage
        ListItemCustomizationState.Leading.WideImage -> OdsListItemIconType.WideImage
    }

@Composable
private fun ListItemCustomizationState.getIconPainter(recipe: Recipe): Painter? {
    return when (selectedLeading.value) {
        ListItemCustomizationState.Leading.None -> null
        ListItemCustomizationState.Leading.Icon -> recipe.iconResId?.let { painterResource(id = it) }
        ListItemCustomizationState.Leading.CircularImage,
        ListItemCustomizationState.Leading.SquareImage,
        ListItemCustomizationState.Leading.WideImage -> {
            val wideImageSizeWidthPx = with(LocalDensity.current) { dimensionResource(id = com.orange.ods.R.dimen.list_wide_image_width).toPx() }
            val wideImageSizeHeightPx = with(LocalDensity.current) { dimensionResource(id = com.orange.ods.R.dimen.list_wide_image_height).toPx() }
            rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.imageUrl)
                    .scale(Scale.FILL)
                    .size(Size(wideImageSizeWidthPx.toInt(), wideImageSizeHeightPx.toInt()))
                    .build(),
                placeholder = painterResource(id = DrawableManager.getPlaceholderSmallResId()),
                error = painterResource(id = DrawableManager.getPlaceholderSmallResId(error = true))
            )
        }
    }
}

private val ListItemCustomizationState.trailing: OdsListItemTrailing?
    @Composable
    get() = when (selectedTrailing.value) {
        ListItemCustomizationState.Trailing.None -> null
        ListItemCustomizationState.Trailing.Checkbox -> {
            val checked = remember { mutableStateOf(true) }
            OdsCheckboxTrailing(checked = checked)
        }
        ListItemCustomizationState.Trailing.Switch -> {
            val checked = remember { mutableStateOf(true) }
            OdsSwitchTrailing(checked = checked)
        }
        ListItemCustomizationState.Trailing.Icon -> {
            val context = LocalContext.current
            val iconText = stringResource(id = R.string.component_element_icon)
            OdsIconTrailing(
                modifier = Modifier.clickable {
                    clickOnElement(context, iconText)
                },
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = stringResource(id = R.string.component_list_information)
            )
        }
        ListItemCustomizationState.Trailing.Caption -> {
            OdsCaptionTrailing(text = stringResource(id = R.string.component_element_caption))
        }
    }