package com.wicc.crud.service;


import com.wicc.crud.Dto.MemberDto;
import com.wicc.crud.Dto.ResponseDto;
import com.wicc.crud.components.FileStorageComponent;
import com.wicc.crud.entity.Member;
import com.wicc.crud.repo.MemberRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService,GenericCrudService <MemberDto,Integer> {


    private final MemberRepo memberRepo;
    private final FileStorageComponent fileStorageComponent;

    public MemberServiceImpl(MemberRepo memberRepo, FileStorageComponent fileStorageComponent){
        this.memberRepo=memberRepo;
        this.fileStorageComponent = fileStorageComponent;
    }

    @Override
    public MemberDto save(MemberDto memberDto) throws IOException {
        Member entity = null;
        ResponseDto responseDto = fileStorageComponent.storeFile(memberDto.getMultipartFile());
        if(responseDto.isStatus()){
            entity = Member.builder()
                    .id(memberDto.getId())
                    .name(memberDto.getName())
                    .email(memberDto.getEmail())
                    .mobileNumber(memberDto.getPhoneNumber())
                    .address(memberDto.getAddress())
                    .filePath(responseDto.getMessage())
                    .build();
            entity = memberRepo.save(entity);
            return MemberDto.builder().id(entity.getId()).build();
        }else{
            log.error(responseDto.getMessage());
            return null;
        }
    }

    @Override
    public List<MemberDto> findAll() {

        List<Member> members =memberRepo.findAll();
        return members.stream().map(
                member -> MemberDto.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .email(member.getEmail())
                        .phoneNumber(member.getMobileNumber())
                        .address(member.getAddress()).build()
        ).collect(Collectors.toList());
    }

    @Override
    public MemberDto findById(Integer integer) throws IOException {
        Member member;
        Optional<Member> optionalMember = memberRepo.findById(integer);
        if(optionalMember.isPresent())
        {
            member = optionalMember.get();
            return  MemberDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .phoneNumber(member.getMobileNumber())
                    .address(member.getAddress())
                    .filePath(member.getFilePath())
                    .build();
        }
        return null;
    }

    @Override
    public MemberDto viewById(Integer integer) throws IOException {
        Member member;
        Optional<Member> optionalMember = memberRepo.findById(integer);
        if(optionalMember.isPresent())
        {
            member = optionalMember.get();
            return  MemberDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .phoneNumber(member.getMobileNumber())
                    .address(member.getAddress())
                    .filePath(fileStorageComponent.returnFileAsBase64(member.getFilePath()))
                    .build();
        }
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
        memberRepo.deleteById(integer);
    }
}
