/*
 * Copyright (c) 2026 OpenAuth Authenticator
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.openauth.authenticator.core.database.mapper

import io.openauth.authenticator.core.database.entity.AccountEntity
import io.openauth.authenticator.core.model.Account
import javax.inject.Inject

class AccountEntityMapper @Inject constructor() {

    fun toDomain(entity: AccountEntity): Account {
        return Account(
            id = entity.id,
            issuer = entity.issuer,
            accountName = entity.accountName,
            encryptedSecret = entity.encryptedSecret,
            secretIv = entity.secretIv,
            type = entity.otpType,
            algorithm = entity.algorithm,
            digits = entity.digits,
            periodSeconds = entity.periodSeconds,
            counter = entity.counter,
            iconId = entity.iconId,
            isFavorite = entity.isFavorite,
            sortPosition = entity.sortPosition,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
            lastUsedAt = entity.lastUsedAt,
            usageCount = entity.usageCount,
            note = entity.note
        )
    }

    fun toEntity(model: Account): AccountEntity {
        return AccountEntity(
            id = model.id,
            issuer = model.issuer,
            accountName = model.accountName,
            encryptedSecret = model.encryptedSecret,
            secretIv = model.secretIv,
            otpType = model.type,
            algorithm = model.algorithm,
            digits = model.digits,
            periodSeconds = model.periodSeconds,
            counter = model.counter,
            iconId = model.iconId,
            isFavorite = model.isFavorite,
            sortPosition = model.sortPosition,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt,
            lastUsedAt = model.lastUsedAt,
            usageCount = model.usageCount,
            note = model.note
        )
    }
}