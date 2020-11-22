package com.dm.photo_bank.ui.list

import androidx.lifecycle.*
import com.dm.photo_bank.core.Resource
import com.dm.photo_bank.data.UnsplashPhoto
import com.dm.photo_bank.domain.ListRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val repo: ListRepo): ViewModel() {

    private val _listLiveData = MutableLiveData<Resource<List<UnsplashPhoto>>>()
    val listLiveData: LiveData<Resource<List<UnsplashPhoto>>> = _listLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _listLiveData.postValue(Resource.Loading)
            _listLiveData.postValue(repo.getPhotos())
        }
    }

    fun searchPhoto(query: String) {
        if (query.isEmpty()) return
        viewModelScope.launch (Dispatchers.IO) {
            _listLiveData.postValue(Resource.Loading)
            _listLiveData.postValue(repo.searchPhotos(query))
        }
    }
}