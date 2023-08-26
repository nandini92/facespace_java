package com.facespace.facespace;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private ArrayList<Integer> friends;
    private String avatarUrl;

    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", friends=" + friends + ", avatarUrl=" + avatarUrl + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((friends == null) ? 0 : friends.hashCode());
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
        if (friends == null) {
            if (other.friends != null)
                return false;
        } else if (!friends.equals(other.friends))
            return false;
        if (avatarUrl == null) {
            if (other.avatarUrl != null)
                return false;
        } else if (!avatarUrl.equals(other.avatarUrl))
            return false;
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriends(ArrayList<Integer> friends) {
        this.friends = friends;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getFriends() {
        return friends;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public User(int id, String name, ArrayList<Integer> friends, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.friends = friends;
        this.avatarUrl = avatarUrl;
    }

    public User(){
    };

    public void addFriend(int friend) {
        friends.add(friend);
    }
}
