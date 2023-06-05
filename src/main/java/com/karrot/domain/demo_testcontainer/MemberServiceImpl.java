package com.karrot.domain.demo_testcontainer;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member getMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public boolean exists(String name) {
        if(memberRepository.findByName(name) != null){
            return true;
        }
        return false;
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
