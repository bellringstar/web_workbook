package com.example.b01.repository;

import com.example.b01.domain.Board;
import com.example.b01.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

}
