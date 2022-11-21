package com.irinayanushkevich.crud_2.view;

public class Console {
    private final CommonView comView = new CommonView();

    public void activateConsole() {

        int choice;
        boolean isFinish = false;

        while (!isFinish) {
            printMenu();
            choice = comView.getAnswer(0, 3);
            isFinish = workWithCategory(choice);
        }
    }

    private void printMenu() {
        System.out.println("""
                \nWhat category would you like to work with? Print 1, 2, 3 or 0:
                1 - Writer
                2 - Post
                3 - Label
                0 - Exit
                """);
    }

    private boolean workWithCategory(int answer) {
        switch (answer) {
            //case 1 -> comView.run("writer");
            case 2 -> comView.run("post");
            case 3 -> comView.run( "label");
            case 0 -> {
                return true;
            }
        }
        return false;
    }
}
