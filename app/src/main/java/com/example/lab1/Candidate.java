package com.example.lab1;

public class Candidate {
    String name,basicEdu,notableWorks,history,earnings;

    public Candidate()
    { }
    public Candidate(String name, String basicEdu, String notableWorks, String history, String earnings) {
        this.name = name;
        this.basicEdu = basicEdu;
        this.notableWorks = notableWorks;
        this.history = history;
        this.earnings = earnings;
    }

    public String getName() {
        return name;
    }

    public String getBasicEdu() {
        return basicEdu;
    }

    public String getNotableWorks() {
        return notableWorks;
    }

    public String getHistory() {
        return history;
    }

    public String getEarnings() {
        return earnings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasicEdu(String basicEdu) {
        this.basicEdu = basicEdu;
    }

    public void setNotableWorks(String notableWorks) {
        this.notableWorks = notableWorks;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }
}
