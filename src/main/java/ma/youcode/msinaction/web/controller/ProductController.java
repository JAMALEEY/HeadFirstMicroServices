package ma.youcode.msinaction.web.controller;

import ma.youcode.msinaction.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//indiquer à Spring que ce contrôleur est un contrôleur REST.
@RestController
// @RestCOntroller = @Controller + @ResponseBody chaque méthode va renvoyer directement la réponse JSON à l'utilisateur,
// donc pas de vue dans le circuit.
public class ProductController {

    @GetMapping("/Produits")
    // à l'anciennce => @RequestMapping(value = "/Produits", method = RequestMethod.GET
    public String productsList(){
        return "prodts";
    }

    @GetMapping( "/Produits/{productId}")
    public Product byproductId(@PathVariable Long productId){
        Product product;
        return product = new Product(productId, new String("Aspirateur"), 100L);
    }
}
