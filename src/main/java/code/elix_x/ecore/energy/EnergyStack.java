package code.elix_x.ecore.energy;

public class EnergyStack {

	private double qu;
	
	public EnergyStack(double q) {
		qu = q;
	}
	
	public double getQU(){
		return qu;
	}
	
	public void setQU(double q){
		qu = q;
	}
	
	public EnergyStack increase(double q){
		qu += q;
		return this;
	}
	
	public EnergyStack decrease(double q){
		qu -= q;
		return this;
	}
	
	public double get(EnergyType type){
		return type.convertToE(qu);
	}
	
	public void set(EnergyType type, double e){
		setQU(type.convertToQU(e));
	}
	
	public EnergyStack increase(EnergyType type, double e){
		return increase(type.convertToQU(e));
	}
	
	public EnergyStack decrease(EnergyType type, double e){
		return decrease(type.convertToQU(e));
	}
}
