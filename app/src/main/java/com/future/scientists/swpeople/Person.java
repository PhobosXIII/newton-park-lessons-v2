package com.future.scientists.swpeople;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "people")
public class Person implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String avatar;
    private String planet;
    private int mass;
    private long birthday;

    public Person(String name, String avatar, String planet, int mass, long birthday) {
        this(0, name, avatar, planet, mass, birthday);
    }

    @Ignore
    public Person(long id, String name, String avatar, String planet, int mass, long birthday) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.planet = planet;
        this.mass = mass;
        this.birthday = birthday;
    }

    @Ignore
    protected Person(Parcel in) {
        id = in.readLong();
        name = in.readString();
        avatar = in.readString();
        planet = in.readString();
        mass = in.readInt();
        birthday = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(avatar);
        dest.writeString(planet);
        dest.writeInt(mass);
        dest.writeLong(birthday);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPlanet() {
        return planet;
    }

    public int getMass() {
        return mass;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                mass == person.mass &&
                birthday == person.birthday &&
                Objects.equals(name, person.name) &&
                Objects.equals(avatar, person.avatar) &&
                Objects.equals(planet, person.planet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, avatar, planet, mass, birthday);
    }
}
