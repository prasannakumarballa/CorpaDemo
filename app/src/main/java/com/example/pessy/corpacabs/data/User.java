package com.example.pessy.corpacabs.data;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.annotation.UniqueGroup;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by pessy on 3/26/2016.
 */


@Table(database = CorpaDb.class,
        uniqueColumnGroups = {@UniqueGroup(groupNumber = 1, uniqueConflict = ConflictAction.FAIL),
                @UniqueGroup(groupNumber = 2, uniqueConflict = ConflictAction.ROLLBACK)})
public class User extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int id;

    @Unique(unique = false, uniqueGroups = 1)
    private String email;

    @Unique(unique = false, uniqueGroups = 1)
    private String mobile;

    @Column
    private String name;

    @Column
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
