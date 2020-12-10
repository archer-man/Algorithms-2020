package lesson7;

import kotlin.NotImplementedError;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        int firstLength = first.length();
        int secondLength = second.length();
        int[][] matrix = new int[firstLength + 1][secondLength + 1];
        for (int i = 0; i < firstLength; i++)
            for (int j = 0; j < secondLength; j++)
                if (first.charAt(i) == second.charAt(j))
                    matrix[i + 1][j + 1] = matrix[i][j] + 1;
                else
                    matrix[i + 1][j + 1] = Math.max(matrix[i + 1][j], matrix[i][j + 1]);
        StringBuilder string = new StringBuilder();
        int k = firstLength;
        int c = secondLength;
        while (k != 0 && c != 0) {
            if (matrix[k][c] == matrix[k - 1][c])
                k--;
            else if (matrix[k][c] == matrix[k][c - 1])
                c--;
            else {
                assert first.charAt(k - 1) == second.charAt(c - 1);
                string.append(first.charAt(k - 1));
                k--;
                c--;
            }
        }
        return string.reverse().toString();
    }

    /*
    Трудоёмкость: O(m*n)
    Ресурсоёмкость: O(m*n)
    */

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        throw new NotImplementedError();
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) throws IOException {
        List<List<Integer>> list = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileReader(inputName))) {
            int row = 0;
            int col = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Scanner scanner = new Scanner(line);
                list.add(new ArrayList<>());
                scanner.useDelimiter(" ");
                while (scanner.hasNextInt()) {
                    list.get(row).add(scanner.nextInt());
                    col++;
                }
                row++;
            }
            col /= row;
            for (int i = 1; i < row; i++) {
                list.get(i).set(0, list.get(i).get(0) + list.get(i - 1).get(0));
            }
            for (int j = 1; j < col; j++) {
                list.get(0).set(j, list.get(0).get(j) + list.get(0).get(j - 1));
            }
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    list.get(i).set(j, list.get(i).get(j) + Math.min(list.get(i - 1).get(j - 1), Math.min(list.get(i - 1).get(j), list.get(i).get(j - 1))));
                }
            }
            return list.get(row - 1).get(col - 1);
        }
    }

    /*
    Трудоёмкость: O(m*n)
    Ресурсоёмкость: O(m*n)
    */

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
