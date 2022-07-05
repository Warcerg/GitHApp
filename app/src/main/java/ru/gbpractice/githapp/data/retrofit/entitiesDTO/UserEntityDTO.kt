package ru.gbpractice.githapp.data.retrofit.entitiesDTO

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ru.gbpractice.githapp.domain.entities.UserEntity

@Parcelize
data class UserEntityDTO(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
) : Parcelable
{
    fun toUserEntity() = UserEntity(login, id, avatarUrl)

    companion object{
        fun fromUserEntity(userEntity: UserEntity): UserEntityDTO {
            return UserEntityDTO(
                userEntity.login,
                userEntity.id,
                userEntity.avatarUrl
            )
        }
    }
}

