package com.abhang.skosystem_demo.domain.usecase

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abhang.skosystem_demo.domain.repository.FakeUserDataLocalRepository
import com.abhang.skosystem_demo.domain.repository.FakeUserDataRepository
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserDataUseCaseTest : TestCase() {

    private lateinit var userDataUseCase:UserDataUseCase
    private lateinit var fakeUserDataRepo:FakeUserDataRepository
    private lateinit var fakeUserDataLocalRepo:FakeUserDataLocalRepository



    @Before
    public override fun setUp() {
        super.setUp()
        fakeUserDataRepo = FakeUserDataRepository()
        fakeUserDataLocalRepo = FakeUserDataLocalRepository()
        userDataUseCase = UserDataUseCase(fakeUserDataRepo, fakeUserDataLocalRepo)

    }

    @After
    public override fun tearDown() {
        super.tearDown()

    }
}