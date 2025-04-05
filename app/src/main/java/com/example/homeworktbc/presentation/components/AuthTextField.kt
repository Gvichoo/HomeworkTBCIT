package com.example.homeworktbc.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homeworktbc.R
import com.example.homeworktbc.domain.core.AuthFieldErrorType
import com.example.homeworktbc.presentation.fragmentRegister.mapToStringResource
import com.example.homeworktbc.presentation.theme.UserAppTheme
import com.example.homeworktbc.presentation.theme.colors.PlaceHolderColor
import com.example.homeworktbc.presentation.theme.dimens.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onTrailingIconClick: () -> Unit = {},
    errorResource: Int? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            isError = errorResource != null,
            placeholder = {
                Text(
                    text = label,
                    color = PlaceHolderColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
            ),
            visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon, contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = Dimens.SpacingLarge)
                        .size(height = 31.dp, width = 29.25.dp)
                )
            },
            trailingIcon = {
                if (isPassword) {
                    IconButton(
                        onClick = { onTrailingIconClick() },
                        modifier = Modifier.padding(horizontal = Dimens.SpacingLarge)
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isPasswordVisible) R.drawable.iconeye else R.drawable.iconblock
                            ),
                            contentDescription = "Toggle Password Visibility",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(height = 31.dp, width = 30.dp)
                        )
                    }
                }
            },
            colors = textFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
        )

        errorResource?.let {
            Text(
                text = stringResource(errorResource),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(
                    start = Dimens.SpacingMedium,
                    top = Dimens.SpacingSmall
                )
            )
        }
    }
}

@Preview
@Composable
fun AuthTextFieldEmailPreview() {
    UserAppTheme {
        AuthTextField(
            value = "jemali@gmail.com",
            onValueChange = {},
            label = "Email",
            leadingIcon = Icons.Default.AccountCircle,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthTextFieldPasswordPreview() {
    UserAppTheme  {
        AuthTextField(
            value = "hdsajkh",
            isPassword = true,
            onValueChange = {},
            label = "Email",
            leadingIcon = Icons.Default.AccountCircle,
            errorResource = AuthFieldErrorType.EMPTY.mapToStringResource()
        )
    }
}