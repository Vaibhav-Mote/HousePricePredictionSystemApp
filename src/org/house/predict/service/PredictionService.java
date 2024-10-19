package org.house.predict.service;

import java.util.ArrayList;
import java.util.List;

import org.house.predict.repository.PredictionRepository;

public class PredictionService {
	PredictionRepository predRepo=new PredictionRepository();

	public int getminOfX() {
		return predRepo.getminOfX();
	}
	
	public int getminOfY() {
		return predRepo.getminOfY();
	}
	
	
	public List<Integer> getValueOfX(){
		return predRepo.getValueOfX();
	}
	public List<Integer> getValueOfY(){
		return predRepo.getValueOfY();
	}
	
	public int getPredictedPrice(int expectedLandSize) {
	    // Step 1: Get minimum values of X and Y
	    int minOfX = this.getminOfX();
	    int minOfY = this.getminOfY();
	    
	    // Step 2: Get values of X and Y
	    List<Integer> valueOfX = this.getValueOfX();
	    List<Integer> valueOfY = this.getValueOfY();
	    
	    // Step 3: Compute differences (dOfX and dOfY)
	    List<Integer> dOfX = new ArrayList<>();
	    List<Integer> dOfY = new ArrayList<>();
	    
	    for (Integer l : valueOfX) {
	        dOfX.add(l - minOfX);
	    }
	    for (Integer l : valueOfY) {
	        dOfY.add(l - minOfY);
	    }
	    
	    // Step 4: Compute the sum of the product of differences (covariance) and sum of squares of X (variance)
	    int sumProductDXY = 0;
	    int sumSquareDX = 0;
	    
	    for (int i = 0; i < valueOfX.size(); i++) {
	        int productXY = dOfX.get(i) * dOfY.get(i);
	        int squareDX = dOfX.get(i) * dOfX.get(i);
	        sumProductDXY += productXY;
	        sumSquareDX += squareDX;
	    }
	    
	    // Step 5: Calculate the slope (m) and intercept (b)
	    double m = (double) sumProductDXY / sumSquareDX;  // Slope
	    double meanX = valueOfX.stream().mapToInt(Integer::intValue).average().orElse(0.0);
	    double meanY = valueOfY.stream().mapToInt(Integer::intValue).average().orElse(0.0);
	    double b = meanY - m * meanX;  // Intercept
	    
	    // Step 6: Predict the price using the linear regression equation
	    return (int) (m * expectedLandSize + b);
	}

	
}
