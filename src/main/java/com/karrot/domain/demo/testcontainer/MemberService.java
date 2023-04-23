package com.karrot.domain.demo.testcontainer;

import java.util.List;

public interface MemberService {
    public Member getMemberById(Long id);

    public Member getMemberByName(String name);

    public List<Member> getAllMembers();

    public boolean exists(String name);

    public Member save(Member member);

}
