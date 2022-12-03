package com.irinayanushkevich.crud_2.view;

import com.irinayanushkevich.crud_2.controller.LabelController;
import com.irinayanushkevich.crud_2.model.Label;

import java.util.List;

public class LabelView {
    private final CommonView cv;
    private final LabelController lc = new LabelController();

    public LabelView(CommonView cv) {
        this.cv = cv;
    }

    public boolean workWithLabelActions(int act) {
        switch (act) {
            case 1 -> {
                String name = cv.askString("Set a name >>>>");
                Label label = lc.create(name);
                cv.printResultName(label);
            }
            case 2 -> {
                Long id = cv.askId();
                Label label = lc.getById(id);
                cv.printResultId(label);
            }
            case 3 -> {
                Long id = cv.askId();
                if (lc.getById(id) != null) {
                    String name = cv.askString("Set a new name >>>>");
                    Label label = lc.edit(id, name);
                    cv.printResultName(label);
                } else {
                    System.out.println("Label with this index doesn't exist.");
                }
            }
            case 4 -> {
                Long id = cv.askId();
                if (lc.getById(id) != null && lc.delete(id)) {
                    System.out.println("\nThe label with id = " + id + " was deleted.");
                } else {
                    System.out.println("\nA label with this id doesn't exist.");
                }
            }
            case 5 -> {
                List<Label> labels = lc.getAll();
                if (labels.size() == 0) {
                    System.out.println("\nFile is empty or doesn't exist.");
                } else {
                    labels.forEach(System.out::println);
                }
            }
            case 0 -> {
                return true;
            }
        }
        return false;
    }
}
