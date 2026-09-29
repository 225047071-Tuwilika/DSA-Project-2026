 import java.util.Random;

import java.util.Scanner;

public class main {

    // =========================================================

    // QUEUE

    // =========================================================

    static class StudentQueue {

        private StudentNode[] arr;

        private int front;
        private int rear;

        private int count;

        private int capacity;

        public StudentQueue(int capacity) {

            this.capacity = capacity;

            arr = new StudentNode[capacity];

            front = 0;

            rear = -1;

            count = 0;

        }

        // ENQUEUE

        public boolean enqueue(StudentNode s) {

            if (count == capacity) {

                System.out.println("Queue is full!");

                return false;

            }

            rear = (rear + 1) % capacity;

            arr[rear] = s;

            count++;

            return true;

        }

        // DEQUEUE

        public StudentNode dequeue() {

            if (isEmpty()) {

                System.out.println("Queue is empty!");

                return null;

            }

            StudentNode s = arr[front];

            arr[front] = null;

            front = (front + 1) % capacity;

            count--;

            return s;

        }

        // PEEK

        public StudentNode peek() {

            if (isEmpty()) {

                return null;

            }

            return arr[front];

        }

        // IS EMPTYS

        public boolean isEmpty() {

            return count == 0;

        }

        // DISPLAY QUEUE

        public void displayQueue() {

            if (isEmpty()) {

               
System.out.println("Waiting queue is empty.");

                return;

            }

            System.out.println("\n----- Waiting Queue -----");

            int idx = front;

            for (int i = 0; i < count; i++)
{

                System.out.println(

                        (i + 1) + ". " + arr[idx]

                );

                idx = (idx + 1) % capacity;

            }

        }

    }

    // =========================================================

    // ARRAY FOR SERVICE TIMES

    // =========================================================

    static final int MAX_SERVED = 1000;

    static int[] serviceTimes = new
int[MAX_SERVED];

    static int servedCount = 0;

    // =========================================================

    // MAIN PROGRAM

    // =========================================================

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentQueue queue = new
StudentQueue(50);

        SinglyLinkedList records =

                new SinglyLinkedList();

        int choice;

