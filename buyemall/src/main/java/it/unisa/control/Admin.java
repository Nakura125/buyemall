package it.unisa.control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.unisa.DAO.OrdineDAO;
import it.unisa.DAO.ProdottoDAO;
import it.unisa.DAO.SpritesDAO;
import it.unisa.bean.Ordine;
import it.unisa.bean.Prodotto;
import it.unisa.bean.Sprites;
import it.unisa.bean.Stato;
import it.unisa.bean.Tipo;


@WebServlet("/Admin")
public class Admin extends HttpServlet {
    private static final long serialVersionUID = 1L;


    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            try {

                switch (action) {
                    case "addProduct":
                        addProduct(request, response);
                        break;
                    case "modificaProdotto":
                        modificaProdotto(request, response);
                        break;
                    case "deleteProduct":
                        deleteProduct(request, response);
                        break;
                    case "searchProduct":
                        searchProduct(request, response);
                        break;
                    case "visualizzaOrdiniPerStato":
                        visualizzaOrdiniPerStato(request, response);
                        break;
                    case "deleteOrdine":
                    	deleteOrdine(request, response);
                        break;
                    case "confermaOrdiniCompletati":
                    	confermaOrdiniCompletati(request, response);
                        break;
                    case "visualizzaOrdiniFiltrati":
                    	visualizzaOrdiniFiltrati(request, response);
                        break;
                    case "logout":
                        logout(request, response);
                        break;
                    default:
                		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
                		dispatcher.forward(request, response);
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("admin/error.jsp");
            }
        } else {
    		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
    		dispatcher.forward(request, response);
        }
    }

    
  //effettua il logout
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        request.getSession().invalidate();
        
        // Reindirizza alla pagina di login
        response.sendRedirect("login.jsp");
    }
    

    
    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Creazione dell'oggetto Prodotto
            Prodotto product = new Prodotto();
            Tipo tipo = Tipo.valueOf(request.getParameter("tipo"));  
            product.setTipo(tipo);
            product.setQuantita(Integer.parseInt(request.getParameter("quantita")));
            product.setNome(request.getParameter("nome"));
            product.setDescrizione(request.getParameter("descrizione"));
            product.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
            product.setGenerazione(Integer.parseInt(request.getParameter("generazione")));
            product.setNazionalita(request.getParameter("nazionalita"));

            // Impostare altre informazioni sul prodotto quali sprites

            Sprites sprites = new Sprites();
            sprites.setLink(request.getParameter("link"));
            sprites.setLink_small(request.getParameter("link_small"));

            // Salvataggio del prodotto nel database
            ProdottoDAO productDAO = new ProdottoDAO();
            Integer generatedId = productDAO.doSaveGenerator(product);
            product.setIdProdotto(generatedId);
            
            

            

            // Salvataggio dello sprites nel database
            SpritesDAO spritesDAO = new SpritesDAO();
//            sprites.setIdSprites(generatedId);
            sprites.setProdotto(product);
            //sprites.getProdotto().setIdProdotto(generatedId);
            spritesDAO.doSave(sprites);

            request.setAttribute("AddSuccess", true);

            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            // Gestione dell'errore di parsing dei numeri
            e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        } catch (IllegalArgumentException e) {
            // Gestione dell'errore di valore non valido per l'enum Tipo
            e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        } catch (SQLException e) {
            // Gestione dell'errore del database
            e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        }
    }


    private void modificaProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
            String nuovoNome = request.getParameter("nuovoNome");
            int nuovaQuantita = Integer.parseInt(request.getParameter("nuovaQuantita"));
            String nuovaDescrizione = request.getParameter("nuovaDescrizione");
            float nuovoPrezzo = Float.parseFloat(request.getParameter("nuovoPrezzo"));

            // Effettua la modifica nel database
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            Prodotto prodotto = prodottoDAO.doRetrieveByKey(idProdotto);

            if (prodotto != null) {
                // Modifica i valori del prodotto
                prodotto.setNome(nuovoNome);
                prodotto.setQuantita(nuovaQuantita);
                prodotto.setDescrizione(nuovaDescrizione);
                prodotto.setPrezzo(nuovoPrezzo);

                // Esegui l'aggiornamento nel database
                prodottoDAO.UpdateNome(nuovoNome,prodotto);
                prodottoDAO.UpdateQuantita(nuovaQuantita,prodotto);
                prodottoDAO.UpdateDescrizione(nuovaDescrizione,prodotto);
                prodottoDAO.UpdatePrezzo(nuovoPrezzo, prodotto);

                request.setAttribute("modificaSuccess", true);
                
                // Reindirizza alla pagina di successo
//                response.sendRedirect("admin/DashboardAdmin.jsp?modificaSuccess=true");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
                dispatcher.forward(request, response);
            } else {
            	request.setAttribute("prodottoNonTrovato", true);
            	
                // Il prodotto non esiste
//                response.sendRedirect("admin/DashboardAdmin.jsp?prodottoNonTrovato=true");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp?prodottoNonTrovato=true");
                dispatcher.forward(request, response);
            
            }
            
        } catch (NumberFormatException e) {
//        	e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        } catch (SQLException e) {
//        	e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        } catch (Exception e) {
//        	e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        }
    }




    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer productId = Integer.parseInt(request.getParameter("idprodotti"));
            // Rimozione del prodotto dal database
            ProdottoDAO productDAO = new ProdottoDAO();
            boolean success = productDAO.doDelete(productId);

            if (success) {
            	request.setAttribute("deleteSuccess", true);
        		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
        		dispatcher.forward(request, response);
            } else {
            	request.setAttribute("prodottoNonTrovato", true);
        		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
        		dispatcher.forward(request, response);
            }

        } catch (NumberFormatException e) {
        	e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        } catch (SQLException e) {
        	e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        } catch (Exception e) {
        	e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        }
    }

    



    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer productId = Integer.parseInt(request.getParameter("idprodotti"));

            // Ricerca del prodotto nel database
            ProdottoDAO productDAO = new ProdottoDAO();
            Prodotto product = productDAO.doRetrieveByKey(productId);

            if (product != null) {
                // Prodotto trovato
                request.setAttribute("product", product);
//                request.getRequestDispatcher("admin/productDetails.jsp").forward(request, response);
        		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/admin/productDetails.jsp");
        		dispatcher.forward(request, response);
            } else {
                // Prodotto non trovato
//                response.sendRedirect("admin/DashboardAdmin.jsp?ProductnotFound=true");
        		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp?ProductnotFound=true");
        		dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("admin/error.jsp");
        } catch (SQLException e) {
            response.sendRedirect("admin/error.jsp");
        } catch (Exception e) {
            response.sendRedirect("admin/error.jsp");
        }
    }
    
