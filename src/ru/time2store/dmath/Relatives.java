package ru.time2store.dmath;


import static java.lang.StrictMath.sqrt;
import static jdk.nashorn.internal.runtime.JSType.isNumber;

public class Relatives extends MyArray {
    private String rArray[][];
    private String binMatrix[][];
    private int numMatrix[][];

    public Relatives() {
        rArray = new String[0][0];

    }


    // Геттеры
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
    public int getHeaderLenght () {
        return arr1.length;}

    // Метод для поиска индекса бинарной матрица из множества R
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

    // Метод для конструирования бинарной матрицы из множества R
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
            i++;
        }
    }

    // Метод для создания массива множества R из строки ввода
    private String[] buildRArray(String str) {
        int i = 0;
        int j = 0;
        String a = "0";
        String[] temp = str.split(" ");
        String[] ch = new String[temp.length * 2];
        while (i < temp.length) {
            String[] tmp =  new String[2];
            tmp = temp[i].split("");
            ch[j] = tmp[0];
            j++;
            ch[j] = tmp[1];
            j++;
            i++;
        }
        rArray = new String[ch.length/2][2];
        i = 0;
        j = 0;
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

    // Метод проверяющий отношения множества R
    public String checkRelatives() {

        // Проверка на то, является ли элемент массива числом или нет
        boolean type = true;
        int i = 0;
        while (i<arr2.length) {
            if (!arr2[i].matches("^-?\\d+$")){
                type = false;
                i = arr2.length - 1;
            }
            i++;
        }

        // Проверка отношений заданных парами элементов на множества R.
        // Для корректной работы необходимо, чтобы отношения всех пар было
        // одинаковым, т.к. проверяется только первая пара элементов.
        String result = "На множестве R заданы следующие отношения aRb:\n";
        if (rArray[0][0].compareTo(rArray[0][1]) > 0) {
            result += "a > b, ";
        } else if (rArray[0][0].compareTo(rArray[0][1]) < 0) {
            result += "a < b, ";
        }
        else if (rArray[0][0].compareTo(rArray[0][1]) == 0) {
            result += "a = b, ";
        }
        //  Если все элементы множества - числа, то проверяется свойство делимости
        if (type) {
            if (Integer.parseInt(rArray[0][0]) % Integer.parseInt(rArray[0][1]) == 0) {result += "a делится на b, ";}
            if (Integer.parseInt(rArray[0][1]) % Integer.parseInt(rArray[0][0]) == 0) {result += "b делится на a.";}
        }
        return result;
    }

    // Метод, проверяющий свойства бинарной матрицы
    private int[] checkProperties(int[][] numM) {
        int[] result = new int[4]; //0 - рефлексивность, 1 - симметричность, 2 - антисемметричность, 3 - транзитивность
        int count = 0, reflect = 0, left = 0, right = 0, tran = 0;
        int i = 0;
        int j;

        // Проверка свойств бинарной матрицы проводится путем подсчета единиц в главной
        // диагонали, справа от нее и слева, а также проверка на транзитивность.
        while (i < arr1.length) {
            j = 0;
            while (j < arr1.length){
                if (i == j && numM[i][j] == 1) {reflect++;}  // Проверка главной диагонали
                if (j != i && numM[i][j] == 1) {count++;}   // Подсчет всего 1 вне главной диагонали
                if (j < i && numM[i][j] == 1) {left++;}     // слева от диагонали
                if (j > i && numM[i][j] == 1) {right++;}    // Справа от диагонали
                if (numMatrix[i][j] != numM[i][j]) {tran++;}  // Проверка на транзитивность
                j++;
            }
            i++;
        }

        // Запись в матрицу result результатов проверок
        if (reflect == arr1.length) {result[0] = 1;}
        if (count > 0 && left == right) {result[1] = 1;}
        if (count > 0 && left != right) {result[2] = 1;}
        if (tran == 0) {result[3] = 1;}

        return result;
    }

    // Метод создающий 2 матрицы R*R и транспонированную матрицу R,
    // и проверяющий их свойства методом  checkProperties
    public String matrixForCheck () {
        int[][] transponirMatrix = new int[arr1.length][arr1.length]; //Транспонированная матрица
        int[][] multiplyMatrix = new int[arr1.length][arr1.length]; // Умножение R*R для проверки транзитивнсти
        int[][] antiSimmetric =  new int[arr1.length][arr1.length];

        // Матрицы с результатами проверок
        int[] resultOriginalMatrix;
        int[] resultAntiSimmetric;
        int[] resultMultiplyMatrix;
        String result = "Свойства множества R:\n";

        int i = 0, j = 0;

        i = 0;

        // Создание int матрицы для численных операций

        while (i<rArray.length){
            numMatrix[findIndex(rArray[i][0])][findIndex(rArray[i][1])] = 1;
            i++;
        }

        // Создание транспонированной матрицы и матрицы результата умножения R*R

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


        // Проверка матриц на их свойства

        resultOriginalMatrix = checkProperties(numMatrix);
        resultMultiplyMatrix = checkProperties(multiplyMatrix);

        j = 0;
        i = 0;
        int Sum = 0;
        while (i < arr1.length) {
            j=0;
            while (j < arr1.length) {
                if (numMatrix[i][j] == 1 && numMatrix[i][j] == transponirMatrix[i][j])Sum++;
                antiSimmetric[i][j] = numMatrix[i][j] * transponirMatrix[i][j];
                //System.out.print(antiSimmetric[i][j] + " ");
                j++;
            }
            i++;
            //System.out.print('\n');
        }
        resultAntiSimmetric = checkProperties(antiSimmetric);

        if (resultOriginalMatrix[0] ==1) {result += " - множество рефлексивно;\n";}
        if (Sum == rArray.length) {result += " - множество симметрично; \n";}
        else {result += " - множество несиммитрично; \n";}
        if (resultOriginalMatrix[0] ==0 && resultAntiSimmetric[1] == 0) {result += " - множество антисимметрично;\n";}
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

    // Метод для отображениея пар множества R
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

    private int[][] multiplicationOfMatrix (int[][] matrix1, int[][] matrix2) {
        int i = 0, j, k;
        int size = matrix1.length;
        int[][] result = new int[size][size];
        while (i < size) {
            j = 0;
            while (j < size) {
                k = 0;
                while (k < size) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                    k++;
                }
                j++;
            }
            i++;
        }

        return result;
    }

}