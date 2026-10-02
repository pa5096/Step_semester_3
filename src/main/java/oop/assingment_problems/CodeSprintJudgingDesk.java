package oop.assingment_problems;

import java.util.ArrayList;
import java.util.List;

enum HackathonState {
    OPEN,
    JUDGING,
    PUBLISHED
}

interface ScoringRule {
    double calculateScore(double idea, double execution, double presentation);
}

class InnovationTrackScoring implements ScoringRule {
    @Override
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea * 0.50) + (execution * 0.30) + (presentation * 0.20);
    }
}

class OpenTrackScoring implements ScoringRule {
    @Override
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Project {
    private String title;

    public Project(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class Team {
    private String name;
    private List<Student> members;
    private ScoringRule scoringRule;
    private Project project;
    private Double finalScore;

    public Team(String name, List<Student> members, ScoringRule scoringRule) {
        this.name = name;
        this.members = members;
        this.scoringRule = scoringRule;
        this.finalScore = null;
    }

    public String getName() {
        return name;
    }

    public List<Student> getMembers() {
        return members;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setScore(double idea, double execution, double presentation) {
        this.finalScore = scoringRule.calculateScore(idea, execution, presentation);
    }
}

class Hackathon {
    private List<Team> registeredTeams = new ArrayList<>();
    private List<Student> registeredStudents = new ArrayList<>();
    private HackathonState state = HackathonState.OPEN;

    public void registerTeam(String teamName, List<Student> members, String trackName, ScoringRule rule) {
        if (members.size() < 2 || members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }

        for (Student student : members) {
            if (registeredStudents.contains(student)) {
                System.out.println("Registration failed: Student " + student.getName() + " is already in a team.");
                return;
            }
        }

        Team team = new Team(teamName, members, rule);
        registeredTeams.add(team);
        registeredStudents.addAll(members);
        System.out.println("Team " + teamName + " registered (" + members.size() + " members, " + trackName + " track).");
    }

    public void submitProject(Team team, Project project) {
        if (state == HackathonState.PUBLISHED) {
            System.out.println("Submission failed: Results already published.");
            return;
        }
        team.setProject(project);
        System.out.println("Project '" + project.getTitle() + "' submitted by " + team.getName() + ".");
    }

    public void scoreProject(Project project, double idea, double execution, double presentation) {
        if (state == HackathonState.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }

        for (Team team : registeredTeams) {
            if (team.getProject() != null && team.getProject().getTitle().equals(project.getTitle())) {
                team.setScore(idea, execution, presentation);
                System.out.println("Score recorded for '" + project.getTitle() + "'. Final score: " + String.format("%.2f", team.getFinalScore()) + ".");
                return;
            }
        }
        System.out.println("Project not found.");
    }

    public void publishResults() {
        this.state = HackathonState.PUBLISHED;
        System.out.println("Results published.");
    }
}

public class CodeSprintJudgingDesk {
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon();

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        List<Student> byteBustersMembers = List.of(asha, ravi, neha);
        hackathon.registerTeam("ByteBusters", byteBustersMembers, "Innovation", new InnovationTrackScoring());

        Student kiran = new Student("Kiran");
        List<Student> soloMembers = List.of(kiran);
        hackathon.registerTeam("SoloCoder", soloMembers, "Open", new OpenTrackScoring());

        Project smartAttend = new Project("SmartAttend");
        Team byteBusters = new Team("ByteBusters", byteBustersMembers, new InnovationTrackScoring());
        hackathon.submitProject(byteBusters, smartAttend);

        hackathon.scoreProject(smartAttend, 8, 7, 9);

        hackathon.publishResults();

        hackathon.scoreProject(smartAttend, 10, 7, 9);
    }
}