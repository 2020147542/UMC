package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.api.request.MemberRequest;
import umc.spring.api.response.MemberResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponse.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponse.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequest.JoinDto joinRequest){

        Gender gender = null;
        switch(joinRequest.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(joinRequest.getAddress())
                .specAddress(joinRequest.getSpecAddress())
                .birthYear(joinRequest.getBirthYear())
                .birthMonth(joinRequest.getBirthMonth())
                .birthDay(joinRequest.getBirthDay())
                .name(joinRequest.getName())
                .gender(gender)
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
