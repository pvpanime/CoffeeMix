package dev.nemi.derekmuller.food;

import dev.nemi.derekmuller.TachibanaHikari;
import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO implements FoodDI {

  private static FoodVO voFrom(ResultSet rs) throws SQLException {
    return FoodVO.builder()
      .id(rs.getLong("id"))
      .name(rs.getString("name"))
      .description(rs.getString("description"))
      .price(rs.getLong("wonPrice"))
      .status(rs.getInt("status"))
      .build();
  }

  private static FoodWithReviewVO rVoFrom(ResultSet rs) throws SQLException {
    return FoodWithReviewVO.builder()
      .id(rs.getLong("id"))
      .name(rs.getString("name"))
      .description(rs.getString("description"))
      .price(rs.getLong("wonPrice"))
      .status(rs.getInt("status"))
      .rate(rs.getDouble("avgRate"))
      .build();
  }

  @Override
  public List<FoodVO> getAll() throws SQLException {
    List<FoodVO> list = new ArrayList<>();
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("SELECT * FROM Food");
    @Cleanup ResultSet rs = ps.executeQuery();
    while (rs.next()) list.add(voFrom(rs));
    return list;
  }

  @Override
  public List<FoodWithReviewVO> listWithReview() throws SQLException {
    List<FoodWithReviewVO> list = new ArrayList<>();
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("SELECT * FROM FoodWithReview");
    @Cleanup ResultSet rs = ps.executeQuery();
    while (rs.next()) list.add(rVoFrom(rs));
    return list;
  }

  @Override
  public FoodVO getOneById(long id) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("SELECT * FROM Food WHERE id = ?");
    ps.setLong(1, id);
    @Cleanup ResultSet rs = ps.executeQuery();
    if (rs.next()) return voFrom(rs);

    return null;
  }

  @Override
  public void add(@NotNull FoodVO food) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("INSERT INTO Food (name, description, wonPrice) VALUES (?, ?, ?)");
    ps.setString(1, food.getName());
    ps.setString(2, food.getDescription());
    ps.setLong(3, food.getPrice());
    ps.executeUpdate();
  }

  @Override
  public void update(@NotNull FoodVO food) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement(
      "UPDATE Food SET name = IFNULL(?, name), description = IFNULL(?, description), wonPrice = IFNULL(?, wonPrice) WHERE id = ?"
    );
    ps.setString(1, food.getName());
    ps.setString(2, food.getDescription());
    ps.setLong(3, food.getPrice());
    ps.setLong(4, food.getId());
    ps.executeUpdate();
  }

  @Override
  public void setStatus(long id, int status) throws SQLException {
    @Cleanup Connection connection = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = connection.prepareStatement("UPDATE Food SET status = ? WHERE id = ?");
    ps.setInt(1, status);
    ps.setLong(2, id);
    ps.executeUpdate();
  }

  @Override
  public int delete(long id) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("DELETE FROM Food WHERE id = ?");
    ps.setLong(1, id);
    int rows = ps.executeUpdate();
    return rows;
  }
}
