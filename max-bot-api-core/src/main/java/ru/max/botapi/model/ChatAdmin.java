package ru.max.botapi.model;

import java.util.List;

/**
 * Represents a chat admin.
 *
 * @param userId The user ID of a member of a group chat or channel, which is being promoted to the administrator.
 * @param permissions A list of user or bot access rights assigned by the administrator of a group chat or channel.
 * @param alias Role description that will be displayed on the client device in the chat or channel settings next
 *              to the user's name.
 */
public record ChatAdmin(

        Long userId,
        List<ChatPermission> permissions,
        @Nullable String alias

) {

    /**
     * Create ChatAdmin with alias.
     * @param userId The user ID of a member of a group chat or channel, which is being promoted to the administrator.
     * @param permissions A list of user or bot access rights assigned by the administrator of a group chat or channel.
     * @param alias Role description that will be displayed on the client device in the chat or channel settings next
     *              to the user's name.
     */
    public ChatAdmin(Long userId, List<ChatPermission> permissions, @Nullable String alias) {
        this.userId = userId;
        this.permissions = permissions;
        this.alias = alias;
    }

    /**
     * Create ChatAdmin.
     * @param userId The user ID of a member of a group chat or channel, which is being promoted to the administrator.
     * @param permissions A list of user or bot access rights assigned by the administrator of a group chat or channel.
     */
    public ChatAdmin(Long userId, List<ChatPermission> permissions) {
        this(userId, permissions, null);
    }

}
