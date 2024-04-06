package com.abhang.skosystem_demo.presentation.user_data_view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abhang.skosystem_demo.domain.repository.FakeUserDataLocalRepository
import com.abhang.skosystem_demo.domain.repository.FakeUserDataRepository
import com.abhang.skosystem_demo.domain.usecase.UserDataUseCase
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserViewModelTest : TestCase(){

    private lateinit var viewModel: UserViewModel
    private lateinit var fakeUserDataRepo: FakeUserDataRepository
    private lateinit var fakeUserDataLocalRepo: FakeUserDataLocalRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()
        fakeUserDataRepo = FakeUserDataRepository()
        fakeUserDataLocalRepo = FakeUserDataLocalRepository()

        val useCase = UserDataUseCase(fakeUserDataRepo, fakeUserDataLocalRepo)
        viewModel = UserViewModel(useCase)
    }

    @Test
    fun getUserData(){
        viewModel.getUSerData()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.userValue.collectLatest {
                assertThat(it.data?.isNotEmpty()).isTrue()
            }
        }
    }


    @After
    public override fun tearDown() {
        super.tearDown()

    }
}