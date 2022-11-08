package com.example.hehe;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class HelloController {
    public Button btntest;

    @FXML
    private Pane titlePane, btnClose, btnMinimize, btnMaximize;

    private static String filename = "C:\\Sample\\Result.txt";

    @FXML
    private TextField userEntered;
    private double x, y;
    private String Title;

    public String getTitle() {
        return Title;
    }

    public void init(Stage stage) {
        //This is to make sure our window is responsive
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

//        btnMaximize.setOnMouseClicked(mouseEvent -> stage.setMaximized(true));
    }





    @FXML
    public void onSortClicked(MouseEvent event) throws IOException {

        //Preparing to write in a text file, and erase previous data
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);


        //Taking inputted array n convertin it to integer array
        String[] input = userEntered.getText().split(" ");
        int[] arr = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        String symbol = ((Pane) event.getSource()).getId().replace("btn", "");
        Title = symbol + " Sort";

        bw.write(String.format("\n"));
        if (bw != null) {
            bw.close();
        }

        switch (symbol) {
            case "Selection":
                selectionSort(arr);
                break;

            case "Insertion":

                insertionSort(arr);
                break;

            case "Bubble":

                bubbleSort(arr);
                break;

            case "Quick":

                quickSort(arr, 0, arr.length - 1);
                break;

            case "Merge":
                mergeSort(arr, 0, arr.length-1);

            case "Count":
                countSort(arr);
        }


//        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
//        bw.write(String.format("After Sorting"));

//        bw.write(String.format("Sort performed successfully with "));
        newSortedWindow();
//        if (bw != null) {
//            bw.close();
//        }

    }


    public void selectionSort(int[] arr) throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);

        for (int i = 0; i < arr.length - 1; i++) {
            int index = i, j;
            for (j = i + 1; j < arr.length; j++) {

                if (arr[j] < arr[index]) {
                    index = j;//searching for lowest index
                }

                //Printing in terimal
                for (int q = 0; q < arr.length; q++) {
                    System.out.print(arr[q] + " ");
                }
                System.out.println("\ti = " + i + " , j = " + j);


                //Writing in text file
                for (int k = 0; k < arr.length; k++) {
                    bw.write(String.format("%d ", arr[k]));
                }
                bw.write(String.format("\ti = %d , j = %d ", i, j));
                bw.newLine();


            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;

            //printing in terimal
            for (int k = 0; k < arr.length; k++) {
                System.out.print(arr[k] + " ");
            }
            System.out.println("\ti = " + i + " , j = " + j + " \n");

            //writing in text file
            for (int k = 0; k < arr.length; k++) {
                bw.write(String.format("%d ", arr[k]));
            }
            bw.write(String.format("\ti = %d , j = %d \n", i, j));
            bw.newLine();
        }

        if (bw != null) {
            bw.close();
        }
    }


    public void bubbleSort(int[] arr) throws IOException {


        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);

        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }

                // PRINTING IN THE TERIMINAL
                for (int k = 0; k < arr.length; k++) {
                    System.out.print(arr[k] + " ");
                }
                System.out.println("\ti = " + i + " , j = " + j);


                //Writing in the txt file
                for (int o = 0; o < arr.length; o++) {
                    bw.write(String.format("%d ", arr[o]));
                }
                bw.write(String.format("\ti = %d , j = %d\n", i, j));
            }
            bw.newLine();
            System.out.println();
        }


        if (bw != null) {
            bw.close();
        }


    }

    public void insertionSort(int[] arr) throws IOException {


        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);


        int n = arr.length;
        for (int j = 1; j < n; j++) {

            int key = arr[j];
            int i = j - 1;

            while ((i > -1) && (arr[i] > key)) {
                arr[i + 1] = arr[i];


                // PRINTING IN THE TERIMAL
                for (int k = 0; k < arr.length; k++) {
                    System.out.print(arr[k] + " ");
                }
                System.out.println("i = " + i + " , j = " + j);


                //WRITING IN THE TEXT FILE
                for (int k = 0; k < arr.length; k++) {
                    bw.write(String.format("%d ", arr[k]));
                }
                bw.write(String.format("\ti = %d , j = %d , key = %d", j, i, key));
                bw.newLine();

                i--;
            }
            arr[i + 1] = key;

            //WRITING IN THE TEXT FILE
            for (int k = 0; k < arr.length; k++) {
                bw.write(String.format("%d ", arr[k]));
            }
            bw.write(String.format("\ti = %d , j = %d \n", j, i));
            bw.newLine();


            // PRINTING IN THE TERIMAL
            for (int k = 0; k < arr.length; k++) {
                System.out.print(arr[k] + " ");
            }
            System.out.println("i = " + i + " , j = " + j + "\n");
        }


        if (bw != null) {
            bw.close();
        }
    }


    int partition(int arr[], int low, int high) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));


        int pivot = arr[high], j;
        int i = (low - 1);
        for (j = low; j < high; j++) {

            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

            // PRINTING IN TERMINAL
            for (int k = low; k < high + 1; k++) {
                System.out.print(arr[k] + " ");
            }
            System.out.println();


            // WRITING IN TEXT FILE
            for (int k = low; k < high + 1; k++) {
                bw.write(String.format("%d ", arr[k]));
            }
            bw.write(String.format("\ti = %d , j = %d ", i, j));
            bw.newLine();

        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // WRITING IN TEXT FILE
        for (int k = low; k < high + 1; k++) {
            bw.write(String.format("%d ", arr[k]));
        }
        bw.write(String.format("\ti = %d , j = %d \n", i, j));
        bw.newLine();

        // PRINTING IN TERMINAL
        for (int k = low; k < high + 1; k++) {
            System.out.print(arr[k] + " ");
        }
        System.out.println("\n");


        if (bw != null) {
            bw.close();
        }

        return i + 1;
    }


    public void quickSort(int arr[], int low, int high) throws IOException {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    public void merge(int arr[], int l, int m, int r) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {

            // WRITING IN TEXT FILE
            for (int q = l; q < r + 1; q++) {
                bw.write(String.format("%d ", arr[q]));
            }
            bw.write(String.format("\ti = %d , j = %d ", i, j));
            bw.newLine();


            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;


            // WRITING IN TEXT FILE
            for (int q = l; q < r + 1; q++) {
                bw.write(String.format("%d ", arr[q]));
            }
            bw.write(String.format("\ti = %d , j = %d ", i, j));
            bw.newLine();


        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

        if (bw != null) {
            bw.close();
        }
    }


    public void mergeSort(int arr[], int l, int r) throws IOException {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }


    public void countSort(int[] arr) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));

        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // WRITING IN TEXT FILE
        for (int k = 0; k < output.length; k++) {
            bw.write(String.format("%d ", output[k]));
        }
        bw.newLine();

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;

            // WRITING IN TEXT FILE
            for (int k = 0; k < output.length; k++) {
                bw.write(String.format("%d ", output[k]));
            }
            bw.newLine();
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }

        if (bw != null) {
            bw.close();
        }
    }



    public void newSortedWindow() throws IOException {

        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SortedWindowFX.fxml"));
        Scene scene = new Scene(loader.load());
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle(Title);
        primaryStage.setScene(scene);
        ((SortedWindow) loader.getController()).init(primaryStage, Title);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.show();

    }
}

