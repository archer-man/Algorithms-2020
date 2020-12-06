package lesson6;

import kotlin.NotImplementedError;

import java.io.IOException;
import java.util.*;

import lesson6.impl.GraphBuilder;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     * <p>
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     * <p>
     * Пример:
     * <p>
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     * <p>
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     * <p>
     * Дан связный граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     * <p>
     * Пример:
     * <p>
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ:
     * <p>
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        GraphBuilder answer = new GraphBuilder();
        /*for (int i=0; i<graph.getVertices().size();i++){

        }*/
        /*Graph.Vertex a = answer.addVertex("A");
        Graph.Vertex b =answer.addVertex("B");
        Graph.Vertex c =answer.addVertex("C");
        answer.addConnection(a,b,2);
        answer.addConnection(b,c,3);
        answer.addConnection(c,a,1);*/

        //while (answer.build().getVertices().size() < graph.getVertices().size()){
            /*for (Graph.Vertex vertex : graph.getVertices()){
                Graph.Edge min = ;
                for (Map.Entry<Graph.Vertex, Graph.Edge> element : graph.getConnections(vertex).entrySet()){
                    if (min.getWeight() > entry.getValue()) {
                }
            }*/
        for (Graph.Vertex vertex : graph.getVertices()) {
            GraphBuilder.VertexImpl a = new GraphBuilder.VertexImpl("A");
            GraphBuilder.VertexImpl b = new GraphBuilder.VertexImpl("B");
            Graph.Edge min = new GraphBuilder.EdgeImpl(0, a, b);

            for (Graph.Edge element : graph.getConnections(vertex).values()) {
                if (min.getWeight() < element.getWeight()) {
                    min = element;
                }
                answer.addVertex(min.getBegin().getName());
                answer.addConnection(min.getBegin(), min.getEnd(), min.getWeight());
            }
        }
        //}
        return answer.build();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     * <p>
     * Дан граф без циклов (получатель), например
     * <p>
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     * <p>
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     * <p>
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     * <p>
     * В данном случае ответ (A, E, F, D, G, J)
     * <p>
     * Если на входе граф с циклами, бросить IllegalArgumentException
     * <p>
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph){
        try {
            Set<Graph.Vertex> answerSet = new LinkedHashSet<>();
            Set<Graph.Vertex> extraVertices = new LinkedHashSet<>();
            //for (Graph.Vertex vertex : graph.getVertices()) {
            /*for (Graph.Edge element : graph.getConnections(vertex).values()) {

                extraVertices.add(element.getEnd());
                answerSet.add(element.getBegin());
            }*/
            /* 1 Graph.Vertex first = graph.getVertices().iterator().next();
            answerSet.add(first);
            for (Graph.Edge element : graph.getConnections(first).values()) {
                extraVertices.add(element.getEnd());
            }
            for (Graph.Vertex extraVertex : extraVertices) {
                for (Graph.Edge goodConnection : graph.getConnections(extraVertex).values()) {
                    if (!answerSet.contains(goodConnection.getBegin()) && !answerSet.contains(goodConnection.getEnd())) {
                        answerSet.add(goodConnection.getEnd());
                    }
                }
            } 1 */
            for (Graph.Edge connection : graph.getEdges()){
                if(!extraVertices.contains(connection.getBegin())) {
                    answerSet.add(connection.getBegin());
                    extraVertices.add(connection.getEnd());
                }else if (!extraVertices.contains(connection.getEnd())){
                    answerSet.add(connection.getEnd());
                }
            }
            if (graph.getEdges().size()==0 && graph.getVertices().size()!=0){
                answerSet.addAll(graph.getVertices());
            }
            return answerSet;
        } catch (NoSuchElementException e){
            return new LinkedHashSet<>();
        }

    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     * <p>
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     * <p>
     * Пример:
     * <p>
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {
        throw new NotImplementedError();
    }


    /**
     * Балда
     * Сложная
     * <p>
     * Задача хоть и не использует граф напрямую, но решение базируется на тех же алгоритмах -
     * поэтому задача присутствует в этом разделе
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}
