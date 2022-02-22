package ex4;

import ex3.HashTableEx3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashTableEx4Test {

    @Test
    void count() {
    }

    @Test
    void size() {
    }

    @Test
    void put() {
        HashTableEx4 hashTableEx4 = new HashTableEx4();
        System.out.println("Las colisiones son: " + hashTableEx4.getCollisionsForKey("0", 3));

        // Inserir un element que no col·lisiona dins una taula vuida.
        hashTableEx4.put("0","hola");
        Assertions.assertEquals(1, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, hola]", hashTableEx4.toString());

        // Inserir un element que no col·lisiona dins una taula no vuida.
        hashTableEx4.put("1","adios");
        Assertions.assertEquals(2, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, hola]" + "\n" +
                " bucket[1] = [1, adios]", hashTableEx4.toString());

        // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.
        hashTableEx4.put("12","que");
        Assertions.assertEquals(3, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, hola]" + "\n" +
                " bucket[1] = [1, adios] -> [12, que]", hashTableEx4.toString());

        // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.
        hashTableEx4.put("23","tal");
        Assertions.assertEquals(4, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, hola]" + "\n" +
                " bucket[1] = [1, adios] -> [12, que] -> [23, tal]", hashTableEx4.toString());

        //Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no vuida.
        hashTableEx4.put("0","jaja");
        Assertions.assertEquals(4, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, jaja]" + "\n" +
                " bucket[1] = [1, adios] -> [12, que] -> [23, tal]", hashTableEx4.toString());

        // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no vuida.
        hashTableEx4.put("12","como");
        Assertions.assertEquals(4, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, jaja]" + "\n" +
                " bucket[1] = [1, adios] -> [12, como] -> [23, tal]", hashTableEx4.toString());

        // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no vuida.
        hashTableEx4.put("23","estas");
        Assertions.assertEquals(4, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, jaja]" + "\n" +
                " bucket[1] = [1, adios] -> [12, como] -> [23, estas]", hashTableEx4.toString());
    }

    @Test
    void get() {
        HashTableEx4 hashTableEx4 = new HashTableEx4();
        System.out.println("Las colisiones son: " + hashTableEx4.getCollisionsForKey("0", 4));
        System.out.println("Las colisiones son: " + hashTableEx4.getCollisionsForKey("1", 2));

        // Obtenir un element que no col·lisiona dins una taula vuida.
        Assertions.assertEquals(null, hashTableEx4.get("0"));

        // Obtenir un element que col·lisiona dins una taula (1a posició dins el mateix bucket).
        // Obtenir un element que col·lisiona dins una taula (2a posició dins el mateix bucket).
        hashTableEx4.put("0","hola");
        hashTableEx4.put("11","adios");
        Assertions.assertEquals("hola", hashTableEx4.get("0"));
        Assertions.assertEquals("adios", hashTableEx4.get("11"));

        // Obtenir un element que col·lisiona dins una taula (3a posició dins el mateix bucket).
        hashTableEx4.put("22","jaja");
        Assertions.assertEquals("jaja", hashTableEx4.get("22"));

        // Obtenir un elements que no existeix perquè la seva posició està buida.
        Assertions.assertEquals(null, hashTableEx4.get("1"));

        // Obtenir un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        hashTableEx4.put("2","comotas");
        Assertions.assertEquals(null, hashTableEx4.get("13"));

        // Obtenir un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        Assertions.assertEquals(null, hashTableEx4.get("33"));
    }

    @Test
    void drop() {
        HashTableEx4 hashTableEx4 = new HashTableEx4();

        hashTableEx4.put("0","hola");
        hashTableEx4.put("1","adios");
        hashTableEx4.put("12","como");
        hashTableEx4.put("23","estas");
        Assertions.assertEquals(4, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        // Esborrar un element que no col·lisiona dins una taula.
        hashTableEx4.drop("0");
        Assertions.assertEquals(3, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, adios] -> [12, como] -> [23, estas]", hashTableEx4.toString());

        // Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).
        hashTableEx4.drop("1");
        Assertions.assertEquals(2, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [12, como] -> [23, estas]", hashTableEx4.toString());

        // Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).
        hashTableEx4.put("1","adios");
        hashTableEx4.drop("23");
        Assertions.assertEquals(2, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [12, como] -> [1, adios]", hashTableEx4.toString());

        // Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).
        hashTableEx4.put("23","estas");
        hashTableEx4.drop("23");
        Assertions.assertEquals(2, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [12, como] -> [1, adios]", hashTableEx4.toString());

        //Eliminar un elements que no existeix perquè la seva posició està buida.
        hashTableEx4.drop("3");
        Assertions.assertEquals(2, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [12, como] -> [1, adios]", hashTableEx4.toString());

        // Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        hashTableEx4.put("0","hola");
        hashTableEx4.drop("11");
        Assertions.assertEquals(3, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, hola]" + "\n" +
                " bucket[1] = [12, como] -> [1, adios]", hashTableEx4.toString());

        // Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        hashTableEx4.put("23","estas");
        hashTableEx4.drop("34");
        Assertions.assertEquals(4, hashTableEx4.count());
        Assertions.assertEquals(16, hashTableEx4.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, hola]" + "\n" +
                " bucket[1] = [12, como] -> [1, adios] -> [23, estas]", hashTableEx4.toString());
    }
}