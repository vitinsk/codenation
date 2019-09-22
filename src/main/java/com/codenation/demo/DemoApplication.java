package com.codenation.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codenation.demo.integracao.CodenationRestService;
import com.codenation.demo.model.Response;
import com.codenation.demo.model.Result;
import com.codenation.demo.utils.CriptografiaUtils;
import com.codenation.demo.utils.FileWriter;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	@Autowired CodenationRestService integracao;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);  
         
    }
    
    
	@Override
	public void run(String... args) throws Exception {

		// Obtem o teste para o tipo object 
        Response res =  integracao.getTest();
        
        // Cria o arquivo answer.json
        FileWriter.writerFile(FileWriter.objectToJson(res));
        
        // Decifra os dados e faz o resumo
        res.setDecifrado(CriptografiaUtils.decriptCesar(res.getNumeroCasas(), res.getCifrado()));        
        res.setResumoCriptografico(CriptografiaUtils.toSHA1(res.getDecifrado()));
        
        // Atualiza o arquivo answer.json
        FileWriter.writerFile(FileWriter.objectToJson(res));
        
        // Submete o resultado para a api da Codenation
        Result result = integracao.sendTest(FileWriter.getFile());
        
        if (result != null) {
			System.out.println("Succesfull: Your note was: " + result.getScore());
		}else {
			System.out.println("You failed, check your code and see what's wrong.");
		}   
		
	}



}
