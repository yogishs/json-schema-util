package com.awpl.sandbox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.awpl.sandbox.domain.Company;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JpaPersistenceTest
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("runtime.rdbms");
		EntityManager em = emf.createEntityManager();
		String jsonData = new String(Files.readAllBytes(Paths.get("./src/main/resources/data.json")));
		ObjectMapper mapper = new ObjectMapper();
		Object companyData = mapper.readValue(jsonData, Class.forName("com.awpl.sandbox.domain.Company"));
		em.getTransaction().begin();
		em.merge(companyData);
		em.getTransaction().commit();
	}
}