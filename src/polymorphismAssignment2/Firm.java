package polymorphismAssignment2;

public class Firm {

	public static void main(String[] args) {
		
		Staff worker = new Staff(); 
		worker.payday();
	}
}

class Staff{
    private StaffMember[] staffList;
    
    public Staff(){
    	staffList = new StaffMember[8];
		staffList[0] = new Executive ("Adelaide", "PT34 Anaji", "0244568902", "345", 67.20, 300); 
		staffList[1] = new Executive ("Joana", "PT45 Kumasi", "0567391067", "225", 93.60, 600); 
		staffList[2] = new Employee ("Kwesi", "PT 248 Obuasi", "0243679003", "867", 56.1 ); 
		staffList[3] = new Employee ("Adwoa", "PT 34 New Site", "0567349018", "312", 30.55); 
		staffList[4] = new Volunteer ("Phil", "PT 511 Racecourse", "0275699003") ; 
		staffList[5] = new Hourly ("Aggie", "PT 212 Whindo", "0596321975","439",24.5); 
		staffList[6] = new Commission ("Joyce", "PT 459 Sekondi", "0243702218", "189", 6.25, 0.2 );
		staffList[7] = new Commission("Edward", "PT 411 BA","0508234901", "905", 9.75, 0.15);

		((Commission) staffList[6]).addHours(35);
		((Commission) staffList[6]).addSales(400);
		((Commission) staffList[7]).addHours(40);
		((Commission) staffList[7]).addSales(950);
    }
    
    public void payday() {
    	double amt;
    	
    	for(int i = 0; i<staffList.length; i++) {
    		System.out.println(staffList[i]);
    		amt = staffList[i].pay();
    		
    		System.out.println("Amount paid is " + amt);
    	}
    }
}

abstract class StaffMember{
	protected String name;
	protected String address;
	protected String phone;
	
	public StaffMember(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "StaffMember [name=" + name + ", address=" + address + ", phone=" + phone + "]";
	}
	
	public abstract double pay();
	
}

class Volunteer extends StaffMember{

	public Volunteer(String name, String address, String phone) {
		super(name, address, phone);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double pay() {
		return 0;
	}	
}

class Employee extends StaffMember{
	protected String socialSecurityNumber;
	protected double payRate;
	
	

	public Employee(String name, String address, String phone, String socialSecurityNumber, double payRate) {
		super(name, address, phone);
		this.socialSecurityNumber = socialSecurityNumber;
		this.payRate = payRate;
	}

    @Override
	public String toString() {
		return "Employee [socialSecurityNumber=" + socialSecurityNumber + ", payRate=" + payRate + "]";
	}

	@Override
	public double pay() {
		return payRate;
	}
}

class Executive extends Employee{
	private double bonus;

	public Executive(String name, String address, String phone, String socialSecurityNumber,double payRate,  double bonus) {
		super(name, address, phone, socialSecurityNumber, payRate);
		this.bonus = bonus;
	}
	
	public void awardBonus(double execBonus) {
	    bonus = execBonus;
	}
	    
	@Override
	public double pay() {
		return super.pay() + bonus;
	}
}



class Hourly extends Employee{
    private int hoursWorked;

	public Hourly(String name, String address, String phone, String socialSecurityNumber, double payRate) {
		super(name, address, phone, socialSecurityNumber, payRate);
		this.hoursWorked = hoursWorked;
	}
		
		public void addHours(int moreHours) {
			hoursWorked = hoursWorked + moreHours;
		}
			
		public double pay() {
			return payRate*hoursWorked;
		}

		@Override
		public String toString() {
			return "Hourly [hoursWorked=" + hoursWorked + ", socialSecurityNumber=" + socialSecurityNumber
					+ ", payRate=" + payRate + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
		}
}    

class Commission extends Hourly{
	double totalSales;
	double commissionRate;
	
	Commission(String name, String address,String phone, String socialSecurityNumber, double payRate,  double commissionRate) {
	    super(name, address, phone, socialSecurityNumber, payRate);
	    this.commissionRate = commissionRate;
	}
	
	public void addSales(double totalSales) {
		this.totalSales = totalSales;
	}
		
	public double pay() {
		double amtPaid = super.pay() + (totalSales * commissionRate );
		return amtPaid;
		}
	
	public String toString() {
		return super.toString() + "\ntotal sales is " + totalSales;
	}
	}


