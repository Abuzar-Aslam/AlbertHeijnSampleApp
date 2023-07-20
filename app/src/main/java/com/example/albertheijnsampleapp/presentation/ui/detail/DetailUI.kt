package com.example.albertheijnsampleapp.presentation.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.albertheijnsampleapp.R
import com.example.albertheijnsampleapp.presentation.model.detail.BreedDetail

/**
 * Composable function that displays the details of a breed.
 *
 * @param breedDetail The [BreedDetail] object containing the details of the breed to be displayed.
 */
@Composable
fun DetailUI(breedDetail: BreedDetail) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Image Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .shadow(elevation = 8.dp),
        ) {

            // Load breed image using AsyncImage
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(breedDetail.url)
                    .build(),
                placeholder = painterResource(R.drawable.cat_placeholder),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // Add vertical scroll behavior
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Breed Details
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = breedDetail.breedDetailItem.name, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))

            tableRow("Temperament:", breedDetail.breedDetailItem.temperament)
            Spacer(modifier = Modifier.height(4.dp))
            tableRow("Origin:", breedDetail.breedDetailItem.origin)
            Spacer(modifier = Modifier.height(4.dp))
            tableRow("Life Span:", breedDetail.breedDetailItem.lifeSpan)

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = breedDetail.breedDetailItem.description,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}
