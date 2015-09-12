package code.elix_x.ecore.energy;

public class EnergyType {

	public static final EnergyType RF = new EnergyType(100); 
	public static final EnergyType EU = new EnergyType(1/3*25*100);
	public static final EnergyType MJ = new EnergyType(1000); 
	
	public double quConversionMultiplier;
	
	public EnergyType(double m) {
		quConversionMultiplier = m;
	}
	
	public double convertToQU(double e){
		return e * quConversionMultiplier;
	}
	
	public double convertToE(double qu){
		return qu / quConversionMultiplier;
	}
}
