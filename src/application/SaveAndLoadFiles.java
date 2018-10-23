package application;
import java.io.*;
import java.util.ArrayList;

public class SaveAndLoadFiles {
	
	public static void savePatientInfo(ArrayList<PatientProfile> patientInfo) {
		try {
		      ObjectOutputStream objOut = new ObjectOutputStream(new
		      FileOutputStream("patientInfo.txt"));
		      objOut.writeObject(patientInfo);
		     //debug: System.out.println("Patients Info has been saved");
		      objOut.close();
		   }catch(IOException ioe) {
		System.out.println("Issue saving Patient Information");
		ioe.printStackTrace();
	}
	}
			
		@SuppressWarnings("unchecked")
		public static ArrayList<PatientProfile>  loadPatientInfo() {
			ArrayList<PatientProfile> patientInfo1 = new ArrayList<PatientProfile>();
					try {
						FileInputStream fileIn =new FileInputStream("patientInfo.txt");
			            ObjectInputStream in = new ObjectInputStream(fileIn);
			            try {
							patientInfo1 =(ArrayList<PatientProfile>)in.readObject();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            in.close();
			            fileIn.close();
			   }catch(IOException ioe) {
			System.out.println("Issue saving Patient Information");
			ioe.printStackTrace();
		}
					return patientInfo1;
		
	}
	
		
		public static void saveCustomerServiceProfile(ArrayList<CustomerServiceProfile> customerServiceProfiles) {
			try {
			      ObjectOutputStream objOut = new ObjectOutputStream(new
			      FileOutputStream("customerServiceProfiles.txt"));
			      objOut.writeObject(customerServiceProfiles);			    
			      objOut.close();
			   }catch(IOException ioe) {
			System.out.println("Issue saving customer service profile");
			ioe.printStackTrace();
		}
		}
				
			@SuppressWarnings("unchecked")
			public static ArrayList<CustomerServiceProfile>  loadCustomerServiceProfile() {
				ArrayList<CustomerServiceProfile> CustomerServiceProfile1 = new ArrayList<CustomerServiceProfile>();
						try {
							FileInputStream fileIn =new FileInputStream("CustomerServiceProfile.txt");
				            ObjectInputStream in = new ObjectInputStream(fileIn);
				            try {
				            	CustomerServiceProfile1 =(ArrayList<CustomerServiceProfile>)in.readObject();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				            in.close();
				            fileIn.close();
				   }catch(IOException ioe) {
				System.out.println("Issue saving customer service profile");
				ioe.printStackTrace();
			}
						return CustomerServiceProfile1;
			
		}
	

}
