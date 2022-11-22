package com.irinayanushkevich.crud_2.repository.jdbc_rep;

import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.model.Post;
import com.irinayanushkevich.crud_2.model.PostStatus;
import com.irinayanushkevich.crud_2.repository.PostRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class JdbcPostRepositoryImpl implements PostRepository {

    @Override
    public Post create(Post post) {
        Post newPost = null;
        long id = generateId();
        Date date = setMyDate();
        try (PreparedStatement preparedStatementPost = JdbcConnector.getPreparedStatement(SqlQuery.createPost);
             PreparedStatement preparedStatementLabels = JdbcConnector.getPreparedStatement(SqlQuery.addLabelToPost)) {
            preparedStatementPost.setLong(1, id);
            preparedStatementPost.setString(2, post.getContent());
            preparedStatementPost.setDate(3, date);
            preparedStatementPost.setDate(4, date);
            preparedStatementPost.setString(5, String.valueOf(PostStatus.UNDER_REVIEW));
            preparedStatementPost.executeUpdate();
            for (Label label : post.getLabels()) {
                preparedStatementLabels.setLong(1, id);
                preparedStatementLabels.setLong(2, label.getId());
                preparedStatementLabels.executeUpdate();
            }
            newPost = getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPost;
    }

    @Override
    public Post getById(Long id) {
        return getAll().stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null);
    }

    public Post edit(Post post) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.getAllPosts)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setContent(resultSet.getString("content"));
                post.setCreated(String.valueOf(resultSet.getDate("created")));
                post.setUpdated(String.valueOf(resultSet.getDate("update")));
                post.setStatus(PostStatus.valueOf(resultSet.getString("post_status")));
                post.setLabels(getPostLabels(post.getId()));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private List<Label> getPostLabels(Long idPost) {
       // JdbcLabelRepositoryImpl labelRep = new JdbcLabelRepositoryImpl();
        List<Label> labels = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcConnector.getPreparedStatement(SqlQuery.getPostLabels)) {
            preparedStatement.setLong(1, idPost);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Label label = new Label(resultSet.getLong("id"), resultSet.getString("name"));
                labels.add(label);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labels;
    }


    private long generateId() {
        List<Post> posts = getAll();
        long id = 1;
        Optional<Post> p = posts.stream().max(Comparator.comparing(Post::getId));
        if (p.isPresent()) {
            id = p.get().getId() + 1;
        }
        return id;
    }

    private Date setMyDate() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        return Date.valueOf(now.format(formatter));
    }
}