package dev.nemi.derekmuller.food;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

public interface FoodDI {
  List<FoodVO> getAll() throws SQLException;

  List<FoodWithReviewVO> listWithReview() throws SQLException;

  FoodVO getOneById(long id) throws SQLException;

  void add(@NotNull FoodVO food) throws SQLException;

  void update(@NotNull FoodVO food) throws SQLException;

  void setStatus(long id, int status) throws SQLException;

  int delete(long id) throws SQLException;

}
