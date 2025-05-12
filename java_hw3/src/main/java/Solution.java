import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Problem 1
 * */
class Author {
    /** WRITE YOUR CODE HERE **/
    String name;
    String email;
    char gender;

    Author(String name, String email, char gender){
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public char getGender() {
        return gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Author[name=" + name + ",email=" + email + ",gender=" + gender + "]";
    }

}

class Book {
    /** WRITE YOUR CODE HERE **/
    String name;
    Author[] authors;
    double price;
    int qty = 0;

    Book (String name, Author[] authors, double price) {
        this.name = name;
        this.authors = authors;
        this.price = price;
    }
    Book (String name, Author[] authors, double price, int qty) {
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String toString() {
        String authorsString = "";
        for(Author man:this.authors){
            authorsString += "," + man.toString();
        }
        String ret = authorsString.substring(1);
        return "Book[name=" + name + ",authors=" + "{" + ret + "}" + ",price=" + price + ",qty=" + qty + "]";
    }

    public String getAuthorNames() {
        String res = "";
        for(Author man:this.authors){
            res = res + "," + man.getName();
        }
        return res.substring(1);
    }
}

/**
 * Problem 2
 * */
class DeathManager{

    Map<String, Integer> deathRecords = new LinkedHashMap<>();
    private List<Person> persons = new ArrayList<>();

    public class Person {
        public int age=0;
        public String name;
        public int health;
        public int strength;
        public int dexterity;
        public int intelligence;
        public int faith;
        public int mana;
        // if you need, you can add other members or methods
        private List<Item> items;

        public Person(String name, int health, int strength, int dexterity, int intelligence, int faith, int mana) {
            this.name = name;
            this.health = health;
            this.strength = strength;
            this.dexterity = dexterity;
            this.intelligence = intelligence;
            this.faith = faith;
            this.mana = mana;
            /** WRITE YOUR CODE HERE **/
            Item useless = new Item();
            this.items = new ArrayList<>();
            age = 0;
        }
        public void physicalAttack(Person other){
            /** WRITE YOUR CODE HERE **/
            if(this.is_alive() && other.is_alive()){
                boolean containsBlade = items.stream().anyMatch(item -> item instanceof Blade);
                if(containsBlade){
                    other.health_reduce(3*(10+this.strength));
                    if(!other.is_alive()){
                        boolean containseye = items.stream().anyMatch(item -> item instanceof RingOfTheEvilEye);
                        if(containseye){
                            this.health+=30;
                        }
                        deathRecords.put(other.name, other.age);
                    }
                    return;
                }
                if(this.strength>=other.dexterity){
                    other.health_reduce(this.strength * this.dexterity);
                    if(!other.is_alive()){
                        boolean containseye = items.stream().anyMatch(item -> item instanceof RingOfTheEvilEye);
                        if(containseye){
                            this.health+=30;
                        }
                        deathRecords.put(other.name, other.age);
                    }
                }
            }
        }
        public void magicAttack(Person other){
            /** WRITE YOUR CODE HERE **/
            if(this.is_alive() && other.is_alive()){
                for(Item the_item:this.items){
                    if(the_item instanceof Wand){
                        the_item.activeSkill(other);
                        if(!other.is_alive()) {
                            boolean containseye = items.stream().anyMatch(item -> item instanceof RingOfTheEvilEye);
                            if (containseye) {
                                this.health += 30;
                            }
                            deathRecords.put(other.name, other.age);
                        }
                        return;
                    }
                }
                if(this.mana>=20){
                    this.mana -= 20;
                    other.health_reduce(this.intelligence * this.faith);
                    if(!other.is_alive()){
                        boolean containseye = items.stream().anyMatch(item -> item instanceof RingOfTheEvilEye);
                        if(containseye){
                            this.health+=30;
                        }
                        deathRecords.put(other.name, other.age);
                    }
                }
            }
        }
        public void health_reduce(int hurt){
            boolean containsShield = items.stream().anyMatch(item -> item instanceof Shield);
            boolean containsring = items.stream().anyMatch(item -> item instanceof CalamityRing);
            if(containsShield){
                if(hurt>=20){
                    hurt-=20;
                }
                else{
                    hurt = 0;
                }
            }
            if(containsring){
                hurt *= 2;
            }
            if(this.health-hurt > 0){
                this.health -= hurt;
            }
            else{
                boolean containsnbring = items.stream().anyMatch(item -> item instanceof RingOfSacrifice);
                if(containsnbring){
                    this.health = 1;
                    for (Item theitem : items) {
                        if(theitem instanceof RingOfSacrifice){
                            removeItem(this, theitem);
                        }
                    }
                }
                else {
                    this.health = 0;
                }
            }
        }
        public boolean is_alive(){
            return this.health != 0;
        }
    }
    public Person newborn(String name,int health, int strength, int dexterity, int intelligence, int faith, int mana){
        Person person = new Person(name, health, strength, dexterity, intelligence, faith, mana);
        if(!person.is_alive()){
            deathRecords.put(person.name, person.age);
        }
        persons.add(person);
        return person;//Do not modify
    }
    public void progressYear() {
        /** WRITE YOUR CODE HERE **/
        for(Person man:persons){
            man.age++;
        }
    }
    public Map<String, Integer> deathRecord() {
        /** WRITE YOUR CODE HERE **/
        return new LinkedHashMap<>(deathRecords);
    }

