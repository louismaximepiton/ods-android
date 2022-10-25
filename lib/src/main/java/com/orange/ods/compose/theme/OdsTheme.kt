/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Typography
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ods.theme.OdsColors
import com.orange.ods.theme.OdsThemeConfigurationContract

private val LocalTypography = staticCompositionLocalOf { Typography() }

private val LocalColors = staticCompositionLocalOf<OdsColors> { error("CompositionLocal LocalColors not present") }
private val LocalLightThemeColors = compositionLocalOf<OdsColors> { error("CompositionLocal LocalLightThemeColors not present") }
private val LocalDarkThemeColors = compositionLocalOf<OdsColors> { error("CompositionLocal LocalDarkThemeColors not present") }

object OdsTheme {

    val colors: OdsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val lightThemeColors: OdsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalLightThemeColors.current

    val darkThemeColors: OdsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDarkThemeColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

/**
 * ODS theme is the theme to apply to your screens in an Orange Jetpack Compose application.
 *
 * @param themeConfiguration The configuration of the OdsTheme: colors, typography...
 * @param darkThemeEnabled Indicates whether the dark theme is enabled or not
 * @param content The content of the theme
 */
@Composable
fun OdsTheme(
    themeConfiguration: OdsThemeConfigurationContract,
    darkThemeEnabled: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkThemeEnabled) themeConfiguration.darkThemeColors else themeConfiguration.lightThemeColors
    // creating a new object for colors to not mutate the initial colors set when updating the values
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        LocalRippleTheme provides OdsRippleTheme,
        LocalColors provides rememberedColors,
        LocalLightThemeColors provides themeConfiguration.lightThemeColors,
        LocalDarkThemeColors provides themeConfiguration.darkThemeColors,
        LocalTypography provides themeConfiguration.typography,
        content = content
    )
}

