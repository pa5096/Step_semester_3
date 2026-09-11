package oop.class_problems;

public class HostelRoom {

    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {

        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to room " + roomNo);
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (int i = 0; i < rooms.length; i++) {

            if (rooms[i].occupied < rooms[i].beds) {
                return rooms[i];
            }
        }

        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {

        HostelRoom room = findAvailableRoom(rooms);

        if (room == null) {
            System.out.println("No rooms available for " + studentName);
        } else {
            room.allot(studentName);
        }
    }

    public static void main(String[] args) {

        HostelRoom r1 = new HostelRoom("C-214", 3, 2);
        HostelRoom r2 = new HostelRoom("C-507", 2, 2);

        HostelRoom[] rooms = {r1, r2};

        safeAllot(rooms, "Divya");

        safeAllot(rooms, "Divya");
    }

}