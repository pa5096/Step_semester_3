package oop.assigment_problems;

public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;
    private static String systemName;

    static {
        systemName="PageTurner";
    }

    public LoanReceipt(String memberId,String[] bookIds) {
        if(memberId==null||memberId.trim().isEmpty()||bookIds==null)
            throw new IllegalArgumentException("construction rejected");
        for(String id:bookIds) {
            if(id==null||!id.matches("BK-[0-9]{3}"))
                throw new IllegalArgumentException("construction rejected");
        }
        this.memberId=memberId;
        this.bookIds=bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index,String newId) {
        if(index<0||index>=bookIds.length||newId==null||!newId.matches("BK-[0-9]{3}"))
            throw new IllegalArgumentException("invalid book id");
        String[] copy=bookIds.clone();
        copy[index]=newId;
        return new LoanReceipt(memberId,copy);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed=0;
        int skipped=0;
        int reference=0;
        int regular=0;

        if(receipts==null)
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";

        for(LoanReceipt r:receipts) {
            if(r==null) {
                skipped++;
                continue;
            }
            processed++;
            if(r instanceof ReferenceOnlyLoanReceipt)
                reference++;
            else
                regular++;
        }

        return processed+" processed | "+skipped+" null skipped | "+reference+" reference-only | "+regular+" regular";
    }

    public static void main(String[] args) {
        LoanReceipt r=new LoanReceipt("LIB-8841",new String[]{"BK-100","BK-101"});
        String[] ids=r.getBookIds();
        ids[0]="HACKED";
        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected=r.withCorrectedBookId(0,"BK-999");
        System.out.println(corrected.getBookIds()[0]);

        LoanReceipt[] receipts={
            new ReferenceOnlyLoanReceipt("LIB-001",new String[]{"BK-200"},"Reading Room 3"),
            null,
            new LoanReceipt("LIB-002",new String[]{"BK-201"})
        };

        System.out.println(processNightlyCirculation(receipts));
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId,String[] bookIds,String roomNumber) {
        super(memberId,bookIds);
        this.roomNumber=roomNumber;
    }
}