package com.example.librarycrud.service;

import com.example.librarycrud.model.dto.MemberDTO;
import com.example.librarycrud.model.entity.Member;
import com.example.librarycrud.model.request.MemberRequest;
import io.minio.errors.*;
import org.springframework.data.domain.Page;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface MemberService {
    List<MemberDTO> getAllMember();
    MemberDTO creatMember(MemberRequest memberRequest) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    MemberDTO updateMember(Long id,MemberRequest memberRequest) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
//    void deleteMember(long id);
    MemberDTO getMemberById(Long id);
    MemberDTO deleteMember(Long id) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    List<MemberDTO> searchMember(String keyword);

    Page<Member> getMemberByPaginate(int currentPage, int size);
}
