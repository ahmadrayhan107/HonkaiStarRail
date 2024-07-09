package com.dicoding.honkaistarrail.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.honkaistarrail.R
import com.dicoding.honkaistarrail.ui.theme.HonkaiStarRailTheme

@Composable
fun About(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .size(36.dp)
                    .clickable { onBackClick() })
        }
        Image(
            painter = painterResource(
                id = R.drawable.img_about
            ),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(top = 8.dp)
                .clip(CircleShape)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.my_name),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.my_email),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    HonkaiStarRailTheme {
        About(onBackClick = {})
    }
}