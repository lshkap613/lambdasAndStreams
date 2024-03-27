import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShulMember implements Comparable<ShulMember> {

	String lName;
	String fName;
	LocalDate birthDate;
	String spouseFName;
	String spouseLName;
	String[] childrenNames; 
	int yearsOfMembership;

	public ShulMember(String lastNameOfMember, String firstNameOfMember, LocalDate birthDateOfMember, String spouseFirstName,
			String spouseLastName, String[] childrenNames, int yearsOfMembership) {
		super();
		this.lName = lastNameOfMember;
		this.fName = firstNameOfMember;
		this.birthDate = birthDateOfMember;
		this.spouseFName = spouseFirstName;
		this.spouseLName = spouseLastName;
		this.childrenNames = childrenNames;
		this.yearsOfMembership = yearsOfMembership;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lastNameOfMember) {
		this.lName = lastNameOfMember;
	}

	public String getFName() {
		return fName;
	}

	public void setFName(String firstNameOfMember) {
		this.fName = firstNameOfMember;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDateOfMember) {
		this.birthDate = birthDateOfMember;
	}

	public String getSpouseFName() {
		return spouseFName;
	}

	public void setSpouseFName(String spouseFirstName) {
		this.spouseFName = spouseFirstName;
	}

	public String getSpouseLName() {
		return spouseLName;
	}

	public void setSpouseLName(String spouseLastName) {
		this.spouseLName = spouseLastName;
	}

	public String[] getChildrenNames() {
		return childrenNames;
	}

	public void setChildrenNames(String[] childrenNames) {
		this.childrenNames = childrenNames;
	}

	public int getYearsOfMembership() {
		return yearsOfMembership;
	}

	public void setYearsOfMembership(int yearsOfMembership) {
		this.yearsOfMembership = yearsOfMembership;
	}
	
	@Override
	public String toString() {
		return lName + ", " + fName + ": ";
	}

	@Override
	public int compareTo(ShulMember o) {
		return this.birthDate.compareTo(o.birthDate);
	}
	

    public static void main(String[] args) {
        List<ShulMember> members = Stream.of(
                new ShulMember("Shkap", "Papa", LocalDate.of(1967, 7, 27), "Mama", "Shkap",
                        new String[]{"Liel", "Ilana", "Eitan", "Liron"}, 12),
                new ShulMember("Garland", "Matthew", LocalDate.of(1977, 12, 6), "Leah", "Garland",
                        new String[]{"Annabelle", "Moshe", "Sophia", "Clara"}, 20),
                new ShulMember("Rabinowitz", "Rabbi", LocalDate.of(1970, 12, 2), "Rebbetzin", "Rabinowitz",
                        new String[]{"Yisroel", "Moshe Leib", "Shragi", "Esti"}, 25),
                new ShulMember("Abramov", "Arkady", LocalDate.of(1949, 2, 11), "Mrs.", "Abramov",
                        new String[]{"Sasha"}, 100),
                new ShulMember("Patt", "Professor", LocalDate.of(1952, 3, 12), "Mrs.", "Patt",
                        new String[]{"Baruch"}, 100)
        ).collect(Collectors.toList());
		
		
		//print families in shul
		int numFamilies = (int) members.stream().count();
		System.out.println("Number of families: " + numFamilies);
		
		
		// sorted order how long each family has been a member of your shul 
		System.out.println("\nYears of Membership:");
		members.stream()
				.sorted(Comparator.comparing(ShulMember::getYearsOfMembership))
				.forEach(member -> System.out.println(member.getLName() + ": " + member.getYearsOfMembership()));

		
		// members, oldest to youngest 
		System.out.println("\nMembers:");
		members.stream()
				.sorted()
				.forEach(member -> System.out.println(member + ", " + member.birthDate));
		
		
		// Sort the names of the spouses of all members 
		System.out.println("\nSpouses:");
		members.stream()
				.sorted(Comparator.comparing(ShulMember::getSpouseFName))
				.forEach(member -> System.out.println(member.spouseFName));
		
		
		// families who have more than 3 children 
		System.out.println("\nFamilies with more than 3 children:");
		members.stream()
				.filter(member -> member.childrenNames.length > 3)
				.forEach(member -> System.out.println(member.lName));
		
		
		// names of all children whose name is larger than the letter “c” (and what family they belong to)
		System.out.println("\nChildren whose names are larger than the letter 'c'");
		members.stream()
				.flatMap(member -> Stream.of(member.childrenNames) // I got the flatMap idea from ChatGPT
				.filter(child -> child.toLowerCase().charAt(0) > 'c')
				.map(child -> child + " " + member.lName))
			.forEach(System.out::println);

	}
		

}
