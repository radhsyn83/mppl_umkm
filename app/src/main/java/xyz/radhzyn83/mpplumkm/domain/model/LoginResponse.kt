package xyz.radhzyn83.mpplumkm.domain.model

/**
 * Created by FathurRadhy on 1/30/2018.
 */
data class LoginResponse(
        val success: Boolean?,
        val result: Array<LoginResponseResult>
)

data class LoginResponseResult(
        val id: String,
        val nama: String,
        val email: String
)

