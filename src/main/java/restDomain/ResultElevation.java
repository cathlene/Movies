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
public class ResultElevation {
     private double elevation;

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }
}