        do {

            displayMenu();

            choice = readInt(sc, "Select option: ");

            switch (choice) {

                // =================================================

                // OPTION 1 - ADD STUDENT TO QUEUE

                // =================================================

                case 1:

                   
System.out.println("\n--- Add Student to Queue ---");

                   
System.out.print("Student No: ");

                    String no = sc.nextLine();

                   
System.out.print("Name: ");

                    String name =
sc.nextLine();

                   
System.out.print("Service Type: ");

                    String type =
sc.nextLine();

                    int time =

                            readInt(sc,
"Estimated Time (min): ");

                    StudentNode student =

                            new StudentNode(

                                    no,

                                    name,

                                    type,

                                    time

                            );

                    if (queue.enqueue(student))
{

                        System.out.println(

                                "Student added to waiting queue."

                        );

                    }

                    break;

                // =================================================

                // OPTION 2 - SERVE NEXT STUDENT

                // =================================================

                case 2:

                    System.out.println(

                            "\n--- Serve Next Student ---"

                    );

                    StudentNode served =

                            queue.dequeue();

                    if (served != null) {

                        System.out.println(

                                "Serving: " + served

                        );

                        // Store service time in array

                        if (servedCount <
MAX_SERVED) {

                           
serviceTimes[servedCount] =

                                   
served.estimatedTime;

                            servedCount++;

                        }

                        // Automatically add served student

                        // to service records

                        records.insertStudent(

                               
served.studentNo,

                                served.name,

                               
served.serviceType,

                               
served.estimatedTime

                        );

                        System.out.println(

                                "Service record added."

                        );

                    }

                    break;

                // =================================================

                // OPTION 3 - DISPLAY QUEUE

                // =================================================

                case 3:

                    queue.displayQueue();

                    break;

                // =================================================

                // OPTION 4 - ADD SERVICE RECORD

                // =================================================

                case 4:

                    System.out.println(

                            "\n--- Add Student Service Record ---"

                    );

                   
System.out.print("Student No: ");

                    no = sc.nextLine();

                   
System.out.print("Name: ");

                    name = sc.nextLine();

                   
System.out.print("Service Type: ");

                    type = sc.nextLine();

                    time =

                            readInt(

                                    sc,

                                   
"Estimated Time (min): "

                            );

                    System.out.println(

                            "\nWhere should the student be inserted?"

                    );

                    System.out.println("1. Beginning");

                    System.out.println("2. End");

                    System.out.println("3. Specific position");

                    int positionChoice =

                            readInt(sc,
"Choice: ");

                    if (positionChoice == 1) {

                       
records.insertAtBeginning(

                                no,

                                name,

                                type,

                                time

                        );

                    } else if (positionChoice
== 2) {

                        records.insertStudent(

                                no,

                                name,

                                type,

                                time

                        );

                    } else if (positionChoice
== 3) {

                        int position =

                                readInt(

                                        sc,

                                       
"Enter position: "

                                );

                       
records.insertAtPosition(

                                position,

                                no,

                                name,

                                type,

                                time

                        );

                    } else {

                        System.out.println(

                                "Invalid insertion choice."

                        );

                        break;

                    }

                    System.out.println(

                            "Student service record added."

                    );

                    break;

                // =================================================

                // OPTION 5 - DISPLAY RECORDS

                // =================================================

                case 5:

                    records.displayStudents();

                    break;

                // =================================================

                // OPTION 6 - SEARCH

                // =================================================

                case 6:

                    System.out.println(

                            "\n--- Search Student Record ---"

                    );

                    System.out.print(

                            "Enter Student No to search: "

                    );

                    no = sc.nextLine();

                    StudentNode found =

                           
records.searchStudent(no);

                    if (found != null) {

                        System.out.println(

                                "Student found:"

                        );

                       
System.out.println(found);

                    } else {

                        System.out.println(

                                "Student not found."

                        );

                    }

                    break;

                // =================================================

                // OPTION 7 - DELETE

                // =================================================

                case 7:

                    System.out.println(

                            "\n--- Remove Student Record ---"

                    );

                    System.out.print(

                            "Enter Student No to remove: "

                    );

                    no = sc.nextLine();

                    if
(records.deleteStudent(no)) {

                        System.out.println(

                                "Record deleted successfully."

                        );

                    } else {

                        System.out.println(

                                "Student not found."

                        );

                    }

                    break;

                // =================================================

                // OPTION 8 - DAILY STATISTICS

                // =================================================

                case 8:

                    displayStatistics();

                    break;

                // =================================================

                // OPTION 9 - SORT SERVICE TIMES

                // =================================================

                case 9:

                    sortServiceTimes(sc);

                    break;

                // =================================================

                // OPTION 10 - SORTING EXPERIMENT

                // =================================================

                case 10:

                    runSortingExperiment();

                    break;

                // =================================================

                // OPTION 11 - EXIT

                // =================================================

                case 11:

                    System.out.println(

                            "Exiting Campus Service Centre..."

                    );

                    break;

                default:

                    System.out.println(

                            "Invalid option. Please choose 1-11."

                    );

            }

        } while (choice != 11);

