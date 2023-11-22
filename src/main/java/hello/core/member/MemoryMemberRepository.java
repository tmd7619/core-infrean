package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    // Map 을 쓸때는 동시성 이슈가 있기 때문에, Concurrent HashMap을 보통 많이 쓴다
    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
