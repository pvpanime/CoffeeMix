package dev.nemi.derekmuller.food;

import dev.nemi.derekmuller.Mapp;
import dev.nemi.derekmuller.food.dto.*;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class FoodService {
  private final FoodDI foodData = new FoodDAO();
  private final ModelMapper mapper = Mapp.er;

  public FoodViewDTO toView(FoodVO vo) {
    return mapper.map(vo, FoodViewDTO.class);
  }

  public FoodVO use(FoodAddDTO dto) {
    return mapper.map(dto, FoodVO.class);
  }

  public FoodVO use(FoodWholeUpdateDTO dto) {
    return mapper.map(dto, FoodVO.class);
  }

  public FoodViewDTO getOne(long id) throws SQLException {
    FoodVO vo = foodData.getById(id);
    return toView(vo);
  }

  public List<FoodViewDTO> listAll() throws SQLException {
    List<FoodVO> ls = foodData.getAll();
    return ls.stream().map(this::toView).collect(Collectors.toList());
  }

  public void add(FoodAddDTO dto) throws SQLException {
    foodData.add(use(dto));
  }

  public void update(FoodWholeUpdateDTO vo) throws SQLException {
    foodData.update(use(vo));
  }

  public void setStatus(long id, int status) throws SQLException {
    foodData.setStatus(id, status);
  }

  public void delete(long id) throws SQLException {
    foodData.delete(id);
  }


}
