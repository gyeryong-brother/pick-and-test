package com.gyeryongbrother.pickandtest.noticeboard.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.noticeboard.application.exception.handler.dto.ErrorResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.CommentResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostsResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.SimplePostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WriteCommentRequest;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WritePostRequest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("게시판 api를 제공한다")
@Sql("/truncate.sql")
public class NoticeboardControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("게시글을 작성합니다")
    void writePost() {
        //given
        WritePostRequest writePostRequest = new WritePostRequest("writePost", "테스팅입니다");
        PostResponse expected = new PostResponse(
                1L,
                1L,
                List.of(),
                "writePost",
                "테스팅입니다",
                null
        );

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse result = response.as(new TypeRef<>() {
        });

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("댓글을 작성합니다")
    void writeComment() {
        //given
        WritePostRequest writePostRequest = new WritePostRequest("writeComment", "테스팅입니다");
        WriteCommentRequest writeCommentRequest1 = new WriteCommentRequest("댓글1입니다");
        WriteCommentRequest writeCommentRequest2 = new WriteCommentRequest("댓글2입니다");
        WriteCommentRequest writeCommentRequest3 = new WriteCommentRequest("댓글3입니다");

        //when
        ExtractableResponse<Response> postResponse = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult = postResponse.as(new TypeRef<>() {
        });

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest1)
                .when().post("noticeboard/{postId}/comment", postResult.id())
                .then().log().all()
                .extract();

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest2)
                .when().post("noticeboard/{postId}/comment", postResult.id())
                .then().log().all()
                .extract();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest3)
                .when().post("noticeboard/{postId}/comment", postResult.id())
                .then().log().all()
                .extract();
        PostResponse result = response.as(new TypeRef<>() {
        });

        Comment comment1 = writeCommentRequest1.toCommand(1L, postResult.id()).toDomain();
        Comment comment2 = writeCommentRequest2.toCommand(1L, postResult.id()).toDomain();
        Comment comment3 = writeCommentRequest3.toCommand(1L, postResult.id()).toDomain();

        CommentResponse commentResponse1 = CommentResponse.from(comment1);
        CommentResponse commentResponse2 = CommentResponse.from(comment2);
        CommentResponse commentResponse3 = CommentResponse.from(comment3);

        List<CommentResponse> commentResponses = List.of(commentResponse1, commentResponse2, commentResponse3);

        PostResponse expected = new PostResponse(
                postResult.id(),
                1L,
                commentResponses,
                "writeComment",
                "테스팅입니다",
                null
        );

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("전체 게시글 조회")
    void findAllPosts() {
        //given
        WritePostRequest writePostRequest1 = new WritePostRequest("findAllPosts", "1번 게시글");
        WritePostRequest writePostRequest2 = new WritePostRequest("findAllPosts", "2번 게시글");
        WritePostRequest writePostRequest3 = new WritePostRequest("findAllPosts", "3번 게시글");

        ExtractableResponse<Response> postResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest1)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult1 = postResponse1.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> postResponse2 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest2)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult2 = postResponse2.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> postResponse3 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest3)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult3 = postResponse3.as(new TypeRef<>() {
        });

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when().get("noticeboard")
                .then().log().all()
                .extract();
        PostsResponse result = response.as(new TypeRef<>() {
        });

        SimplePostResponse simplePostResponse1 = new SimplePostResponse(postResult1.id(), postResult1.memberId(),
                postResult1.title(), null);
        SimplePostResponse simplePostResponse2 = new SimplePostResponse(postResult2.id(), postResult2.memberId(),
                postResult2.title(), null);
        SimplePostResponse simplePostResponse3 = new SimplePostResponse(postResult3.id(), postResult3.memberId(),
                postResult3.title(), null);
        PostsResponse expected = new PostsResponse(
                List.of(simplePostResponse1, simplePostResponse2, simplePostResponse3));

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("게시글id로 게시글 조회")
    void findPostById() {
        //given
        WritePostRequest writePostRequest1 = new WritePostRequest("findPostById", "1번 게시글");
        WritePostRequest writePostRequest2 = new WritePostRequest("findPostById", "2번 게시글");
        WritePostRequest writePostRequest3 = new WritePostRequest("findPostById", "3번 게시글");

        WriteCommentRequest writeCommentRequest1 = new WriteCommentRequest("댓글1입니다");
        WriteCommentRequest writeCommentRequest2 = new WriteCommentRequest("댓글2입니다");
        WriteCommentRequest writeCommentRequest3 = new WriteCommentRequest("댓글3입니다");

        ExtractableResponse<Response> postResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest1)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult1 = postResponse1.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> postResponse2 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest2)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult2 = postResponse2.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> postResponse3 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest3)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult3 = postResponse3.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> commentResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest1)
                .when().post("noticeboard/2/comment")
                .then().log().all()
                .extract();
        PostResponse commentResult1 = commentResponse1.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> commentResponse2 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest2)
                .when().post("noticeboard/2/comment")
                .then().log().all()
                .extract();
        PostResponse commentResult2 = commentResponse2.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> commentResponse3 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest3)
                .when().post("noticeboard/2/comment")
                .then().log().all()
                .extract();
        PostResponse commentResult3 = commentResponse3.as(new TypeRef<>() {
        });

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when().get("noticeboard/2")
                .then().log().all()
                .extract();
        PostResponse result = response.as(new TypeRef<>() {
        });

        PostResponse expected = new PostResponse(
                postResult2.id(),
                postResult2.memberId(),
                commentResult3.commentResponses(),
                postResult2.title(),
                postResult2.content(),
                null
        );

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() {
        //given
        WritePostRequest writePostRequest1 = new WritePostRequest("findPostById", "1번 게시글");

        WriteCommentRequest writeCommentRequest1 = new WriteCommentRequest("댓글1입니다");
        WriteCommentRequest writeCommentRequest2 = new WriteCommentRequest("댓글2입니다");
        WriteCommentRequest writeCommentRequest3 = new WriteCommentRequest("댓글3입니다");

        ExtractableResponse<Response> postResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest1)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult1 = postResponse1.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> commentResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest1)
                .when().post("noticeboard/1/comment")
                .then().log().all()
                .extract();
        PostResponse commentResult1 = commentResponse1.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> commentResponse2 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest2)
                .when().post("noticeboard/1/comment")
                .then().log().all()
                .extract();
        PostResponse commentResult2 = commentResponse2.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> commentResponse3 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest3)
                .when().post("noticeboard/1/comment")
                .then().log().all()
                .extract();
        PostResponse commentResult3 = commentResponse3.as(new TypeRef<>() {
        });

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .header("memberId", 1L)
                .when().delete("noticeboard/1/2")
                .then().log().all()
                .extract();
        PostResponse result = response.as(new TypeRef<>() {
        });

        List<CommentResponse> commentResponses = List.of(
                commentResult3.commentResponses().get(0),
                commentResult3.commentResponses().get(2)
        );

        PostResponse expected = new PostResponse(
                postResult1.id(),
                postResult1.memberId(),
                commentResponses,
                postResult1.title(),
                postResult1.content(),
                null
        );

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("게시글id로 게시글 삭제")
    void deletePost() {
        //given
        WritePostRequest writePostRequest1 = new WritePostRequest("findPostById", "1번 게시글");
        WritePostRequest writePostRequest2 = new WritePostRequest("findPostById", "2번 게시글");
        WritePostRequest writePostRequest3 = new WritePostRequest("findPostById", "3번 게시글");

        WriteCommentRequest writeCommentRequest1 = new WriteCommentRequest("댓글1입니다");

        ExtractableResponse<Response> postResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest1)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult1 = postResponse1.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> postResponse2 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest2)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult2 = postResponse2.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> postResponse3 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest3)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult3 = postResponse3.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> commentResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest1)
                .when().post("noticeboard/2/comment")
                .then().log().all()
                .extract();
        PostResponse commentResult1 = commentResponse1.as(new TypeRef<>() {
        });

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .header("memberId", 1L)
                .contentType(ContentType.JSON)
                .when().delete("noticeboard/2")
                .then().log().all()
                .extract();
        PostsResponse result = response.as(new TypeRef<>() {
        });

        SimplePostResponse simplePostResponse1 = new SimplePostResponse(
                postResult1.id(),
                postResult1.memberId(),
                postResult1.title(),
                postResult1.time()
        );
        SimplePostResponse simplePostResponse3 = new SimplePostResponse(
                postResult3.id(),
                postResult3.memberId(),
                postResult3.title(),
                postResult3.time()
        );

        PostsResponse expected = new PostsResponse(List.of(simplePostResponse1, simplePostResponse3));

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("다른 사람의 댓글 삭제")
    void deleteCommentWithInvalidMemberId() {
        //given
        WritePostRequest writePostRequest1 = new WritePostRequest("findPostById", "1번 게시글");

        WriteCommentRequest writeCommentRequest1 = new WriteCommentRequest("댓글1입니다");

        ExtractableResponse<Response> postResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest1)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult1 = postResponse1.as(new TypeRef<>() {
        });

        ExtractableResponse<Response> commentResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writeCommentRequest1)
                .when().post("noticeboard/1/comment")
                .then().log().all()
                .extract();
        PostResponse commentResult1 = commentResponse1.as(new TypeRef<>() {
        });

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .header("memberId", 2L)
                .when().delete("noticeboard/1/1")
                .then().log().all()
                .extract();
        ErrorResponse result = response.as(new TypeRef<>() {
        });

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
        assertThat(result.errorMessage()).isEqualTo("댓글을 삭제할 권한이 없습니다");
    }

    @Test
    @DisplayName("다른 사람의 게시글 삭제")
    void deletePostWithInvalidMemberId() {
        //given
        WritePostRequest writePostRequest1 = new WritePostRequest("findPostById", "1번 게시글");

        ExtractableResponse<Response> postResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest1)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult1 = postResponse1.as(new TypeRef<>() {
        });

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .header("memberId", 2L)
                .when().delete("noticeboard/1")
                .then().log().all()
                .extract();
        ErrorResponse result = response.as(new TypeRef<>() {
        });

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
        assertThat(result.errorMessage()).isEqualTo("게시글을 삭제할 권한이 없습니다");
    }

    @Test
    @DisplayName("없는 게시글 조회")
    void findNonExistPost() {
        //given
        WritePostRequest writePostRequest1 = new WritePostRequest("findPostById", "1번 게시글");

        ExtractableResponse<Response> postResponse1 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("memberId", 1L)
                .body(writePostRequest1)
                .when().post("noticeboard")
                .then().log().all()
                .extract();
        PostResponse postResult1 = postResponse1.as(new TypeRef<>() {
        });

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("noticeboard/2")
                .then().log().all()
                .extract();
        ErrorResponse result = response.as(new TypeRef<>() {
        });

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(result.errorMessage()).isEqualTo("존재하지 않는 게시글입니다");
    }
}