//visualizza tutti i prodotti
//    private void visualizzaAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            // Recupera tutti i prodotti dal database
//            ProdottoDAO productDAO = new ProdottoDAO();
//            List<Prodotto> productList = (List<Prodotto>) productDAO.doRetrieveAll("idProdotti");
//
//            // Imposta l'attributo nella richiesta per visualizzare i prodotti nella pagina JSP
//            request.setAttribute("productList", productList);
//
//            request.getRequestDispatcher("DashboardAdmin.jsp").forward(request, response); 
//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.sendRedirect("admin/error.jsp");
//        }
//    }


    //Elimina ordine 
    
    private void deleteOrdine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer IdOrdine = Integer.parseInt(request.getParameter("idOrdine"));
            // Rimozione dell' ordine dal database
            OrdineDAO OrdineDAO = new OrdineDAO();
            boolean success = OrdineDAO.doDelete(IdOrdine);

            if (success) {
            	request.setAttribute("deleteOrderSuccess", true);
                RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
        		dispatcher.forward(request, response);
            } else {
//                response.sendRedirect("admin/DashboardAdmin.jsp?OrderError=true");
            	request.setAttribute("ordineNonTrovato", true);
                RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
        		dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("admin/error.jsp");
        } catch (SQLException e) {
            response.sendRedirect("admin/error.jsp");
        } catch (Exception e) {
            response.sendRedirect("admin/error.jsp");
        }
    }

    
    

    
    
//ordina gli ordini in funzione dello stato in cui si trovano
    
    private void visualizzaOrdiniPerStato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String statoParam = request.getParameter("stato");
        Stato stato = Stato.valueOf(statoParam);

        OrdineDAO ordineDAO = new OrdineDAO();
        List<Ordine> ordini = ordineDAO.visualizzaOrdiniPerStato(stato, null);

        request.setAttribute("ordini", ordini);
        RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/admin/visualizzaOrdini.jsp");
		dispatcher.forward(request, response);
    }

    private void confermaOrdiniCompletati(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer idOrdine = Integer.parseInt(request.getParameter("idOrdine"));
            // Verifica e conferma l'ordine come completato
            OrdineDAO ordineDAO = new OrdineDAO();
            Ordine ordine = ordineDAO.doRetrieveByKey(idOrdine);

            if (ordine != null && ordine.getStato() == Stato.attesa) {
                // Modifica lo stato dell'ordine a COMPLETATO
                ordine.setStato(Stato.completato);
                
                // Effettua l'aggiornamento nel database
                ordineDAO.UpdateStato(Stato.completato, ordine);

                // Reindirizza alla pagina di successo
                request.setAttribute("confermaSuccess", true);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
                dispatcher.forward(request, response);
            } else {
                // L'ordine non si trova nello stato di attesa o non esiste
//                response.sendRedirect("admin/DashboardAdmin.jsp?confermaError=true");
            	request.setAttribute("confermaErrors", true);
            	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
        	e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        } catch (SQLException e) {
        	e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        } catch (Exception e) {
        	e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        }
    }

    public void visualizzaOrdiniFiltrati (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	String data1String = request.getParameter("data1");
    	String data2String = request.getParameter("data2");
    	
        Date data1=null;
        Date data2=null;
        try {
        	data1 = Date.valueOf(data1String);
        	data2 = Date.valueOf(data2String);
        }catch(IllegalArgumentException e) {
        	e.printStackTrace();
            response.sendRedirect("admin/error.jsp");
        }
        
        request.setAttribute("dateOrdine", new OrdineDAO().doRetrieveByData(data1, data2));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/DashboardAdmin.jsp");
        dispatcher.forward(request, response);
        
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
