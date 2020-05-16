import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("autoCompleteRequestString") val autoCompleteRequestString: String,
        @SerializedName("addressList") val addressList: List<AddressList>
)