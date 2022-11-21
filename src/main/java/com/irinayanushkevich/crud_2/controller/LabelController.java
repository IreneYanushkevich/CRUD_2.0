package com.irinayanushkevich.crud_2.controller;

import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.service.LabelService;

import java.util.List;

public class LabelController {
    private final LabelService LabelService = new LabelService();

    public Label create(String name) {
        Long id = null;
        Label l = new Label(id, name);
        return LabelService.create(l);
    }

    public Label getById(Long id) {
        return LabelService.getById(id);
    }

    public Label edit(Long id, String name) {
        Label label = new Label(id, name);
        return LabelService.edit(label);
    }

    public boolean delete(Long id) {
        return LabelService.delete(id);
    }

    public List<Label> getAll() {
        return LabelService.getAll();
    }
}
