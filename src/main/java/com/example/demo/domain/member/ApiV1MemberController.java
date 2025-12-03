package com.example.demo.domain.member;

import com.example.demo.domain.post.post.ApiV1PostController;
import com.example.demo.domain.post.post.RsData;
import com.example.demo.global.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ApiV1MemberController {

    private final MemberService memberService;
    private final AuthTokenService authTokenService;

    @Getter
    @NoArgsConstructor
    static class LoginReqBody {
        private String username;
        private String password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class LoginResBody {
        private String apiKey;
        private String accessToken;
    }

    @PostMapping("/api/v1/login")
    @ResponseBody
    public RsData login(@RequestBody LoginReqBody loginReqBody) {

        Optional<Member> opMember = memberService.findByUsername(loginReqBody.username);

        if (opMember.isEmpty()) {
            throw new ServiceException("401", "없는 회원입니다.");
        }

        Member member = opMember.get();

        if (!member.getPassword().equals(loginReqBody.password)) {
            throw new ServiceException("401", "비밀번호를 틀렸습니다.");
        }

        String accessToken = authTokenService.genAccessToken(member);
        String apiKey = member.getApiKey();

        LoginResBody resBody = new LoginResBody(apiKey, accessToken);

        RsData<LoginResBody> rsData = new RsData();
        rsData.setResultCode("200");
        rsData.setMessage(loginReqBody.username + "님 로그인 하셨습니다.");
        rsData.setData(resBody);

        return rsData;
    }


    @GetMapping("/api/v1/members")
    @ResponseBody
    public List<Member> list() {
        List<Member> members = memberService.list();
        return members;
    }

    // 회원가입 기능 구현 - worker1


}
