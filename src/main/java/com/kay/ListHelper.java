package com.kay;

import java.util.ArrayList;
import java.util.List;

public final class ListHelper {

    public static List<Integer> asMutableList(int... nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }

}
