package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("회원 저장소 테스트")
class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("싱글톤으로 조회 테스트")
    void getInstance() {

        //given
        Member member1 = new Member("hello", 20);
        Member member2 = new Member("hello", 20);

        //when
        MemberRepository instance1 = MemberRepository.getInstance();
        MemberRepository instance2 = MemberRepository.getInstance();

        //then
        assertThat(member1).isNotSameAs(member2);
        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("회원 저장 테스트")
    void save() {

        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(savedMember).isEqualTo(member);
    }

    @Test
    @DisplayName("회원 아이디로 회원 조회 테스트")
    void findById() {

        //given
        Member member = new Member("hello", 20);
        Member savedMember = memberRepository.save(member);
        Long memberId = savedMember.getId();

        //when
        Member findMember = memberRepository.findById(memberId);

        //then
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @DisplayName("회원 전체 조회 테스트")
    void findAll() {

        //given
        Member member1 = new Member("hello1", 20);
        memberRepository.save(member1);
        Member member2 = new Member("hello2", 20);
        memberRepository.save(member2);

        //when
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("회원 저장소 전체 삭제 테스트")
    void clearStore() {

        //given
        Member member1 = new Member("hello1", 20);
        memberRepository.save(member1);
        Member member2 = new Member("hello2", 20);
        memberRepository.save(member2);

        //when
        memberRepository.clearStore();
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members.size()).isEqualTo(0);
    }
}