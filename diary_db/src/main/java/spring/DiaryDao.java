package spring;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.sql.DataSource;
import java.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class DiaryDao {

	private JdbcTemplate jdbcTemplate;

	public DiaryDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Diary selectBytitle(String title) {
		List<Diary> results = jdbcTemplate.query(
				"select * from Diary where TITLE = ?",
				new RowMapper<Diary>() {
					@Override
					public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
						Diary diary = new Diary(
								rs.getString("EMAIL"),
								rs.getTimestamp("REGDATE").toLocalDateTime(),
								rs.getString("TITLE"),
								rs.getString("TEXT"),
								rs.getString("FILE"));
						diary.setId(rs.getLong("ID"));
						return diary;
					}
				}, title);

		return results.isEmpty() ? null : results.get(0);
	}


	public Diary selectByid(int id) { // 다이어리 ID값으로 조회하기
		List<Diary> results = jdbcTemplate.query(
				"select * from Diary where ID = ?",
				new RowMapper<Diary>() {
					@Override
					public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
						Diary diary = new Diary(
								rs.getString("EMAIL"),
								rs.getTimestamp("REGDATE").toLocalDateTime(),
								rs.getString("TITLE"),
								rs.getString("TEXT"),
								rs.getString("FILE"));
						diary.setId(rs.getLong("ID"));
						return diary;
					}
				}, id);

		return results.isEmpty() ? null : results.get(0);
	}
	
	public void insert(Diary diary) { // 다이어리 작성
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
						"insert into Diary (EMAIL, REGDATE, TITLE, TEXT, FILE) " +
						"values (?, ?, ?, ?, ?)",
						new String[] { "ID" });
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, diary.getEmail());
				pstmt.setTimestamp(2, Timestamp.valueOf(diary.getRegisterDateTime()));
				pstmt.setString(3, diary.getTitle());
				pstmt.setString(4, diary.getText());
				pstmt.setString(5, diary.getFile());
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		diary.setId(keyValue.longValue());
	}

	public void update_title(Diary diary) { // 다이어리 제목 변경
		jdbcTemplate.update(
				"update Diary set TITLE = ? where ID = ?",
				diary.getTitle(), diary.getId());
	}
	
	public void update_text(Diary diary) { // 다이어리 내용 변경
		jdbcTemplate.update(
				"update Diary set TEXT = ? where ID = ?",
				diary.getText(), diary.getId());
	}
	
	public void update_file(Diary diary) { // 다이어리 경로 변경
		jdbcTemplate.update(
				"update Diary set FILE = ? where ID = ?",
				diary.getFile(), diary.getId());
	}

	public List<Diary> selectAll() { // 다이어리 목록 
		List<Diary> results = jdbcTemplate.query("select * from DIARY",
				(ResultSet rs, int rowNum) -> {
					Diary diary = new Diary(
							rs.getString("EMAIL"),
							rs.getTimestamp("REGDATE").toLocalDateTime(),
							rs.getString("TITLE"),
							rs.getString("TEXT"),
							rs.getString("FILE"));
					diary.setId(rs.getLong("ID"));
					return diary;
				});
		return results;
	}
	
	
	public List<Diary> selectAll_email(String email) { // 해당 유저의 다이어리 목록
		List<Diary> results = jdbcTemplate.query("select * from DIARY where EMAIL = ? ",
				(ResultSet rs, int rowNum) -> {
					Diary diary = new Diary(
							rs.getString("EMAIL"),
							rs.getTimestamp("REGDATE").toLocalDateTime(),
							rs.getString("TITLE"),
							rs.getString("TEXT"),
							rs.getString("FILE"));
					diary.setId(rs.getLong("ID"));
					return diary;
				},email);
		return results;
	}
	
	public List<Diary> selectAll_title(String title, String email) { // 유저 다이어리의 제목 검색 결과
		List<Diary> results = jdbcTemplate.query("select * from DIARY where TITLE = ? AND EMAIL = ?",
				(ResultSet rs, int rowNum) -> {
					Diary diary = new Diary(
							rs.getString("EMAIL"),
							rs.getTimestamp("REGDATE").toLocalDateTime(),
							rs.getString("TITLE"),
							rs.getString("TEXT"),
							rs.getString("FILE"));
					diary.setId(rs.getLong("ID"));
					return diary;
				},title, email);
		return results;
	}
	
}
