package com.example.todolist;

public class Item {
    private long id;
    private String title;
    private String description;
    private int priority;
    private String userEmail;
    private String user;

    public Item() {
        title =  "Titulo do Item";
        description = "Finalizar a AVA 2 da disciplina de Android";
        priority =  1;
        userEmail = "moisesNaoConsegue@gmail.com";
        user =  "Moises";
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        if (!title.isEmpty()) {
            this.title = title;
        }
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        if (!description.isEmpty()) {
            this.description = description;
        }
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        if (priority >= 0) {
            this.priority = priority;
        }
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        if (!userEmail.isEmpty()) {
            this.userEmail = userEmail;
        }
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        if (!user.isEmpty()) {
        this.user = user;
        }
    }
    public String textoLista() {
        String item;
        item = getTitle();
        item += "\nDescrição: " + getDescription();
        item += "\nPrioridade: " + getPriority();
        item += "\nEmail: " + getUserEmail();
        item += "\nUsuario: " + getUser();
        return item;
    }
}
