package com.example.tornado.Model;

import java.util.ArrayList;

public class NameTag {
    private String mName;
    private int id;

    public NameTag(String mName, int id) {
        this.mName = mName;
        this.id = id;
    }

    public NameTag(String mName) {
        this.mName = mName;
    }

    public String getmName() {
        return mName;
    }

    public int getId() {
        return id;
    }

    private static int lastNameId = 0;


    public static ArrayList<NameTag> createNamesList(int numNames){
        ArrayList<NameTag> names = new ArrayList<>();

        for(int i = 1; i <= numNames; i++){
            names.add(new NameTag("Pierre " + ++i, i ));
            lastNameId++;
        }
        return names;
    }
}
