package com.irinayanushkevich.crud_2.repository.jdbc_rep;

import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.model.Post;
import com.irinayanushkevich.crud_2.model.PostStatus;
import com.irinayanushkevich.crud_2.repository.PostRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository {

    @Override
    public Post create(Post post) {
        Long id;
        Timestamp date = getDate();
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatementWithKeys(SqlQuery.createPost)) {
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, date);
            preparedStatement.setTimestamp(3, date);
            preparedStatement.setString(4, PostStatus.UNDER_REVIEW.toString());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            id = getId(resultSet);
            fillDependencies(post.getLabels(), id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return getById(id);
    }

    @Override
    public Post getById(Long id) {
        return getAll().stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null);
    }

    public Post edit(Post post) {
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.editPost)) {
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, getDate());
            preparedStatement.setString(3, PostStatus.ACTIVE.toString());
            preparedStatement.setLong(4, post.getId());
            preparedStatement.executeUpdate();
            deleteOldDependencies(post.getId());
            fillDependencies(post.getLabels(), post.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return getById(post.getId());
    }

    @Override
    public boolean delete(Long id) {
        boolean deleted = false;
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.deletePost)) {
            preparedStatement.setTimestamp(1, getDate());
            preparedStatement.setString(2, PostStatus.DELETED.toString());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
            deleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.getAllPosts)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = mapResultSetToPost(resultSet);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return posts;
    }

    public Post mapResultSetToPost(ResultSet resultSet) {
        try {
            Post post = new Post();
            post.setId(resultSet.getLong("id"));
            post.setContent(resultSet.getString("content"));
            post.setCreated(resultSet.getTimestamp("created").toString());
            post.setUpdated(resultSet.getTimestamp("updated").toString());
            post.setStatus(PostStatus.valueOf(resultSet.getString("post_status")));
            post.setLabels(getPostLabels(post.getId()));
            return post;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Label> getPostLabels(Long postId) {
        List<Label> labels = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.getPostLabels)) {
            preparedStatement.setLong(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Label label = new Label(resultSet.getLong("id"), resultSet.getString("name"));
                labels.add(label);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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

    private Timestamp getDate() {
        return new Timestamp(new Date().getTime());
    }

    private void fillDependencies(List<Label> labels, Long id) {
        for (Label label : labels) {
            try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.fillDependencies)) {
                preparedStatement.setLong(1, id);
                preparedStatement.setLong(2, label.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteOldDependencies(Long id) {
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.deleteOldDependencies)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
