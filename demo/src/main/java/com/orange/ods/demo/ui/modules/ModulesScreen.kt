/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ods.compose.theme.OdsMaterialTheme

@Composable
fun ModulesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Modules",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ModulesScreenPreview() {
    OdsMaterialTheme {
        ModulesScreen()
    }
}