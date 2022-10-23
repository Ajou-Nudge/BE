package com.vone.vone.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vone.vone.data.dto.*;
import com.vone.vone.data.entity.PostDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class FlowChartTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;

    //given
    String testHolder = "dongjae712";
    String testIssuer = "ajouUniv";
    String testVerifier = "ajouGraduateSchool";
    String testContext = "graduate-certificate";
    VCDto testVC = new VCDto(testContext,testIssuer,new CredentialSubject("Ajou-univ-graduate-certificate","DONGJAE-OH","2022-02-10","software","","","",""));

    PostDto testPost = new PostDto("Ajou-GraduateSchool","2022-12-10","[graduate-certificate]","https://testurl.com",testVerifier);

    @Test
    @DisplayName("1. [Issuer] Context 등록")
    void test01() throws Exception {
        //given
        CredentialSubject credentialSubject = new CredentialSubject("title","name","date","major","doubleMajor","minor","","");
        //when
        /**
         * Object를 JSON으로 변환
         * */
        String body = mapper.writeValueAsString(
                ContextDto.builder().context(testContext).credentialSubject(credentialSubject).build()
        );
        //then
        mvc.perform(post("/issuer/context")
                        .content(body) //HTTP Body에 데이터를 담는다
                        .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("2. [Issuer] VC 발행")
    void test02() throws Exception {

        String vc2Issue = mapper.writeValueAsString(
                VC2IssueDto.builder().holderId(testHolder).vc(testVC).build()
        );

        List<String> body = new ArrayList<>();
        body.add(vc2Issue);

        mvc.perform(post("/issuer/vc")
                        .content(body.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("3. [Issuer] 발행한 context 목록 반환")
    void test03() throws Exception {

        List<String> expected = new ArrayList<>();
        expected.add(("\""+testContext+"\""));

        mvc.perform(get("/issuer/context-list/ajouUniv")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(expected.toString()));
    }

    @Test
    @DisplayName("4. [Issuer] context에 따른 발행한 VC 리스트 반환")
    void test04() throws Exception {
        VC2IssueDto vc2Issue = new VC2IssueDto(testHolder,testVC);
        mvc.perform(get("/issuer/vc-list/graduate-certificate")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].holderId").value(vc2Issue.getHolderId()))
                .andExpect(jsonPath("$[0].vc.context").value(vc2Issue.getVc().getContext()))
                .andExpect(jsonPath("$[0].vc.issuer").value(vc2Issue.getVc().getIssuer()))
                .andExpect(jsonPath("$[0].vc.credentialSubject.value1").value(vc2Issue.getVc().getCredentialSubject().getValue1()))
                .andExpect(jsonPath("$[0].vc.credentialSubject.value2").value(vc2Issue.getVc().getCredentialSubject().getValue2()))
                .andExpect(jsonPath("$[0].vc.credentialSubject.value3").value(vc2Issue.getVc().getCredentialSubject().getValue3()))
                .andExpect(jsonPath("$[0].vc.credentialSubject.value4").value(vc2Issue.getVc().getCredentialSubject().getValue4()));
    }

    @Test
    @DisplayName("5. [Verifier] Verifier가 채용 공고 등록")
    void test05() throws Exception {
        String body = mapper.writeValueAsString(
                PostDto.builder().url(testPost.getUrl()).title(testPost.getTitle()).expired(testPost.getExpired()).required(testPost.getRequired()).verifierId(testPost.getVerifierId()).build()
        );

        mvc.perform(post("/verifier/post")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("6. [Verifier] 내가 등록한 채용공고 확인")
    void test06() throws Exception {
        mvc.perform(get("/verifier/post-list/ajouGraduateSchool")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(testPost.getTitle()))
                .andExpect(jsonPath("$[0].expired").value(testPost.getExpired()))
                .andExpect(jsonPath("$[0].required").value(testPost.getRequired()))
                .andExpect(jsonPath("$[0].url").value(testPost.getUrl()))
                .andExpect(jsonPath("$[0].verifierId").value(testPost.getVerifierId()));

    }

    @Test
    @DisplayName("7. [Holder] 내가 보유한 VC 확인")
    void test07() throws Exception {
        mvc.perform(get("/holder/vc-list/dongjae712")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].context").value(testVC.getContext()))
                .andExpect(jsonPath("$[0].issuer").value(testVC.getIssuer()))
                .andExpect(jsonPath("$[0].credentialSubject.value1").value(testVC.getCredentialSubject().getValue1()))
                .andExpect(jsonPath("$[0].credentialSubject.value2").value(testVC.getCredentialSubject().getValue2()))
                .andExpect(jsonPath("$[0].credentialSubject.value3").value(testVC.getCredentialSubject().getValue3()))
                .andExpect(jsonPath("$[0].credentialSubject.value4").value(testVC.getCredentialSubject().getValue4()));

    }


    @Test
    @DisplayName("8. [Holder] 올라온 채용공고 확인")
    void test08() throws Exception {
        mvc.perform(get("/holder/post-list")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(testPost.getTitle()))
                .andExpect(jsonPath("$[0].url").value(testPost.getUrl()))
                .andExpect(jsonPath("$[0].verifierId").value(testPost.getVerifierId()))
                .andExpect(jsonPath("$[0].expired").value(testPost.getExpired()))
                .andExpect(jsonPath("$[0].required").value(testPost.getRequired()));
    }

    @Test
    @DisplayName("9. [Holder] VC 제출")
    void test09() throws Exception {
        Long one = new Long(1);
        List<Long> vcIds = new ArrayList<>();
        vcIds.add(one);
        String body = mapper.writeValueAsString(
                VC2VerifyDto.builder().postId(one).vcIds(vcIds).holder(testHolder).build()
        );

        mvc.perform(post("/holder/submitted-vc")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("10. [Holder] 제출된 VC 목록")
    void test10() throws Exception {
        mvc.perform(get("/holder/submitted-vc-list/dongjae712")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].vcId").value(new Long(1)))
                .andExpect(jsonPath("$[0].postId").value(new Long(1)))
                .andExpect(jsonPath("$[0].verifier").value(testVerifier))
                .andExpect(jsonPath("$[0].title").value(testPost.getTitle()))
                .andExpect(jsonPath("$[0].status").value("pending"));
    }

    @Test
    @DisplayName("11. [Verifier] 제출받은 VC 확인")
    void test11() throws Exception {
        mvc.perform(get("/verifier/submitted-vc-list/ajouGraduateSchool/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].vcId").value(new Long(1)))
                .andExpect(jsonPath("$[0].postId").value(new Long(1)))
                .andExpect(jsonPath("$[0].verifier").value(testVerifier))
                .andExpect(jsonPath("$[0].title").value(testPost.getTitle()))
                .andExpect(jsonPath("$[0].status").value("pending"));
    }

    @Test
    @DisplayName("12. [Verifier] VC 검증")
    void test12() throws Exception {
        Long one = new Long(1);
        List<Long> vcIds = new ArrayList<>();
        vcIds.add(one);
        String body = mapper.writeValueAsString(
                VC2VerifyDto.builder().postId(one).vcIds(vcIds).holder(testHolder).build()
        );

        mvc.perform(post("/verifier/verify")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("13. [Holder] 제출된 VC 목록(검증 완료)")
    void test13() throws Exception {
        mvc.perform(get("/holder/submitted-vc-list/dongjae712")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].vcId").value(new Long(1)))
                .andExpect(jsonPath("$[0].postId").value(new Long(1)))
                .andExpect(jsonPath("$[0].verifier").value(testVerifier))
                .andExpect(jsonPath("$[0].title").value(testPost.getTitle()))
                .andExpect(jsonPath("$[0].status").value("success"));
    }

}