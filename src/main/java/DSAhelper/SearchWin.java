package DSAhelper;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

class SearchWin extends JPanel {
    int chosen;
    Application mainClass;
    public SearchWin(Application mainClass){
        this.mainClass = mainClass;
        this.chosen = 0;
        init();
    }

    private void init(){
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

        JLabel title = new JLabel("Searching Algorithm Analysis");
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
        input.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter array (seperated by space)");
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
                    }else if(element.getText().length()>1 && !element.getText().chars().allMatch(Character::isDigit)){
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
                        <title>Linear Search Overview</title>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                line-height: 1.6;
                                padding: 20px;
                            }
                            h1, h2, h3 {
                                color: #333;
                            }
                            pre {
                                padding: 10px;
                                border-radius: 5px;
                                overflow-x: auto;
                            }
                        </style>
                    </head>
                    <body>
                        <h1>Linear Search Overview</h1>
                                        
                        <h2>Algorithm Description:</h2>
                        <p>Linear Search is a simple searching algorithm that sequentially checks each element in a collection until a match is found or the entire collection has been searched.</p>
                                        
                        <h2>Key Concepts:</h2>
                        <ul>
                            <li><strong>Sequential Search:</strong> Linear Search is a sequential or iterative search algorithm.</li>
                            <li><strong>Unsorted Data:</strong> It can efficiently locate a target in an unordered dataset.</li>
                            <li><strong>Comparison-Based:</strong> Each element is compared with the target until a match is found.</li>
                            <li><strong>Single Pass:</strong> It requires at most one pass through the dataset.</li>
                        </ul>
                                        
                        <h2>Example Scenario:</h2>
                        <p>Imagine you have a phone book with names listed in alphabetical order. To find a specific name (e.g., "John"), you would start from the beginning and check each name sequentially until you find a match or reach the end of the phone book.</p>
                                        
                        <h2>Usage and Considerations:</h2>
                        <p>Linear Search is suitable for small datasets or when the dataset is unsorted. It's a fundamental algorithm used in many practical scenarios:</p>
                        <ul>
                            <li><strong>Phone Book Search:</strong> Finding a contact by name.</li>
                            <li><strong>Checking Membership:</strong> Determining if an item is present in a list.</li>
                            <li><strong>Simple Search Tasks:</strong> Basic searching operations in applications.</li>
                        </ul>
                                        
                        <h2>Performance Analysis:</h2>
                        <p>The time complexity of Linear Search is O(n), where n is the number of elements in the dataset. This means the search time increases linearly with the size of the dataset.</p>
                        <p>In terms of space complexity, Linear Search requires only O(1) additional space for storing loop counters and temporary variables.</p>
                                        
                        <h2>Visual Representation:</h2>
                        <p>Imagine searching for a target value (e.g., a specific number) in an array or list. Linear Search visually represents a sequential scan through the dataset until the target is located or the end is reached.</p>
                        <p>For example, searching for the number 5 in the array [4, 2, 7, 1, 5, 3] involves checking each element one by one until the value 5 is found.</p>
                                        
