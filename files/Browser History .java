/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package browserhistory;

/**
 *
 * @author daryl
 */
import java.util.Scanner;

class Page {
    String url;
    Page next;
    Page prev;

    Page(String url) {
        this.url = url;
        this.next = null;
        this.prev = null;
    }
}

class BrowserHistory {
    private Page currentPage;

    public BrowserHistory() {
        currentPage = null;
    }

    public void visit(String url) {
        Page newPage = new Page(url);

        if (currentPage != null) {
            currentPage.next = newPage; // Clear forward history
            newPage.prev = currentPage;
        }

        currentPage = newPage;
        System.out.println("\nVisited: " + url);
    }

    public void goBack() {
        if (currentPage != null && currentPage.prev != null) {
            currentPage = currentPage.prev;
            System.out.println("\nBack to: " + currentPage.url);
        } else {
            System.out.println("\nNo previous page.");
        }
    }

    public void goForward() {
        if (currentPage != null && currentPage.next != null) {
            currentPage = currentPage.next;
            System.out.println("\nForward to: " + currentPage.url);
        } else {
            System.out.println("\nNo next page.");
        }
    }

    public void displayCurrentPage() {
        if (currentPage != null) {
            System.out.println("\nCurrent page: " + currentPage.url);
        } else {
            System.out.println("\nNo pages visited yet.");
        }
    }

    public void displayHistory() {
        if (currentPage == null) {
            System.out.println("\nNo history.");
            return;
        }

        System.out.println("\nHistory:");
        Page temp = currentPage;

        // Traverse backwards to display full history
        while (temp.prev != null) {
            temp = temp.prev;
        }

        // Display history from oldest to newest
        while (temp != null) {
            System.out.println(temp.url);
            temp = temp.next;
        }
    }
}

public class BrowserHistoryApp {
    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(" Browser History Menu:");
            System.out.println("1. Visit a page");
            System.out.println("2. Go Back");
            System.out.println("3. Go Forward");
            System.out.println("4. Display Current Page");
            System.out.println("5. Display History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the URL of the page to visit: ");
                    String url = scanner.nextLine();
                    history.visit(url);
                    break;
                case 2:
                    history.goBack();
                    break;
                case 3:
                    history.goForward();
                    break;
                case 4:
                    history.displayCurrentPage();
                    break;
                case 5:
                    history.displayHistory();
                    break;
                case 6:
                    System.out.println("\nExiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("\nInvalid choice, please try again.");
            }
        }
    }
}
