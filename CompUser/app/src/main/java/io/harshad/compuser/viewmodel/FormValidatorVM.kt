package io.harshad.compuser.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FormValidatorVM: ViewModel() {
    val name = MutableLiveData<String>()
    val age = MutableLiveData<String>()
    val dob = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val validationMessage = MutableLiveData<String>()

    fun validateForm(): Boolean {
        if (name.value.isNullOrEmpty()) {
            validationMessage.value = "Name cannot be empty"
            return false
        }
        if (age.value.isNullOrEmpty()) {
            validationMessage.value = "Age cannot be empty"
            return false
        }
        val ageInt = age.value?.toIntOrNull()
        if (ageInt == null || ageInt <= 0) {
            validationMessage.value = "Age must be a positive number"
            return false
        }
        if (dob.value.isNullOrEmpty()) {
            validationMessage.value = "DOB cannot be empty"
            return false
        }
        if (address.value.isNullOrEmpty()) {
            validationMessage.value = "Address cannot be empty"
            return false
        }
        return true
    }

}