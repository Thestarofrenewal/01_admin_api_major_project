package in.aman.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlanForm {
	
	private String planCategory;
	
	private String planName;
	
	private LocalDate planStartDate;
	
	private LocalDate planEndDate;
	

}
