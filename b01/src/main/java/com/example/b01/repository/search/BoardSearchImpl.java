package com.example.b01.repository.search;

import com.example.b01.domain.Board;
import com.example.b01.domain.QBoard;
import com.example.b01.domain.QReply;
import com.example.b01.dto.BoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    //QuerydslRepositorySupport를 상속받아 새로운 클래스를 정의할 때 해당 클래스의 생성자에서 super 키워드를 사용하여 상위 클래스의 생성자를 호출해야한다.
    //이렇게 함으로써 하위 클래스는 상위 클래스에서 정의도니 필드와 메서드들을 초기화하고 사용할 수 있다.
    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if ( (types != null && types.length > 0) && keyword != null) {
            //검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {

                switch (type) {
                    case "t" -> {
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    }
                    case "c" -> {
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    }
                    case "w" -> {
                        booleanBuilder.or(board.writer.contains(keyword));
                    }
                }
            }
            query.where(booleanBuilder);
        }
        // bno > 0
        query.where(board.bno.gt(0L));
        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

//        return null;
        return new PageImpl<>(list, pageable, count);
    }


    @Override
    public Page<Board> search1(Pageable pageable) {

        QBoard board = QBoard.board; //Q도메인 객체

        JPQLQuery<Board> query = from(board); //select.. from board

        query.where(board.title.contains("1")); //where title like...

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(board.title.contains("11")); // title like ...
        booleanBuilder.or(board.content.contains("11")); // content like ...

        query.where(booleanBuilder);
        query.where(board.bno.gt(0L));

        return null;
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));

        query.groupBy(board);

        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t" -> {
                        booleanBuilder.or(board.title.contains(keyword));
                    }
                    case "c" -> {
                        booleanBuilder.or(board.content.contains(keyword));
                    }
                    case "w" -> {
                        booleanBuilder.or(board.writer.contains(keyword));
                    }
                }
            }
            query.where(booleanBuilder);
        }

        query.where(board.bno.gt(0L));

        /* Projections.bean() = JPA에서는 Projection이라고 해서 JPQL 결과를 바로 DTO로 처리하는 기능을 제공한다.
         Querydsl도 마찬가지로 이러한 기능을 제공한다. */
        JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(
                Projections.bean(BoardListReplyCountDTO.class,
                        board.bno,
                        board.title,
                        board.writer,
                        board.regDate,
                        reply.count().as("replyCount)")));

        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<BoardListReplyCountDTO> dtoList = dtoQuery.fetch();

        long count = dtoQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
    }
}
