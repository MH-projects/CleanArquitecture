package com.mh.mhapp.domain.usecase

import android.content.Context
import com.mh.mhapp.domain.repository.InfoRepository

class InfoUseCase {

    private val infoRepository = InfoRepository()

    suspend operator fun invoke(ctx: Context, user: String) = infoRepository.getInfoUser(ctx, user)

    suspend fun getLocalInfoUser(ctx: Context, user: String) =
        infoRepository.getLocalInfoUser(ctx, user)
}
