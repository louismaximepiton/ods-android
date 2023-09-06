/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.content

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale

/**
 * An image in a component.
 */
abstract class OdsComponentImage(
    private val graphicsObject: Any,
    private val contentDescription: String,
    private val contentScale: ContentScale = ContentScale.Fit
) : OdsComponentContent() {

    protected constructor(
        painter: Painter,
        contentDescription: String,
        contentScale: ContentScale = ContentScale.Fit
    ) : this(painter as Any, contentDescription, contentScale)

    protected constructor(
        imageVector: ImageVector,
        contentDescription: String,
        contentScale: ContentScale = ContentScale.Fit
    ) : this(imageVector as Any, contentDescription, contentScale)

    protected constructor(
        bitmap: ImageBitmap,
        contentDescription: String,
        contentScale: ContentScale = ContentScale.Fit
    ) : this(bitmap as Any, contentDescription, contentScale)

    @Composable
    override fun Content(modifier: Modifier) {
        when (graphicsObject) {
            is Painter -> Image(painter = graphicsObject, contentDescription = contentDescription, modifier = modifier, contentScale = contentScale)
            is ImageVector -> Image(imageVector = graphicsObject, contentDescription = contentDescription, modifier = modifier, contentScale = contentScale)
            is ImageBitmap -> Image(bitmap = graphicsObject, contentDescription = contentDescription, modifier = modifier, contentScale = contentScale)
            else -> {}
        }
    }
}