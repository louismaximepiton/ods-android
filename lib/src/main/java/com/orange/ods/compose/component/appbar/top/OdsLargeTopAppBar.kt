/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.appbar.top

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@OdsComposable
fun OdsLargeTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: OdsTopAppBarNavigationIcon? = null,
    actions: List<OdsTopAppBarActionButton> = emptyList(),
    overflowMenuActions: List<OdsTopAppBarOverflowMenuActionItem> = emptyList(),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    OdsLargeTopAppBarInternal(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        overflowMenuActions = overflowMenuActions,
        scrollBehavior = scrollBehavior
    )
}

// TODO: Remove this method once OdsSearchTopAppBar is developed
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@OdsComposable
fun OdsLargeTopAppBarInternal(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: OdsTopAppBarNavigationIcon? = null,
    actions: List<OdsComponentContent> = emptyList(),
    overflowMenuActions: List<OdsTopAppBarOverflowMenuActionItem> = emptyList(),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val contentColor = OdsTheme.colors.component.topAppBar.barContent
    val expandedTitleStartPadding = 48.dp
    val collapsedTitleStartPadding = 8.dp
    val expandedTitleAlpha = 1f
    val expandedTitleMaxLines = 2
    val collapsedTitleMaxLines = 1
    val stateChangeFraction = 0.7

    val titleStartPadding by remember(scrollBehavior) {
        derivedStateOf {
            if (scrollBehavior != null && scrollBehavior.state.collapsedFraction >= 0.85) collapsedTitleStartPadding else expandedTitleStartPadding
        }
    }

    val titleAlpha by remember(scrollBehavior) {
        derivedStateOf {
            if (scrollBehavior != null) {
                when (scrollBehavior.state.collapsedFraction) {
                    in 0.0..stateChangeFraction -> 1 - (scrollBehavior.state.collapsedFraction * (1f / stateChangeFraction))
                    in (stateChangeFraction + 0.15)..1.0 -> 0 + scrollBehavior.state.collapsedFraction
                    else -> 0
                }.toFloat()
            } else {
                expandedTitleAlpha
            }
        }
    }
    val titleMaxLines by remember(scrollBehavior) {
        derivedStateOf {
            if (scrollBehavior != null && scrollBehavior.state.collapsedFraction >= stateChangeFraction) collapsedTitleMaxLines else expandedTitleMaxLines
        }
    }

    LargeTopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .padding(
                        start = titleStartPadding,
                        end = dimensionResource(id = R.dimen.spacing_m)
                    )
                    .alpha(titleAlpha),
                text = title,
                style = OdsTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = titleMaxLines,
            )
        },
        modifier = modifier,
        navigationIcon = { navigationIcon?.Content() },
        actions = { OdsTopAppBarActions(actions = actions, overflowMenuActions = overflowMenuActions) },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = OdsTheme.colors.component.topAppBar.barBackground,
            navigationIconContentColor = contentColor,
            titleContentColor = contentColor,
            actionIconContentColor = contentColor
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@UiModePreviews.Default
@Composable
private fun PreviewOdsLargeTopAppBar(@PreviewParameter(OdsLargeTopAppBarPreviewParameterProvider::class) parameter: OdsLargeTopAppBarPreviewParameter) =
    Preview {
        val actions = listOf(OdsTopAppBarActionButton(painterResource(id = android.R.drawable.ic_dialog_info), "Info") {})
        val overflowMenuActions = listOf(
            OdsTopAppBarOverflowMenuActionItem("Settings") { },
            OdsTopAppBarOverflowMenuActionItem("Account") { }
        )
        OdsLargeTopAppBar(
            title = parameter.title,
            navigationIcon = parameter.navigationIcon,
            actions = actions,
            overflowMenuActions = overflowMenuActions
        )
    }

internal data class OdsLargeTopAppBarPreviewParameter(
    val title: String,
    val navigationIcon: OdsTopAppBarNavigationIcon?
)

private class OdsLargeTopAppBarPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsLargeTopAppBarPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsLargeTopAppBarPreviewParameter>
    get() {
        val navigationIcon = OdsTopAppBarNavigationIcon(Icons.Filled.ArrowBack, "") {}
        return listOf(
            OdsLargeTopAppBarPreviewParameter("One line title", navigationIcon),
            OdsLargeTopAppBarPreviewParameter("Two lines title is allowed in large top app bar", navigationIcon),
            OdsLargeTopAppBarPreviewParameter("The title will be truncated if it is too long to fit in the large top app bar like this one", navigationIcon),
            OdsLargeTopAppBarPreviewParameter("One line title", null)
        )
    }