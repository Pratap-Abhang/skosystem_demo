package com.abhang.skosystem_demo.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abhang.skosystem_demo.data.local.dao.UserDao
import com.abhang.skosystem_demo.domain.models.UserData
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDatabaseTest: TestCase() {

    private lateinit var db: UserDatabase
    private lateinit var dao: UserDao


    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java).build()
        dao = db.getUserDao()
    }

    @Test
    fun writeAndReadUser() = runBlocking {
        val user = UserData(
            userId= 1,
                    firstName= "Pratap",
                    lastName= "Abhang",
                    email= "pratapabhangg1@gmail.com",
                    avatar= "demo",
        )
        dao.addUser(user)
        val userData = dao.getUserCount(user.userId)
        assertThat(userData!=0).isTrue()
    }

    @Test
    fun writeAndReadDiffUser() = runBlocking {
        val user = UserData(
            userId= 1,
            firstName= "Pratap",
            lastName= "Abhang",
            email= "pratapabhangg1@gmail.com",
            avatar= "demo",
        )
        dao.addUser(user)
        val userData = dao.getUserCount(user.userId + 1)
        assertThat(userData!=0).isFalse()
    }

    @Test
    fun writeAndReadMultipleUser() = runBlocking {
        val mList = ArrayList<UserData>()
        for (i in 0..5){
            val user = UserData(
                userId= i+1,
                firstName= "Pratap $i",
                lastName= "Abhang $i",
                email= "pratapabhangg1@gmail.com $i",
                avatar= "demo $i",
            )
            mList.add(user)
        }
        dao.addUsers(mList)
        val userData = dao.getData(10,0)
        assertThat(userData.count() == mList.size).isTrue()
    }

    @After
    public override fun tearDown() {
        super.tearDown()
    }
}