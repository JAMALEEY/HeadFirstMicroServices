package ma.youcode.msinaction.web.controller;

import ma.youcode.msinaction.dao.ProductDao;
import ma.youcode.msinaction.model.Product;
import org.apache.catalina.Store;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//indiquer à Spring que ce contrôleur est un contrôleur REST.
@RestController
// @RestCOntroller = @Controller + @ResponseBody chaque méthode va renvoyer directement la réponse JSON à l'utilisateur,
// donc pas de vue dans le circuit.
public class ProductController {

    ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/Produits")
    // à l'anciennce => @RequestMapping(value = "/Produits", method = RequestMethod.GET
    public List<Product> productsList(){
        return productDao.findAll();
    }

    @PostMapping("/Produits")
    //ResponseEntity est une classe qui hérite de HttpEntity
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        // L'intérêt de ResponseEntity est de nous donner la main pour personnaliser le code facilement.
        Product productAdded = productDao.save(product);
        if (Objects.isNull(productAdded)){
            return ResponseEntity.noContent().build();
            // si le produit ajouté est vide ou n'existe pas, return code 204
        }
        // on a besoin, en plus du code 201, d'ajouter l'URI vers cette nouvelle ressource créée,
        // afin d'être conformes avec le protocole HTTP.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                // ajoutons ensuite l'Id du produit à l'URI
                .buildAndExpand(productAdded.getProductId())
                .toUri();
        // la méthode created de ResponseEntity, elle accepte comme argument l'URI de la ressource nouvellement créée,
        // et renvoie le code de statut 201.
        return ResponseEntity.created(location).build();
    }

    @GetMapping( "/Produits/{productId}")
    public Product byproductId(@PathVariable Long productId){
       return productDao.findById(productId);
    }
}
