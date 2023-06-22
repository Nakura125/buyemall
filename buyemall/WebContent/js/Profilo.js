/**
 * 
 */

 $(document).ready(function () {
        // Nasconde tutti i contenuti all'avvio della pagina
        $(".container-fluid.pt-5").hide();
		$("#Profilo").show();
        // Gestisce il clic sul link Profilo
        $('[href="#linkProfilo"]').click(function () {
            $(".container-fluid.pt-5").hide();
            $("#Profilo").show();
        });

        // Gestisce il clic sul link Ordini
        $('[href="#linkOrdini"]').click(function () {
            $(".container-fluid.pt-5").hide();
            $("#Ordini").show();
        });

        // Gestisce il clic sul link Metodi di Pagamento
        $('[href="#linkMetodoPagamento"]').click(function () {
            $(".container-fluid.pt-5").hide();
            $("#MetodoPagamento").show();
        });
    });