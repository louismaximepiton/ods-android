/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.GuidelinesNavigationItem

internal sealed class GuidelinesCardItem(
    @DrawableRes var image: Int,
    @StringRes var title: Int,
    var route: String
) {
    object Colour :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_colour,
            title = R.string.guideline_colour,
            route = GuidelinesNavigationItem.Color.route
        )
    object Typography :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_typography,
            title = R.string.guideline_typography,
            route = GuidelinesNavigationItem.Typography.route
        )
    object Imagery :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_imagery,
            title = R.string.guideline_imagery,
            route = GuidelinesNavigationItem.Imagery.route
        )
    object Iconography :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_iconography,
            title = R.string.guideline_iconography,
            route = GuidelinesNavigationItem.Iconography.route
        )
}