package com.vone.vone.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vone.vone.data.dao.HoldersVCDAO;
import com.vone.vone.data.dao.PostDAO;
import com.vone.vone.data.dao.SubmittedVCDAO;
import com.vone.vone.data.dao.VCDAO;
import com.vone.vone.data.dto.*;
import com.vone.vone.data.entity.*;
import com.vone.vone.data.repository.ContextRepository;
import com.vone.vone.service.KlaytnService;
import com.vone.vone.service.VCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VCServiceImpl implements VCService {
    private final VCDAO vcDAO;
    private final HoldersVCDAO holdersVCDAO;

    private final PostDAO postDAO;

    private final SubmittedVCDAO submittedVCDAO;

    private final KlaytnService klaytnService;

    private final ContextRepository contextRepository;

    @Autowired
    public VCServiceImpl(VCDAO vcDAO, HoldersVCDAO holdersVCDAO, SubmittedVCDAO submittedVCDAO, PostDAO postDAO, KlaytnService klaytnService, ContextRepository contextRepository){
        this.vcDAO = vcDAO;
        this.holdersVCDAO = holdersVCDAO;
        this.submittedVCDAO=submittedVCDAO;
        this.postDAO = postDAO;
        this.klaytnService = klaytnService;
        this.contextRepository = contextRepository;
    }

    @Override
    public String getFileNameById(Long id) throws JSONException {
        VC vc = vcDAO.selectVC(id);
        String index = Integer.toString(vc.getCredentialSubject().size());
        System.out.println(index);
        return vc.getCredentialSubject().get(index);
    }

    @Override
    public VC2ResponseDto issueVC(VC2IssueDto vc2IssueDto) throws Exception{
        VC vc = new VC();
        vc.setContext(vc2IssueDto.getVc().getContext());
        vc.setIssuer(vc2IssueDto.getVc().getIssuer());

        Map<String, String> credentialSubject = new HashMap<>();
        Context context = contextRepository.findByContext(vc2IssueDto.getVc().getContext());

        for (int i = 0; i < context.getCredentialSubject().size(); i++)
        {
            credentialSubject.put(context.getCredentialSubject().get(i), vc2IssueDto.getVc().getCredentialSubject().get(Integer.toString(i)));
        }

        vc.setCredentialSubject(credentialSubject);
        vc.setUpdatedAt(LocalDateTime.now());
        vc.setCreatedAt(LocalDateTime.now());
        VC savedVC = vcDAO.insertVC(vc);

        HoldersVC holdersVC = new HoldersVC();
        holdersVC.setContext(savedVC.getContext());
        holdersVC.setIssuerId(savedVC.getIssuer());
        holdersVC.setVcId(savedVC.getId());
        holdersVC.setHolderId(vc2IssueDto.getHolder());
        holdersVC.setCreatedAt(LocalDateTime.now());
        holdersVC.setUpdatedAt(LocalDateTime.now());
        holdersVCDAO.insertHoldersVC(holdersVC);

        List<String> csList = new ArrayList<>(credentialSubject.values());
        String hash = klaytnService.hash(csList);

        VC2ResponseDto vc2ResponseDto = new VC2ResponseDto();
        vc2ResponseDto.setVcId(vc.getId());
        vc2ResponseDto.setHash(hash);
        return vc2ResponseDto;
    }
    @Override
    public Long listVC(VC2IssueDto vc2IssueDto) {
        VC vc = new VC();
        vc.setContext(vc2IssueDto.getVc().getContext());
        vc.setIssuer(vc2IssueDto.getVc().getIssuer());
        vc.setCredentialSubject(vc2IssueDto.getVc().getCredentialSubject());
        vc.setUpdatedAt(LocalDateTime.now());
        vc.setCreatedAt(LocalDateTime.now());
        VC savedVC = vcDAO.insertVC(vc);

        HoldersVC holdersVC = new HoldersVC();
        holdersVC.setContext(savedVC.getContext());
        holdersVC.setIssuerId(savedVC.getIssuer());
        holdersVC.setVcId(savedVC.getId());
        holdersVC.setHolderId(vc2IssueDto.getHolder());
        holdersVC.setCreatedAt(LocalDateTime.now());
        holdersVC.setUpdatedAt(LocalDateTime.now());
        holdersVCDAO.insertHoldersVC(holdersVC);

        return savedVC.getId();
    }
    @Override
    public List<VC2IssueDto> getVCByContext(String context) throws JSONException {
        List<HoldersVC> vcs = holdersVCDAO.getHoldersVCByContext(context);
        List<VC2IssueDto> vcResponses = new ArrayList<>();
        for(HoldersVC vc : vcs){
            VC2IssueDto vcResponse = new VC2IssueDto();
            vcResponse.setHolder(vc.getHolderId());
            VC tempVc = vcDAO.selectVC(vc.getVcId());
            vcResponse.setVc(VCToVCDto(tempVc));
            vcResponses.add(vcResponse);
        }
        return vcResponses;
    }

    @Override
    public List<String> getVCsContextByIssuerId(String issuerId){
        List<HoldersVC> vcs = holdersVCDAO.getHoldersVCByIssuerId(issuerId);
        List<String> vcResponses = new ArrayList<>();
        for(HoldersVC vc : vcs){
            vcResponses.add(vc.getContext());
        }
        return vcResponses;
    }

    private VCDto VCToVCDto(VC vc){
        VCDto result = new VCDto();
        result.setContext(vc.getContext());
        result.setIssuer(vc.getIssuer());
        result.setCredentialSubject(vc.getCredentialSubject());
        result.setVcId(vc.getId());
        return result;
    }

    @Override
    public List<VCDto> getVCByHolderId(String holderId) throws JSONException {
        List<HoldersVC> vcs = holdersVCDAO.getHoldersVCByHolderId(holderId);
        List<VCDto> vcResponses = new ArrayList<>();
        for(HoldersVC vc : vcs){
            VC tempVc = vcDAO.selectVC(vc.getVcId());
            VCDto vcResponse = VCToVCDto(tempVc);
            vcResponses.add(vcResponse);
        }
        return vcResponses;
    }

    @Override
    public boolean submitVC(VC2VerifyDto vc2VerifyDto) throws JSONException {
        String verifierId = postDAO.selectPost(vc2VerifyDto.getPostId()).getVerifierId();

        for(Long vcid : vc2VerifyDto.getVcIds()){
            VC tempVC = vcDAO.selectVC(vcid);
            SubmittedVC submittedVC = new SubmittedVC();
            submittedVC.setVerifierId(verifierId);
            submittedVC.setPostId(vc2VerifyDto.getPostId());
            submittedVC.getVcIds().add(tempVC.getId());
            submittedVC.setHolderId(vc2VerifyDto.getHolder());
            submittedVC.setIssuerId(tempVC.getIssuer());
            submittedVC.setStatus("pending");
            submittedVC.setCreatedAt(LocalDateTime.now());
            submittedVC.setUpdatedAt(LocalDateTime.now());
            submittedVCDAO.insertSubmittedVC(submittedVC);
        }
        return true;
    }

    @Override
    public List<SubmittedVCResponseDto> getSubmittedVCByHolder (String holderId){
        List<SubmittedVC> submittedVCS = submittedVCDAO.getSubmittedVCByHolderId(holderId);
        List<SubmittedVCResponseDto> submittedVCResponseDtos = new ArrayList<>();
        for(SubmittedVC vc : submittedVCS){
            Post post = postDAO.selectPost(vc.getPostId());
            SubmittedVCResponseDto submittedVCResponseDto = new SubmittedVCResponseDto();
            submittedVCResponseDto.setDate(vc.getUpdatedAt());
            submittedVCResponseDto.setStatus(vc.getStatus());
            submittedVCResponseDto.setTitle(post.getTitle());
            submittedVCResponseDto.setVerifier(vc.getVerifierId());
            submittedVCResponseDto.setPostId(vc.getPostId());
            submittedVCResponseDto.setVcIds(vc.getVcIds());
            submittedVCResponseDtos.add(submittedVCResponseDto);
        }
        return submittedVCResponseDtos;
    }

    @Override
    public List<SubmittedVCResponseDto> getSubmittedVCByVerifier (String verifier, Long postId){
        List<SubmittedVC> submittedVCS = submittedVCDAO.getSubmittedVCByVerifierId(verifier, postId);
        List<SubmittedVCResponseDto> submittedVCResponseDtos = new ArrayList<>();
        for(SubmittedVC vc : submittedVCS){
            Post post = postDAO.selectPost(vc.getPostId());
            SubmittedVCResponseDto submittedVCResponseDto = new SubmittedVCResponseDto();
            submittedVCResponseDto.setDate(vc.getUpdatedAt());
            submittedVCResponseDto.setStatus(vc.getStatus());
            submittedVCResponseDto.setTitle(post.getTitle());
            submittedVCResponseDto.setVerifier(vc.getVerifierId());
            submittedVCResponseDto.setVcIds(vc.getVcIds());
            submittedVCResponseDto.setPostId(vc.getPostId());
            submittedVCResponseDtos.add(submittedVCResponseDto);
        }

        return submittedVCResponseDtos;
    }

    @Override
    public List<VC2IssueDto> getAllVC() throws JSONException {
        List<HoldersVC> vcs = holdersVCDAO.getAllVC();
        List<VC2IssueDto> vcResponses = new ArrayList<>();
        for(HoldersVC vc : vcs){
            VC2IssueDto vcResponse = new VC2IssueDto();
            vcResponse.setHolder(vc.getHolderId());
            VC tempVc = vcDAO.selectVC(vc.getVcId());
            vcResponse.setVc(VCToVCDto(tempVc));
            vcResponses.add(vcResponse);
        }
        return vcResponses;
    }
}