        sc.close();

    }

    // =========================================================

    // MENU

    // =========================================================

    public static void displayMenu() {

        System.out.println(

               
"\n========================================"

        );

        System.out.println(

                "        CAMPUS SERVICE CENTRE"

        );

        System.out.println(

               
"========================================"

        );

        System.out.println(

                "1. Add student to waiting queue"

        );

        System.out.println(

                "2. Serve next student"

        );

        System.out.println(

                "3. Display waiting students"

        );

        System.out.println(

                "4. Add student service record"

        );

        System.out.println(

                "5. Display student service records"

        );

        System.out.println(

                "6. Search for student record"

        );

        System.out.println(

                "7. Remove student record"

        );

        System.out.println(

                "8. Display daily statistics"

        );

        System.out.println(

                "9. Sort service times"

        );

        System.out.println(

                "10. Run sorting experiment"

        );

        System.out.println(

                "11. Exit"

        );

        System.out.println(

               
"========================================"

        );

    }

    // =========================================================

    // DAILY STATISTICS

    // =========================================================

    public static void displayStatistics() {

        if (servedCount == 0) {

            System.out.println(

                    "No students served yet."

            );

            return;

        }

        int total = 0;

        int highest = serviceTimes[0];

        int lowest = serviceTimes[0];

        int longerThan10 = 0;

        for (int i = 0; i < servedCount;
i++) {

            int time = serviceTimes[i];

            total += time;

            if (time > highest) {

                highest = time;

            }

            if (time < lowest) {

                lowest = time;

            }

            if (time > 10) {

                longerThan10++;

            }

        }

        double average =

                (double) total / servedCount;

        System.out.println(

                "\n----- Daily Statistics -----"

        );

        System.out.println(

                "Total students served : "

                        + servedCount

        );

        System.out.println(

                "Total service time    : "

                        + total + " min"

        );

        System.out.println(

                "Average service time  : "

                        +
String.format("%.2f", average)

                        + " min"

        );

        System.out.println(

                "Highest service time  : "

                        + highest + " min"

        );

        System.out.println(

                "Lowest service time   : "

                        + lowest + " min"

        );

        System.out.println(

                "Services > 10 min    : "

                        + longerThan10

        );

    }

    // =========================================================

    // OPTION 9 - SORT SERVICE TIMES

    // =========================================================

    public static void sortServiceTimes(Scanner
sc) {

        if (servedCount == 0) {

            System.out.println(

                    "No service-time data available."

            );

            return;

        }

        int[] copy =

                new int[servedCount];

        for (int i = 0; i < servedCount;
i++) {

            copy[i] = serviceTimes[i];

        }

        System.out.println(

                "\n--- Sort Service Times ---"

        );

        System.out.println("1. Selection Sort");

        System.out.println("2. Insertion Sort");

        System.out.println("3. Merge Sort");

        System.out.println("4. Quick Sort");

        int choice =

                readInt(sc, "Choose sorting algorithm: ");

        long start;

        long end;

        long comparisons;

        switch (choice) {

            case 1:

                start = System.nanoTime();

                comparisons =

                        selectionSort(copy);

                end = System.nanoTime();

                break;

            case 2:

                start = System.nanoTime();

                comparisons =

                        insertionSort(copy);

                end = System.nanoTime();

                break;

            case 3:

                start = System.nanoTime();

                comparisons =

                        mergeSort(copy);

                end = System.nanoTime();

                break;

            case 4:

                start = System.nanoTime();

                comparisons =

                        quickSort(copy);

                end = System.nanoTime();

                break;

            default:

                System.out.println(

                        "Invalid sorting choice."

                );

                return;

        }

        System.out.println(

                "\nSorted service times:"

        );

        printArray(copy);

        System.out.println(

                "Data comparisons: "

                        + comparisons

        );

        System.out.println(

                "Execution time: "

                        + (end - start)

                        + " ns"

        );

    }

    // =========================================================

    // PART C SORTING EXPERIMENT

    // =========================================================

    public static void runSortingExperiment() {

        System.out.println(

               
"\n========================================"

        );

        System.out.println(

                "        SORTING EXPERIMENT"

        );

        System.out.println(

               
"========================================"

        );

        int[] sizes = {

                20,

                50,

                100,

                500

        };

        Random random =

                new Random(42);

        for (int size : sizes) {

            int[] original =

                    generateRandomArray(

                            size,

                            random

                    );

            int[] selection =

                    copyArray(original);

            int[] insertion =

                    copyArray(original);

            int[] merge =

                    copyArray(original);

            int[] quick =

                    copyArray(original);

            long start;

            long end;

            // -----------------------------------------

            // SELECTION SORT

            // -----------------------------------------

            start = System.nanoTime();

            long selectionComparisons =

                    selectionSort(selection);

            end = System.nanoTime();

            long selectionTime =

                    end - start;

            // -----------------------------------------

            // INSERTION SORT

            // -----------------------------------------

            start = System.nanoTime();

            long insertionComparisons =

                    insertionSort(insertion);

            end = System.nanoTime();

            long insertionTime =

                    end - start;

            // -----------------------------------------

            // MERGE SORT

            // -----------------------------------------

            start = System.nanoTime();

            long mergeComparisons =

                    mergeSort(merge);

            end = System.nanoTime();

            long mergeTime =

                    end - start;

            // -----------------------------------------

            // QUICK SORT

            // -----------------------------------------

            start = System.nanoTime();

            long quickComparisons =

                    quickSort(quick);

            end = System.nanoTime();

            long quickTime =

                    end - start;

            // -----------------------------------------

            // DISPLAY RESULTS

            // -----------------------------------------

            System.out.println(

                    "\nArray Size: "
+ size

            );

            System.out.println(

                   
"------------------------------------------------"

            );

            System.out.printf(

                    "%-18s %-15s %-15s%n",

                    "Algorithm",

                    "Comparisons",

                    "Time (ns)"

            );

            System.out.println(

                   
"------------------------------------------------"

            );

            System.out.printf(

                    "%-18s %-15d %-15d%n",

                    "Selection Sort",

                    selectionComparisons,

                    selectionTime

            );

            System.out.printf(

                    "%-18s %-15d %-15d%n",

                    "Insertion Sort",

                    insertionComparisons,

                    insertionTime

            );

            System.out.printf(

                    "%-18s %-15d %-15d%n",

                    "Merge Sort",

                    mergeComparisons,

                    mergeTime

            );

            System.out.printf(

                    "%-18s %-15d %-15d%n",

                    "Quick Sort",

                    quickComparisons,

                    quickTime

            );

        }

        // =====================================================

        // ALMOST-SORTED ARRAY OF 100

        // =====================================================

        System.out.println(

               
"\n========================================"

        );

        System.out.println(

                "      ALMOST-SORTED ARRAY TEST"

        );

        System.out.println(

               
"========================================"

        );

        int[] almostSorted =

                generateRandomArray(

                        100,

                        new Random(42)

                );

        // First sort it into ascending order

        prepareAlmostSorted(almostSorted);

        // Swap five neighbouring pairs

        swap(almostSorted, 9, 10);

        swap(almostSorted, 29, 30);

        swap(almostSorted, 49, 50);

        swap(almostSorted, 69, 70);

        swap(almostSorted, 89, 90);

        int[] insertion =

                copyArray(almostSorted);

        int[] merge =

                copyArray(almostSorted);

        int[] quick =

                copyArray(almostSorted);

        int[] selection =

                copyArray(almostSorted);

        long start;

        long end;

        // Selection

        start = System.nanoTime();

        long selectionComparisons =

                selectionSort(selection);

        end = System.nanoTime();

        long selectionTime =

                end - start;

        // Insertion

        start = System.nanoTime();

        long insertionComparisons =

                insertionSort(insertion);

        end = System.nanoTime();

        long insertionTime =

                end - start;

        // Merge

        start = System.nanoTime();

        long mergeComparisons =

                mergeSort(merge);

        end = System.nanoTime();

        long mergeTime =

                end - start;

        // Quick

        start = System.nanoTime();

        long quickComparisons =

                quickSort(quick);

        end = System.nanoTime();

        long quickTime =

                end - start;

        System.out.println(

                "\nAlmost-Sorted Array Results:"

        );

        System.out.println(

               
"------------------------------------------------"

        );

        System.out.printf(

                "%-18s %-15s %-15s%n",

                "Algorithm",

                "Comparisons",

                "Time (ns)"

        );

        System.out.println(

               
"------------------------------------------------"

        );

        System.out.printf(

                "%-18s %-15d %-15d%n",

                "Selection Sort",

                selectionComparisons,

                selectionTime

        );

        System.out.printf(

                "%-18s %-15d %-15d%n",

                "Insertion Sort",

                insertionComparisons,

                insertionTime

        );

        System.out.printf(

                "%-18s %-15d %-15d%n",

                "Merge Sort",

                mergeComparisons,

                mergeTime

        );

        System.out.printf(

                "%-18s %-15d %-15d%n",

                "Quick Sort",

                quickComparisons,

                quickTime

        );

        System.out.println(

                "\nSorting experiment completed."

        );

    }

    // =========================================================

    // SELECTION SORT

    // =========================================================

    public static long selectionSort(int[] arr)
{

        long comparisons = 0;

        for (int i = 0; i < arr.length - 1;
i++) {

            int minIndex = i;

            for (int j = i + 1;

                 j < arr.length;

                 j++) {

                comparisons++;

                if (arr[j] < arr[minIndex])
{

                    minIndex = j;

                }

            }

            if (minIndex != i) {

                int temp = arr[i];

                arr[i] = arr[minIndex];

                arr[minIndex] = temp;

            }

        }

        return comparisons;

    }

    // =========================================================

    // INSERTION SORT

    // =========================================================

    public static long insertionSort(int[] arr)
{

        long comparisons = 0;

        for (int i = 1;

             i < arr.length;

             i++) {

            int key = arr[i];

            int j = i - 1;

            while (j >= 0) {

                comparisons++;

                if (arr[j] > key) {

                    arr[j + 1] = arr[j];

                    j--;

                } else {

                    break;

                }

            }

            arr[j + 1] = key;

        }

        return comparisons;

    }

    // =========================================================

    // MERGE SORT

    // =========================================================

    public static long mergeSort(int[] arr) {

        if (arr.length <= 1) {

            return 0;

        }

        int[] temp =

                new int[arr.length];

        return mergeSortRecursive(

                arr,

                temp,

                0,

                arr.length - 1

        );

    }

    private static long mergeSortRecursive(

            int[] arr,

            int[] temp,

            int left,

            int right) {

        if (left >= right) {

            return 0;

        }

        int middle =

                (left + right) / 2;

        long comparisons = 0;

        comparisons +=

                mergeSortRecursive(

                        arr,

                        temp,

                        left,

                        middle

                );

        comparisons +=

                mergeSortRecursive(

                        arr,

                        temp,

                        middle + 1,

                        right

                );

        comparisons +=

                merge(

                        arr,

                        temp,

                        left,

                        middle,

                        right

                );

        return comparisons;

    }

    private static long merge(

            int[] arr,

            int[] temp,

            int left,

            int middle,

            int right) {

        int i = left;

        int j = middle + 1;

        int k = left;

        long comparisons = 0;

        while (i <= middle &&

               j <= right) {

            comparisons++;

            if (arr[i] <= arr[j]) {

                temp[k] = arr[i];

                i++;

            } else {

                temp[k] = arr[j];

                j++;

            }

            k++;

        }

        while (i <= middle) {

            temp[k] = arr[i];

            i++;

            k++;

        }

        while (j <= right) {

            temp[k] = arr[j];

            j++;

            k++;

        }

        for (int x = left;

             x <= right;

             x++) {

            arr[x] = temp[x];

        }

        return comparisons;

    }

    // =========================================================

    // QUICK SORT

    // =========================================================

    public static long quickSort(int[] arr) {

        return quickSortRecursive(

                arr,

                0,

                arr.length - 1

        );

    }

    private static long quickSortRecursive(

            int[] arr,

            int low,

            int high) {

        if (low >= high) {

            return 0;

        }

        PartitionResult result =

                partition(

                        arr,

                        low,

                        high

                );

        long comparisons =

                result.comparisons;

        comparisons +=

                quickSortRecursive(

                        arr,

                        low,

                        result.pivotIndex - 1

                );

        comparisons +=

                quickSortRecursive(

                        arr,

                        result.pivotIndex + 1,

                        high

                );

        return comparisons;

    }

    // Last element is used as pivot

    private static PartitionResult partition(

            int[] arr,

            int low,

            int high) {

        int pivot = arr[high];

        int i = low - 1;

        long comparisons = 0;

        for (int j = low;

             j < high;

             j++) {

            comparisons++;

            if (arr[j] <= pivot) {

                i++;

                int temp = arr[i];

                arr[i] = arr[j];

                arr[j] = temp;

            }

        }

        int temp = arr[i + 1];

        arr[i + 1] = arr[high];

        arr[high] = temp;

        int pivotIndex = i + 1;

        return new PartitionResult(

                pivotIndex,

                comparisons

        );

    }

    // =========================================================

    // PARTITION RESULT

    // =========================================================

    static class PartitionResult {

        int pivotIndex;

        long comparisons;

        public PartitionResult(

                int pivotIndex,

                long comparisons) {

            this.pivotIndex =

                    pivotIndex;

            this.comparisons =

                    comparisons;

        }

    }

    // =========================================================

    // GENERATE RANDOM ARRAY

    // =========================================================

    public static int[] generateRandomArray(

            int size,

            Random random) {

        int[] arr =

                new int[size];

        for (int i = 0;

             i < size;

             i++) {

            arr[i] =

                    random.nextInt(1000);

        }

        return arr;

    }

    // =========================================================

    // COPY ARRAY

    // =========================================================

    public static int[] copyArray(

            int[] original) {

        int[] copy =

                new int[original.length];

        for (int i = 0;

             i < original.length;

             i++) {

            copy[i] =

                    original[i];

        }

        return copy;

    }

    // =========================================================

    // PREPARE ALMOST-SORTED ARRAY

    // =========================================================

    public static void prepareAlmostSorted(

            int[] arr) {

        // Sort the array first without

        // using Java's built-in sorting.

        for (int i = 0;

             i < arr.length - 1;

             i++) {

            int minIndex = i;

            for (int j = i + 1;

                 j < arr.length;

                 j++) {

                if (arr[j] < arr[minIndex])
{

                    minIndex = j;

                }

            }

            if (minIndex != i) {

                swap(

                        arr,

                        i,

                        minIndex

                );

            }

        }

    }

    // =========================================================

    // SWAP

    // =========================================================

    public static void swap(

            int[] arr,

            int i,

            int j) {

        int temp = arr[i];

        arr[i] = arr[j];

        arr[j] = temp;

    }

    // =========================================================

    // PRINT ARRAY

    // =========================================================

    public static void printArray(

            int[] arr) {

        for (int i = 0;

             i < arr.length;

             i++) {

            System.out.print(

                    arr[i] + " "

            );

        }

        System.out.println();

    }

    // =========================================================

    // SAFE INTEGER INPUT

    // =========================================================

    public static int readInt(

            Scanner sc,

            String message) {

        while (true) {

            System.out.print(message);

            if (sc.hasNextInt()) {

                int value =

                        sc.nextInt();

                sc.nextLine();

                return value;

            } else {

                System.out.println(

                        "Please enter a valid number."

                );

                sc.nextLine();

            }

        }

    }


    // =========================================================
    // STUDENT NODE
    // =========================================================
    static class StudentNode {
        String studentNo;
        String name;
        String serviceType;
        int estimatedTime;
        StudentNode next;

        StudentNode(String studentNo, String name, String serviceType, int estimatedTime) {
            this.studentNo = studentNo;
            this.name = name;
            this.serviceType = serviceType;
            this.estimatedTime = estimatedTime;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Student No: " + studentNo
                    + " | Name: " + name
                    + " | Service Type: " + serviceType
                    + " | Estimated Time: " + estimatedTime + " min";
        }
    }

    // =========================================================
    // SINGLY LINKED LIST
    // =========================================================
    static class SinglyLinkedList {
        private StudentNode head;
        private int size;

        public void insertStudent(String studentNo, String name,
                                   String serviceType, int estimatedTime) {
            StudentNode newNode = new StudentNode(studentNo, name, serviceType, estimatedTime);
            if (head == null) {
                head = newNode;
            } else {
                StudentNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
        }

        public void insertAtBeginning(String studentNo, String name,
                                      String serviceType, int estimatedTime) {
            StudentNode newNode = new StudentNode(studentNo, name, serviceType, estimatedTime);
            newNode.next = head;
            head = newNode;
            size++;
        }

        public void insertAtPosition(int position, String studentNo, String name,
                                     String serviceType, int estimatedTime) {
            if (position <= 1 || head == null) {
                insertAtBeginning(studentNo, name, serviceType, estimatedTime);
                return;
            }

            StudentNode newNode = new StudentNode(studentNo, name, serviceType, estimatedTime);
            StudentNode current = head;
            int currentPosition = 1;

            while (current.next != null && currentPosition < position - 1) {
                current = current.next;
                currentPosition++;
            }

            newNode.next = current.next;
            current.next = newNode;
            size++;
        }

        public StudentNode searchStudent(String studentNo) {
            StudentNode current = head;
            while (current != null) {
                if (current.studentNo.equalsIgnoreCase(studentNo)) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }

        public boolean deleteStudent(String studentNo) {
            if (head == null) {
                return false;
            }

            if (head.studentNo.equalsIgnoreCase(studentNo)) {
                head = head.next;
                size--;
                return true;
            }

            StudentNode current = head;
            while (current.next != null) {
                if (current.next.studentNo.equalsIgnoreCase(studentNo)) {
                    current.next = current.next.next;
                    size--;
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        public void displayStudents() {
            if (head == null) {
                System.out.println("No student service records.");
                return;
            }

            System.out.println("\n----- Student Service Records -----");
            StudentNode current = head;
            int number = 1;
            while (current != null) {
                System.out.println(number + ". " + current);
                current = current.next;
                number++;
            }
        }
    }

}
