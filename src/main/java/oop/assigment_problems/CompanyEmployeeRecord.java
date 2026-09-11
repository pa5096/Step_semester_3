package oop.assigment_problems;
public class CompanyEmployeeRecord{
static class Employee{
private int empId;
private String name;
private double salary;
Employee(int empId,String name,double salary){
this.empId=empId;
this.name=name;
this.salary=salary;
}
double getSalary(){
return salary;
}
}
static class ManagerEmployee extends Employee{
private double bonus;
ManagerEmployee(int empId,String name,double salary,double bonus){
super(empId,name,salary);
this.bonus=bonus;
}
double effectiveSalary(){
return getSalary()+bonus;
}
}
static class ParkingSlot{
String slotNo;
boolean occupied;
ParkingSlot(String slotNo){
this.slotNo=slotNo;
occupied=false;
}
boolean park(){
if(!occupied){
occupied=true;
return true;
}
return false;
}
}
String name;
String empId;
Employee employee;
ParkingSlot slot;
static int totalRecords=0;
CompanyEmployeeRecord(String name,String empId,Employee employee){
this.name=name;
this.empId=empId;
this.employee=employee;
slot=null;
totalRecords++;
}
String fullProfile(){
double pay;
if(employee instanceof ManagerEmployee){
ManagerEmployee manager=(ManagerEmployee)employee;
pay=manager.effectiveSalary();
}else{
pay=employee.getSalary();
}
if(slot==null){
return name+" | Pay: Rs "+pay+" | Slot: no parking assigned";
}else{
return name+" | Pay: Rs "+pay+" | Slot: "+slot.slotNo;
}
}
static void safePark(CompanyEmployeeRecord record,ParkingSlot[] slots){
for(int i=0;i<slots.length;i++){
if(slots[i].park()){
record.slot=slots[i];
return;
}
}
}
public static void main(String[] args){
ManagerEmployee e1=new ManagerEmployee(101,"Divya",70000,8000);
Employee e2=new Employee(102,"Karan",40000);
Employee e3=new Employee(103,"Meera",10000);
CompanyEmployeeRecord r1=new CompanyEmployeeRecord("Divya","101",e1);
CompanyEmployeeRecord r2=new CompanyEmployeeRecord("Karan","102",e2);
CompanyEmployeeRecord r3=new CompanyEmployeeRecord("Meera","103",e3);
ParkingSlot p1=new ParkingSlot("A1");
ParkingSlot p2=new ParkingSlot("A2");
ParkingSlot[] slots={p1,p2};
safePark(r1,slots);
safePark(r2,slots);
System.out.println(r1.fullProfile());
System.out.println(r2.fullProfile());
System.out.println(r3.fullProfile());
System.out.println("Total records: "+CompanyEmployeeRecord.totalRecords);
}
}