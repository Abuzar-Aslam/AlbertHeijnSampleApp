package com.example.albertheijnsampleapp

import com.example.albertheijnsampleapp.presentation.model.detail.BreedDetail
import com.example.albertheijnsampleapp.presentation.model.detail.BreedDetailItem
import com.example.albertheijnsampleapp.presentation.model.list.BreedItem
import com.example.albertheijnsampleapp.presentation.model.list.ImageItem


val mockBreedingListResult = listOf(
    BreedItem(
        id = "1",
        name = "Breed 1",
        temperament = "Cute, Fluffy",
        image = ImageItem(id = "11", width = 50, height = 50, url = "google.com/image")
    ),
    BreedItem(
        id = "2",
        name = "Breed 2",
        temperament = "Sweet, loyal, Quite",
        image = ImageItem(id = "12", width = 50, height = 50, url = "google.com/image")
    )
)


val expectedBreedDetailResult = BreedDetail(
    id = "1",
    url = "google.com",
    breedDetailItem = BreedDetailItem(
        id = "1",
        "dummy Name",
        temperament = "Kind",
        origin = "Italy",
        description = "Kind and loving",
        lifeSpan = "2-4 years"
    )
)