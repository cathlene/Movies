/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restUi;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.springframework.web.client.RestTemplate;
import restDomain.MovieRest;
import restDomain.Result;

/**
 *
 * @author cathlene
 */
public class TestRest {
       public static void main(String[] args){
           Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("t", "frozen");
          RestTemplate restTemplate = new RestTemplate();
        
        Result response = restTemplate.getForObject("http://www.omdbapi.com/?t={t}&y=&plot=short&r=json", Result.class,parameters);
           JOptionPane.showMessageDialog(null,"ghj");
        System.out.println(response.getImdbRating());
        
    }
    

}
