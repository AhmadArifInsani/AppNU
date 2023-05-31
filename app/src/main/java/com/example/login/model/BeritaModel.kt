package com.example.login.model

import java.io.Serializable

data class BeritaModel(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val detail: String,
    val writer: String,
    val dateCreated: String,
) : Serializable
