package shared;

public class AutorDto {
	private Integer id;
	private String name;
    private String country;
	public AutorDto(Integer id, String string, String string2) {
		this.id = id;
		this.name = string;
		this.country = string2;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
