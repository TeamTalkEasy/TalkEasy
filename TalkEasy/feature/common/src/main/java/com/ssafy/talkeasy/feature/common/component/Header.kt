package com.ssafy.talkeasy.feature.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.talkeasy.feature.common.R
import com.ssafy.talkeasy.feature.common.ui.theme.typography

@Preview(showBackground = true)
@Composable
fun ProtectorHeader(
    modifier: Modifier = Modifier,
    title: String = "친구등록",
    onClickedBackButton: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        IconButton(onClick = { onClickedBackButton() }, modifier = modifier.padding(18.dp)) {
            Icon(
                modifier = modifier.size(23.dp),
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = stringResource(id = R.string.ic_arrow_back_text)
            )
        }
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp)
                .align(Alignment.Center),
            text = title,
            style = typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}