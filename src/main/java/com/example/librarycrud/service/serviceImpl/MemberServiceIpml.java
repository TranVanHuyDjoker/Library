package com.example.librarycrud.service.serviceImpl;

import com.example.librarycrud.config.exception.BadRequestException;
import com.example.librarycrud.model.dto.DataMailDTO;
import com.example.librarycrud.model.dto.MemberDTO;
import com.example.librarycrud.model.entity.Member;
import com.example.librarycrud.model.request.MemberRequest;
import com.example.librarycrud.repository.MemberRepository;
import com.example.librarycrud.service.MailService;
import com.example.librarycrud.service.MemberService;
import com.example.librarycrud.service.MinioService;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MemberServiceIpml implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private MinioService minioService;
    @Autowired
    private Executor executor;

    @Override
    public List<MemberDTO> getAllMember() {
        return memberRepository.findAll().stream().map(member -> mapper.map(member, MemberDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MemberDTO creatMember(MemberRequest memberRequest) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        String img_name = memberRequest.getFile().getOriginalFilename();
        String[] parts = img_name.split("\\.(?=[^\\.]+$)");
        memberRequest.setAvatar(uuidAsString + "." + parts[1]);

        Member member = mapper.map(memberRequest, Member.class);
        memberRepository.save(member);

        long size = memberRequest.getFile().getSize();
        minioService.putObject(member.getAvatar(), memberRequest.getFile().getInputStream(), size);

        executor.execute(() -> {
            DataMailDTO dataMail = new DataMailDTO();
            dataMail.setTo(memberRequest.getMail());
            dataMail.setSubject("Đăng kí thành công");

            Map<String, Object> props = new HashMap<>();
            props.put("name", memberRequest.getName());
            props.put("major", memberRequest.getMajor());
            props.put("mail", memberRequest.getMail());
            props.put("pwd", memberRequest.getPassword());
            props.put("date", memberRequest.getBirthday());
            props.put("expired", memberRequest.getExpired());
            dataMail.setProps(props);

            try {
                mailService.sendHtmlMail(dataMail, "mail/a-member");
                log.info("cập nhật thành viên có Id: " + member.getId());
            } catch (MessagingException exp) {
                log.error("gửi mail lỗi!", exp);
            }
        });
        return mapper.map(member, MemberDTO.class);
    }

    @Override
    @Transactional
    public MemberDTO updateMember(Long id, MemberRequest memberRequest) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        String img_name = memberRequest.getFile().getOriginalFilename();
        String[] parts = img_name.split("\\.(?=[^\\.]+$)");
        memberRequest.setAvatar(uuidAsString + "." + parts[1]);

        Member member = memberRepository.findById(id).orElseThrow(() -> new BadRequestException("Member not found"));

        minioService.removeFile(member.getAvatar());

        member.setName(memberRequest.getName());
        member.setMajor(memberRequest.getMajor());
        member.setMail(memberRequest.getMail());
        member.setPassword(memberRequest.getPassword());
        member.setExpired(memberRequest.getExpired());
        member.setBirthday(memberRequest.getBirthday());
        member.setAvatar(memberRequest.getAvatar());

        memberRepository.save(member);

        long size = memberRequest.getFile().getSize();
        minioService.putObject(member.getAvatar(), memberRequest.getFile().getInputStream(), size);

        executor.execute(() -> {
            DataMailDTO dataMail = new DataMailDTO();
            dataMail.setTo(memberRequest.getMail());
            dataMail.setSubject("Cập nhật thông tin thành công");

            Map<String, Object> props = new HashMap<>();
            props.put("name", memberRequest.getName());
            props.put("major", memberRequest.getMajor());
            props.put("mail", memberRequest.getMail());
            props.put("pwd", memberRequest.getPassword());
            props.put("date", memberRequest.getBirthday());
            props.put("expired", memberRequest.getExpired());
            dataMail.setProps(props);

            try {
                mailService.sendHtmlMail(dataMail, "mail/up-member");
                log.info("cập nhật thành viên có Id: " + member.getId());

            } catch (MessagingException exp) {
                log.error("gửi mail lỗi!", exp);
            }
        });

        return mapper.map(member, MemberDTO.class);

    }

    @Override
    public MemberDTO deleteMember(Long id) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Member member = memberRepository.findById(id).orElseThrow(() -> new BadRequestException("no"));
        minioService.removeFile(member.getAvatar());
        memberRepository.deleteById(id);
        return mapper.map(member, MemberDTO.class);
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new BadRequestException("Member not found"));
        log.info("Lấy thông tin memberId: " + id);
        MemberDTO dto = mapper.map(member, MemberDTO.class);
        return dto;

    }

    @Override
    public List<MemberDTO> searchMember(String keyword) {
        return memberRepository.searchMember(keyword).stream().map(member -> mapper.map(member, MemberDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Page<Member> getMemberByPaginate(int currentPage, int size) {
        Pageable p = PageRequest.of(currentPage - 1, size);
        return memberRepository.findAll(p);
    }


}


