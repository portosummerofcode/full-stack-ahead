package xyz.fullstackahead.where2go.ui.viewmodel

import ai.api.model.AIError
import ai.api.model.AIResponse
import ai.api.ui.AIButton
import ai.api.ui.AIDialog
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import xyz.fullstackahead.where2go.R
import xyz.fullstackahead.where2go.Recommendation
import xyz.fullstackahead.where2go.Where2GoApp
import java.util.*

class LandingViewModel(application: Application?) : AndroidViewModel(application), AIDialog.AIDialogListener {

    companion object {
        const val TAG = "LandingViewModel"
    }

    val apiResponse: MutableLiveData<String> = MutableLiveData()
    val recommendations: MutableLiveData<List<Recommendation>> = MutableLiveData()

    private var googleApiClient: GoogleApiClient? = null

    fun init(context: Context) {
        if (googleApiClient == null) {
            googleApiClient = GoogleApiClient.Builder(context).addApi(LocationServices.API).build()
            googleApiClient?.connect()
        }
    }


    fun getRecommendations() {
        // TODO network call
        val list = ArrayList<Recommendation>()
        (1..50).mapTo(list) {
            Recommendation(
                    getApplication<Where2GoApp>().getString(R.string.placeholder_title, it),
                    getApplication<Where2GoApp>().getString(R.string.placeholder_description),
                    "",
                    Random().nextInt(5))
        }
        recommendations.postValue(list)
    }


    fun getLocation(): Location? {
        return try {
            LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
        } catch (ex: SecurityException) {
            Log.d(TAG, ex.message)
            null
        }
    }


    // AIButtonListener Callbacks
    override fun onCancelled() {
        Log.d(TAG, "API.AI - onCancelled")
    }

    override fun onResult(result: AIResponse?) {
        Log.d(TAG, "API.AI - onResult")
        result?.result?.fulfillment?.speech?.let { apiResponse.postValue(it) }
    }

    override fun onError(error: AIError?) {
        Log.d(TAG, "API.AI - onResult")
    }
}