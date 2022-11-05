package com.vone.vone.service;

import com.vone.vone.data.dao.HoldersVCDAO;
import com.vone.vone.data.dao.PostDAO;
import com.vone.vone.data.dao.SubmittedVCDAO;
import com.vone.vone.data.dao.VCDAO;
import com.vone.vone.data.dto.*;
import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.Post;
import com.vone.vone.data.entity.SubmittedVC;
import com.vone.vone.data.entity.VC;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VCService {

    private final SubmittedVCDAO submittedVCDAO;
    private final PostDAO postDAO;
    private final HoldersVCDAO holdersVCDAO;
    private final VCDAO vcDao;

    @Autowired
    public Long issueVC(VC2IssueDto vc2IssueDto)
    {
        VC vc = new VC();
        vc.setContext(vc2IssueDto.getVc().getContext());
        vc.setIssuer(vc2IssueDto.getVc().getIssuer());
        vc.setValue1(vc2IssueDto.getVc().getCredentialSubject().getValue1());
        vc.setValue2(vc2IssueDto.getVc().getCredentialSubject().getValue2());
        vc.setValue3(vc2IssueDto.getVc().getCredentialSubject().getValue3());
        vc.setValue4(vc2IssueDto.getVc().getCredentialSubject().getValue4());
        vc.setValue5(vc2IssueDto.getVc().getCredentialSubject().getValue5());
        vc.setValue6(vc2IssueDto.getVc().getCredentialSubject().getValue6());
        vc.setValue7(vc2IssueDto.getVc().getCredentialSubject().getValue7());
        vc.setValue8(vc2IssueDto.getVc().getCredentialSubject().getValue8());
        vc.setUpdatedAt(LocalDateTime.now());
        vc.setCreatedAt(LocalDateTime.now());

        VC savedVC = vcDao.insertVC(vc);

        HoldersVC holdersVC = new HoldersVC();
        holdersVC.setContext(savedVC.getContext());
        holdersVC.setIssuerId(savedVC.getIssuer());
        holdersVC.setVcId(savedVC.getId());
        holdersVC.setHolderId(vc2IssueDto.getHolderId());
        holdersVC.setCreatedAt(LocalDateTime.now());
        holdersVC.setUpdatedAt(LocalDateTime.now());
        holdersVCDAO.insertHoldersVC(holdersVC);

        return savedVC.getId();
    }


    @Autowired
    public List<VC2IssueDto> getVCByIssuerId(String issuerId, HoldersVCDAO holdersVCDAO, VCDAO vcDao)
    {
        List<HoldersVC> vcs = holdersVCDAO.getHoldersVCByIssuerId(issuerId);
        List<VC2IssueDto> vcResponses = new ArrayList<>();
        for(HoldersVC vc : vcs){
            VC2IssueDto vcResponse = new VC2IssueDto();

            vcResponse.setHolderId(vc.getHolderId());
            VC tempVc = vcDao.selectVC(vc.getVcId());
            vcResponse.setVc(VCToVCDto(tempVc));

            vcResponses.add(vcResponse);
        }
        return vcResponses;
    }

    public VCDto VCToVCDto(VC vc){
        VCDto result = new VCDto();
        result.setContext(vc.getContext());
        result.setIssuer(vc.getIssuer());
        CredentialSubject credentialSubject = new CredentialSubject(vc.getValue1(), vc.getValue2(), vc.getValue3(), vc.getValue4(), vc.getValue5(), vc.getValue6(),vc.getValue7(),vc.getValue8());


        result.setCredentialSubject(credentialSubject);
        return result;
    }


    @Autowired
    public List<VCDto> getVCByHolderId(String holderId)
    {
        List<HoldersVC> vcs = holdersVCDAO.getHoldersVCByHolderId(holderId);
        List<VCDto> vcResponses = new ArrayList<>();
        for(HoldersVC vc : vcs){
            VC tempVc = vcDao.selectVC(vc.getVcId());
            VCDto vcResponse = VCToVCDto(tempVc);

            vcResponses.add(vcResponse);
        }

        return vcResponses;
    }


    @Autowired
    public boolean submitVC(VC2VerifyDto vc2VerifyDto)
    {

        String verifierId = postDAO.selectPost(vc2VerifyDto.getPostId()).getVerifierId();

        for(Long vcid : vc2VerifyDto.getVcIds()){
            VC tempVC = vcDao.selectVC(vcid);

            SubmittedVC submittedVC = new SubmittedVC();
            submittedVC.setVerifierId(verifierId);
            submittedVC.setPostId(vc2VerifyDto.getPostId());
            submittedVC.setVcId((tempVC.getId()));
            submittedVC.setHolderId(vc2VerifyDto.getHolder());
            submittedVC.setIssuerId(tempVC.getIssuer());
            submittedVC.setStatus("pending");
            submittedVC.setCreatedAt(LocalDateTime.now());
            submittedVC.setUpdatedAt(LocalDateTime.now());


            submittedVCDAO.insertSubmittedVC(submittedVC);

        }
        return true;
    }


    @Autowired
    public List<SubmittedVCResponseDto> getSubmittedVCByHolder (String holderId)
    {
        List<SubmittedVC> submittedVCS = submittedVCDAO.getSubmittedVCByHolderId(holderId);
        List<SubmittedVCResponseDto> submittedVCResponseDtos = new ArrayList<>();
        for(SubmittedVC vc : submittedVCS){
            Post post = postDAO.selectPost(vc.getPostId());

            SubmittedVCResponseDto submittedVCResponseDto = new SubmittedVCResponseDto();
            submittedVCResponseDto.setDate(vc.getUpdatedAt());
            submittedVCResponseDto.setStatus(vc.getStatus());
            submittedVCResponseDto.setTitle(post.getTitle());
            submittedVCResponseDto.setVerifier(vc.getVerifierId());
            submittedVCResponseDto.setVcId(vc.getVcId());
            submittedVCResponseDto.setPostId(vc.getPostId());

            submittedVCResponseDtos.add(submittedVCResponseDto);
        }
        return submittedVCResponseDtos;
    }


    @Autowired
    public List<SubmittedVCResponseDto> getSubmittedVCByVerifier (String verifier, Long postId)
    {
        List<SubmittedVC> submittedVCS = submittedVCDAO.getSubmittedVCByVerifierId(verifier, postId);
        List<SubmittedVCResponseDto> submittedVCResponseDtos = new ArrayList<>();
        for(SubmittedVC vc : submittedVCS){
            Post post = postDAO.selectPost(vc.getPostId());

            SubmittedVCResponseDto submittedVCResponseDto = new SubmittedVCResponseDto();
            submittedVCResponseDto.setDate(vc.getUpdatedAt());
            submittedVCResponseDto.setStatus(vc.getStatus());
            submittedVCResponseDto.setTitle(post.getTitle());
            submittedVCResponseDto.setVerifier(vc.getVerifierId());
            submittedVCResponseDto.setVcId(vc.getVcId());
            submittedVCResponseDto.setPostId(vc.getPostId());

            submittedVCResponseDtos.add(submittedVCResponseDto);
        }

        return submittedVCResponseDtos;
    }
}
