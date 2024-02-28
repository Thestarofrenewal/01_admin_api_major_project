package in.aman.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "IES_USERS")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	private String fullName;

	private String email;

	private String pwd;

	private Long mobileNo;

	private String gender;

	private LocalDate dob;

	private Long ssn;
	
	private Integer roleId;

	private String accStatus;

	private String activeSw;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<PlanEntity> plans;

}
