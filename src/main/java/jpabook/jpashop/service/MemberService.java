package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  //JPA의 로직이나 데이터 변경은 가급적 트랜잭션 안에서 다 실행되어야 함
@RequiredArgsConstructor  //final이 있는 필드만 생성자 만들어줌
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    /*
     * 회원 가입
     */
    @Transactional  //데이터를 변경해야 하므로 readOnly = true를 하지 않고 Transactional 설정
    public Long join(Member member) {

        validateDuplicateMember(member);  //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
