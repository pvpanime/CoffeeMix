package dev.nemi.derekmuller;

import dev.nemi.derekmuller.food.FoodService;
import dev.nemi.derekmuller.food.dto.FoodAddDTO;
import dev.nemi.derekmuller.food.dto.FoodViewDTO;
import dev.nemi.derekmuller.food.dto.FoodWholeUpdateDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

@Log4j2
public class FoodServiceTest {

  private FoodService service;

  @BeforeEach
  public void setUp() {
    service = new FoodService();
  }

  //  @Test
//  public void getOneTest() throws SQLException {
//    FoodViewDTO dto = service.getOne(16);
//    log.info(dto);
//  }
  @Test
  public void addTest() throws SQLException {
//    service.add(FoodAddDTO.builder().name("건빵").price(3000).description("배만 채우는데는 이만한 게 없다").build());
    service.add(FoodAddDTO.builder().name("삼각김밥").price(1000).description("내 인생을 책임져 준 메뉴").build());
  }




  @Test
  public void getAllTest() throws SQLException {
    List<FoodViewDTO> list = service.listAll();
    log.info(list);
  }
//
//  @Test
//  public void updateTest() throws SQLException {
//    FoodViewDTO dto = service.getOne(21);
//    log.info(dto);
//
//    service.update(FoodWholeUpdateDTO.builder()
//      .id(21)
//      .name("건빵")
//      .price(3000)
//      .description("이 건빵진 놈")
//      .build()
//    );
//
//    dto = service.getOne(21);
//    log.info(dto);
//  }
//
//  @Test
//  public void updateStatusTest() throws SQLException {
//    FoodViewDTO dto;
//    service.setStatus(21, 0);
//    dto = service.getOne(21);
//    log.info(dto);
//  }
//
//  @Test
//  public void deleteTest() throws SQLException {
//    service.delete(21);
//  }

}
