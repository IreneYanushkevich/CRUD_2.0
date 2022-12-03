package com.irinayanushkevich.crud_2.repository.jdbc_rep;

public class SqlQuery {

    // SQL-queries for Label
    public static final String createLabel = "INSERT INTO label (name) VALUES (?)";
    public static final String getByIdLabel = "SELECT * FROM label WHERE id = ?";
    public static final String editLabel = "UPDATE label SET name = ? WHERE id = ?";
    public static final String deleteLabel = "DELETE FROM label WHERE id = ?";
    public static final String getAllLabels = "SELECT * FROM label";

    // SQL-queries for Post
    public static final String createPost = "INSERT INTO post (content, created, updated, post_status) VALUES (?, ?, ?, ?)";
    public static final String getByIdPost = "SELECT * FROM post WHERE id = ?";
    public static final String editPost = "UPDATE post SET content = ?, updated = ?, post_status = ?  WHERE id = ?";
    public static final String deletePost = "UPDATE post SET updated = ?, post_status = ? WHERE id = ?";
    public static final String getAllPosts = "SELECT * FROM post";
    public static final String getPostLabels = "SELECT * FROM label l LEFT JOIN posts_labels pl ON pl.label_id = l.id WHERE post_id = ?";
    public static final String fillDependencies = "INSERT INTO posts_labels (post_id, label_id) VALUES (?, ?)";
    public static final String deleteOldDependenciesPL = "DELETE FROM posts_labels WHERE post_id = ?";

    // SQL-queries for Writer
    public static final String createWriter = "INSERT INTO writer (first_name, last_name) VALUES (?, ?)";
    public static final String getByIdWriter = "SELECT * FROM writer WHERE id = ?";
    public static final String editWriter = "UPDATE writer SET first_name = ?, last_name = ? WHERE id = ?";
    public static final String deleteWriter = "DELETE FROM writer where id = ?";
    public static final String getAllWriters = "SELECT * FROM writer";
    public static final String getWriterPosts = "SELECT * FROM post WHERE writer_id = ?";
    public static final String setWriterIdToPost = "UPDATE post SET writer_id = ? WHERE id = ?";
    public static final String deleteOldDependenciesWP = "UPDATE post SET writer_id = null WHERE writer_id = ?";
}
