package ru.stqa.lesson6;

import java.util.Arrays;

public class Array {



    public static void main(String [] args) {
        int[] list = {1,2,9, 5,10,7,8, 7,9,10, 14,7,8 ,9,5};

        Array ar = new Array();
        ar.returnIndexes(list, 3);
    }
        public int[] returnIndexes(int[] list, int n){

            int[] indexes = {};
            int steps = n-1;

            for (int i = 0; i < list.length - n; i++) {
                if(i+steps >= list.length){
                    break;
                }
                int positive_pass = 0;
                int negative_pass = 0;
                int test_number = list[i];

                for(int j = 1; j < n; j++ ){
                    if(list[i+j] == test_number + j){
                        positive_pass +=1;
                    }
                    if(list[i+j] == test_number - j){
                        negative_pass += 1;
                    }
                    if(positive_pass == steps || negative_pass == steps){
                        indexes = Arrays.copyOf(indexes, indexes.length + 1);
                        indexes[indexes.length - 1] = i;
                    }
                }
            }
            System.out.println(Arrays.toString(indexes));
            return indexes;
        }
}



