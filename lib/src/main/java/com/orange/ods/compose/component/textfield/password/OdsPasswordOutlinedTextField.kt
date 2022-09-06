/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.textfield.password

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ods.compose.component.textfield.odsOutlinedTextFieldColors
import com.orange.ods.compose.component.utilities.Preview

/**
 * Outlined password text fields allows to display and use a text field with common password behaviors like a visualisation icon.
 *
 * If you are looking for a filled version, see [OdsPasswordTextField].
 *
 * @param value the input text to be shown in the text field
 * @param onValueChange the callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback
 * @param modifier a [Modifier] for this text field
 * @param enabled controls the enabled state of the [TextField]. When `false`, the text field will
 * be neither editable nor focusable, the input of the text field will not be selectable,
 * visually text field will appear in the disabled UI state
 * @param readOnly controls the editable state of the [TextField]. When `true`, the text
 * field can not be modified, however, a user can focus it and copy text from it. Read-only text
 * fields are usually used to display pre-filled forms that user can not edit
 * @param label the optional label to be displayed inside the text field container. The default
 * text style for internal [Text] is [Typography.caption] when the text field is in focus and
 * [Typography.subtitle1] when the text field is not in focus
 * @param placeholder the optional placeholder to be displayed when the text field is in focus and
 * the input text is empty. The default text style for internal [Text] is [Typography.subtitle1]
 * @param visualisationIcon If `true`, an eye icon will be display to allow showing/hiding password.
 * @param isError indicates if the text field's current value is in error state. If set to
 * true, the label, bottom indicator and trailing icon by default will be displayed in error color
 * @param keyboardOptions software keyboard options that contains configuration such as
 * [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback
 * is called. Note that this IME action may be different from what you specified in
 * [KeyboardOptions.imeAction].
 */
@Composable
fun OdsPasswordOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    visualisationIcon: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    val odsPasswordTextFieldState = rememberOdsPasswordTextFieldState()

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.subtitle1,
        label = label?.let { { Text(label) } },
        placeholder = placeholder?.let { { Text(text = placeholder, style = MaterialTheme.typography.subtitle1) } },
        trailingIcon = if (visualisationIcon) {
            { OdsPasswordVisualisationIcon(odsPasswordTextFieldState, enabled) }
        } else null,
        isError = isError,
        visualTransformation = odsPasswordTextFieldState.visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        colors = odsOutlinedTextFieldColors()
    )

}

@Composable
private fun PreviewOdsPasswordOutlinedTextField() = Preview {
    var text by remember { mutableStateOf("Input text") }
    OdsPasswordOutlinedTextField(
        value = text,
        onValueChange = { text = it },
        placeholder = "Placeholder"
    )
}

@Preview(name = "OdsPasswordOutlinedTextField - Light")
@Composable
private fun PreviewOdsPasswordOutlinedTextFieldLight() = PreviewOdsPasswordOutlinedTextField()

@Preview(
    name = "OdsPasswordOutlinedTextField - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun PreviewOdsPasswordOutlinedTextFieldDark() = PreviewOdsPasswordOutlinedTextField()