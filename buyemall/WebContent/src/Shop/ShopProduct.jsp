<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
    <!-- Shop Product Start -->
            <div class="col-lg-9 col-md-12">
                <div class="row pb-3" id="Selettore">
                    <div class="col-12 pb-1">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <form action="javascript:void(0)">
                                <div class="input-group">
                                    <input value="" type="text" name="SearchForm" class="form-control" placeholder="Cerca per nome">
                                    <div class="input-group-append" onclick="callRequest()">
                                        <span class="input-group-text bg-transparent text-primary">
                                            <i class="fa fa-search"></i>
                                        </span>
                                    </div>
                                </div>
                            </form>
                            <div class="dropdown ml-4">
                                <button class="btn border dropdown-toggle" type="button" id="triggerId" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false">
                                            Filtra per
                                        </button>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="triggerId">
                                    <a class="dropdown-item" href="#">Ultimi Aggiunti</a>
                                    <a class="dropdown-item" href="#">Popolarità</a>
                                    <a class="dropdown-item" href="#">Nazionalità</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    
                    <div class="col-lg-4 col-md-6 col-sm-12 pb-1">
                        <div class="card product-item border-0 mb-4">
                            <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0 bordertopproduct">
                                <img class="img-fluid w-100" src="C:\Users\Luigi\Desktop\TSW\dp1-1.png" alt="" style="height: 250px; object-fit: contain;">
                            </div>
                            <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                <h6 class="text-truncate mb-3">Dialga</h6>
                                <div class="d-flex justify-content-center">
                                    <h6>$123.00</h6><h6 class="text-muted ml-2"><del>$123.00</del></h6>
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between bg-light border borderbottomproduct">
                                <a href="" class="btn btn-sm text-dark p-0"><i class="far mr-1"><img src="C:\Users\Luigi\Desktop\TSW\lente.png" width="25" height="25" style="padding: 10%;"></i>Dettagli</a>
                                <a href="" class="btn btn-sm text-dark p-0"><i class="far mr-1"><img src="C:\Users\Luigi\Desktop\TSW\cart.png" width="25" height="25"></i>Acquista</a>
                            </div>
                        </div>
                    </div>
                    
                    
                    <div class="col-12 pb-1" >
                        <nav aria-label="Page navigation">
                          <ul class="pagination justify-content-center mb-3" id="Trivia">
                            
                            <li class="page-item active"><a class="page-link" href="#Selettore">1</a></li>
                            <li class="page-item"><a class="page-link" href="#Selettore">2</a></li>
                            <li class="page-item"><a class="page-link" href="#Selettore">3</a></li>
                            
                          </ul>
                        </nav>
                    </div>
                </div>
            </div>
            
            
            <script src="js/ShopProduct.js">
            	
            </script>