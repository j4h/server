package com.dreamers.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    //sum of all integers in array
    public static int sum(List<Integer> integers){
        int sum = 0;
        for (int aT : integers) {
            sum += aT;
        }
        return sum;
    }

    //index of minimum value
    public static int minValueIndex (List<Integer> gp) {

        int lowestElement = gp.get(0);
        for (Integer aGp : gp) {
            if (aGp.compareTo(lowestElement) < 0)
                lowestElement = aGp;
        }
        return gp.indexOf(lowestElement);
    }

    public static int maxValueIndex (List<Integer> gp) {

        int highestElement = gp.get(0);
        for (Integer aGp : gp) {
            if (aGp.compareTo(highestElement) > 0)
                highestElement = aGp;
        }
        return gp.indexOf(highestElement);
    }

    public static <T> boolean allObjEqual(List<T> T) {
        List<T> equalObjList = getEqualObjIndex(T);
        return equalObjList.size() == T.size();
    }

    //get indexes of equal objects //don't work now
    public static <T> List<T> getEqualObjIndex(List<T> objList) {

        List<T> equalObjIndex = new ArrayList<>();
        final Object equalObj = firstEqualObj(objList);

        equalObjIndex.addAll(objList.stream().filter(anObjList -> equalObj == anObjList).collect(Collectors.toList()));
        return equalObjIndex;
    }

    public static <T> List<Integer> getEqualCardSuitObjIndex(List<T> objList) {

        int firstEqualObj = firstEqualObj(objList);
        List<Integer> equalObjIndex = new ArrayList<>();

        for (T t:objList){
            if (t.equals(firstEqualObj) && !t.equals(objList.get(firstEqualObj)) ){
                equalObjIndex.add(objList.indexOf(t));
            }
        }
        return equalObjIndex;
    }

    public static <T> boolean atLeastTwoEqualObj(List<T> objList) {

        int comparableElement = 0;

        for (int i =0;i<objList.size() -1; i++) {
            comparableElement++;
            if (objList.get(i).equals(objList.get(comparableElement))){
                return true;
            }
        }
        return false;
    }

    //returns index of 1st equal Object in list
    public static <T> Integer firstEqualObj(List<T> objList) {

        int comparableElement = 0;

        for (int i =0;i<objList.size() -1; i++) {
            comparableElement++;
            if (objList.get(i).equals(objList.get(comparableElement))){
            return i;
            }
        }
        return -1;
    }
}
