package ex4;

public class Main {
    public static void main(String[] args) {
        HashTableEx4 hashTable = new HashTableEx4();

        // Put some key values.
        for (int i = 0; i < 30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
        HashTableEx4.log("****   HashTable  ***");
        HashTableEx4.log(hashTable.toString());
        HashTableEx4.log("\nValue for key(20) : " + hashTable.get("20"));
    }
}