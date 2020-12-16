package jp.co.cybermissions.itspj.android.gourmetnavigation.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cybermissions.itspj.android.gourmetnavigation.network.Rest
import jp.co.cybermissions.itspj.android.gourmetnavigation.network.RestSearchAPI
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    // ステータス
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    //
    private val _rests = MutableLiveData<List<Rest>>()
    val rests: LiveData<List<Rest>>
        get() = _rests
    private val _navigateToSelectedRest = MutableLiveData<Rest>()
    val navigateToSelectedRest : LiveData<Rest>
        get() = _navigateToSelectedRest

    init {
        getRest()
    }

    private fun getRest() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _rests.value = RestSearchAPI.retrofitService.getRests("いちばん")
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _rests.value = ArrayList()
            }
        }
    }

    fun displayDetails(rest: Rest) {
        _navigateToSelectedRest.value = rest
    }
    fun displayDetailsComplete() {
        _navigateToSelectedRest.value = null
    }
}

/** ステータス*/
enum class ApiStatus { LOADING, ERROR, DONE }