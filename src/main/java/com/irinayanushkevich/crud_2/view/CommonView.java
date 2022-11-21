package com.irinayanushkevich.crud_2.view;

import java.util.Scanner;

public class CommonView {

    private final Scanner scanner = new Scanner(System.in);
    private final LabelView lv = new LabelView(this);
    private final PostView pv = new PostView(this);
   // private final WriterView wv = new WriterView(this);

    public void run(String category) {
        int act;
        boolean isFinish = false;
        while (!isFinish) {
            printActions(category);
            act = getAnswer(0, 5);
            switch (category) {
              //  case "writer" -> isFinish = wv.workWithWriterActions(act);
                case "post" -> isFinish = pv.workWithPostActions(act);
                case "label" -> isFinish = lv.workWithLabelActions(act);
            }
        }
    }

    private void printActions(String category) {
        System.out.println("""
                \nChoose what you want to do with\t""" + category + """
                \nPrint 1, 2, 3, 4 or 0:
                1 - Create
                2 - Find by index
                3 - Edit
                4 - Delete
                5 - See the whole list
                0 - Exit
                """);
    }

    public int getAnswer(int from, int to) {
        int answer;
        try {
            answer = Integer.parseInt(scanner.nextLine());
            if (answer < from || answer > to) {
                System.out.println("\nYou can choose only from " + from + " to " + to + "!\n");
            } else {
                return answer;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid input. Try again.");
        }
        return -1;
    }

    public String askString(String message) {
        System.out.println("\n" + message);
        return scanner.nextLine();
    }

    public long askId() {
        long id;
        System.out.println("\nEnter the id of the position >>>");
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Try again.");
            }
        }
        return id;
    }

    public void printResultId(Object obj) {
        if (obj == null) {
            System.out.println("\nPosition with this index doesn't exist.");
        } else {
            System.out.println("\nDone! Work with the next position is completed: " + obj);
        }
    }

    public void printResultName(Object obj) {
        if (obj == null) {
            System.out.println("\nPosition with this name already exist.");
        } else {
            System.out.println("\nDone! Work with the next position is completed: " + obj);
        }
    }
}
