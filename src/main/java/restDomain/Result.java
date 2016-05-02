/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restDomain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author cathlene
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
  private double imdbRating;

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }   
}
