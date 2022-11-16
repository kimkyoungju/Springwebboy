package com.web.domain.dao;

import com.web.domain.dto.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public BoardDao() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root", "1234");
            ps = con.prepareStatement("SELECT * FROM board");
            rs = ps.executeQuery();
        } catch (Exception e) {
            System.out.println("연동실패");
        }

    }

    public boolean setboard(BoardDto board) {

        String sql = "insert into board(btitle,bcontent)values(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, board.getBtitle());
            ps.setString(2, board.getBcontent());
            ps.executeUpdate();
            ;
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


    public ArrayList<BoardDto> getboards() {
        String sql = "select * from board";
        ArrayList<BoardDto> boards = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                  boards.add(
                          BoardDto
                                  .builder().
                                  btitle(rs.getString(1))
                                  .bcontent(rs.getString(2))
                                  .build()
                  );
                return boards;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return boards;

    }
}

