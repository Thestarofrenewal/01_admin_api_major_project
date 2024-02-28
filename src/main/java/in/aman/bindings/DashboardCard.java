package in.aman.bindings;

import lombok.Data;

@Data
public class DashboardCard {

	private Long plansCnt;

	private Long approvedCnt;

	private Long deniedCnt;

	private Double benifitAmtGiven;
	
	private UserAccForm user;
}
 