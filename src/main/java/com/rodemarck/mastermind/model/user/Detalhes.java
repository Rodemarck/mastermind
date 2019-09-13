package com.rodemarck.mastermind.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Detalhes implements UserDetailsService{


    @Override
    public UserDetails loadUserByUsername(String username){
        try{
            System.out.println("carregando");
            /*Professor u = ProfessorDAO.getPeloNome(username);
            System.out.println("e....");*/
            List<GrantedAuthority> grantedAuthority = AuthorityUtils.createAuthorityList("");
            //return new org.springframework.security.core.userdetails.User(u.getLogin(),u.getSenha(),grantedAuthority);
        }catch (Exception e){
            System.out.println("login erro :"+e.getMessage());
            throw new UsernameNotFoundException(e.getMessage());
        }
        return null;
    }
}
