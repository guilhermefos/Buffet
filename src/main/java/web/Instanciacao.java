package web;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Buffet;
import dominio.BuffetComponente;
import dominio.Componente;

/**
 * Servlet implementation class Instanciacao
 */
@WebServlet("/instanciacao")
public class Instanciacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			Buffet b1 = new Buffet(null, "Casamento", new BigDecimal("3000"));
			Buffet b2 = new Buffet(null, "Bodas de Ouro", new BigDecimal("1500"));
			
			Componente c1 = new Componente(null, "Salgados", new BigDecimal("500"));
			Componente c2 = new Componente(null, "Mesa de Frios", new BigDecimal("300"));
			Componente c3 = new Componente(null, "Bolo", new BigDecimal("400"));
			
			BuffetComponente bc1 = new BuffetComponente(null, b1, c1);
			BuffetComponente bc2 = new BuffetComponente(null, b1, c2);
									
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema.buffets");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(b1);
			em.persist(b2);
			em.persist(c1);
			em.persist(c2);
			em.persist(c3);
			em.persist(bc1);
			em.persist(bc2);
			em.getTransaction().commit();
			
			Query query = em.createQuery("select id, nome, preco from Componente");
			List<Object> results = query.getResultList();
			List<Componente> componentes = new ArrayList<Componente>();
						
			em.close();
			emf.close();
			
			for(Object list : results)
			{
				componentes.add((Componente) list);
			}
			
			for(Componente c : componentes )
			{
				response.getWriter().append("Componente: "+c.getNome());
			}

		}
		catch(Exception e)
		{
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
