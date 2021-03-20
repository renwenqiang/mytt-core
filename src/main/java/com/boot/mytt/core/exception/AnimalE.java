package com.boot.mytt.core.exception;

import com.boot.mytt.core.ext.TestParent;

/**
 * @author renwq
 * @date 2021/3/19 23:44
 */
public class AnimalE extends TestParent {

    public static void main(String[] args) {
        AnimalE animalE = new AnimalE();
        animalE.fnC();
        animalE.fnD();
    }
}
