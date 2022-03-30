package com.example.chatbotsimple;

public class Person {
    private String name;
    private String age;
    private String occupation;
    private String hobbies;
    private String query;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", query(s)='" + query + '\'' +
                '}';
    }

    public Person(String name, String age, String occupation, String hobbies, String query) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.hobbies = hobbies;
        this.query = query;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
