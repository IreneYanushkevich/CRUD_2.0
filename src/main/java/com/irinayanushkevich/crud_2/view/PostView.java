package com.irinayanushkevich.crud_2.view;

import com.irinayanushkevich.crud_2.controller.LabelController;
import com.irinayanushkevich.crud_2.controller.PostController;
import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostView {

    private final CommonView cv;
    private final PostController pc = new PostController();

    public PostView(CommonView cv) {
        this.cv = cv;
    }

    public boolean workWithPostActions(int act) {
        switch (act) {
            case 1 -> {
                Post post = preparePostData();
                System.out.println("\nYou created a new post:\n " + post);
            }
            case 2 -> {
                Long id = cv.askId();
                Post post = pc.getById(id);
                cv.printResultId(post);
            }
            case 3 -> {
                Long id = cv.askId();
                Post postForEdit = pc.getById(id);
                if (postForEdit != null) {
                    Post postAfterEdit = chooseWhatToEdit(postForEdit);
                    cv.printResultId(postAfterEdit);
                } else {
                    System.out.println("\nA post with this id doesn't exist.");
                }
            }
            case 4 -> {
                Long id = cv.askId();
                if (pc.delete(id)) {
                    System.out.println("\nThe post with id = " + id + " was deleted.");
                } else {
                    System.out.println("\nA post with this id doesn't exist.");
                }
            }
            case 5 -> {
                List<Post> posts = pc.getAll();
                if (posts.size() == 0) {
                    System.out.println("\nFile is empty or doesn't exist.");
                } else {
                    posts.forEach(System.out::println);
                }
            }
            case 0 -> {
                return true;
            }
        }
        return false;
    }

    protected Post preparePostData() {
        String content = cv.askString("Please, write your post content >>>>");
        List<Label> chosenLabels = chooseLabels();
        return pc.create(content, chosenLabels);
    }

    private List<Label> chooseLabels() {
        LabelController lc = new LabelController();
        List<Label> labels = lc.getAll();
        List<Label> chosen = new ArrayList<>();
        while (true) {
            System.out.println("\nInput the labels' id for adding to post from the list.\n" + labels);
            System.out.println("\nOr  '-2' for exit; '-1' for adding a new one");
            long id = cv.askId();
            if (id == -2) {
                break;
            } else if (id == -1) {
                Label newLabel = lc.create(cv.askString("Input new name >>>>>"));
                cv.printResultName(newLabel);
                if (newLabel != null) {
                    chosen.add(newLabel);
                }
            } else {
                Label addL = lc.getById(id);
                if (addL != null && !chosen.contains(addL)) {
                    chosen.add(addL);
                    labels.remove(addL);
                } else {
                    System.out.println("Label with this id doesn't exist or it's already added");
                }
            }
        }
        return chosen;
    }

    private void printSubMenuP() {
        System.out.println("""
                Choose what you want to change?
                1 - content
                2 - labels
                3 - content and labels
                4 - leave all as it is, change status on 'Active' only
                0 - exit
                """);
    }

    private Post chooseWhatToEdit(Post post) {
        Post postAfterEdit = null;
        long id = post.getId();
        printSubMenuP();
        int choice = cv.getAnswer(0, 4);
        switch (choice) {
            case 1 -> {
                String content = cv.askString("Please, write your post content >>>>>>");
                postAfterEdit = pc.edit(id, content, post.getLabels());
            }
            case 2 -> {
                List<Label> labels = chooseLabels();
                postAfterEdit = pc.edit(id, post.getContent(), labels);
            }
            case 3 -> {
                String content = cv.askString("Please, write your post content >>>>>>");
                List<Label> labels = chooseLabels();
                postAfterEdit = pc.edit(id, content, labels);
            }
            case 4 -> postAfterEdit = pc.edit(id, post.getContent(), post.getLabels());
            case 0 -> {
            }
        }
        return postAfterEdit;
    }
}