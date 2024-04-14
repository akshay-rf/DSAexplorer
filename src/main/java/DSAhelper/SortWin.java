package DSAhelper;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SortWin extends JPanel {
    int chosen;
    public Application mainClass;
    public SortWin(Application mainClass){
        this.chosen=0;
        this.mainClass = mainClass;
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

        JLabel title = new JLabel("Sorting Algorithm Analysis");
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");

        JPanel titlePanel = new JPanel(new MigLayout("wrap, insets 0", "[][grow]"));
        titlePanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        titlePanel.add(back);
        titlePanel.add(title, "growx 50");

        JPanel inputPanel = new JPanel(new MigLayout("wrap, insets 0", "[grow][][]"));
        inputPanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        input = new JTextField();
        input.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter array (seperated by space)");
        input.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc: 6;" +
                "showClearButton: true;" +
                "minimumWidth: 5");
        JComboBox algorithms = new JComboBox(options);
        algorithms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosen = algorithms.getSelectedIndex();
                updateEducate(chosen);
            }
        });
        JButton sort = new JButton("Start");
        sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosen = algorithms.getSelectedIndex();
                updateTextArea(input.getText(), chosen);
            }
        });
        inputPanel.add(input, "growx 70");
        inputPanel.add(algorithms);
        inputPanel.add(sort);

        output = new JEditorPane("text/html", """
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Bubble Sort Overview</title>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                max-width: 600px;
                                margin: 0 auto;
                                padding: 20px;
                                color: #333;
                                line-height: 1.6;
                            }
                            h1, h2, h3 {
                                color: #004d99;
                            }
                            ul {
                                padding-left: 20px;
                            }
                        </style>
                    </head>
                    <body>
                        <h1>Bubble Sort Overview</h1>
                        <p>
                            Bubble Sort is a simple comparison-based sorting algorithm. It repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. This process continues until the list is fully sorted.
                        </p>
                        <h2>Key Points</h2>
                        <ul>
                            <li><strong>Time Complexity:</strong> O(n^2) in the worst and average cases</li>
                            <li><strong>Space Complexity:</strong> O(1) (in-place sorting algorithm)</li>
                            <li><strong>Algorithm Steps:</strong>
                                <ul>
                                    <li>Start from the beginning of the list.</li>
                                    <li>Compare adjacent elements and swap them if they are out of order.</li>
                                    <li>Repeat the above process until no more swaps are needed.</li>
                                </ul>
                            </li>
                        </ul>
                        <h2>Sorting Process</h2>
                        <p>
                            Bubble Sort works by repeatedly iterating through the list. During each iteration, it compares adjacent elements and swaps them if they are in the wrong order. The largest unsorted element "bubbles up" to its correct position in each pass.
                        </p>
                        <h2>Example</h2>
                        <p>Consider sorting the array <strong>[5, 3, 8, 2, 1]</strong> using Bubble Sort:</p>
                        <ol>
                            <li><strong>First Pass:</strong> [3, 5, 2, 1, 8]</li>
                            <li><strong>Second Pass:</strong> [3, 2, 1, 5, 8]</li>
                            <li><strong>Third Pass:</strong> [2, 1, 3, 5, 8]</li>
                            <li><strong>Fourth Pass:</strong> [1, 2, 3, 5, 8]</li>
                        </ol>
                        <p>
                            After four passes, the array is sorted in ascending order. Despite its simplicity, Bubble Sort is inefficient for large lists due to its quadratic time complexity.
                        </p>
                        <p>
                            Bubble Sort is primarily used for educational purposes and is rarely used in practical applications where more efficient sorting algorithms like Merge Sort or Quick Sort are preferred.
                        </p>
                    </body>
                    </html>""");
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

    private void updateTextArea(String array, int choice){
        comparisons=0;
        swaps=0;
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
                        chars[i]=tokens[i].charAt(Integer.MAX_VALUE);
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

                switch(choice){
                    case 0:
                        int n = chars.length;
                        boolean swapped;

                        for (int i = 0; i < n - 1; i++) {
                            swapped = false;
                            comparisons++;
                            for (int j = 0; j < n - 1 - i; j++) {
                                if (chars[j] > chars[j + 1]) {
                                    // Swap elements
                                    char temp = chars[j];
                                    chars[j] = chars[j + 1];
                                    chars[j + 1] = temp;
                                    swaps++;
                                    swapped = true;
                                }
                            }

                            // If no swap occurred in the inner loop, array is already sorted
                            if (!swapped) {
                                break;
                            }
                        }
                        timeCom = "O(n^2)";
                        spaceCom = "O(1)";
                        bestCom = "O(n)";
                        avgCom = "O(n^2)";
                        worstCom = "O(n^2)";
                        break;
                    case 1:
                        quickSort(chars, 0, chars.length - 1);
                        timeCom = "O(n log n)";
                        spaceCom = "O(log n)";
                        bestCom = "O(n log n)";
                        avgCom = "O(n log n)";
                        worstCom = "O(n^2)";
                        break;
                    case 2:
                        mergeSort(chars, 0, chars.length - 1);
                        timeCom = "O(n log n)";
                        spaceCom = "O(n)";
                        bestCom = "O(n log n)";
                        avgCom = "O(n log n)";
                        worstCom = "O(n log n)";
                        break;
                    case 3:
                        insertionSort(chars);
                        timeCom = "O(n^2)";
                        spaceCom = "O(1)";
                        bestCom = "O(n)";
                        avgCom = "O(n^2)";
                        worstCom = "O(n^2)";
                        break;
                    case 4:
                        heapSort(chars);
                        timeCom = "O(n log n)";
                        spaceCom = "O(1)";
                        bestCom = "O(n log n)";
                        avgCom = "O(n log n)";
                        worstCom = "O(n log n)";
                        break;
                }
                long endTime = System.nanoTime();
                double elapsedTime = (endTime - startTime)/1000000.0;
                StringBuilder arraySorted = new StringBuilder("[");
                for(char c : chars){
                    arraySorted.append(c);
                    if(c!=chars[chars.length-1]){
                        arraySorted.append(", ");
                    }

                }
                String sortedChars = String.format("""
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>YAML Content in HTML</title>
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
        Input Size: %d
        Algorithm Name: %s
        Sorted Array: %s

        Time Complexity Analysis:
        - Elapsed Time: %.4f milliseconds
        - Time Complexity Class: %s

        Performance Metrics:
        - Comparisons: %d
        - Swaps or Moves: %d
        - Space Complexity: %s
        
        Execution Details:
        - Best Case: %s (already sorted array)
        - Average Case: %s (randomly shuffled array)
        - Worst Case: %s (reverse-sorted array)
    </pre>

</body>
</html>

                            """, options[choice], chars.length, options[choice], arraySorted+"]", elapsedTime, timeCom, comparisons, swaps, spaceCom, bestCom
                        , avgCom, worstCom);

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

                switch(choice){
                    case 0:
                        int n = numbers.length;
                        boolean swapped;

                        for (int i = 0; i < n - 1; i++) {
                            swapped = false;
                            comparisons++;
                            for (int j = 0; j < n - 1 - i; j++) {
                                if (numbers[j] > numbers[j + 1]) {
                                    // Swap elements
                                    int temp = numbers[j];
                                    numbers[j] = numbers[j + 1];
                                    numbers[j + 1] = temp;
                                    swaps++;
                                    swapped = true;
                                }
                            }

                            // If no swap occurred in the inner loop, array is already sorted
                            if (!swapped) {
                                break;
                            }
                        }
                        timeCom = "O(n^2)";
                        spaceCom = "O(1)";
                        bestCom = "O(n)";
                        avgCom = "O(n^2)";
                        worstCom = "O(n^2)";
                        break;
                    case 1:
                        quickSortNum(numbers, 0, numbers.length - 1);
                        timeCom = "O(n log n)";
                        spaceCom = "O(log n)";
                        bestCom = "O(n log n)";
                        avgCom = "O(n log n)";
                        worstCom = "O(n^2)";
                        break;
                    case 2:
                        mergeSortNum(numbers, 0, numbers.length - 1);
                        timeCom = "O(n log n)";
                        spaceCom = "O(n)";
                        bestCom = "O(n log n)";
                        avgCom = "O(n log n)";
                        worstCom = "O(n log n)";
                        break;
                    case 3:
                        insertionSortNum(numbers);
                        timeCom = "O(n^2)";
                        spaceCom = "O(1)";
                        bestCom = "O(n)";
                        avgCom = "O(n^2)";
                        worstCom = "O(n^2)";
                        break;
                    case 4:
                        heapSortNum(numbers);
                        timeCom = "O(n log n)";
                        spaceCom = "O(1)";
                        bestCom = "O(n log n)";
                        avgCom = "O(n log n)";
                        worstCom = "O(n log n)";
                        break;
                }
                long endTime = System.nanoTime();
                double elapsedTime = (endTime - startTime)/1000000.0;
                StringBuilder arraySorted = new StringBuilder("[");
                for(int i : numbers){
                    arraySorted.append(i);
                    if(i!=numbers[numbers.length-1]){
                        arraySorted.append(", ");
                    }

                }
                String sortedChars = String.format("""
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>YAML Content in HTML</title>
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
        Input Size: %d
        Algorithm Name: %s
        Sorted Array: %s

        Time Complexity Analysis:
        - Elapsed Time: %.4f milliseconds
        - Time Complexity Class: %s

        Performance Metrics:
        - Comparisons: %d
        - Swaps or Moves: %d
        - Space Complexity: %s
        
        Execution Details:
        - Best Case: %s (already sorted array)
        - Average Case: %s (randomly shuffled array)
        - Worst Case: %s (reverse-sorted array)
    </pre>

</body>
</html>

                            """, options[choice], numbers.length, options[choice], arraySorted+"]", elapsedTime, timeCom, comparisons, swaps, spaceCom, bestCom
                        , avgCom, worstCom);

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

    public void heapSort(char[] arr) {
        int n = arr.length;

        // Build max heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            char temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            swaps++;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    public void heapSortNum(int[] arr) {
        int n = arr.length;

        // Build max heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyNum(arr, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            swaps++;

            // Call max heapify on the reduced heap
            heapifyNum(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is an index in arr[]
    public void heapify(char[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        // If left child is larger than root
        if (leftChild < n && arr[leftChild] > arr[largest]) {
            largest = leftChild;
            comparisons++;
        }

        // If right child is larger than largest so far
        if (rightChild < n && arr[rightChild] > arr[largest]) {
            largest = rightChild;
            comparisons++;
        }

        // If largest is not root
        if (largest != i) {
            // Swap arr[i] with arr[largest]
            char temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            swaps++;

            // Recursively heapify the affected subtree
            heapify(arr, n, largest);
        }
    }

    public void heapifyNum(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        // If left child is larger than root
        if (leftChild < n && arr[leftChild] > arr[largest]) {
            largest = leftChild;
            comparisons++;
        }

        // If right child is larger than largest so far
        if (rightChild < n && arr[rightChild] > arr[largest]) {
            largest = rightChild;
            comparisons++;
        }

        // If largest is not root
        if (largest != i) {
            // Swap arr[i] with arr[largest]
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            swaps++;

            // Recursively heapify the affected subtree
            heapifyNum(arr, n, largest);
        }
    }

    public void insertionSort(char[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            comparisons++;
            char key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key, to one position ahead
            // of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                swaps++;
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public void insertionSortNum(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            comparisons++;
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key, to one position ahead
            // of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                swaps++;
                j--;
            }
            arr[j + 1] = key;
        }
    }



    public void quickSort(char[] arr, int low, int high) {
        if (low < high) {
            // Partition the array into two halves
            int partitionIndex = partition(arr, low, high);

            // Recursively sort the left and right halves
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    public void quickSortNum(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array into two halves
            int partitionIndex = partitionNum(arr, low, high);

            // Recursively sort the left and right halves
            quickSortNum(arr, low, partitionIndex - 1);
            quickSortNum(arr, partitionIndex + 1, high);
        }
    }

    // Helper method to partition the array and return the pivot index
    public int partition(char[] arr, int low, int high) {
        // Choose the pivot element (last element in this case)
        char pivot = arr[high];
        int i = low - 1; // Index of the smaller element

        for (int j = low; j < high; j++) {
            comparisons++;
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++; // Increment index of smaller element
                // Swap arr[i] and arr[j]
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                swaps++;
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        char temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        swaps++;

        return i + 1; // Return the partition index
    }

    public int partitionNum(int[] arr, int low, int high) {
        // Choose the pivot element (last element in this case)
        int pivot = arr[high];
        int i = low - 1; // Index of the smaller element

        for (int j = low; j < high; j++) {
            comparisons++;
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++; // Increment index of smaller element
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                swaps++;
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        swaps++;

        return i + 1; // Return the partition index
    }

    public void mergeSort(char[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point to divide the array into two halves
            int mid = left + (right - left) / 2;

            // Recursively sort the first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    public void mergeSortNum(int[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point to divide the array into two halves
            int mid = left + (right - left) / 2;

            // Recursively sort the first and second halves
            mergeSortNum(arr, left, mid);
            mergeSortNum(arr, mid + 1, right);

            // Merge the sorted halves
            mergeNum(arr, left, mid, right);
        }
    }

    // Helper method to merge two halves of the array
    public void merge(char[] arr, int left, int mid, int right) {
        // Calculate the sizes of the two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays to hold the left and right subarrays
        char[] leftArray = new char[n1];
        char[] rightArray = new char[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        // Merge the two temporary arrays back into the original array
        int i = 0, j = 0;
        int k = left; // Initial index of merged subarray
        while (i < n1 && j < n2) {
            comparisons++;
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray, if any
        while (i < n1) {
            swaps++;
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray, if any
        while (j < n2) {
            swaps++;
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public void mergeNum(int[] arr, int left, int mid, int right) {
        // Calculate the sizes of the two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays to hold the left and right subarrays
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        // Merge the two temporary arrays back into the original array
        int i = 0, j = 0;
        int k = left; // Initial index of merged subarray
        while (i < n1 && j < n2) {
            comparisons++;
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray, if any
        while (i < n1) {
            swaps++;
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray, if any
        while (j < n2) {
            swaps++;
            arr[k] = rightArray[j];
            j++;
            k++;
        }
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
                                <title>Bubble Sort Overview</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        max-width: 600px;
                                        margin: 0 auto;
                                        padding: 20px;
                                        color: #333;
                                        line-height: 1.6;
                                    }
                                    h1, h2, h3 {
                                        color: #004d99;
                                    }
                                    ul {
                                        padding-left: 20px;
                                    }
                                </style>
                            </head>
                            <body>
                                <h1>Bubble Sort Overview</h1>
                                <p>
                                    Bubble Sort is a simple comparison-based sorting algorithm. It repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. This process continues until the list is fully sorted.
                                </p>
                                <h2>Key Points</h2>
                                <ul>
                                    <li><strong>Time Complexity:</strong> O(n^2) in the worst and average cases</li>
                                    <li><strong>Space Complexity:</strong> O(1) (in-place sorting algorithm)</li>
                                    <li><strong>Algorithm Steps:</strong>
                                        <ul>
                                            <li>Start from the beginning of the list.</li>
                                            <li>Compare adjacent elements and swap them if they are out of order.</li>
                                            <li>Repeat the above process until no more swaps are needed.</li>
                                        </ul>
                                    </li>
                                </ul>
                                <h2>Sorting Process</h2>
                                <p>
                                    Bubble Sort works by repeatedly iterating through the list. During each iteration, it compares adjacent elements and swaps them if they are in the wrong order. The largest unsorted element "bubbles up" to its correct position in each pass.
                                </p>
                                <h2>Example</h2>
                                <p>Consider sorting the array <strong>[5, 3, 8, 2, 1]</strong> using Bubble Sort:</p>
                                <ol>
                                    <li><strong>First Pass:</strong> [3, 5, 2, 1, 8]</li>
                                    <li><strong>Second Pass:</strong> [3, 2, 1, 5, 8]</li>
                                    <li><strong>Third Pass:</strong> [2, 1, 3, 5, 8]</li>
                                    <li><strong>Fourth Pass:</strong> [1, 2, 3, 5, 8]</li>
                                </ol>
                                <p>
                                    After four passes, the array is sorted in ascending order. Despite its simplicity, Bubble Sort is inefficient for large lists due to its quadratic time complexity.
                                </p>
                                <p>
                                    Bubble Sort is primarily used for educational purposes and is rarely used in practical applications where more efficient sorting algorithms like Merge Sort or Quick Sort are preferred.
                                </p>
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
                                <title>QuickSort Overview</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        max-width: 600px;
                                        margin: 0 auto;
                                        padding: 20px;
                                        color: #333;
                                        line-height: 1.6;
                                    }
                                    h1, h2, h3 {
                                        color: #004d99;
                                    }
                                    ul {
                                        padding-left: 20px;
                                    }
                                </style>
                            </head>
                            <body>
                                <h1>QuickSort Overview</h1>
                                <p>
                                    QuickSort is a highly efficient sorting algorithm and is based on the divide-and-conquer strategy. It works by selecting a pivot element and partitioning the array into two sub-arrays around the pivot: one containing elements less than the pivot and the other containing elements greater than the pivot. QuickSort then recursively sorts the sub-arrays.
                                </p>
                                <h2>Key Points</h2>
                                <ul>
                                    <li><strong>Time Complexity:</strong> O(n log n) on average, O(n^2) in the worst case (rare)</li>
                                    <li><strong>Space Complexity:</strong> O(log n) due to recursive calls (not counting the input array)</li>
                                    <li><strong>Algorithm Steps:</strong>
                                        <ul>
                                            <li>Choose a pivot element from the array (often the last element).</li>
                                            <li>Partition the array into two sub-arrays: elements less than the pivot and elements greater than the pivot.</li>
                                            <li>Recursively apply QuickSort to the sub-arrays.</li>
                                        </ul>
                                    </li>
                                </ul>
                                <h2>Sorting Process</h2>
                                <p>
                                    QuickSort follows these steps:
                                </p>
                                <ol>
                                    <li>Choose a pivot element (e.g., the last element of the array).</li>
                                    <li>Partition the array into two sub-arrays: elements less than the pivot and elements greater than the pivot.</li>
                                    <li>Recursively apply QuickSort to the sub-arrays.</li>
                                </ol>
                                <h2>Example</h2>
                                <p>Consider sorting the array <strong>[7, 2, 1, 6, 8, 5, 3, 4]</strong> using QuickSort:</p>
                                <ol>
                                    <li><strong>Initial Array:</strong> [7, 2, 1, 6, 8, 5, 3, 4]</li>
                                    <li><strong>After First Pass:</strong> [4, 2, 1, 3, 5, 6, 7, 8]</li>
                                </ol>
                                <p>
                                    The array is now sorted in ascending order using QuickSort. This algorithm is highly efficient for large datasets due to its average-case time complexity of O(n log n).
                                </p>
                                <p>
                                    QuickSort is widely used in practice and is one of the fastest sorting algorithms available, especially for random data.
                                </p>
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
                                <title>Merge Sort Overview</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        max-width: 600px;
                                        margin: 0 auto;
                                        padding: 20px;
                                        color: #333;
                                        line-height: 1.6;
                                    }
                                    h1, h2, h3 {
                                        color: #004d99;
                                    }
                                    ul {
                                        padding-left: 20px;
                                    }
                                </style>
                            </head>
                            <body>
                                <h1>Merge Sort Overview</h1>
                                <p>
                                    Merge Sort is a comparison-based sorting algorithm that uses a divide-and-conquer approach. It divides the array into smaller sub-arrays, sorts them recursively, and then merges them back together to create a sorted array.
                                </p>
                                <h2>Key Points</h2>
                                <ul>
                                    <li><strong>Time Complexity:</strong> O(n log n) in all cases (best, average, and worst)</li>
                                    <li><strong>Space Complexity:</strong> O(n) due to auxiliary space for merging (not counting the input array)</li>
                                    <li><strong>Algorithm Steps:</strong>
                                        <ul>
                                            <li>Divide the array into two halves.</li>
                                            <li>Recursively apply Merge Sort to each half.</li>
                                            <li>Merge the sorted halves back together.</li>
                                        </ul>
                                    </li>
                                </ul>
                                <h2>Sorting Process</h2>
                                <p>
                                    Merge Sort follows these steps:
                                </p>
                                <ol>
                                    <li>Divide the array into two halves.</li>
                                    <li>Recursively apply Merge Sort to each half until each sub-array contains one element (sorted by definition).</li>
                                    <li>Merge the sorted sub-arrays back together to form a single sorted array.</li>
                                </ol>
                                <h2>Example</h2>
                                <p>Consider sorting the array <strong>[6, 2, 7, 1, 3, 9, 4, 5]</strong> using Merge Sort:</p>
                                <ol>
                                    <li><strong>Initial Array:</strong> [6, 2, 7, 1, 3, 9, 4, 5]</li>
                                    <li><strong>After Merge Sort:</strong> [1, 2, 3, 4, 5, 6, 7, 9]</li>
                                </ol>
                                <p>
                                    The array is now sorted in ascending order using Merge Sort. This algorithm is highly efficient and stable, making it suitable for a wide range of applications.
                                </p>
                                <p>
                                    Merge Sort's consistent time complexity of O(n log n) makes it a popular choice for sorting large datasets where performance is critical.
                                </p>
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
                                <title>Insertion Sort Overview</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        max-width: 600px;
                                        margin: 0 auto;
                                        padding: 20px;
                                        color: #333;
                                        line-height: 1.6;
                                    }
                                    h1, h2, h3 {
                                        color: #004d99;
                                    }
                                    ul {
                                        padding-left: 20px;
                                    }
                                </style>
                            </head>
                            <body>
                                <h1>Insertion Sort Overview</h1>
                                <p>
                                    Insertion Sort is a simple comparison-based sorting algorithm. It builds the final sorted array one element at a time by iteratively inserting each element into its correct position within the sorted part of the array.
                                </p>
                                <h2>Key Points</h2>
                                <ul>
                                    <li><strong>Time Complexity:</strong> O(n^2) in the worst case (when the array is reverse sorted), O(n) in the best case (when the array is already sorted)</li>
                                    <li><strong>Space Complexity:</strong> O(1) (in-place sorting algorithm, requires only a constant amount of additional space)</li>
                                    <li><strong>Algorithm Steps:</strong>
                                        <ul>
                                            <li>Start with the second element and iterate through the array.</li>
                                            <li>For each element, compare it with the elements in the sorted part of the array and insert it into its correct position.</li>
                                        </ul>
                                    </li>
                                </ul>
                                <h2>Sorting Process</h2>
                                <p>
                                    Insertion Sort follows these steps:
                                </p>
                                <ol>
                                    <li>Start with the second element (assume the first element is already "sorted").</li>
                                    <li>Compare the current element with the elements in the sorted part of the array.</li>
                                    <li>Shift the larger elements to the right to make space for the current element in its correct position.</li>
                                    <li>Insert the current element into its correct position within the sorted part of the array.</li>
                                    <li>Repeat until all elements are in the correct order.</li>
                                </ol>
                                <h2>Example</h2>
                                <p>Consider sorting the array <strong>[5, 2, 4, 6, 1, 3]</strong> using Insertion Sort:</p>
                                <ol>
                                    <li><strong>Initial Array:</strong> [5, 2, 4, 6, 1, 3]</li>
                                    <li><strong>After Insertion Sort:</strong> [1, 2, 3, 4, 5, 6]</li>
                                </ol>
                                <p>
                                    The array is now sorted in ascending order using Insertion Sort. Despite its O(n^2) worst-case time complexity, Insertion Sort performs efficiently on small datasets and is suitable for situations where the input size is limited.
                                </p>
                                <p>
                                    Insertion Sort is also used in practice to complement other sorting algorithms, such as when sorting small sub-arrays in algorithms like QuickSort or MergeSort.
                                </p>
                            </body>
                            </html>
                            """);
                output.setCaretPosition(0);
                break;

            case 4:
                output.setText("""
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Heap Sort Overview</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        max-width: 600px;
                                        margin: 0 auto;
                                        padding: 20px;
                                        color: #333;
                                        line-height: 1.6;
                                    }
                                    h1, h2, h3 {
                                        color: #004d99;
                                    }
                                    ul {
                                        padding-left: 20px;
                                    }
                                </style>
                            </head>
                            <body>
                                <h1>Heap Sort Overview</h1>
                                <p>
                                    Heap Sort is a comparison-based sorting algorithm that uses a binary heap data structure. It transforms the input array into a max-heap (or min-heap for descending order), then repeatedly removes the root element (the maximum or minimum) to produce a sorted array.
                                </p>
                                <h2>Key Points</h2>
                                <ul>
                                    <li><strong>Time Complexity:</strong> O(n log n) in all cases (best, average, and worst)</li>
                                    <li><strong>Space Complexity:</strong> O(1) (in-place sorting algorithm, requires only a constant amount of additional space)</li>
                                    <li><strong>Algorithm Steps:</strong>
                                        <ul>
                                            <li>Build a max-heap from the input array.</li>
                                            <li>Repeatedly remove the root (maximum element) from the heap and adjust the heap.</li>
                                        </ul>
                                    </li>
                                </ul>
                                <h2>Sorting Process</h2>
                                <p>
                                    Heap Sort follows these steps:
                                </p>
                                <ol>
                                    <li>Build a max-heap (or min-heap) from the input array.</li>
                                    <li>Swap the root (maximum element) with the last element of the heap and reduce the size of the heap.</li>
                                    <li>Restore the heap property (heapify) to maintain the heap structure.</li>
                                    <li>Repeat the above steps until the heap is empty, resulting in a sorted array.</li>
                                </ol>
                                <h2>Example</h2>
                                <p>Consider sorting the array <strong>[4, 10, 3, 5, 1]</strong> using Heap Sort:</p>
                                <ol>
                                    <li><strong>Initial Array:</strong> [4, 10, 3, 5, 1]</li>
                                    <li><strong>After Heap Sort:</strong> [1, 3, 4, 5, 10]</li>
                                </ol>
                                <p>
                                    The array is now sorted in ascending order using Heap Sort. This algorithm is efficient and guarantees optimal time complexity of O(n log n) due to its heapify operations.
                                </p>
                                <p>
                                    Heap Sort is commonly used in situations where stable sorting and optimal time complexity are required, especially when dealing with large datasets.
                                </p>
                            </body>
                            </html>
                            """);
                output.setCaretPosition(0);
                break;

        }
    }

    private int swaps = 0;
    private int comparisons = 0;
    private final String[] options = {"Bubble Sort", "Quick Sort", "Merge Sort", "Insertion Sort", "Heap Sort"};
    private JEditorPane output;
    private JTextField input;

}