    /**
     * Problem 3
     * */
    public void grantItem(Person person, Item item) {
        /** WRITE YOUR CODE HERE **/
        if(!person.items.contains(item)){
            item.user = person;
            person.items.add(item);
            item.applyBuff(person);
        }
    }

    public void removeItem(Person person, Item item) {
        /** WRITE YOUR CODE HERE **/
        if(person.items.contains(item)){
            item.user = null;
            person.items.remove(item);
            item.removeBuff(person);
        }
    }

    private static class Item {
        Person user;
        void activeSkill(Person other) {
        }
        void applyBuff(Person person) {
        }
        void removeBuff(Person person) {
        }
    }

    public static class Blade extends Item {
        /** WRITE YOUR CODE HERE **/
        void applyBuff(Person person) {
            person.dexterity+=5;
        }
        void removeBuff(Person person) {
            person.dexterity-=5;
        }
        void activeSkill(Person other) {
            if(this.user.is_alive() && other.is_alive()) {
                other.health_reduce(3 * (10 + this.user.strength));
            }
        }
    }

    public static class Shield extends Item {
        /** WRITE YOUR CODE HERE **/
    }

    public static class Wand extends Item {
        /** WRITE YOUR CODE HERE **/
        int num_left = 0;
        void applyBuff(Person person) {
            num_left = 5;
            user = person;
        }
        void removeBuff(Person person) {
            num_left = 0;
            user = null;
        }
        void activeSkill(Person other) {
            if(num_left>0 && this.user.is_alive() && other.is_alive()){
                if(user.dexterity>=100){
                    user.dexterity-=100;
                    other.health_reduce(200);
                    num_left--;
                    if(num_left==0){
                        this.user = null;
                        this.user.items.remove(this);
                    }
                }
            }
        }
    }

    public static class RingOfSacrifice extends Item {
        /** WRITE YOUR CODE HERE **/
    }

    public static class CalamityRing extends Item {
        /** WRITE YOUR CODE HERE **/
    }

    public static class RingOfTheEvilEye extends Item {
        /** WRITE YOUR CODE HERE **/
    }
}

/**
 * Problem 4
 * */
public class Solution {
    public boolean checkClassInfo(String path) {
        try {
            /** WRITE YOUR CODE HERE **/
            Class<?> c = Class.forName(path);
            String name_class = c.getName();
            if(name_class.equals("Node")){
                return false;
            }

        }
        catch (ClassNotFoundException ignored) {
            return false; // error handling, simply returns false
        }
        return false;
    }
}
