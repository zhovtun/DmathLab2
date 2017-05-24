package ru.time2store.dmath;


public class Relatives extends MyArray {
    private String rArray[][];
    private String binMatrix[][];
    private int numMatrix[][];

    public Relatives() {
        rArray = new String[0][0];

    }


    public String[][] getArray (){return rArray;}

    public String[] getHeader () {
        String[] arr = new String[arr1.length+1];
        int i = 0;
        while (i<arr.length) {
            arr[i] = "" + i;
            i++;
        }
        return arr;
    }
    public String[][] getBinMatrix () {
        buildBinMatrix();
        return binMatrix;
    }
    public int getTableDimension () {return arr1.length+1;}

    public int getRArryLenght () {return arr1.length;}
    public int getHeaderLenght () {return arr1.length;}

    private int findIndex (String string){
        int i = 0;
        int index = 0;
        while (i< arr1.length){
            if (arr1[i].equals(string)) {
                index = i;
                break;
            }
            i++;
        }
        return index;
    }

    private void buildBinMatrix() {
        int i = 1;
        while (i< arr1.length+1) {
            binMatrix[0][i] = arr1[i-1];
            binMatrix[i][0] = arr1[i-1];
            i++;
        }
        i = 0;
        while (i<rArray.length){
            binMatrix[findIndex(rArray[i][0])+1][findIndex(rArray[i][1])+1] = "1";
            //numMatrix[findIndex(rArray[i][0])][findIndex(rArray[i][1])] = 1;
            i++;
        }
    }

    private String[] buildRArray(String str) {
        int i = 0;
        int j = 0;
        String a = "0";
        String[] ch = str.split("");
        rArray = new String[ch.length/2][2];

        while (i < ch.length / 2) {
            rArray[i][0] = ch[j];
            j++;
            rArray[i][1] = ch[j];
            j++;
            i++;
        }
        String[] array = new String[ch.length];
        String[] result;

        sort(ch);

        i=0;
        j=0;
        while (i < ch.length) {
            if (a.compareTo(ch[i])>-74 && a.compareTo(ch[i])<1) {
                array[j] = ch[i];
                j++;
                i++;
            }
            else{i++;}
        }
        if (ch.length == j) {
            result = deleteDoubleChar(array);
            return result;
        }
        else {
            result = cutArray(array);
            array = deleteDoubleChar(result);
            return array;
        }
    }

    public String checkRelatives() {
        boolean type = true;

        //TODO Сделать роверку что элемент массива rArray число записать type

        String result = "На множестве R заданы следующие отношения aRb:\n";
        if (rArray[0][0].compareTo(rArray[0][1]) > 0) {
            result += "a > b, ";
        } else if (rArray[0][0].compareTo(rArray[0][1]) < 0) {
            result += "a < b, ";
        }
        else if (rArray[0][0].compareTo(rArray[0][1]) == 0) {
            result += "a = b, ";
        }
        if (type) {
            if (Integer.parseInt(rArray[0][0]) % Integer.parseInt(rArray[0][1]) == 0) {result += "a делится на b.";}
        }
        return result;
    }

    private int[] checkProperties(int[][] numM) {
        int[] result = new int[4]; //0 - рефлексивность, 1 - симметричность, 2 - антисемметричность, 3 - транзитивность
        int count = 0, reflect = 0, left = 0, right = 0, tran = 0;
        int i = 0;
        int j = 0;

        while (i < arr1.length) {
            j = 0;
            while (j < arr1.length){
                if (i == j && numM[i][j] == 1) {reflect++;}
                if (j != i && numM[i][j] == 1) {count++;}
                if (j < i && numM[i][j] == 1) {left++;}
                if (j > i && numM[i][j] == 1) {right++;}
                if (numMatrix[i][j] != numM[i][j]) {tran++;}
                //System.out.print(numMatrix[i][j] + " ");
                j++;
            }
            //System.out.print('\n');
            i++;
        }
        if (reflect == arr1.length) {result[0] = 1;}
        if (count > 0 && left == right) {result[1] = 1;}
        if (count > 0 && left != right) {result[2] = 1;}
        if (tran == 0) {result[3] = 1;}

        return result;
    }

    public String matrixForCheck () {
        int[][] transponirMatrix = new int[arr1.length][arr1.length]; //Транспонированная матрица
        int[][] multiplyMatrix = new int[arr1.length][arr1.length]; // Умножение R*R для проверки транзитивнсти
        int[] resultOriginalMatrix;
        int[] resultTransponierMatrix;
        int[] resultMultiplyMatrix;
        String result = "Свойства множества R:\n";

        int i = 0, j = 0;

        i = 0;
        while (i<rArray.length){
            numMatrix[findIndex(rArray[i][0])][findIndex(rArray[i][1])] = 1;
            i++;
        }

        i=0;

        while (i < arr1.length) {
            j = 0;
            while (j < arr1.length) {
                transponirMatrix[j][i] = numMatrix[i][j];
                multiplyMatrix[i][j] = numMatrix[i][j] * numMatrix [i][j];
                j++;
            }
            i++;
        }

        resultOriginalMatrix = checkProperties(numMatrix);
        resultTransponierMatrix = checkProperties(transponirMatrix);
        resultMultiplyMatrix = checkProperties(multiplyMatrix);

        if (resultOriginalMatrix[0] ==1) {result += " - множество рефлексивно;\n";}
        if (resultTransponierMatrix[1] == 1) {result += " - множество симметрично\n";}
        if (resultTransponierMatrix[2] == 1) {result += " - множество антисеммитрично\n";}
        if (resultMultiplyMatrix[3] == 1) {result += " - множество транзитивно\n";}
        return  result;
    }

    @Override
    public void setArray (String str1, String str2) {
        arr1 = buildArray (str1);
        arr2 = buildRArray (str2);
        binMatrix = new String[arr1.length+1][arr1.length+1];
        numMatrix = new int[arr1.length][arr1.length];
    }

    public String showRArray () {
        String result = "{ ";
        int i = 0;
        while (i<rArray.length) {
            result = result + "(" + rArray[i][0] + ", " + rArray[i][1] + ") ";
            i++;
        }
        result = result + "}";
        return result;
    }

}