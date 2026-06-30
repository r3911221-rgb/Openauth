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

package io.openauth.authenticator.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "issuer")
    val issuer: String?,

    @ColumnInfo(name = "account_name")
    val accountName: String,

    @ColumnInfo(name = "encrypted_secret")
    val encryptedSecret: ByteArray,

    @ColumnInfo(name = "secret_iv")
    val secretIv: ByteArray,

    @ColumnInfo(name = "otp_type")
    val otpType: String,

    @ColumnInfo(name = "algorithm")
    val algorithm: String,

    @ColumnInfo(name = "digits")
    val digits: Int,

    @ColumnInfo(name = "period_seconds")
    val periodSeconds: Long,

    @ColumnInfo(name = "counter")
    val counter: Long,

    @ColumnInfo(name = "icon_id")
    val iconId: String?,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean,

    @ColumnInfo(name = "sort_position")
    val sortPosition: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long,

    @ColumnInfo(name = "last_used_at")
    val lastUsedAt: Long?,

    @ColumnInfo(name = "usage_count")
    val usageCount: Long,

    @ColumnInfo(name = "note")
    val note: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AccountEntity) return false
        return id == other.id &&
               issuer == other.issuer &&
               accountName == other.accountName &&
               encryptedSecret.contentEquals(other.encryptedSecret) &&
               secretIv.contentEquals(other.secretIv)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + encryptedSecret.contentHashCode()
        result = 31 * result + secretIv.contentHashCode()
        return result
    }
}