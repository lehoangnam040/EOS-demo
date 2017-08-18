/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entity.Answer;
import entity.Question;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nam
 */
public class QuestionDatabaseLoader {

    Connection connection;

    public QuestionDatabaseLoader() {
        try {
            //you need to edit the database name, user name, password in your computer
            String url = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName;
            String username = "username";
            String password = "password";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuestionDatabaseLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Question> getAllQuestion() {
        ArrayList<Question> list = new ArrayList<>();
        try {
            String sql = "select [id], [content] from Question";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String content = rs.getString("content");
                ArrayList<Answer> answers = new ArrayList<>();
                ResultSet tmpRS = connection.prepareStatement("select [content], [correct] from Answer where qid = '" + id + "'").executeQuery();
                while (tmpRS.next()) {
                    String ansContent = tmpRS.getString("content");
                    boolean correct = tmpRS.getBoolean("correct");
                    answers.add(new Answer(ansContent, correct));
                }
                list.add(new Question(id, content, answers));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDatabaseLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
