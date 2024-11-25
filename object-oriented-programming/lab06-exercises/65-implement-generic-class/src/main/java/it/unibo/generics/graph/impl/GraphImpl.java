package it.unibo.generics.graph.impl;

import it.unibo.generics.graph.api.Graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of {@link Graph} only storing explicitly defined arcs.
 * It is the ideal choice when handling poorly connected graphs.
 *
 * @param <N> the type of the nodes of the graph
 */
public final class GraphImpl<N> implements Graph<N> {

    private final Map<N, Set<N>> edges = new LinkedHashMap<>();

    @SafeVarargs
    private boolean nodesExist(final N... nodes) {
        for (final N node : nodes) {
            if (!edges.containsKey(node)) {
                throw new IllegalArgumentException("No such node: " + node);
            }
        }
        return true;
    }

    @Override
    public void addEdge(final N source, final N target) {
        if (nodesExist(source, target)) {
            edges.get(source).add(target);
        }
    }

    @Override
    public void addNode(final N node) {
        edges.putIfAbsent(node, new HashSet<>());
    }

    @Override
    public List<N> getPath(final N source, final N target) {
        if (nodesExist(source, target)) {
            final Map<N, N> predecessors = new LinkedHashMap<>();
            final List<N> queue = new java.util.LinkedList<>();
            queue.add(source);
            predecessors.put(source, null);

            while (!queue.isEmpty()) {
                final N current = queue.remove(0);
                if (current.equals(target)) {
                    return buildPath(predecessors, target);
                }
                for (final N neighbor : edges.get(current)) {
                    if (!predecessors.containsKey(neighbor)) {
                        queue.add(neighbor);
                        predecessors.put(neighbor, current);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private List<N> buildPath(final Map<N, N> predecessors, final N target) {
        final List<N> path = new java.util.LinkedList<>();
        for (N at = target; at != null; at = predecessors.get(at)) {
            path.add(0, at);
        }
        return path;
    }

    @Override
    public Set<N> linkedNodes(final N node) {
        return edges.get(node);
    }

    @Override
    public Set<N> nodeSet() {
        return new HashSet<>(edges.keySet());
    }
}