import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class PG258711_도넛과_막대_그래프 {
	private static List<List<Integer>> graph;
	private static boolean[] visited;
	private static int startVertex;
	private static int maxVertex;
	private static int graphNum;
	private static int[] incomingEdges;

	public static int[] solution(int[][] edges) {
		int[] answer = new int[4];
		initGraph(edges);

		startVertex = findCreatedVertex();
		graphNum = graph.get(startVertex).size();
		answer[0] = startVertex;
		removeEdgesFromCreatedVertex(startVertex);

		answer[2] = countBarGraphs();
		answer[3] = countEightShape();
		answer[1] = graphNum - (answer[2] + answer[3]);

		System.out.println(countIncomingEdges(maxVertex - 1));
		return answer;
	}

	public static void initGraph(int[][] edges) {
		maxVertex = 0;

		for (int[] edge : edges) {
			maxVertex = Math.max(maxVertex, Math.max(edge[0], edge[1]));
		}

		visited = new boolean[maxVertex + 1];
		incomingEdges = new int[maxVertex + 1];
		graph = new ArrayList<>(maxVertex + 1);

		for (int i = 0; i <= maxVertex; i++) {
			graph.add(new LinkedList<>());
		}

		for (int i = 0; i < edges.length; i++) {
			graph.get(edges[i][0]).add(edges[i][1]);// 단방향 그래프
			incomingEdges[edges[i][1]]++;
		}
	}

	private static int countBarGraphs() {
		int count = 0;
		for (int i = 1; i < graph.size(); i++) {
			if (i == startVertex) {
				continue;
			}
			if (graph.get(i).isEmpty()) {// 나가는게 없다.
				count++;
				visited[i] = true;
			}
		}
		return count;
	}

	private static int countEightShape() {
		int count = 0;
		for (int i = 1; i < graph.size(); i++) {
			if (!visited[i]) {
				if (graph.get(i).size() == 2 && countIncomingEdges(i) == 2) {
					System.out.println(i);
					count++;
				}
			}
		}
		return count;
	}

	private static int findCreatedVertex() {
		int createdVertex = -1;
		for (int i = 1; i < graph.size(); i++) {
			if (graph.get(i).size() >= 2 && countIncomingEdges(i) == 0) {
				createdVertex = i;
				break;
			}
		}
		visited[createdVertex] = true;
		return createdVertex;
	}

	private static int countIncomingEdges(int vertex) {
		return incomingEdges[vertex];
	}

	private static void removeEdgesFromCreatedVertex(int vertex) {
		for (int end : graph.get(vertex)) {
			incomingEdges[end]--;
		}
		graph.set(vertex, new LinkedList<>());
	}

	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 2, 3 }, { 4, 3 }, { 1, 1 }, { 2, 1 } }));
		System.out.println(solution(new int[][] { { 4, 11 }, { 1, 12 }, { 8, 3 }, { 12, 7 }, { 4, 2 }, { 7, 11 },
				{ 4, 8 }, { 9, 6 }, { 10, 11 }, { 6, 10 }, { 3, 5 }, { 11, 1 }, { 5, 3 }, { 11, 9 }, { 3, 8 } }));
	}
}