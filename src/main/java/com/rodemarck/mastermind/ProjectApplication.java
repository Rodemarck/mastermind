package com.rodemarck.mastermind;

import com.rodemarck.mastermind.model.Repositorio;
import com.rodemarck.mastermind.model.beans.Jogo;
import com.rodemarck.mastermind.model.beans.Tabuleiro;
import com.rodemarck.mastermind.model.user.Usuario;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
                /*try{
                    Repositorio.getInstance().getUsuarios().add(new Usuario("rode", "123"));
                    Repositorio.getInstance().getJogos().add(new Jogo(Repositorio.getInstance().getUsuarios().get(0), LocalDateTime.now(), "nome",1, new int[]{1,2,3,4,5}));
                }catch (IOException e){System.out.println(e);}
		//SpringApplication.run(ProjectApplication.class, args);
        String s = "";
                for(int x=0;x<40;x++)
                    s+="0";
        System.out.println(s);*/
                int[][] matriz = new int[2][2];
                matriz[0][0]=1;
                matriz[0][1]=2;
                matriz[1][0]=3;
                matriz[1][1]=4;

                for(int[]y:matriz)
                    for(int x:y)
                        System.out.println(x);
	}
        

}
