package ex3;

public class Main {
    public static void main(String[] args) {
        HashTableEx3 hashTable = new HashTableEx3();

        // Put some key values.
        for (int i = 0; i < 30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
        HashTableEx3.log("****   HashTable  ***");
        HashTableEx3.log(hashTable.toString());
        HashTableEx3.log("\nValue for key(20) : " + hashTable.get("20"));
    }
}