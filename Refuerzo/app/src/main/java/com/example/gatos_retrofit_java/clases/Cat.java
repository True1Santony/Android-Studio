package com.example.gatos_retrofit_java.clases;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Cat implements Parcelable   {
// formato json {
//    "length": "12 to 16 inches",
//    "origin": "Southeast Asia",
//    "image_link": "https://api-ninjas.com/images/cats/abyssinian.jpg",
//    "family_friendly": 3,
//    "shedding": 3,
//    "general_health": 2,
//    "playfulness": 5,
//    "children_friendly": 5,
//    "grooming": 3,
//    "intelligence": 5,
//    "other_pets_friendly": 5,
//    "min_weight": 6,
//    "max_weight": 10,
//    "min_life_expectancy": 9,
//    "max_life_expectancy": 15,
//    "name": "Abyssinian"
//  }
private String name;
private String image_link;
private String length;
private String origin;
private int family_friendly;
private int shedding;
private int general_health;
private int playfulness;
private int children_friendly;
private int grooming;
private int intelligence;
private int other_pets_friendly;
private double min_weight;
private double max_weight;
private int min_life_expectancy;
private int max_life_expectancy;

    public Cat() {
    }

    public Cat(String length, String origin, String imageLink, int familyFriendly, int shedding, int generalHealth,
               int playfulness, int childrenFriendly, int grooming, int intelligence, int otherPetsFriendly,
               int minWeight, int maxWeight, int minLifeExpectancy, int maxLifeExpectancy, String name) {
        this.length = length;
        this.origin = origin;
        this.image_link = imageLink;
        this.family_friendly = familyFriendly;
        this.shedding = shedding;
        this.general_health = generalHealth;
        this.playfulness = playfulness;
        this.children_friendly = childrenFriendly;
        this.grooming = grooming;
        this.intelligence = intelligence;
        this.other_pets_friendly = otherPetsFriendly;
        this.min_weight = minWeight;
        this.max_weight = maxWeight;
        this.min_life_expectancy = minLifeExpectancy;
        this.max_life_expectancy = maxLifeExpectancy;
        this.name = name;
    }
// getters y setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getFamily_friendly() {
        return family_friendly;
    }

    public void setFamily_friendly(int family_friendly) {
        this.family_friendly = family_friendly;
    }

    public int getShedding() {
        return shedding;
    }

    public void setShedding(int sheddingding) {
        this.shedding = shedding;
    }

    public int getGeneral_health() {
        return general_health;
    }
    public void setGeneral_health(int general_health) {
        this.general_health = general_health;
    }

    public int getPlayfulness() {
        return playfulness;
    }

    public void setPlayfulness(int playfulness) {
        this.playfulness = playfulness;
    }

    public int getChildren_friendly() {
        return children_friendly;
    }

    public void setChildren_friendly(int children_friendly) {
        this.children_friendly = children_friendly;
    }

    public int getGrooming() {
        return grooming;
    }

    public void setGrooming(int grooming) {
        this.grooming = grooming;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {

        this.intelligence = intelligence;

    }

    public int getOther_pets_friendly() {
        return other_pets_friendly;
    }

    public void setOther_pets_friendly(int other_pets_friendly) {
        this.other_pets_friendly = other_pets_friendly;
    }

    public double getMin_weight() {
        return min_weight;
    }

    public void setMin_weight(int min_weight) {
        this.min_weight = min_weight;
    }

    public double getMax_weight() {
        return max_weight;

    }

    public void setMax_weight(int max_weight) {
        this.max_weight = max_weight;
    }
    public int getMin_life_expectancy() {

        return min_life_expectancy;

    }

    public void setMin_life_expectancy(int min_life_expectancy) {
        this.min_life_expectancy = min_life_expectancy;

    }

    public int getMax_life_expectancy() {
        return max_life_expectancy;
    }

    public void setMax_life_expectancy(int max_life_expectancy) {
        this.max_life_expectancy = max_life_expectancy;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    protected Cat(Parcel in) {
        length = in.readString();
        origin = in.readString();
        image_link = in.readString();
        family_friendly = in.readInt();
        shedding = in.readInt();
        general_health = in.readInt();
        playfulness = in.readInt();
        children_friendly = in.readInt();
        grooming = in.readInt();
        intelligence = in.readInt();
        other_pets_friendly = in.readInt();
        min_weight = in.readInt();
        max_weight = in.readInt();
        min_life_expectancy = in.readInt();
        max_life_expectancy = in.readInt();
        name = in.readString();
    }

    public static final Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(length);
        dest.writeString(origin);
        dest.writeString(image_link);
        dest.writeInt(family_friendly);
        dest.writeInt(shedding);
        dest.writeInt(general_health);
        dest.writeInt(playfulness);
        dest.writeInt(children_friendly);
        dest.writeInt(grooming);
        dest.writeInt(intelligence);
        dest.writeInt(other_pets_friendly);
        dest.writeDouble(min_weight);
        dest.writeDouble(max_weight);
        dest.writeInt(min_life_expectancy);
        dest.writeInt(max_life_expectancy);
        dest.writeString(name);
    }
}
