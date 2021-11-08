package it.unibo.oop.lab06.generics1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class GraphImpl<N> implements Graph<N> {

	private final List<N> nodes;
	private final List<ArrayList<N>> edges;
	private int[] d;
	private int[] p;
	private Colour[] colour;
	
	public GraphImpl(final int nodeCount) {
		this.nodes = new ArrayList<N>();
		this.edges = new ArrayList<ArrayList<N>>(nodeCount);
		for (int i = 0; i < nodeCount; i++) {
			this.edges.add(new ArrayList<>());
		}
	}
	
	@Override
	public void addNode(N node) {
		if (node != null && !this.nodes.contains(node)) {
			this.nodes.add(node);
		}
	}
	
	@Override
	public void addEdge(N source, N target) {
		if (source != null && target != null) {
			this.edges.get(this.nodes.indexOf(source)).add(target);
		}
	}
	
	@Override
	public Set<N> nodeSet() {
		final Set<N> nodeSet = new TreeSet<N>();
		for (N i : this.nodes) {
			nodeSet.add(i);
		}
		return nodeSet;
	}
	
	@Override
	public Set<N> linkedNodes(N node) {
		final Set<N> linkedNodes = new TreeSet<N>();
		for (N i : this.edges.get(this.nodes.indexOf(node))) {
			linkedNodes.add(i);
		}
		return linkedNodes;
	}
	
	private void bfs(final int source) {
		//Initialization
		this.d = new int[this.nodes.size()];
		this.p = new int[this.nodes.size()];
		this.colour = new Colour[this.nodes.size()];	
		final Queue<Integer> Q = new ArrayDeque<>(5);
		for (N i : this.nodes) {
			this.colour[this.nodes.indexOf(i)] = Colour.WHITE;
			this.d[this.nodes.indexOf(i)] = Integer.MAX_VALUE;
			this.p[this.nodes.indexOf(i)] = -1;
		}
		this.colour[source] = Colour.GREY;
		this.d[source] = 0;
		this.p[source] = -1;
		Q.add(source);
		
		//bfs exploration of current graph
		while(!Q.isEmpty()) {
			int u = Q.poll();
			for (N i : this.edges.get(u)) {
				if (this.colour[this.nodes.indexOf(i)] == Colour.WHITE) {
					this.colour[this.nodes.indexOf(i)] = Colour.GREY;
					this.d[this.nodes.indexOf(i)] = this.d[u] + 1;
					this.p[this.nodes.indexOf(i)] = u;
					Q.add(this.nodes.indexOf(i));
				}
			}
			this.colour[u] = Colour.BLACK;
		}
	}
	
	@Override
	public List<N> getPath(N source, N target) {
		this.bfs(this.nodes.indexOf(source));
		final List<N> path = new ArrayList<>();
		int father = this.p[this.nodes.indexOf(target)];
		path.add(target);
		while (father != -1) {
			path.add(this.nodes.get(father));
			father = this.p[father];
		}
		return path;
	}
	
}
