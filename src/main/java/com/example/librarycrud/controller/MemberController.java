package com.example.librarycrud.controller;

import com.example.librarycrud.model.dto.MemberDTO;
import com.example.librarycrud.model.entity.Member;
import com.example.librarycrud.model.request.MemberRequest;
import com.example.librarycrud.service.MemberService;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/api/v1")
public class MemberController {

    @Autowired
    private ModelMapper mapper;
    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.bucket.name}")
    private String bucket;

    private final MemberService memberService;

    @GetMapping("/members")
    public String memberList(@RequestParam(defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "1") int page, Model m) {
        List<MemberDTO> memberDTOList = memberService.searchMember(keyword);
        m.addAttribute("memberDTOList", memberDTOList);

//        m.addAttribute("urlMinio",minioUrl+"/"+bucket);

        Page<Member> list = memberService.getMemberByPaginate(page, 2);
        m.addAttribute("memberDTOList", list);
        m.addAttribute("currentPage", page);
        m.addAttribute("totalPages", list.getTotalPages());
        m.addAttribute("totalItems", list.getTotalElements());

        return "member/members";
    }

    @GetMapping("member/add")
    public String add(Model model) {
//        model.addAttribute("member", new MemberRequest());
        return "member/add-member";
    }

    @PostMapping("member/add")
    public ResponseEntity<?> doAdd(@ModelAttribute @Valid MemberRequest memberRequest, BindingResult bindingResult, ModelMap modelMap) throws BindException, ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.OK).body(memberService.creatMember(memberRequest));
    }

    @GetMapping("member/update")
    public String Update(Model model, @RequestParam Long id) {
        MemberDTO member = memberService.getMemberById(id);
        MemberRequest memberRequest = mapper.map(member, MemberRequest.class);
        model.addAttribute("memberId", id);
        model.addAttribute("member", memberRequest);

        return "member/update-member";
    }

    @PostMapping("member/update")
    public ResponseEntity<?> doUpdate(@RequestParam Long id, @ModelAttribute @Valid MemberRequest memberRequest) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        return ResponseEntity.status(HttpStatus.OK).body(memberService.updateMember(id, memberRequest));
    }

    @DeleteMapping("/delete/members/{id}")
    public ResponseEntity<MemberDTO> deleteBook(@PathVariable Long id) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(memberService.deleteMember(id));

    }

}
