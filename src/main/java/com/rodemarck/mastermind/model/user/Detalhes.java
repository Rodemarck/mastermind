package com.rodemarck.mastermind.model.user;

import com.rodemarck.mastermind.model.Repositorio;
import java.io.IOException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class Detalhes implements UserDetailsService{


    @Override
    public UserDetails loadUserByUsername(String username){
        try{
            System.out.println("carregando");
            Usuario u = Repositorio.getInstance().procurarPorLogin(username);
            if(u == null)
                throw new UsernameNotFoundException("nome de usario não encontrado");
            System.out.println("e....");
            List<GrantedAuthority> grantedAuthority = AuthorityUtils.createAuthorityList("user");
            return new org.springframework.security.core.userdetails.User(u.getLogin(),new BCryptPasswordEncoder().encode(u.getSenha()),grantedAuthority);
        }catch (IOException | UsernameNotFoundException e){
            System.out.println("login erro :"+e.getMessage());
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
