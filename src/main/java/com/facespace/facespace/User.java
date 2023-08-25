package com.facespace.facespace;

import java.util.Arrays;

public class User {
    private int id;
    private String name;
    private int[] friends;
    private String avatarUrl;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Arrays.hashCode(friends);
        result = prime * result + ((avatarUrl == null) ? 0 : avatarUrl.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (!Arrays.equals(friends, other.friends))
            return false;
        if (avatarUrl == null) {
            if (other.avatarUrl != null)
                return false;
        } else if (!avatarUrl.equals(other.avatarUrl))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", friends=" + Arrays.toString(friends) + ", avatarUrl="
                + avatarUrl + "}";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int[] getFriends() {
        return friends;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public User(int id, String name, int[] friends, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.friends = friends;
        this.avatarUrl = avatarUrl;
    }

    public User(){

    };
}
