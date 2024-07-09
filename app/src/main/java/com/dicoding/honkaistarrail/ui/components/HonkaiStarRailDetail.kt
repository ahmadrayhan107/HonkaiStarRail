package com.dicoding.honkaistarrail.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.honkaistarrail.R
import com.dicoding.honkaistarrail.ui.theme.HonkaiStarRailTheme

@Composable
fun HonkaiStarRailDetail(
    name: String,
    photoUrl: String,
    description: String,
    fraction: String,
    story: String,
    voiceActor: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(480.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Text(
            text = "\"$description\"",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.va, voiceActor),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = modifier
                    .weight(1f)
            )
            Text(
                text = stringResource(id = R.string.fraction, fraction),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                modifier = modifier
                    .weight(1f)
            )
        }
        Text(
            text = stringResource(id = R.string.story),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
        Text(
            text = story,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HonkaiStarRailDetailPreview() {
    HonkaiStarRailTheme {
        HonkaiStarRailDetail(
            name = "Kafka",
            photoUrl = "https://webstatic.hoyoverse.com/upload/op-public/2022/04/11/d9ab700ee0713acf78eb04d930f5c3fc_4622587084501383915.png",
            description = "You won't remember a thing except me.",
            fraction = "Stellaron Hunters",
            story = "On the Interastral Peace Corporation's wanted list, Kafka's entry only has two things — her name, and a single sentence: \"Likes collecting coats.\"\nLittle is known about this Stellaron Hunter, other than that she is one of Destiny's Slave Elio's most trusted members.\nIn order to achieve Elio's envisioned future, Kafka gets to work.",
            voiceActor = "Ito Shizuka",
            onBackClick = {},
        )
    }
}