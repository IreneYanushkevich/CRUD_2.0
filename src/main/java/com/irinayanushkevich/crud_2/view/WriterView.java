package com.irinayanushkevich.crud_2.view;

import com.irinayanushkevich.crud_2.controller.PostController;
import com.irinayanushkevich.crud_2.controller.WriterController;
import com.irinayanushkevich.crud_2.model.Post;
import com.irinayanushkevich.crud_2.model.Writer;

import java.util.ArrayList;
import java.util.List;

public class WriterView {
    private final CommonView cv;
    private final WriterController wc = new WriterController();
    private final PostController pc = new PostController();

    public WriterView(CommonView cv) {
        this.cv = cv;
    }

    public boolean workWithWriterActions(int act) {
        switch (act) {
            case 1 -> {
                String firstName = cv.askString("Please, input your name >>>>");
                String lastName = cv.askString("Please, input your last name >>>>");
                List<Post> chosenPosts = choosePosts();
                Writer writer = wc.create(firstName, lastName, chosenPosts);
                System.out.println("\nYou created a new writer:\n " + writer);
            }
            case 2 -> {
                Long id = cv.askId();
                Writer writer = wc.getById(id);
                cv.printResultId(writer);
            }
            case 3 -> {
                Long id = cv.askId();
                Writer writerForEdit = wc.getById(id);
                if (writerForEdit != null) {
                    Writer writerAfterEdit = chooseWhatToEdit(writerForEdit);
                    cv.printResultId(writerAfterEdit);
                } else {
                    System.out.println("\nA writer with this id doesn't exist.");
                }
            }
            case 4 -> {
                Long id = cv.askId();
                if (wc.getById(id) != null && wc.delete(id)) {
                    System.out.println("\nThe writer with id = " + id + " was deleted.");
                } else {
                    System.out.println("\nA writer with this id doesn't exist.");
                }
            }
            case 5 -> {
                List<Writer> writers = wc.getAll();
                if (writers.size() == 0) {
                    System.out.println("\nFile is empty or doesn't exist.");
                } else {
                    writers.forEach(System.out::println);
                }
            }
            case 0 -> {
                return true;
            }
        }
        return false;
    }

    private List<Post> choosePosts() {
        List<Post> posts = pc.getAll();
        List<Post> choice = new ArrayList<>();
        while (true) {
            System.out.println("\nInput the posts' id for adding to writer from the list.\n" + posts);
            System.out.println("\nOr '0' for exit, '-1' for adding a new post. >>>>>>>>\n");
            long id = cv.askId();
            if (id == 0) {
                break;
            } else if (id == -1) {
                PostView pv = new PostView(cv);
                Post post = pv.preparePostData();
                choice.add(post);
            } else {
                Post addP = pc.getById(id);
                if (addP != null && !choice.contains(addP)) {
                    choice.add(addP);
                    posts.remove(addP);
                } else {
                    System.out.println("Post with this id doesn't exist or it's already added");
                }
            }
        }
        return choice;
    }

    private Writer chooseWhatToEdit(Writer writer) {
        Writer writerAfterEdit = null;
        long id = writer.getId();
        printSubMenuW();
        int choice = cv.getAnswer(0, 3);
        switch (choice) {
            case 1 -> {
                String name = cv.askString("Input a new name >>>");
                writerAfterEdit = wc.edit(id, name, writer.getLastName(), writer.getPosts());
            }
            case 2 -> {
                String secondName = cv.askString("Input a new second name >>>");
                writerAfterEdit = wc.edit(id, writer.getFirstName(), secondName, writer.getPosts());
            }
            case 3 -> {
                List<Post> posts = choosePosts();
                writerAfterEdit = wc.edit(id, writer.getFirstName(), writer.getLastName(), posts);
            }
            case 0 -> {
            }
        }
        return writerAfterEdit;
    }

    private void printSubMenuW() {
        System.out.println("""
                Choose what you want to change?
                1 - name
                2 - second name
                3 - list of posts
                0 - exit
                """);
    }
}
