/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.imageitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme

/**
 *
 * @param image Image display in the [OdsImageItem].
 * @param iconChecked Specified if icon is currently checked
 * @param iconSelected Specified whether the icon is selected or nor
 * @param onIconCheckedChange Callback to be invoked when this icon is selected
 * @param checkedIcon Optional icon displayed in front of the [OdsImageItem]
 * @param uncheckedIcon Optional icon displayed in front of the [OdsImageItem]
 * @param modifier Modifier to be applied to this [OdsImageItem]
 * @param imageContentDescription Optional image content description
 * @param iconContentDescription Optional icon content description
 * @param title Text displayed in the image
 */
@Composable
@OdsComponentApi
fun OdsImageItem(
    image: Painter,
    iconChecked: Boolean,
    iconSelected: Boolean,
    onIconCheckedChange: (Boolean) -> Unit,
    checkedIcon: Painter,
    uncheckedIcon: Painter,
    modifier: Modifier = Modifier,
    iconContentDescription: String? = null,
    imageContentDescription: String? = null,
    title: String? = null,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = image,
            contentDescription = imageContentDescription,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxSize()
        )
        title?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(color = Color.Black.copy(alpha = 0.38f))
                    .align(Alignment.BottomStart)
                    .height(dimensionResource(id = R.dimen.list_single_line_item_height))
            ) {
                Text(
                    text = it,
                    color = Color.White,
                    style = OdsTheme.typography.subtitle1,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = dimensionResource(id = R.dimen.spacing_m), end = dimensionResource(id = R.dimen.spacing_m)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (iconSelected) {
                    OdsIconToggleButton(
                        checked = iconChecked,
                        onCheckedChange = onIconCheckedChange,
                        uncheckedPainter = uncheckedIcon,
                        checkedPainter = checkedIcon,
                        iconContentDescription = iconContentDescription,
                        displaySurface = OdsDisplaySurface.Dark
                    )
                }
            }
        }
    }
}


@UiModePreviews.Default
@Composable
private fun PreviewOdsImageList(@PreviewParameter(OdsImageListPreviewParameterProvider::class) parameter: OdsImageListPreviewParameter) =
    Preview {
        OdsImageItem(
            image = painterResource(id = parameter.image),
            iconSelected = true,
            uncheckedIcon = painterResource(id = parameter.unCheckedIcon),
            checkedIcon = painterResource(id = parameter.checkedIcon),
            title = parameter.title,
            iconChecked = parameter.checked,
            modifier = Modifier.size(parameter.size),
            iconContentDescription = "",
            onIconCheckedChange = { parameter.checked }
        )
    }

private data class OdsImageListPreviewParameter(
    val image: Int,
    val checked: Boolean,
    val title: String?,
    val checkedIcon: Int,
    val unCheckedIcon: Int,
    val size: Dp
)

private class OdsImageListPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsImageListPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsImageListPreviewParameter>
    get() {
        val title = "Subtitle 1"
        val image = R.drawable.placeholder
        val checkedIcon = R.drawable.ic_check
        val unCheckedIcon = R.drawable.ic_check

        return listOf(
            OdsImageListPreviewParameter(image, title = title, checkedIcon = checkedIcon, unCheckedIcon = unCheckedIcon, checked = false, size = 150.dp),
            OdsImageListPreviewParameter(image, title = title, checkedIcon = checkedIcon, unCheckedIcon = unCheckedIcon, checked = false, size = 300.dp),
            OdsImageListPreviewParameter(image, title = title, checkedIcon = checkedIcon, unCheckedIcon = unCheckedIcon, checked = true, size = 400.dp)
        )
    }