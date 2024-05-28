package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    // get 요청에 대한 비즈니스 로직 처리
    @Override
    public Optional<Member> findMember(Long id){
        Optional<Member> member = memberRepository.findById(id);
        return member;
    }
}
