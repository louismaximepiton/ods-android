/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.innovationcup

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.ThemeColors
import kotlinx.parcelize.Parcelize

@Parcelize
class InnovationCupThemeConfiguration : OdsThemeConfigurationContract {

    override val name: String
        get() = "Innovation Cup"

    override val colors: ThemeColors
        get() = ThemeColors(InnovationCupLightColors, InnovationCupDarkColors)

    override val shapes: Shapes
        get() = Shapes().copy(
            small = RoundedCornerShape(8.dp),
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(4.dp)
        )
}