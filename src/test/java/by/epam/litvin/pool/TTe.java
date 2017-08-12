package by.epam.litvin.pool;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TTe {
    public static void main(String[] args) {
       m(9);

       I1 i1 = new Abstract() {};
       I2 i2 = new Abstract() {};

       C1 c1 = new Abstract() {};
       System.out.println(c1.getInt());


        HashMap<String, Integer> map = new HashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);

        Set<Map.Entry<String, Integer>> set = map.entrySet();

        Object[] arr = set.toArray();



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