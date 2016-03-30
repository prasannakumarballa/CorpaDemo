package com.pessy.corpa;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {

    public static void main(String[] args) throws Exception
    {
        Schema schema = new Schema(2,"com.example.pessy.corpacabs.db");

        Entity user = schema.addEntity("User");
        user.addIdProperty().autoincrement();
        user.addStringProperty("name");
        user.addStringProperty("email");
        user.addStringProperty("mobile");
        user.addStringProperty("password");

        new DaoGenerator().generateAll(schema,"./app/src/main/java");
    }
}
