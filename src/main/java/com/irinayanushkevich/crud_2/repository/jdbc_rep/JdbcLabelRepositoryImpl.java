package com.irinayanushkevich.crud_2.repository.jdbc_rep;

import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.repository.LabelRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class JdbcLabelRepositoryImpl implements LabelRepository {

    public Label create(Label label) {
        Long id;
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatementWithKeys(SqlQuery.createLabel)) {
            preparedStatement.setString(1, label.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            id = getId(resultSet);
        } catch (SQLIntegrityConstraintViolationException e) {
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return getById(id);
    }

    public Label getById(Long id) {
        return getAll().stream().filter(label -> label.getId().equals(id)).findFirst().orElse(null);
    }

    public Label edit(Label label) {
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.editLabel)) {
            preparedStatement.setString(1, label.getName());
            preparedStatement.setLong(2, label.getId());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
                Label label = new Label("test");
                label.setId(resultSet.getLong("id"));
                label.setName(resultSet.getString("name"));
                labels.add(label);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labels;
    }

    private Long getId(ResultSet resultSet) {
        Long id = null;
        try {
            if (resultSet != null && resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
