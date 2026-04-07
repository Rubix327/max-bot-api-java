package ru.max.botapi.model;

import java.util.List;

/**
 * AddedMemberList
 * @param success success
 * @param message message
 * @param failedUserIds failedUserIds
 * @param failedUserDetails failedUserDetails
 */
public record AddedMemberList (
        boolean success,
        @Nullable String message,
        List<Integer> failedUserIds,
        List<FailedUserDetails> failedUserDetails
) {
}
