package ru.max.botapi.model;

import java.util.List;

/**
 * FailedUserDetails
 * @param errorCode errorCode
 * @param userIds userIds
 */
public record FailedUserDetails(
        String errorCode,
        List<Integer> userIds
) {
}
