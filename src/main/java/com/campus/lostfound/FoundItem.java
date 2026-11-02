package com.campus.lostfound.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "items")
public class FoundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter an item name")
    private String title;

    @NotBlank(message = "Please enter a description")
    @Column(length = 1000)
    private String description;

    @NotBlank(message = "Please enter a category")
    private String category;

    @NotBlank(message = "Please enter the location")
    private String location;

    @NotNull(message = "Please select the date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateReported;

    @NotBlank(message = "Please enter your name")
    private String contactName;

    @NotBlank(message = "Please enter your email")
    @Email(message = "Please enter a valid email")
    private String contactEmail;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ItemType type;

    @Enumerated(EnumType.STRING)
    private ItemStatus status = ItemStatus.OPEN;

    public FoundItem() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getDateReported() { return dateReported; }
    public void setDateReported(LocalDate dateReported) { this.dateReported = dateReported; }

    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public ItemType getType() { return type; }
    public void setType(ItemType type) { this.type = type; }

    public ItemStatus getStatus() { return status; }
    public void setStatus(ItemStatus status) { this.status = status; }
}
