
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class FindingPrice {
	/**
	 * This FindingPrice.java  takes 2 arrays as input. Simply finds the minimum 
	 * prices in the price list based on prescription
	 * 
	 * @author Busra Cicek
	 *
	 */
	public String[] prescript;
	public String[] medicament;
	
	/**
	 * 
	 * @return total price of medicament
	 * @throws ParseException
	 * 
	 */
	
	public double CompareCalculate(){
	
		int sizePrescript = prescript.length;
		int sizeMedicament = medicament.length;
		double CurrentUnitPriceofMedicament = 0;
		double unitprice = 0;
		int quantity = 0;
		double CurrentPriceofMedicament = 0;
		double total = 0;
		DecimalFormat decFormat = new DecimalFormat("#.00");
	//COMPARE MEDICAMENT NAME-SOCIAL SECURITY INSTITUTION-DATE-PRICE
	for(int i = 3; i<sizePrescript; i+=2){
		ArrayList<Double> prices = new ArrayList<Double>();
		for (int j=0; j<sizeMedicament; j++){
			
			//COMPARE MEDICAMENT NAME
			int comparisonMedic = (prescript[i].compareTo(medicament[j]));
			
			if (comparisonMedic==0){
				//COMPARE SOCIAL SECURITY INSTITUTION
				int comparisonSSI = (prescript[1].compareTo(medicament[j+1]));
				if (comparisonSSI==0){
					try{
						
						//CHANGE STRING TO DATE
						SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
						
						Date PrescriptionDate= format.parse(prescript[2]);
						Date ValidityDate= format.parse(medicament[j+2]);
						Date ExpiryDate= format.parse(medicament[j+3]);
						
						//COMPARE VALIDITY ? PRESCRIPTION ? EXPIRY DATE
						if (PrescriptionDate.after(ValidityDate) &&  PrescriptionDate.before(ExpiryDate)){
							unitprice = Double.parseDouble(medicament[j+4]);
							
							//FIND MINIMUM PRICE OF MEDICAMENT
								if (CurrentUnitPriceofMedicament <= 0){
									CurrentUnitPriceofMedicament = unitprice;
									quantity = Integer.parseInt(prescript[i+1]);
								}
								else if (unitprice < CurrentUnitPriceofMedicament && CurrentUnitPriceofMedicament != 0){
									CurrentUnitPriceofMedicament = unitprice;
									quantity = Integer.parseInt(prescript[i+1]);
								}
								CurrentPriceofMedicament = CurrentUnitPriceofMedicament * quantity;
								prices.add(CurrentPriceofMedicament);
								Collections.sort(prices);
						}
						else
							continue;
						CurrentUnitPriceofMedicament = 0;
					}catch(ParseException excptn){
						excptn.printStackTrace();
					}
				}
				else
					continue;
			}
			else
				continue;
		}
		double PriceofMedicament = prices.get(0);
		total = total + PriceofMedicament;
		System.out.println(prescript[i]+"\t"+decFormat.format(PriceofMedicament/quantity)+"\t"+quantity+"\t"+decFormat.format(PriceofMedicament));
			
	}
	return total;
	}
}

