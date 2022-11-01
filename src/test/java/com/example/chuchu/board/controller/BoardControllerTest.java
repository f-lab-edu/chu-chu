//package com.example.chuchu.board.controller;
//
//import com.example.chuchu.board.dto.BoardRequestDTO;
//import com.example.chuchu.board.dto.BoardResponseDTO;
//import com.example.chuchu.board.entity.BoardType;
//import com.example.chuchu.board.service.BoardService;
//import com.example.chuchu.category.entity.Category;
//import com.example.chuchu.category.repository.CategoryRepository;
//import com.example.chuchu.comment.dto.CommentResponseDTO;
//import com.example.chuchu.common.global.HttpResponseEntity;
//import com.example.chuchu.member.dto.MemberDTO;
//import com.example.chuchu.member.entity.Level;
//import com.example.chuchu.member.entity.Member;
//import com.example.chuchu.member.entity.UserRole;
//import com.example.chuchu.member.repository.MemberRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.RestDocumentationContextProvider;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.payload.PayloadDocumentation;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static reactor.core.publisher.Mono.when;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(RestDocumentationExtension.class)    // JUnit5 필수
//public class BoardControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
////                .apply(springSecurity())    // springSecurity설정이 되어있지 않으면 생략
//                .apply(documentationConfiguration(restDocumentation)).build();
//    }
//
//    @Test
//    void getList() {
//    }
//
//    @Test
//    void getOne() {
//    }
//
//    @Test
//    @DisplayName("게시글 등록")
//    public void insert() throws Exception{
//
//        Member member = Member.builder()
//                .id(1L)
//                .email("email1")
//                .nickName("nick1")
//                .password("pw1")
//                .level(Level.BRONZE)
//                .userRole(UserRole.ROLE_USER)
//                .build();
//
//        memberRepository.save(member);
//
//        Category category = Category.builder()
//                .id(1L)
//                .name("cate1")
//                .build();
//
//        categoryRepository.save(category);
//
//        BoardRequestDTO boardRequestDTO = new BoardRequestDTO("COMPLAIN", 1L, 1L,
//                "title1", "hashtag1", "content", false);
//
//        String requestJson = objectMapper.writeValueAsString(boardRequestDTO);
//
//        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/chuchu")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(status().isOk())
//                .andDo(document("board-insert",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                fieldWithPath("boardType").description("게시판 종류"),
//                                fieldWithPath("memberId").description("멤버 ID"),
//                                fieldWithPath("cateId").description("카테고리 ID"),
//                                fieldWithPath("title").description("게시글 제목"),
//                                fieldWithPath("hashTag").description("hashtag 입력"),
//                                fieldWithPath("content").description("content 입력"),
//                                fieldWithPath("isSecret").description("비밀글 유무")
//                        ))
//                );
//
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//}