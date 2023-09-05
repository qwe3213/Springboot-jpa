package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class Memberservice {

    // command + shift + t 테스트 파일 만들기
    private final MemberRepository memberRepository;

   /*
   *  회원가입
   * */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 존재하는 회원인지 확인하기 위한 코드
    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByname(member.getUsername());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
         return memberRepository.findAll();
    }

    public Member findOnd(Long memberId){
         return memberRepository.findOne(memberId);
    }
}
