import com.google.gson.annotations.SerializedName

data class SearchData(
        @SerializedName("requestId") val requestId: String,
        @SerializedName("data") val data: Data
)