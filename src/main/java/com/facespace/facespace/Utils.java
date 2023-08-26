package com.facespace.facespace;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    private ArrayList<User> users = new ArrayList<User>();

    public Utils() {
    }

    public void loadCustomersFromJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClassPathResource resource = new ClassPathResource("/static/users.json");
            InputStream inputStream = resource.getInputStream();

            this.users = objectMapper.readValue(inputStream, new TypeReference<ArrayList<User>>(){});

            System.out.println(this.users);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }

    public User findUser(int id){
        for(User user : this.users){
            if(user.getId() == id){
                return user;
            }
        }

        // How to properly handle case where User does not exist?
        return new User();
    }

    public int findUserIndex(int id){
        for(User user : this.users){
            if(user.getId() == id){
                return id;
            }
        }

        // How to properly handle case where User id does not exist?
        return 0;
    }

    public User findUserName(String name){
        System.out.println(name);
        for(User user : this.users){
            if(user.getName().equals(name)){
                return user;
            }
        }

        // How to properly handle case where User name does not exist?
        return new User();
    }

    public boolean deleteUser(int id){
        for(User user : this.users){
            if(user.getId() == id){
                users.remove(user);
                return true;
            }
        }

        return false;
    }

    public boolean findFriend(ArrayList<Integer> friends, int newFriend){
        for(int friend : friends){
            if(friend == newFriend){
                return true;
            }
        }

        return false;
    }
}
