package by.epam.litvin.pool;

public class TTe {
    public static void main(String[] args) {
       m(9.9999999999F);

       I1 i1 = new Abstract() {};
       I2 i2 = new Abstract() {};

       C1 c1 = new Abstract() {};
       System.out.println(c1.getInt());

    }

    static void m(double d) {
        System.out.println("d");
    }

    static void m(float f) {
        System.out.println("f");
    }
}


interface I1 {
    int getInt();
}

interface I2 {
    int getInt();
}

class C1 implements I1 {
    public int getInt() {
        return 2;
    }
}

abstract class Abstract extends C1 implements I2 {
    public int getInt() {
        return 1;
    }
}