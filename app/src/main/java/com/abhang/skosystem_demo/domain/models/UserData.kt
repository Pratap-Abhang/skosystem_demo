package com.abhang.skosystem_demo.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abhang.skosystem_demo.utils.Constants

@Entity(tableName = Constants.DB_TABLE_NAME)
data class UserData(
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String
) {

    @PrimaryKey(true)
    var pid : Long = 0

    var name : String = "$firstName $lastName"

    override fun equals(other: Any?): Boolean {
        val obj: UserData = other as UserData
        return userId == obj.userId &&
                firstName == obj.firstName &&
                lastName == obj.lastName &&
                email == obj.email &&
                avatar == obj.avatar
    }

    override fun hashCode(): Int {
        var result = userId
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + avatar.hashCode()
        result = 31 * result + pid.hashCode()
        return result
    }
}