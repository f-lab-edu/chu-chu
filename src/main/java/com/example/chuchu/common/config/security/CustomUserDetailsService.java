package com.example.chuchu.common.config.security;

import com.example.chuchu.common.errors.exception.UserNotFoundException;
import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Cacheable(value = "user", key = "#email", unless = "#result == null")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("email in loadUserByUsername = {}", email);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Can't find User"));
        return new CustomUserDetails(member.getEmail(), member.getPassword(), member.getNickName(), List.of(member.getUserRole().getValue()));
    }
}
