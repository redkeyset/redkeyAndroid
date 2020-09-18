package com.imooc.lib_java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 */
public class MyClass {
    public static void main(String[] args) {
        //{2, 3, 1, 0, 2, 5}
        Integer lists[] = {2, 3, 1, 0, 2, 5, 5, 6, 3, 2, 3};
        show(lists);

        show02(lists);
    }

    private static void show02(Integer[] lists) {
        Integer[] integers = new Integer[lists.length];


    }

    private static void show(Integer[] lists) {
        Set<Integer> integers = new HashSet<>();
        for (Integer item : lists) {
            if (integers.contains(item)) {
                continue;
            }

            int count = 0;
            for (Integer k : lists) {
                if (k == item) {
                    count = count + 1;
                }
            }
            System.out.println(item + "--数量：" + count);
            integers.add(item);
        }
    }
}