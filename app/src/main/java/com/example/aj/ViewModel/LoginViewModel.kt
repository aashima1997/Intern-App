package com.example.aj.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.aj.DataRepository.RoomDB.Attendees
import com.example.aj.DataRepository.RoomDB.User
import com.example.aj.DataRepository.RoomDB.UserDatabase
import com.example.aj.DataRepository.RoomDB.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel (application: Application): AndroidViewModel(application) {
     val repository: UserRepository
    var readAllData1: LiveData<List<Attendees>>
    init {
        val userDao = UserDatabase.getDatabase(application)?.userDao()
        repository = UserRepository(userDao!!)
        readAllData1=repository.readAllData1
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)

        }
    }

    fun addUser1(user1: Attendees) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser1(user1)

        }
    }

    fun getUsername(userName: String): User {
      return repository.getUsername(userName)
    }

}
