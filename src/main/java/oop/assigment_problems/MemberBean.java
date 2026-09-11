package oop.assigment_problems;

public class MemberBean {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public MemberBean() {
        this(null,null,false,null);
    }

    public MemberBean(String name) {
        this(null,name,false,null);
    }

    public MemberBean(String membershipId,String name) {
        this(membershipId,name,false,null);
    }

    private MemberBean(String membershipId,String name,boolean premiumMember,String securityAnswer) {
        this.membershipId=membershipId;
        this.name=name;
        this.premiumMember=premiumMember;
        this.securityAnswer=securityAnswer;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if(membershipId==null)
            membershipId=id;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        premiumMember=premium;
    }

    public void setSecurityAnswer(String answer) {
        if(answer!=null)
            securityAnswer=String.valueOf(answer.hashCode());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public static void main(String[] args) {
        MemberBean m1=new MemberBean("Priya Nair");
        System.out.println(m1.getMembershipId());

        MemberBean m2=new MemberBean("LIB-8841","Priya Nair");
        System.out.println(m2.getMembershipId());

        MemberBean m3=new MemberBean();
        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000");
        System.out.println(m3.getMembershipId());
    }
}