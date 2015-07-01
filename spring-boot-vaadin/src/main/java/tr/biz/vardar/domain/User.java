package tr.biz.vardar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String password;
	private String username;
    public User() {
		super();
	}

	public User(String password, String username, String role,
			String firstName, String lastName, String title, boolean male,
			String email, String location, String phone,
			Integer newsletterSubscription, String website, String bio) {
		super();
		this.password = password;
		this.username = username;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.male = male;
		this.email = email;
		this.location = location;
		this.phone = phone;
		this.newsletterSubscription = newsletterSubscription;
		this.website = website;
		this.bio = bio;
	}

	public User(Long id, String password, String username, String role,
			String firstName, String lastName, String title, boolean male,
			String email, String location, String phone,
			Integer newsletterSubscription, String website, String bio) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.male = male;
		this.email = email;
		this.location = location;
		this.phone = phone;
		this.newsletterSubscription = newsletterSubscription;
		this.website = website;
		this.bio = bio;
	}

	private String role;
    private String firstName;
    private String lastName;
    private String title;
    private boolean male;
    private String email;
    private String location;
    private String phone;
    private Integer newsletterSubscription;
    private String website;
    private String bio;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public Integer getNewsletterSubscription() {
        return newsletterSubscription;
    }

    public void setNewsletterSubscription(final Integer newsletterSubscription) {
        this.newsletterSubscription = newsletterSubscription;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(final String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(final String bio) {
        this.bio = bio;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(final boolean male) {
        this.male = male;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    
    public User(String username,String password){
    	this.firstName = username;
    	this.password = password;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
