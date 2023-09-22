/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.card

import androidx.compose.foundation.background
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.component.content.OdsComponentCircleImage
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun OdsCard(modifier: Modifier, onClick: (() -> Unit)?, content: @Composable () -> Unit) {
    if (onClick != null) {
        Card(modifier = modifier, onClick = onClick, content = content)
    } else {
        Card(modifier = modifier.semantics(mergeDescendants = true) {}, content = content)
    }
}

/**
 * A button in an [OdsHorizontalCard], an [OdsVerticalHeaderFirstCard] or an [OdsVerticalImageFirstCard].
 *
 * @constructor Creates an instance of [OdsCardButton].
 * @param text Text of the button.
 * @param onClick Will be called when the user clicks the button.
 */
class OdsCardButton(private val text: String, private val onClick: () -> Unit) : OdsComponentContent<Nothing>() {

    @Composable
    override fun Content(modifier: Modifier) {
        OdsTextButton(text = text, onClick = onClick, modifier = modifier, style = OdsTextButtonStyle.Primary)
    }
}

/**
 * An image in an [OdsSmallCard], [OdsHorizontalCard], [OdsVerticalHeaderFirstCard] or [OdsVerticalImageFirstCard].
 */
class OdsCardImage private constructor(
    graphicsObject: Any,
    contentDescription: String,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    private val backgroundColor: Color? = null
) : OdsComponentImage<Nothing>(graphicsObject, contentDescription, alignment, contentScale) {

    /**
     * Creates an instance of [OdsCardImage].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsCardImage].
     * @param alignment Alignment parameter used to place the [Painter] in the given
     * bounds defined by the width and height.
     * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used
     * if the bounds are a different size from the intrinsic size of the [Painter]
     * @param backgroundColor Optional background color of the card image.
     */
    constructor(
        painter: Painter,
        contentDescription: String,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Crop,
        backgroundColor: Color? = null
    ) : this(painter as Any, contentDescription, alignment, contentScale, backgroundColor)

    /**
     * Creates an instance of [OdsCardImage].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsCardImage].
     * @param alignment Alignment parameter used to place the [ImageVector] in the given
     * bounds defined by the width and height.
     * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used
     * if the bounds are a different size from the intrinsic size of the [ImageVector]
     * @param backgroundColor Optional background color of the card image.
     */
    constructor(
        imageVector: ImageVector,
        contentDescription: String,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Crop,
        backgroundColor: Color? = null
    ) : this(imageVector as Any, contentDescription, alignment, contentScale, backgroundColor)

    /**
     * Creates an instance of [OdsCardImage].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsCardImage].
     * @param alignment Alignment parameter used to place the [ImageBitmap] in the given
     * bounds defined by the width and height.
     * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used
     * if the bounds are a different size from the intrinsic size of the [ImageBitmap]
     * @param backgroundColor Optional background color of the card image.
     */
    constructor(
        bitmap: ImageBitmap,
        contentDescription: String,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Crop,
        backgroundColor: Color? = null
    ) : this(bitmap as Any, contentDescription, alignment, contentScale, backgroundColor)

    @Composable
    override fun Content(modifier: Modifier) {
        super.Content(modifier = with(modifier) { if (backgroundColor != null) background(backgroundColor) else this })
    }
}

/**
 * A thumbnail in a card.
 */
class OdsCardThumbnail : OdsComponentCircleImage {

    /**
     * Creates an instance of [OdsCardThumbnail].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsCardThumbnail].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

    /**
     * Creates an instance of [OdsCardThumbnail].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsCardThumbnail].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

    /**
     * Creates an instance of [OdsCardThumbnail].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsCardThumbnail].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription)
}
