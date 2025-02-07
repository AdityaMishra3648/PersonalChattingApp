package com.example.MessegingApp.Entity;

import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
    @Id
    private ObjectId id;


    @NonNull
    private String userName;
    @NonNull
    private String password;

    public User() {
    }
    public User(ObjectId id, @NonNull String userName, @NonNull String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }


}
