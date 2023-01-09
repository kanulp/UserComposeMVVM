package com.kanulp.userscompose.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanulp.userscompose.data.models.Data
import com.kanulp.userscompose.data.models.UserModel
import com.kanulp.userscompose.repository.UserRepository
import com.kanulp.userscompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _apiData = MutableLiveData<Resource<UserModel>>()
    //val userList = _apiData
    private val _state = MutableStateFlow(emptyList<Data>())
    val state:StateFlow<List<Data>>
    get() = _state

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {

          repository.fetchUser().collect {
              _apiData.value = it
              _state.value= it.data?.data ?: emptyList()
          }
//            sum.value = _apiData.value?.data?.map {
//                it.amount
//            }?.sum()?.toDouble()
        }
    }
}