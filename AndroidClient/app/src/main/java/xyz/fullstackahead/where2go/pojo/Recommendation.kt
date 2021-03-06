package xyz.fullstackahead.where2go.pojo

import com.google.gson.annotations.SerializedName

data class Recommendation(
        val id: String = "",
        @SerializedName("rating")
        var userRating: Float = 0F,
        val price: String? = null,
        val name: String = "",
        @SerializedName("image_url")
        val imageUrl: String? = null,
        val categories: List<String> = emptyList(),
        @SerializedName("predicted_rating")
        val predictedRating: Float? = null
)
