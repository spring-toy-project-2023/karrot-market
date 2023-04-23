package com.karrot.domain.demo.testcontainer;

import com.karrot.TestContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class MemberServiceImplTest extends TestContainer {

    @TestConfiguration
    static class MemberServiceTestContextConfiguration {
        @Bean
        public MemberServiceImpl memberService() {
            return new MemberServiceImpl();
        }
    }

    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @Before
    public void setUp() {
        Member john = new Member("john");
        john.setId(11L);

        Member bob = new Member("bob");
        Member alex = new Member("alex");

        List<Member> allMembers = Arrays.asList(john, bob, alex);

        Mockito.when(memberRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(memberRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(memberRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(memberRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(memberRepository.findAll()).thenReturn(allMembers);
        Mockito.when(memberRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    public void whenValidName_thenMemberShouldBeFound() {
        String name = "alex";
        Member found = memberService.getMemberByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }

    @Test
    public void whenInValidName_thenMemberShouldNotBeFound() {
        Member fromDb = memberService.getMemberByName("wrong_name");
        assertThat(fromDb).isNull();

        verifyFindByNameIsCalledOnce("wrong_name");
    }

    @Test
    public void whenValidName_thenMemberShouldExist() {
        boolean doesMemberExist = memberService.exists("john");
        assertThat(doesMemberExist).isEqualTo(true);

        verifyFindByNameIsCalledOnce("john");
    }

    @Test
    public void whenNonExistingName_thenMemberShouldNotExist() {
        boolean doesMemberExist = memberService.exists("some_name");
        assertThat(doesMemberExist).isEqualTo(false);

        verifyFindByNameIsCalledOnce("some_name");
    }

    @Test
    public void whenValidId_thenMemberShouldBeFound() {
        Member fromDb = memberService.getMemberById(11L);
        assertThat(fromDb.getName()).isEqualTo("john");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenMemberShouldNotBeFound() {
        Member fromDb = memberService.getMemberById(-99L);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }

    @Test
    public void given3Members_whengetAll_thenReturn3Records() {
        Member alex = new Member("alex");
        Member john = new Member("john");
        Member bob = new Member("bob");

        List<Member> allMembers = memberService.getAllMembers();
        verifyFindAllMembersIsCalledOnce();
        assertThat(allMembers).hasSize(3).extracting(Member::getName).contains(alex.getName(), john.getName(), bob.getName());
    }

    private void verifyFindByNameIsCalledOnce(String name) {
        Mockito.verify(memberRepository, VerificationModeFactory.times(1)).findByName(name);
        Mockito.reset(memberRepository);
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(memberRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(memberRepository);
    }

    private void verifyFindAllMembersIsCalledOnce() {
        Mockito.verify(memberRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(memberRepository);
    }

}