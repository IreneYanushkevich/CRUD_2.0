package com.irinayanushkevich.crud_2.repository.jdbc_rep;

public class SqlQuery {

    // SQL-queries for Label
    public static final String createLabel = "INSERT INTO label (id, name) VALUES (?,?)";
    public static final String editLabel = "UPDATE label SET name = ? WHERE id = ?";
    public static final String deleteLabel = "DELETE FROM label WHERE id = ?";
    public static final String getAllLabels = "SELECT * FROM label";

    // SQL-queries for Post
    public static final String createPost = "INSERT INTO post (id, content, created, `update`, post_status) VALUES (?, ?, ?, ?, ?)";
    public static final String editPost = "UPDATE post SET content = ?, `update` = ?, post_status = ?  WHERE id = ?";
    //public static final String deletePost = "DELETE FROM post WHERE id = ?";
    public static final String deletePost = "UPDATE post SET `update` = ?, post_status = ? WHERE id = ?";
    public static final String getAllPosts = "SELECT * FROM post";
    public static final String getPostLabels = "SELECT id, name FROM label WHERE post_id = ?";
    public static final String addLabelToPost = "UPDATE label SET post_id = ? WHERE id = ?";

}
