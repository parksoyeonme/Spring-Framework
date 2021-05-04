package com.kh.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kh.spring.member.model.service.MemberService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MemberService memberService;
	
	/**
	 * 클래스레벨에 작성한 @Configuration 하위에서 작동. 
	 * 리턴 객체를 빈으로 등록하는 사용.
	 * id : passwordEncoder 메소드 이름
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * WebSecurity객체를 통해 인증대상에서 제외될 자원을 설정
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	/**
	 * HttpSecurity객체를 통해 인증/권한, 로그인/로그아웃등을 설정
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/member/**").authenticated()
			.antMatchers("/board/**").authenticated()
			.antMatchers("/admin/**").authenticated()
			.antMatchers("/").permitAll();
		//인증된 사용자만
		//모두허용
		
		http.formLogin()
			.loginPage("/member/memberLogin.do")	// GET /login
			.loginProcessingUrl("/member/memberLogin.do") // POST /login
			.usernameParameter("id")
			.passwordParameter("password")
			.defaultSuccessUrl("/")
			.permitAll();
		
		http.logout()
			.logoutUrl("/member/memberLogout.do") // POST
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true);
	}

	/**
	 * AuthenticationManagerBuilder 객체를 통해
	 * UserDetailsService구현체, 비밀번호 암호화 빈
	 * 
	 * */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService)
		.passwordEncoder(passwordEncoder());
		
	}

	
	
	
}