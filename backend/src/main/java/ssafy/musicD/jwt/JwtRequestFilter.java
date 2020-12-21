package ssafy.musicD.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import ssafy.musicD.service.UserDetailsServiceImpl;

public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

	public Authentication getAuthentication(String token) {
		Map<String, Object> parseInfo = jwtUtils.getUserParseInfo(token);
		System.out.println("parseinfo: " + parseInfo);
		List<String> rs = (List) parseInfo.get("role");
		Collection<GrantedAuthority> tmp = new ArrayList<>();
		for (String a : rs) {
			tmp.add(new SimpleGrantedAuthority(a));
		}
		UserDetails userDetails = User.builder().username(String.valueOf(parseInfo.get("username"))).authorities(tmp)
				.password("mdmdmd").build();
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		return usernamePasswordAuthenticationToken;
	}

	@Bean
	public FilterRegistrationBean JwtRequestFilterRegistration(JwtRequestFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("REQUEST : " + request.getHeader("Authorization"));
		String requestTokenHeader = request.getHeader("Authorization");
		logger.info("tokenHeader: " + requestTokenHeader);
		String email = null;
		String jwt = parseJwt(request);
		if (jwt != null) {
			logger.info("token in requestfilter: " + jwt);

			try {
				email = jwtUtils.getEmailFromToken(jwt);
			} catch (IllegalArgumentException e) {
				logger.warn("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		if (email == null) {
			logger.info("token maybe expired: email is null.");
		} else if (redisTemplate.opsForValue().get(jwt) != null) {
			logger.warn("this token already logout!");
		} else {
			// DB access 대신에 파싱한 정보로 유저 만들기!
			Authentication authen = getAuthentication(jwt);
			// 만든 authentication 객체로 매번 인증받기
			SecurityContextHolder.getContext().setAuthentication(authen);
			response.setHeader("email", email);
		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7);
		}

		return null;
	}
}