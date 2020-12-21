package ssafy.musicD.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssafy.musicD.Domain.Member;
import ssafy.musicD.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepo userRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member user = userRepo.findByEmail(email);
		List<GrantedAuthority> roles = new ArrayList<>();

		if (user == null)
			throw new UsernameNotFoundException("User Not Found with email: " + email);

		if ((user.getRole()).equals("ROLE_ADMIN")) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		return new User(user.getEmail(), user.getPassword(), roles);
	}
}