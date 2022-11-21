package com.irinayanushkevich.crud_2.repository.jdbc_rep;

import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.repository.LabelRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class JdbcLabelRepositoryImpl implements LabelRepository {

    public Label create(Label label) {
        if (checkName(label.getName())) {
            return null;
        } else {
            long id = generateId();
            label.setId(id);
            try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.createLabel)) {
                preparedStatement.setLong(1, id);
                preparedStatement.setString(2, label.getName());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return label;
    }

    public Label getById(Long id) {
        return getAll().stream().filter(label -> label.getId().equals(id)).findFirst().orElse(null);
    }

    public Label edit(Label label) {
        if (checkName(label.getName())) {
            return null;
        } else {
            try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.editLabel)) {
                preparedStatement.setString(1, label.getName());
                preparedStatement.setLong(2, label.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return label;
    }

    public boolean delete(Long id) {
        boolean deleted = false;
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.deleteLabel)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            deleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    public List<Label> getAll() {
        List<Label> labels = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.getAllLabels)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Label label = new Label();
                label.setId(resultSet.getLong("id"));
                label.setName(resultSet.getString("name"));
                labels.add(label);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labels;
    }

    private long generateId() {
        List<Label> labels = getAll();
        long id = 0;
        Optional<Label> l = labels.stream().max(Comparator.comparing(Label::getId));
        if (l.isPresent()) {
            id = l.get().getId() + 1;
        }
        return id;
    }

    private boolean checkName(String name) {
        List<Label> labels = getAll();
        return labels.stream().anyMatch(label -> label.getName().equals(name));
    }
}
