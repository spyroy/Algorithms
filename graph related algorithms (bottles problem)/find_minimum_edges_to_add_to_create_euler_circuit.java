package Graph_Related_algorithms;

//program to find minimum edge 
//required to make Euler Circuit 
import java.io.*;
import java.util.*;

public class find_minimum_edges_to_add_to_create_euler_circuit {

	// Depth-First Search to find
	// a connected component
	// not needed if the graph is connected
	static void dfs(Vector<Integer>[] g, int[] vis, int[] odd, int[] deg, int comp, int v) {
		vis[v] = 1;
		if (deg[v] % 2 == 1)
			odd[comp]++;
		for (int u : g[v])
			if (vis[u] == 0)
				dfs(g, vis, odd, deg, comp, u);
	}

	// Return minimum edge required
	// to make Euler Circuit
	static int minEdge(int n, int m, int[] s, int[] d) {

		// g : to store adjacency list
		// representation of graph.
		// e : to store list of even degree vertices
		// o : to store list of odd degree vertices
		@SuppressWarnings("unchecked")
		Vector<Integer>[] g = new Vector[n + 1];
		Vector<Integer> e = new Vector<>();
		Vector<Integer> o = new Vector<>();

		for (int i = 0; i < n + 1; i++)
			g[i] = new Vector<>();

		// Degrees of vertices
		int[] deg = new int[n + 1];

		// To store visited in DFS
		int[] vis = new int[n + 1];

		// Number of odd nodes in components
		int[] odd = new int[n + 1];
		Arrays.fill(deg, 0);
		Arrays.fill(vis, 0);
		Arrays.fill(odd, 0);

		for (int i = 0; i < m; i++) {
			g[s[i]].add(d[i]);
			g[d[i]].add(s[i]);
			deg[s[i]]++;
			deg[d[i]]++;
		}

		// 'ans' is result and
		// 'comp' is component id
		int ans = 0, comp = 0;
		for (int i = 1; i <= n; i++) {
			if (vis[i] == 0) {
				comp++;
				dfs(g, vis, odd, deg, comp, i);

				// Checking if connected component
				// is odd.
				if (odd[comp] == 0)
					e.add(comp);

				// Checking if connected component
				// is even.
				else
					o.add(comp);
			}
		}

		// If whole graph is a single connected
		// component with even degree.
		if (o.size() == 0 && e.size() == 1)
			return 0;

		// If all connected component is even
		if (o.size() == 0)
			return e.size();

		// If graph have atleast one
		// even connected component
		if (e.size() != 0)
			ans += e.size();

		// For all the odd connected component.
		for (int i : o)
			ans += odd[i] / 2;

		return ans;
	}

	public static void main(String[] args) throws IOException {
		int n = 3, m = 2;
		int[] source = { 1, 2 };
		int[] destination = { 2, 3 };

		System.out.println(minEdge(n, m, source, destination));
	}
}