package ssafy.musicD.config;

import ssafy.musicD.jwt.AuthEntryPointJwt;
import ssafy.musicD.jwt.JwtRequestFilter;
import ssafy.musicD.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private AuthEntryPointJwt authEntryPointJwt;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable() // 기본설정 사용안함, 비인증시 로그인 폼 화면으로 리다이렉트
				.csrf().disable() // csrf 보안 사용안함
				.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용안함
				.and().authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
				.antMatchers("/api/account/login", "/api/account/signup", "/api/account/checkemail",
						"/api/account/checknickname", "/api/account/refresh")
				.permitAll() // 누구나 접속가능, ex) "/*/siginin",
				// "/*/signup"
				.and().authorizeRequests().anyRequest().hasAnyRole("USER", "ADMIN") // 나머지 요청은 모두 인증된 회원만 접근 가능
//				.antMatchers(HttpMethod.GET, "/exception/**").permitAll() // exception으로 시작하는 GET요청 리소스는 누구나 접근가능
				.and().exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
				.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public JwtRequestFilter authenticationJwtTokenFilter() {
		return new JwtRequestFilter();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/v2/api-docs", "/configuration/ui",
				"/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
