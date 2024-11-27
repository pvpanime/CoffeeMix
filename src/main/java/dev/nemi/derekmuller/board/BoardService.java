package dev.nemi.derekmuller.board;

import dev.nemi.derekmuller.Mapp;
import dev.nemi.derekmuller.board.dto.BoardAddDTO;
import dev.nemi.derekmuller.board.dto.BoardUpdateDTO;
import dev.nemi.derekmuller.board.dto.BoardViewDTO;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BoardService {

  private final BoardDI dao;
  private final ModelMapper mapper;

  public BoardService() {
    this.dao = new BoardDAO();
    this.mapper = Mapp.er;
  }

  public void add(BoardAddDTO dto) throws SQLException {
    dao.insert(mapper.map(dto, BoardVO.class));
  }

  public List<BoardViewDTO> list(long start, long count) throws SQLException {
    return dao.getListAt(start, count).stream().map(board -> mapper.map(board, BoardViewDTO.class)).collect(Collectors.toList());
  }

  public void update(BoardUpdateDTO dto) throws SQLException {
    dao.update(mapper.map(dto, BoardVO.class));
  }

  public BoardViewDTO get(long id) throws SQLException {
    return mapper.map(dao.getBoardById(id), BoardViewDTO.class);
  }

  public BoardViewDTO getByPathInfo(String pathInfo) throws NumberFormatException, SQLException {
    long id = Long.parseLong(pathInfo.substring(1));
    return get(id);
  }

  public void remove(String pathInfo) throws SQLException {
    long id = Long.parseLong(pathInfo.substring(1));
    dao.remove(id);
  }

  public long getMaxPage(int numPerPage) throws SQLException {
    return dao.getMaxPages(numPerPage);
  }


}
