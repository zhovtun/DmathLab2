
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
        String result = "Свойства отношений, заданных на множестве R:\n";

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

        // Для проверки симметричности матрицы производится сравнение мматрицы и транспонированной матрицы.
        // Если ячейка матрицы равна 1 и ячейка матрицы равна ячейке транспонированной матрицы то счетчик увеличивается
        // и если счетчик  равен размеру матрицы, содержащей пары числе, то матрица симметрична.
        // Для проверки на антисимметричность, выполняется поэлементное умножение матрицы на транспонированную матрицу
        j = 0;
        i = 0;
        int Sum = 0;
        while (i < arr1.length) {
        j=0;
        while (j < arr1.length) {
        if (numMatrix[i][j] == 1 && numMatrix[i][j] == transponirMatrix[i][j])Sum++;
        antiSimmetric[i][j] = numMatrix[i][j] * transponirMatrix[i][j];
        j++;
        }
        i++;
        }
        resultAntiSimmetric = checkProperties(antiSimmetric);

        if (resultOriginalMatrix[0] ==1) {result += " - множество рефлексивно;\n";}
        if (Sum == rArray.length) {result += " - множество симметрично; \n";}
        else {result += " - множество несиммитрично; \n";}
        if (resultOriginalMatrix[0] ==0 && resultAntiSimmetric[1] == 0) {result += " - множество антисимметрично;\n";}
        if (resultMultiplyMatrix[3] == 1) {result += " - множество транзитивно\n";}
        return  result;
        }