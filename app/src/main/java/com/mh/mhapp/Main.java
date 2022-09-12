package com.mh.mhapp;

public class Main {

    public static class Animal {

        private static Animal INSTANCE = null;

        private Animal() {

        }

        public static Animal getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new Animal();
            }
            return INSTANCE;
        }

    }

    public static void main(String[] arg) {


        Animal animal = Animal.getInstance();

    }
}
