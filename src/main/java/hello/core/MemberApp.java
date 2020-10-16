package hello.core;

import hello.core.member.*;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService(); // appConfig.memberService()가 MemberServiceImpl을 준다.
//        MemberService memberService = new MemberServiceImpl();

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new memberA = " + memberA.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
