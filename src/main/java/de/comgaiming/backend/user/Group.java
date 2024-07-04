package de.comgaiming.backend.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Group {

    @Id
    private Long id;

    private String groupname;
    private String groupsyntax;

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupsyntax() {
        return groupsyntax;
    }

    public void setGroupsyntax(String groupsyntax) {
        this.groupsyntax = groupsyntax;
    }
}
