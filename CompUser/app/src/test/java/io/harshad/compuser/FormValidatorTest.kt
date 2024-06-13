package io.harshad.compuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.harshad.compuser.viewmodel.FormValidatorVM
import junit.framework.TestCase.assertFalse
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class FormValidatorTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = FormValidatorVM()
    private val observer: Observer<String> = mock(Observer::class.java) as Observer<String>

    @Test
    fun validateFormName() {
        viewModel.validationMessage.observeForever(observer)
        viewModel.name.value = ""
        viewModel.age.value = "25"
        viewModel.dob.value = "13-Jun-2001"
        viewModel.address.value = "123 Main St"

        assertFalse(viewModel.validateForm())
        verify(observer).onChanged("Name cannot be empty")
    }

    @Test
    fun validateFormAge() {
        viewModel.validationMessage.observeForever(observer)
        viewModel.name.value = "Harshad"
        viewModel.age.value = ""
        viewModel.dob.value = "13-Jun-2001"
        viewModel.address.value = "123 Main St"

        assertFalse(viewModel.validateForm())
        verify(observer).onChanged("Name cannot be empty")
    }


    @Test
    fun validateFormZeroAge() {
        viewModel.validationMessage.observeForever(observer)
        viewModel.name.value = "Harshad"
        viewModel.age.value = "0"
        viewModel.dob.value = "13-Jun-2001"
        viewModel.address.value = "123 Main St"

        assertFalse(viewModel.validateForm())
        verify(observer).onChanged("Name cannot be empty")
    }

    @Test
    fun validateFormDOB() {
        viewModel.validationMessage.observeForever(observer)
        viewModel.name.value = "Harshad"
        viewModel.age.value = "24"
        viewModel.dob.value = ""
        viewModel.address.value = "123 Main St"

        assertFalse(viewModel.validateForm())
        verify(observer).onChanged("Name cannot be empty")
    }


    @Test
    fun validateFormAdr() {
        viewModel.validationMessage.observeForever(observer)
        viewModel.name.value = "Harshad"
        viewModel.age.value = "24"
        viewModel.dob.value = "13-jun-1999"
        viewModel.address.value = ""

        assertFalse(viewModel.validateForm())
        verify(observer).onChanged("Name cannot be empty")
    }

    @Test
    fun validateForm() {
        viewModel.validationMessage.observeForever(observer)
        viewModel.name.value = "Harshad"
        viewModel.age.value = "24"
        viewModel.dob.value = "13-jun-1999"
        viewModel.address.value = "nashik MH"

        assertFalse(viewModel.validateForm())
        verify(observer).onChanged("Name cannot be empty")
    }

}