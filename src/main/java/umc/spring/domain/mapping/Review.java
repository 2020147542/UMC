package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Member;
import umc.spring.domain.Store;
import umc.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public void setMember(Member member) {
        if(this.member != null){
            member.getReviewList().remove(this);
        }
        this.member = member;
        member.getReviewList().add(this);
    }

    public void setStore(Store store) {
        if(this.store != null){
            member.getReviewList().remove(this);
        }
        this.store = store;
        store.getReviewList().add(this);
    }
}
