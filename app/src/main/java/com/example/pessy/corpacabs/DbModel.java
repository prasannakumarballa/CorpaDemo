package com.example.pessy.corpacabs;

import android.net.Uri;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by pessy on 3/1/2016.
 */

@Table(database = InformationActivity.class, name = "information")
public class DbModel extends BaseModel {
    @Column
    @PrimaryKey
    int id;

    @Column
    String personName;
    String personEmail;
    String personId;
    Uri personPhoto;

    public void setName(String name) {
        this.personName = name;
    }
    public void setEmail(String email)
    {
        this.personEmail=email;
    }

}
