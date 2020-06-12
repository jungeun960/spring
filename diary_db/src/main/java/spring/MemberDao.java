package spring;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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


public class MemberDao {

	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) { // 로그인 시 회원 정보 출력
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER1 where EMAIL = ?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("EMAIL"),
								rs.getString("PASSWORD"),
								rs.getString("NAME"),
								rs.getTimestamp("REGDATE").toLocalDateTime(),
								rs.getString("PHONENUM"), 
								rs.getString("ADDRESS"));
						member.setId(rs.getLong("ID"));
						return member;
					}
				}, email);

		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(Member member) { // 회워가입 등록
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER1 (EMAIL, PASSWORD, NAME, REGDATE, PHONENUM, ADDRESS) " +
						"values (?, ?, ?, ?, ?, ?)",
						new String[] { "ID" });
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4,
						Timestamp.valueOf(member.getRegisterDateTime()));
				pstmt.setString(5, member.getPhonenum());
				pstmt.setString(6, member.getAddress());
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}

	public void update(Member member) { // 패스워드 변경
		jdbcTemplate.update(
				"update MEMBER1 set NAME = ?, PASSWORD = ? where EMAIL = ?",
				member.getName(), member.getPassword(), member.getEmail());

//		jdbcTemplate.update(
//				"update MEMBER1 set PASSWORD = ? PHONENUM = ? ADDRESS = ? where EMAIL = ?",
//				member.getPassword(), member.getPhonenum(), member.getAddress(), member.getEmail());
	}
	
	public void update1(Member member) { // 번호 변경
		jdbcTemplate.update(
				"update MEMBER1 set PHONENUM = ? where EMAIL = ?",
				member.getPhonenum(), member.getEmail());
	}
	
	public void update2(Member member) { // 주소 변경
		jdbcTemplate.update(
				"update MEMBER1 set ADDRESS = ? where EMAIL = ?",
				member.getAddress(), member.getEmail());
	}

	public List<Member> selectAll() {// 회원 리스트 출력
		List<Member> results = jdbcTemplate.query("select * from MEMBER1",
				(ResultSet rs, int rowNum) -> {
					Member member = new Member(
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE").toLocalDateTime(),
							rs.getString("PHONENUM"), 
							rs.getString("ADDRESS"));
					member.setId(rs.getLong("ID"));
					return member;
				});
		return results;
	}

}
