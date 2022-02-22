package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HashTableEx2Test {

    @Test
    void count() {
    }

    @Test
    void size() {
    }

    @Test
    void put() {
        HashTableEx2 ht = new HashTableEx2();
        System.out.println("Las colisiones son: " + ht.getCollisionsForKey("0", 3));

        // Inserir un element que no col·lisiona dins una taula vuida.
        ht.put("0","hola");
        Assertions.assertEquals(1,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, hola]",ht.toString());

        // Inserir un element que no col·lisiona dins una taula no vuida.
        ht.put("1","adios");
        Assertions.assertEquals(2,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, hola]" + "\n" +
                " bucket[1] = [1, adios]",ht.toString());

        // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.
        ht.put("12","que");
        Assertions.assertEquals(3,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, hola]" + "\n" +
                " bucket[1] = [1, adios] -> [12, que]",ht.toString());

        // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.
        ht.put("23","tal");
        Assertions.assertEquals(4,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, hola]" + "\n" +
                " bucket[1] = [1, adios] -> [12, que] -> [23, tal]",ht.toString());

        //Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no vuida.
        ht.put("0","jaja");
        Assertions.assertEquals(4,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, jaja]" + "\n" +
                " bucket[1] = [1, adios] -> [12, que] -> [23, tal]",ht.toString());

        // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no vuida.
        ht.put("12","como");
        Assertions.assertEquals(4,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, jaja]" + "\n" +
                " bucket[1] = [1, adios] -> [12, como] -> [23, tal]",ht.toString());

        // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no vuida.
        ht.put("23","estas");
        Assertions.assertEquals(4,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, jaja]" + "\n" +
                " bucket[1] = [1, adios] -> [12, como] -> [23, estas]",ht.toString());
    }

    @Test
    void get() {
        HashTableEx2 ht = new HashTableEx2();
        System.out.println("Las colisiones son: " + ht.getCollisionsForKey("0", 4));
        System.out.println("Las colisiones son: " + ht.getCollisionsForKey("1", 2));

        // Obtenir un element que no col·lisiona dins una taula vuida.
        Assertions.assertEquals(null,ht.get("0"));

        // Obtenir un element que col·lisiona dins una taula (1a posició dins el mateix bucket).
        // Obtenir un element que col·lisiona dins una taula (2a posició dins el mateix bucket).
        ht.put("0","hola");
        ht.put("11","adios");
        Assertions.assertEquals("hola",ht.get("0"));
        Assertions.assertEquals("adios",ht.get("11"));

        // Obtenir un element que col·lisiona dins una taula (3a posició dins el mateix bucket).
        ht.put("22","jaja");
        Assertions.assertEquals("jaja",ht.get("22"));

        // Obtenir un elements que no existeix perquè la seva posició està buida.
        Assertions.assertEquals(null,ht.get("1"));

        // Obtenir un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        ht.put("2","comotas");
        Assertions.assertEquals(null,ht.get("13"));

        // Obtenir un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        Assertions.assertEquals(null,ht.get("33"));
    }

    @Test
    void drop() {
        HashTableEx2 ht = new HashTableEx2();

        ht.put("1","hola");
        ht.put("2","adios");
        ht.put("3","que");
        ht.put("4","tal");
        Assertions.assertEquals(4,ht.count());
        Assertions.assertEquals(16,ht.size());
        // Esborrar un element que no col·lisiona dins una taula.
        ht.drop("1");
        Assertions.assertEquals(3,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[2] = [2, kjsegbrs] -> [13, vb,msbd] -> [24, afkhvqer]",ht.toString());

        // Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).
        ht.drop("2");
        Assertions.assertEquals(2,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, vb,msbd] -> [24, afkhvqer]",ht.toString());

        // Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).
        ht.put("2","kjsegbrs");
        ht.drop("24");
        Assertions.assertEquals(2,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, vb,msbd] -> [2, kjsegbrs]",ht.toString());

        // Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).
        ht.put("24","afkhvqer");
        ht.drop("24");
        Assertions.assertEquals(2,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, vb,msbd] -> [2, kjsegbrs]",ht.toString());

        //Eliminar un elements que no existeix perquè la seva posició està buida.
        ht.drop("3");
        Assertions.assertEquals(2,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, vb,msbd] -> [2, kjsegbrs]",ht.toString());

        // Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        ht.put("1","hola");
        ht.drop("12");
        Assertions.assertEquals(3,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [13, vb,msbd] -> [2, kjsegbrs]",ht.toString());

        // Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        ht.drop("35");
        Assertions.assertEquals(3,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [13, vb,msbd] -> [2, kjsegbrs]",ht.toString());
    }
}