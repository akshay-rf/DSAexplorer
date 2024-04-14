package DSAhelper;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

class GraphWin extends JPanel {
    public int chosen;
    public Application mainClass;

    public GraphWin(Application mainClass) {
        this.mainClass = mainClass;
        this.chosen = 0;
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill, insets 5", "[center]", "[center]"));
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "fill,700:650"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "background:lighten(@background, 3%)");

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainClass.getContentPane().removeAll();
                mainClass.getContentPane().add(new Home(mainClass));
                mainClass.getContentPane().revalidate();
                mainClass.getContentPane().repaint();
            }
        });

        JLabel title = new JLabel("Graph Algorithm Analysis");
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");

        JPanel titlePanel = new JPanel(new MigLayout("wrap, insets 0", "[][grow]"));
        titlePanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        titlePanel.add(back);
        titlePanel.add(title, "growx 50");

        JPanel inputPanel = new JPanel(new MigLayout("wrap, insets 0", "[grow][][][]"));
        inputPanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        input = new JTextField();
        input.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter edges (example: 1-2 3-4 2-5 5-6)");
        input.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc: 6;" +
                "showClearButton: true;" +
                "minimumWidth: 5");
        element = new JTextField();
        element.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        element.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc: 6");

        JComboBox algorithms = new JComboBox(options);
        algorithms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosen = algorithms.getSelectedIndex();
                updateEducate(chosen);
                System.out.println(options[chosen]);
            }
        });
        JButton sort = new JButton("Start");
        sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosen = algorithms.getSelectedIndex();
                try {
                    if(element.getText().length()>1 && element.getText().chars().allMatch(Character::isDigit)) {
                        updateTextArea(input.getText(), element.getText(), chosen);
                    }else if(element.getText().length()>1 && !element.getText().chars().allMatch(Character::isDigit) || element.getText().isEmpty()){
                        element.getText().charAt(Integer.MAX_VALUE);
                    }else{
                        updateTextArea(input.getText(), element.getText(), chosen);
                    }
                }catch(IndexOutOfBoundsException e1){
                    JOptionPane.showMessageDialog(mainClass,
                            "Only one element to search",
                            "Invalid Search",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        inputPanel.add(input, "growx 20");
        inputPanel.add(element);
        inputPanel.add(algorithms);
        inputPanel.add(sort);

        output = new JEditorPane("text/html", """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Breadth-First Search (BFS) Overview</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            padding: 20px;
                        }

                        .highlight {
                            font-weight: bold;
                        }
                    </style>
                </head>
                <body>
                    <h1>Breadth-First Search (BFS) Overview</h1>
                                    
                    <div class="section">
                        <h2>Algorithm Description</h2>
                        <p>Breadth-First Search (BFS) is a graph traversal algorithm that explores all nodes of a graph level by level.</p>
                        <p>Key steps of BFS:</p>
                        <ol>
                            <li>Choose a starting node (e.g., <span class="highlight">source</span>).</li>
                            <li>Initialize a queue and enqueue the <span class="highlight">source</span> node.</li>
                            <li>While the queue is not empty:</li>
                            <ul>
                                <li>Dequeue a node (<span class="highlight">current</span>) from the front of the queue.</li>
                                <li>Visit and process the <span class="highlight">current</span> node.</li>
                                <li>Enqueue all unvisited neighboring nodes of <span class="highlight">current</span>.</li>
                            </ul>
                        </ol>
                    </div>
                                    
                    <div class="section">
                        <h2>Key Concepts</h2>
                        <ul>
                            <li><strong>Queue:</strong> BFS uses a queue to manage the order of nodes to be visited.</li>
                            <li><strong>Level-Order Traversal:</strong> Nodes are visited level by level, exploring all nodes at the current level before moving to the next level.</li>
                            <li><strong>Visited Nodes:</strong> Use a set or array to track visited nodes to avoid processing the same node multiple times.</li>
                        </ul>
                    </div>
                                    
                    <div class="section">
                        <h2>Time Complexity</h2>
                        <p>The time complexity of BFS is <strong>O(V + E)</strong>:</p>
                        <ul>
                            <li><strong>V:</strong> Number of vertices (nodes) in the graph.</li>
                            <li><strong>E:</strong> Number of edges in the graph.</li>
                        </ul>
                    </div>
                                    
                    <div class="section">
                        <h2>Visualization</h2>
                        <p>BFS can be visualized using HTML elements like nodes and edges to demonstrate the traversal process.</p>
                    </div>
                                    
                </body>
                </html>
                """);
//            output.setLineWrap(true);
        output.setEnabled(false);
        JScrollPane scroll = new JScrollPane(output);
        output.putClientProperty(FlatClientProperties.STYLE, ""+
                "background: lighten(@background, 10%)");

//            panel.add(back);
        panel.add(titlePanel);
        panel.add(inputPanel, "gapy 10");
        panel.add(scroll,  "gapy 5");

        add(panel);
    }

    private void updateEducate(int choice){
        switch (choice){
            case 0:
                output.setText("""
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Breadth-First Search (BFS) Overview</title>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    padding: 20px;
                                }
                                .section {
                                    margin-bottom: 20px;
                                }
                                .highlight {
                                    font-weight: bold;
                                }
                            </style>
                        </head>
                        <body>
                            <h1>Breadth-First Search (BFS) Overview</h1>
                                                    
                            <div class="section">
                                <h2>Algorithm Description</h2>
                                <p>Breadth-First Search (BFS) is a graph traversal algorithm that explores all nodes of a graph level by level.</p>
                                <p>Key steps of BFS:</p>
                                <ol>
                                    <li>Choose a starting node (e.g., <span class="highlight">source</span>).</li>
                                    <li>Initialize a queue and enqueue the <span class="highlight">source</span> node.</li>
                                    <li>While the queue is not empty:</li>
                                    <ul>
                                        <li>Dequeue a node (<span class="highlight">current</span>) from the front of the queue.</li>
                                        <li>Visit and process the <span class="highlight">current</span> node.</li>
                                        <li>Enqueue all unvisited neighboring nodes of <span class="highlight">current</span>.</li>
                                    </ul>
                                </ol>
                            </div>
                                                    
                            <div class="section">
                                <h2>Key Concepts</h2>
                                <ul>
                                    <li><strong>Queue:</strong> BFS uses a queue to manage the order of nodes to be visited.</li>
                                    <li><strong>Level-Order Traversal:</strong> Nodes are visited level by level, exploring all nodes at the current level before moving to the next level.</li>
                                    <li><strong>Visited Nodes:</strong> Use a set or array to track visited nodes to avoid processing the same node multiple times.</li>
                                </ul>
                            </div>
                                                    
                            <div class="section">
                                <h2>Time Complexity</h2>
                                <p>The time complexity of BFS is <strong>O(V + E)</strong>:</p>
                                <ul>
                                    <li><strong>V:</strong> Number of vertices (nodes) in the graph.</li>
                                    <li><strong>E:</strong> Number of edges in the graph.</li>
                                </ul>
                            </div>
                                                    
                            <div class="section">
                                <h2>Visualization</h2>
                                <p>BFS can be visualized using HTML elements like nodes and edges to demonstrate the traversal process.</p>
                            </div>
                                                    
                        </body>
                        </html>
                        """);
                output.setCaretPosition(0);
                break;
            case 1:
                output.setText("""
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Depth-First Search (DFS) Overview</title>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    padding: 20px;
                                }

                                .highlight {
                                    font-weight: bold;
                                }
                            </style>
                        </head>
                        <body>
                            <h1>Depth-First Search (DFS) Overview</h1>
                                                    
                            <div class="section">
                                <h2>Algorithm Description</h2>
                                <p>Depth-First Search (DFS) is a graph traversal algorithm that explores as far as possible along each branch before backtracking.</p>
                                <p>Key steps of DFS:</p>
                                <ol>
                                    <li>Choose a starting node (e.g., <span class="highlight">source</span>).</li>
                                    <li>Initialize a stack (or recursion) and push the <span class="highlight">source</span> node.</li>
                                    <li>While the stack is not empty:</li>
                                    <ul>
                                        <li>Pop a node (<span class="highlight">current</span>) from the top of the stack.</li>
                                        <li>Visit and process the <span class="highlight">current</span> node.</li>
                                        <li>Push all unvisited neighboring nodes of <span class="highlight">current</span> onto the stack.</li>
                                    </ul>
                                </ol>
                            </div>
                                                    
                            <div class="section">
                                <h2>Key Concepts</h2>
                                <ul>
                                    <li><strong>Stack (or Recursion):</strong> DFS uses a stack (or system call stack with recursion) to manage the order of nodes to be visited.</li>
                                    <li><strong>Backtracking:</strong> DFS explores as deep as possible along each branch before backtracking to explore other branches.</li>
                                    <li><strong>Visited Nodes:</strong> Use a set or array to track visited nodes to avoid processing the same node multiple times.</li>
                                </ul>
                            </div>
                                                    
                            <div class="section">
                                <h2>Time Complexity</h2>
                                <p>The time complexity of DFS is <strong>O(V + E)</strong>:</p>
                                <ul>
                                    <li><strong>V:</strong> Number of vertices (nodes) in the graph.</li>
                                    <li><strong>E:</strong> Number of edges in the graph.</li>
                                </ul>
                            </div>
                                                    
                            <div class="section">
                                <h2>Visualization</h2>
                                <p>DFS can be visualized using HTML elements like nodes and edges to demonstrate the traversal process.</p>
                            </div>
                                                    
                        </body>
                        </html>
                        """);
                output.setCaretPosition(0);
                break;
        }
    }

    private void updateTextArea(String edges, String key, int chosen){
        int result=0;
        String res = "";
        comparisons=0;
        boolean charArray = false;
        String[] tokens = edges.split(" ");
        try{
            Integer.parseInt(tokens[0]);
        }catch(NumberFormatException e){
            if(tokens[0].split("-")[0].length()==1){
                charArray=true;
            }
        }

        if(charArray){
            try {
                long startTime = System.nanoTime();

                Map<Character, List<Character>> graph = buildGraph(tokens);
                switch (chosen) {
                    case 0:
                        res = bfs(graph, key.charAt(0));
                        break;
                    case 1:
                        res = dfs(graph, key.charAt(0));
                        break;
                }
                long endTime = System.nanoTime();
                double elapsedTime = (endTime - startTime)/1000000.0;
                String sortedChars = String.format("""
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>BFS Traversal Performance Analysis</title>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    line-height: 1.6;
                                    margin: 20px;
                                    padding: 20px;
                                }
                                h1, h2, h3 {
                                    color: #333;
                                }
                                p {
                                    line-height: 1.5;
                                }
                                table {
                                    width:500px;
                                    border-collapse: collapse;
                                    margin-bottom: 20px;
                                }
                                table, th, td {
                                    border: 1px solid #ddd;
                                    padding: 10px;
                                    text-align: left;
                                }
                            </style>
                        </head>
                        <body>
                            <h1>%s Traversal Performance Analysis</h1>
                                                    
                            <h2>Dataset Details</h2>
                            %s
                                                    
                            <h2>%s Performance Metrics</h2>
                            <pre>
                            Traversal: %s
                            Elapsed Time: %.4f milliseconds
                            Space Complexity (Visited Nodes): %d
                            </pre>
                                                    
                        </body>
                        </html>
                        """, options[chosen], convertEdgesToHTMLTable(tokens), options[chosen], res, elapsedTime, comparisons);
                output.setText(sortedChars.toString());
                output.setCaretPosition(0);
            }catch(Exception e){
                JOptionPane.showMessageDialog(mainClass,
                        "Only enter character edges.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }else{
            try {
                long startTime = System.nanoTime();

                switch (chosen) {
                    case 0:
                        res = bfsNum(tokens, key.charAt(0));
                        break;
                    case 1:
                        res = dfsNum(tokens, key.charAt(0));
                        break;
                }
                long endTime = System.nanoTime();
                double elapsedTime = (endTime - startTime)/1000000.0;
                String sortedChars = String.format("""
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>BFS Traversal Performance Analysis</title>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    line-height: 1.6;
                                    margin: 20px;
                                    padding: 20px;
                                }
                                h1, h2, h3 {
                                    color: #333;
                                }
                                p {
                                    line-height: 1.5;
                                }
                                table {
                                    width:500px;
                                    border-collapse: collapse;
                                    margin-bottom: 20px;
                                }
                                table, th, td {
                                    border: 1px solid #ddd;
                                    padding: 10px;
                                    text-align: left;
                                }
                            </style>
                        </head>
                        <body>
                            <h1>%s Traversal Performance Analysis</h1>
                                                    
                            <h2>Dataset Details</h2>
                            %s
                                                    
                            <h2>%s Performance Metrics</h2>
                            <pre>
                            Traversal: %s
                            Elapsed Time: %.4f milliseconds
                            Space Complexity (Visited Nodes): %d
                            </pre>
                                                    
                        </body>
                        </html>
                        """, options[chosen], convertEdgesToHTMLTable(tokens), options[chosen], res, elapsedTime, comparisons);
                output.setText(sortedChars.toString());
                output.setCaretPosition(0);
            }catch(Exception e){
                JOptionPane.showMessageDialog(mainClass,
                        "Only enter integer edges following the format.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String convertEdgesToHTMLTable(String[] edges) {
        // Map to store neighboring nodes for each node
        Map<Character, List<Character>> adjacencyList = new HashMap<>();

        // Build the adjacency list from edge strings
        for (String edge : edges) {
            String[] nodes = edge.split("-");
            char node1 = nodes[0].charAt(0);
            char node2 = nodes[1].charAt(0);

            if (!adjacencyList.containsKey(node1)) {
                adjacencyList.put(node1, new ArrayList<>());
            }
            adjacencyList.get(node1).add(node2);

            if (!adjacencyList.containsKey(node2)) {
                adjacencyList.put(node2, new ArrayList<>());
            }
            adjacencyList.get(node2).add(node1);
        }

        // Generate HTML table code for the adjacency list
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table>\n");
        htmlTable.append("<tr><th>Node</th><th>Neighboring Nodes</th></tr>\n");

        for (char node : adjacencyList.keySet()) {
            List<Character> neighbors = adjacencyList.get(node);
            StringBuilder neighborStr = new StringBuilder();
            for (int i = 0; i < neighbors.size(); i++) {
                neighborStr.append(neighbors.get(i));
                if (i < neighbors.size() - 1) {
                    neighborStr.append(",");
                }
            }

            htmlTable.append("<tr>\n");
            htmlTable.append("<td>").append(node).append("</td>\n");
            htmlTable.append("<td>").append(neighborStr).append("</td>\n");
            htmlTable.append("</tr>\n");
        }

        htmlTable.append("</table>");
        return htmlTable.toString();
    }


    private Map<Character, List<Character>> buildGraph(String[] edges) {
        Map<Character, List<Character>> graph = new HashMap<>();

        for (String edge : edges) {
            String[] nodes = edge.split("-");
            char from = nodes[0].charAt(0);
            char to = nodes[1].charAt(0);

            // Add edge from 'from' to 'to'
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            graph.get(from).add(to);

            // Add edge from 'to' to 'from' (assuming undirected graph)
            if (!graph.containsKey(to)) {
                graph.put(to, new ArrayList<>());
            }
            graph.get(to).add(from);
        }

        return graph;
    }

    private String bfs(Map<Character, List<Character>> graph, char start) {
        StringBuilder traversal = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            char current = queue.poll();
            traversal.append(current + " ");

            List<Character> neighbors = graph.getOrDefault(current, new ArrayList<>());
            for (char neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    comparisons++;
                }
            }
        }
        return traversal.toString();
    }

    public String bfsNum(String[] edges, char target) {
        // Create adjacency list to represent the graph
        Map<Character, List<Character>> graph = new HashMap<>();

        // Build the graph from the given edges
        for (String edge : edges) {
            String[] nodes = edge.split("-");
            char node1 = nodes[0].charAt(0);
            char node2 = nodes[1].charAt(0);

            graph.computeIfAbsent(node1, k -> new ArrayList<>()).add(node2);
            graph.computeIfAbsent(node2, k -> new ArrayList<>()).add(node1); // for undirected graph
        }

        // Perform BFS traversal starting from the target node
        StringBuilder traversal = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();

        queue.offer(target);
        visited.add(target);

        while (!queue.isEmpty()) {
            char current = queue.poll();
            traversal.append(current).append(" ");

            List<Character> neighbors = graph.getOrDefault(current, new ArrayList<>());
            for (char neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    comparisons++;
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return traversal.toString();
    }

    public String dfs(Map<Character, List<Character>> graph, char start) {
        traversal = new StringBuilder();
        visited = new HashSet<>();
        comparisons = 0; // Initialize comparisons (optional)

        // Perform DFS traversal starting from the specified start node
        dfsHelper(graph, start);

        return traversal.toString();
    }

    public String dfsNum(String[] edges, char target) {
        // Create adjacency list to represent the graph
        Map<Character, List<Character>> graph = new HashMap<>();

        // Build the graph from the given edges
        for (String edge : edges) {
            String[] nodes = edge.split("-");
            char node1 = nodes[0].charAt(0);
            char node2 = nodes[1].charAt(0);

            graph.computeIfAbsent(node1, k -> new ArrayList<>()).add(node2);
            graph.computeIfAbsent(node2, k -> new ArrayList<>()).add(node1); // for undirected graph
        }

        // Perform DFS traversal starting from the target node
        StringBuilder traversal = new StringBuilder();
        Set<Character> visited = new HashSet<>();

        dfsHelperNum(graph, target, visited, traversal);

        return traversal.toString();
    }

    private void dfsHelper(Map<Character, List<Character>> graph, char current) {
        // Mark the current node as visited and append to traversal string
        visited.add(current);
        traversal.append(current).append(" ");

        // Get the neighbors of the current node
        List<Character> neighbors = graph.getOrDefault(current, new ArrayList<>());

        // Recursively visit each unvisited neighbor
        for (char neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsHelper(graph, neighbor);
                comparisons++; // Increment comparisons (optional)
            }
        }
    }

    private void dfsHelperNum(Map<Character, List<Character>> graph, char current,
                                  Set<Character> visited, StringBuilder traversal) {
        // Mark the current node as visited and append to traversal string
        visited.add(current);
        traversal.append(current).append(" ");

        // Get the neighbors of the current node
        List<Character> neighbors = graph.getOrDefault(current, new ArrayList<>());

        // Recursively visit each unvisited neighbor
        for (char neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsHelperNum(graph, neighbor, visited, traversal);
                comparisons++;
            }
        }
    }

    private StringBuilder traversal;
    private Set<Character> visited;

    private int comparisons;
    JEditorPane output;
    JTextField input;
    JTextField element;

    private final String[] options = {"BFS", "DFS"};
}