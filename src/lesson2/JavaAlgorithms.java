package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        int localDiff;
        int maxDiff = 0;
        int buying = 0;
        Integer selling = 0;
        int buyingIndex = 0;
        int sellingIndex = 0;
        int i = 0;
        try {
            Scanner scanner = new Scanner(new File(inputName));
            List<Integer> prices = new ArrayList<>();
            while (scanner.hasNextInt()) {
                prices.add(i++, scanner.nextInt());
            }
            scanner.close();
            Integer min = prices.get(0);
            Integer max = prices.get(0);
            for (i = 1; i < prices.size(); i++) {
                if (min > prices.get(i)) {
                    localDiff = max - min;
                    if (localDiff > maxDiff) {
                        buying = min;
                        selling = max;
                        maxDiff = localDiff;
                    }
                    min = prices.get(i);
                    max = prices.get(i);
                } else if (max < prices.get(i)) {
                    max = prices.get(i);
                }
            }
            localDiff = max - min;
            if (localDiff > maxDiff) {
                buying = min;
                selling = max;
            }
            buyingIndex = prices.indexOf(buying);
            for (int b = buyingIndex; b < prices.size(); b++) {
                if (prices.get(b).equals(selling)) {
                    sellingIndex = b;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Pair<>(buyingIndex + 1, sellingIndex + 1);
    }

    /*
        Трудоёмкость: O(n)
        Ресурсоёмкость: O(n)
    */

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String firs, String second) {
        throw new NotImplementedError();
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        int counter = 0;
        if (limit <= 1) return 0;
        if (limit == 2) return 1;
        boolean array[] = new boolean[limit + 1];
        Arrays.fill(array, Boolean.TRUE);
        for (int i = 2; i * i <= limit; i++) {
            if (array[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    array[j] = false;
                }
            }
        }
        for (int i = 2; i <= limit; i++) {
            if (array[i]) counter++;
        }
        return counter;
    }
    /*
        Трудоёмкость: O(n*log(log(n)))
        Ресурсоёмкость: O(n)
     */
}