                        <h2>Educational Value:</h2>
                        <p>Linear Search is a fundamental algorithm for understanding basic search techniques and iterative algorithms. It provides a building block for more complex searching and sorting algorithms in computer science.</p>
                        <p>By learning and implementing Linear Search, one can grasp essential concepts in algorithm design, problem-solving, and data structure operations.</p>
                                        
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
                                <title>Linear Search Overview</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        line-height: 1.6;
                                        padding: 20px;
                                    }
                                    h1, h2, h3 {
                                        color: #333;
                                    }
                                    pre {
                                        padding: 10px;
                                        border-radius: 5px;
                                        overflow-x: auto;
                                    }
                                </style>
                            </head>
                            <body>
                                <h1>Linear Search Overview</h1>
                                                        
                                <h2>Algorithm Description:</h2>
                                <p>Linear Search is a simple searching algorithm that sequentially checks each element in a collection until a match is found or the entire collection has been searched.</p>
                                                        
                                <h2>Key Concepts:</h2>
                                <ul>
                                    <li><strong>Sequential Search:</strong> Linear Search is a sequential or iterative search algorithm.</li>
                                    <li><strong>Unsorted Data:</strong> It can efficiently locate a target in an unordered dataset.</li>
                                    <li><strong>Comparison-Based:</strong> Each element is compared with the target until a match is found.</li>
                                    <li><strong>Single Pass:</strong> It requires at most one pass through the dataset.</li>
                                </ul>
                                                        
                                <h2>Example Scenario:</h2>
                                <p>Imagine you have a phone book with names listed in alphabetical order. To find a specific name (e.g., "John"), you would start from the beginning and check each name sequentially until you find a match or reach the end of the phone book.</p>
                                                        
                                <h2>Usage and Considerations:</h2>
                                <p>Linear Search is suitable for small datasets or when the dataset is unsorted. It's a fundamental algorithm used in many practical scenarios:</p>
                                <ul>
                                    <li><strong>Phone Book Search:</strong> Finding a contact by name.</li>
                                    <li><strong>Checking Membership:</strong> Determining if an item is present in a list.</li>
                                    <li><strong>Simple Search Tasks:</strong> Basic searching operations in applications.</li>
                                </ul>
                                                        
                                <h2>Performance Analysis:</h2>
                                <p>The time complexity of Linear Search is O(n), where n is the number of elements in the dataset. This means the search time increases linearly with the size of the dataset.</p>
                                <p>In terms of space complexity, Linear Search requires only O(1) additional space for storing loop counters and temporary variables.</p>
                                                        
                                <h2>Visual Representation:</h2>
                                <p>Imagine searching for a target value (e.g., a specific number) in an array or list. Linear Search visually represents a sequential scan through the dataset until the target is located or the end is reached.</p>
                                <p>For example, searching for the number 5 in the array [4, 2, 7, 1, 5, 3] involves checking each element one by one until the value 5 is found.</p>
                                                        
                                <h2>Educational Value:</h2>
                                <p>Linear Search is a fundamental algorithm for understanding basic search techniques and iterative algorithms. It provides a building block for more complex searching and sorting algorithms in computer science.</p>
                                <p>By learning and implementing Linear Search, one can grasp essential concepts in algorithm design, problem-solving, and data structure operations.</p>
                                                        
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
                                <title>Jump Search Overview</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        line-height: 1.6;
                                        padding: 20px;
                                    }
                                    h1, h2, h3 {
                                        color: #333;
                                    }
                                    pre {
                                        padding: 10px;
                                        border-radius: 5px;
                                        overflow-x: auto;
                                    }
                                </style>
                            </head>
                            <body>
                                <h1>Jump Search Overview</h1>
                                                       
                                <h2>Algorithm Description:</h2>
                                <p>Jump Search is a searching algorithm used for finding an element's position in a sorted array. It works by making jumps ahead in fixed steps and then performing a linear search in the identified block.</p>
                                                       
                                <h2>Key Concepts:</h2>
                                <ul>
                                    <li><strong>Sorted Array:</strong> Jump Search requires the array to be sorted in ascending order.</li>
                                    <li><strong>Jump Steps:</strong> It jumps ahead in fixed steps to cover larger portions of the array.</li>
                                    <li><strong>Linear Search:</strong> It performs a linear search within the identified block after jumping.</li>
                                    <li><strong>Optimal Jump Size:</strong> The optimal step size (`jumpSize`) is typically √n, where n is the array length.</li>
                                </ul>
                                                       
                                <h2>Example Scenario:</h2>
                                <p>Imagine searching for a specific value (e.g., 30) in a sorted array [10, 20, 30, 40, 50, 60, 70]. Jump Search starts by jumping ahead in fixed steps (e.g., jumpSize = 2) and then performs a linear search within the identified block until the target value is found or exceeded.</p>
                                                       
                                <h2>Usage and Considerations:</h2>
                                <p>Jump Search is suitable for large sorted arrays where direct access to elements is costly. It offers an efficient approach by reducing the number of comparisons compared to linear search.</p>
                                <p>However, for very large datasets, more advanced algorithms like Binary Search may provide better performance due to their logarithmic time complexity.</p>
                                                       
                                <h2>Performance Analysis:</h2>
                                <p>The time complexity of Jump Search is O(√n), where n is the number of elements in the array. This makes it more efficient than linear search for large datasets.</p>
                                <p>Jump Search requires O(1) additional space for storing loop counters and temporary variables, making it space-efficient.</p>
                                                       
                                <h2>Educational Value:</h2>
                                <p>Jump Search is a valuable algorithm for understanding search optimization techniques. It combines the concept of jumping ahead with a subsequent linear search, offering insights into balanced search strategies and algorithmic efficiency.</p>
                                <p>By learning and implementing Jump Search, one can explore the trade-offs between time complexity, space complexity, and search performance, leading to a deeper understanding of algorithm design and optimization.</p>
                                                       
                            </body>
                            </html>
                            """);
                output.setCaretPosition(0);
                break;
            case 2:
                output.setText("""
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Binary Search Overview</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        line-height: 1.6;
                                        padding: 20px;
                                    }
                                    h1, h2, h3 {
                                        color: #333;
                                    }
                                    pre {
                                        padding: 10px;
                                        border-radius: 5px;
                                        overflow-x: auto;
                                    }
                                </style>
                            </head>
                            <body>
                                <h1>Binary Search Overview</h1>
                                                        
                                <h2>Algorithm Description:</h2>
                                <p>Binary Search is a searching algorithm used for finding an element's position in a sorted array. It works by repeatedly dividing the search interval in half until the target element is found.</p>
                                                        
                                <h2>Key Concepts:</h2>
                                <ul>
                                    <li><strong>Sorted Array:</strong> Binary Search requires the array to be sorted in ascending order.</li>
                                    <li><strong>Divide and Conquer:</strong> It applies the divide and conquer strategy to efficiently locate the target element.</li>
                                    <li><strong>Middle Element:</strong> It compares the target element with the middle element of the current search interval and narrows down the search based on the comparison.</li>
                                    <li><strong>Efficiency:</strong> Binary Search has a time complexity of O(log n), making it highly efficient for large datasets.</li>
                                </ul>
                                                        
                                <h2>Example Scenario:</h2>
                                <p>Imagine searching for a specific value (e.g., 30) in a sorted array [10, 20, 30, 40, 50, 60, 70]. Binary Search starts by comparing the target value with the middle element of the array and narrows down the search interval based on the comparison.</p>
                                                        
                                <h2>Usage and Considerations:</h2>
                                <p>Binary Search is ideal for large sorted datasets where direct access to elements is feasible. It significantly reduces the number of comparisons compared to linear search.</p>
                                <p>However, Binary Search requires the array to be sorted initially, which may add preprocessing overhead.</p>
                                                        
                                <h2>Performance Analysis:</h2>
                                <p>The time complexity of Binary Search is O(log n), where n is the number of elements in the array. This logarithmic time complexity ensures efficient searching even for large datasets.</p>
                                <p>Binary Search requires O(1) additional space for storing loop counters and temporary variables, making it space-efficient.</p>
                                                        
                                <h2>Educational Value:</h2>
                                <p>Binary Search is a fundamental algorithm for understanding efficient search strategies and divide and conquer techniques. It offers insights into logarithmic time complexity and the importance of sorted data for optimization.</p>
                                <p>By learning and implementing Binary Search, one can gain valuable experience in algorithmic design, problem-solving, and performance analysis, essential for computer science and software development.</p>
                                                        
                            </body>
                            </html>
                            """);
                output.setCaretPosition(0);
                break;

            case 3:
                output.setText("""
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Ternary Search Overview</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        line-height: 1.6;
                                        padding: 20px;
                                    }
                                    h1, h2, h3 {
                                        color: #333;
                                    }
                                    pre {
                                        padding: 10px;
                                        border-radius: 5px;
                                        overflow-x: auto;
                                    }
                                </style>
                            </head>
                            <body>
                                <h1>Ternary Search Overview</h1>
                                                       
                                <h2>Algorithm Description:</h2>
                                <p>Ternary Search is a searching algorithm used to find the position of a specific value in a sorted array. It divides the array into three parts and determines which part the target element is likely to be in.</p>
                                                       
                                <h2>Key Concepts:</h2>
                                <ul>
                                    <li><strong>Sorted Array:</strong> Ternary Search requires the array to be sorted in ascending order.</li>
                                    <li><strong>Divide and Conquer:</strong> It divides the search interval into three parts and selects one part based on comparisons with the target element.</li>
                                    <li><strong>Recursion:</strong> Ternary Search can be implemented recursively to narrow down the search interval.</li>
                                    <li><strong>Efficiency:</strong> Ternary Search has a time complexity of O(log3 n), making it efficient for large datasets.</li>
                                </ul>
                                                       
                                <h2>Example Scenario:</h2>
                                <p>Imagine searching for a specific value (e.g., 30) in a sorted array [10, 20, 30, 40, 50, 60, 70]. Ternary Search divides the array into three parts based on comparisons with the target element, narrowing down the search interval with each step.</p>
                                                       
                                <h2>Usage and Considerations:</h2>
                                <p>Ternary Search is suitable for scenarios where the search space can be divided into three distinct parts. It can be more efficient than Binary Search in certain situations.</p>
                                <p>However, Ternary Search may involve more comparisons compared to Binary Search due to its partitioning into three segments.</p>
                                                       
                                <h2>Performance Analysis:</h2>
                                <p>The time complexity of Ternary Search is O(log3 n), where n is the number of elements in the array. Although less common than Binary Search, Ternary Search offers a logarithmic time complexity for efficient search operations.</p>
                                <p>Ternary Search requires O(log3 n) additional space for recursive function calls, making it space-efficient for large datasets.</p>
                                                       
                                <h2>Educational Value:</h2>
                                <p>Ternary Search provides insights into advanced search strategies beyond binary partitioning. It demonstrates the concept of dividing the search space into multiple segments and selecting the relevant segment based on comparisons.</p>
                                <p>By learning and implementing Ternary Search, one can explore recursive algorithms, logarithmic time complexity, and alternative search techniques, enriching their understanding of algorithm design and optimization.</p>
                                                       
                            </body>
                            </html>
                            """);
                output.setCaretPosition(0);
                break;
        }
    }

    private void updateTextArea(String array, String key, int choice){
        int result=0;
        String res = "";
        comparisons=0;
        boolean charArray = false;
        String[] tokens = array.split(" ");
        try{
            Integer.parseInt(tokens[0]);
        }catch(NumberFormatException e){
            if(tokens[0].length()==1){
                charArray=true;
            }
        }

        if(charArray){
            char[] chars = new char[tokens.length];

            try {
                for (int i = 0; i < tokens.length; i++) {
                    if(tokens[i].length()!=1 || tokens[i].chars().allMatch(Character::isDigit)){
                        chars[i]=tokens[i].charAt(Integer.MAX_VALUE     );
                    }else {
                        chars[i] = tokens[i].charAt(0);
                    }
                }

                long startTime = System.nanoTime();
                String timeCom = "O(n)";
                String spaceCom = "O(n)";
                String bestCom = "O(n)";
                String avgCom = "O(n)";
                String worstCom = "O(n)";
                Arrays.sort(chars);
                StringBuilder arraySorted = new StringBuilder("[");
                for(char c : chars){
                    arraySorted.append(c);
                    if(c!=chars[chars.length-1]){
                        arraySorted.append(", ");
                    }

                }

                switch(choice){
                    case 0:
                        result = linearSearch(chars, key);
                        timeCom = "O(n)";
                        spaceCom = "O(1)";
                        bestCom = "O(1)";
                        avgCom = "O(n)";
                        worstCom = "O(n)";
                        break;
                    case 1:
                        result = jumpSearch(chars, key);
                        timeCom = "O(√n)";
                        spaceCom = "O(1)";
                        bestCom = "O(1)";
                        avgCom = "O(√n)";
                        worstCom = "O(√n)";
                        break;
                    case 2:
                        result = binarySearchIterative(chars, key);
                        timeCom = "O(log n)";
                        spaceCom = "O(1)";
                        bestCom = "O(1)";
                        avgCom = "O(log n)";
                        worstCom = "O(log n)";
                        break;
                    case 3:
                        result = ternarySearch(chars, key);
                        timeCom = "O(log3 n)";
                        spaceCom = "O(1)";
                        bestCom = "O(1)";
                        avgCom = "O(log3 n)";
                        worstCom = "O(log3 n)";
                        break;
                }
                long endTime = System.nanoTime();
                double elapsedTime = (endTime - startTime)/1000000.0;

                if(result == -1){
                    res = "Element not found.";
                }else{
                    res = res+result;
                }
                String sortedChars = String.format("""
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Search Algorithm Performance Analysis</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        line-height: 1.6;
                                        margin: 10px;
                                        padding: 10px;
                                    }
                                    pre {
                                        padding: 10px;
                                        border-radius: 5px;
                                        overflow-x: auto;
                                    }
                                </style>
                            </head>
                            <body>
                                <h2>Algorithm Performance Analysis (%s):</h2>
                                                        
                                <pre>
                                    Dataset Size: %d
                                    Algorithm Name: %s
                                                        
                                    Time Complexity Analysis:
                                    - Elapsed Time: %.4f milliseconds
                                    - Time Complexity Class: %s
                                                        
                                    Performance Metrics:
                                    - Comparisons: %d
                                    - Space Complexity: %s
                                
                                    Example Scenarios:
                                    - Target Value: %s
                                    - Sorted Dataset: %s
                                    - Found at index: %s
                                                        
                                    More Details:
                                    - Best Case: %s
                                    - Average Case: %s
                                    - Worst Case: %s
                                </pre>
                                                        
                            </body>
                            </html>
                            """, options[choice], chars.length, options[choice], elapsedTime, timeCom, comparisons, spaceCom, key, arraySorted+"]", res, bestCom, avgCom, worstCom);

                output.setText(sortedChars.toString());
                output.setCaretPosition(0);
            }catch(Exception e){
                JOptionPane.showMessageDialog(mainClass,
                        "Only enter characters seperated by space.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }else {
            int[] numbers = new int[tokens.length];

            try {
                for (int i = 0; i < tokens.length; i++) {
                    numbers[i] = Integer.parseInt(tokens[i]);
                }
                long startTime = System.nanoTime();
                String timeCom = "O(n)";
                String spaceCom = "O(n)";
                String bestCom = "O(n)";
                String avgCom = "O(n)";
                String worstCom = "O(n)";
                Arrays.sort(numbers);
                StringBuilder arraySorted = new StringBuilder("[");
                for(int c : numbers){
                    arraySorted.append(c);
                    if(c!=numbers[numbers.length-1]){
                        arraySorted.append(", ");
                    }

                }

                switch(choice){
                    case 0:
                        result = linearSearchNum(numbers, key);
                        timeCom = "O(n)";
                        spaceCom = "O(1)";
                        bestCom = "O(1)";
                        avgCom = "O(n)";
                        worstCom = "O(n)";
                        break;
                    case 1:
                        result = jumpSearchNum(numbers, key);
                        timeCom = "O(√n)";
                        spaceCom = "O(1)";
                        bestCom = "O(1)";
                        avgCom = "O(√n)";
                        worstCom = "O(√n)";
                        break;
                    case 2:
                        result = binarySearchIterativeNum(numbers, key);
                        timeCom = "O(log n)";
                        spaceCom = "O(1)";
                        bestCom = "O(1)";
                        avgCom = "O(log n)";
                        worstCom = "O(log n)";
                        break;
                    case 3:
                        result = ternarySearchNum(numbers, key);
                        timeCom = "O(log3 n)";
                        spaceCom = "O(1)";
                        bestCom = "O(1)";
                        avgCom = "O(log3 n)";
                        worstCom = "O(log3 n)";
                        break;
                }


                long endTime = System.nanoTime();
                double elapsedTime = (endTime - startTime)/1000000.0;

                if(result == -1){
                    res = "Element not found.";
                }else{
                    res = res+result;
                }
                String sortedChars = String.format("""
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Search Algorithm Performance Analysis</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        line-height: 1.6;
                                        margin: 10px;
                                        padding: 10px;
                                    }
                                    pre {
                                        padding: 10px;
                                        border-radius: 5px;
                                        overflow-x: auto;
                                    }
                                </style>
                            </head>
                            <body>
                                <h2>Algorithm Performance Analysis (%s):</h2>
                                                        
                                <pre>
                                    Dataset Size: %d
                                    Algorithm Name: %s
                                                        
                                    Time Complexity Analysis:
                                    - Elapsed Time: %.4f milliseconds
                                    - Time Complexity Class: %s
                                                        
                                    Performance Metrics:
                                    - Comparisons: %d
                                    - Space Complexity: %s
                                
                                    Example Scenarios:
                                    - Target Value: %s
                                    - Sorted Dataset: %s
                                    - Found at index: %s
                                                        
                                    More Details:
                                    - Best Case: %s
                                    - Average Case: %s
                                    - Worst Case: %s
                                </pre>
                                                        
                            </body>
                            </html>
                            """, options[choice], numbers.length, options[choice], elapsedTime, timeCom, comparisons, spaceCom, key, arraySorted+"]", res, bestCom, avgCom, worstCom);


                output.setText(sortedChars.toString());
                output.setCaretPosition(0);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainClass,
                        "Only enter integer values seperated by space.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int linearSearch(char[] arr, String tar) {
        char target = tar.charAt(0);
        // Iterate through the array
        for (int i = 0; i < arr.length; i++) {
            // Check if the current element matches the target
            comparisons++;
            if (arr[i] == target) {
                // Return the index of the target character if found
                return i;
            }
        }
        // Return -1 if the target character is not found in the array
        return -1;
    }

    private int linearSearchNum(int[] arr, String target) {
        int num = Integer.parseInt(target);
        // Iterate through the array
        for (int i = 0; i < arr.length; i++) {
            // Check if the current element matches the target
            comparisons++;
            if (arr[i] == num) {
                // Return the index of the target character if found
                return i;
            }
        }
        // Return -1 if the target character is not found in the array
        return -1;
    }

    private int jumpSearch(char[] arr, String tar) {
        char target = tar.charAt(0);
        int n = arr.length;
        int blockSize = (int) Math.sqrt(n); // Calculate block size

        // Find the block where the target might be present
        int left = 0;
        int right = blockSize;
        while (right < n && arr[right] < target) {
            comparisons++;
            left = right;
            right = Math.min(right + blockSize, n);
        }

        // Perform linear search within the identified block
        for (int i = left; i < right; i++) {
            comparisons++;
            if (arr[i] == target) {
                return i; // Character found
            }
        }

        return -1; // Character not found
    }

    private int jumpSearchNum(int[] arr, String target) {
        int num = Integer.parseInt(target);
        int n = arr.length;
        int blockSize = (int) Math.sqrt(n); // Calculate block size

        // Find the block where the target might be present
        int left = 0;
        int right = blockSize;
        while (right < n && arr[right] < num) {
            comparisons++;
            left = right;
            right = Math.min(right + blockSize, n);
        }

        // Perform linear search within the identified block
        for (int i = left; i < right; i++) {
            comparisons++;
            if (arr[i] == num) {
                return i; // Character found
            }
        }

        return -1; // Character not found
    }

    private int binarySearchIterative(char[] arr, String tar) {
        char target = tar.charAt(0);
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the target is present at the middle element
            comparisons++;
            if (arr[mid] == target) {
                return mid;
            }

            // If the target is smaller than the middle element, search the left half
            comparisons++;
            if (target < arr[mid]) {
                right = mid - 1;
            } else { // If the target is larger than the middle element, search the right half
                left = mid + 1;
            }
        }

        // Element not found
        return -1;
    }

    private int binarySearchIterativeNum(int[] arr, String target) {
        int num = Integer.parseInt(target);
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the target is present at the middle element
            comparisons++;
            if (arr[mid] == num) {
                return mid;
            }

            // If the target is smaller than the middle element, search the left half
            comparisons++;
            if (num < arr[mid]) {
                right = mid - 1;
            } else { // If the target is larger than the middle element, search the right half
                left = mid + 1;
            }
        }

        // Element not found
        return -1;
    }

    private int ternarySearch(char[] arr, String tar) {
        char target = tar.charAt(0);
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            comparisons++;
            if (arr[mid1] == target) {
                return mid1;
            } else if (arr[mid2] == target) {
                return mid2;
            } else if (target < arr[mid1]) {
                right = mid1 - 1;
            } else if (target > arr[mid2]) {
                left = mid2 + 1;
            } else {
                left = mid1 + 1;
                right = mid2 - 1;
            }
        }

        return -1; // Element not found
    }

    private int ternarySearchNum(int[] arr, String target) {
        int num = Integer.parseInt(target);
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            comparisons++;
            if (arr[mid1] == num) {
                return mid1;
            } else if (arr[mid2] == num) {
                return mid2;
            } else if (num < arr[mid1]) {
                right = mid1 - 1;
            } else if (num > arr[mid2]) {
                left = mid2 + 1;
            } else {
                left = mid1 + 1;
                right = mid2 - 1;
            }
        }

        return -1; // Element not found
    }

    private int comparisons = 0;

    JTextField input;
    JTextField element;
    JEditorPane output;
    private final String[] options = {"Linear Search", "Jump Search", "Binary Search", "Ternary Search"};
}