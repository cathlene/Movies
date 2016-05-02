/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restDomain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 *
 * @author cathlene
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieRest { // heel die string is een object movie
     private List<Result> results;

    public List<Result> getResult() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    } 
}
