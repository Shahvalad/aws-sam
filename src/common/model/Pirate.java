import java.time.LocalDateTime;

public class Pirate {
    
    private String pirateId;
    private LocalDateTime createdAt;
    private String name; 
    private String age;
    private String crew;

    public Pirate(String pirateId, String age, String crew, String name) {
        this.age = age;
        this.createdAt = LocalDateTime.now();
        this.crew = crew;
        this.name = name;
        this.pirateId = pirateId;
    }

    public String getPirateId() {
        return pirateId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCrew() {
        return crew;
    }

    public void setPirateId(String pirateId) {
        this.pirateId = pirateId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }
}
