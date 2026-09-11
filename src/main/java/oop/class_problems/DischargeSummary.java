package oop.class_problems;
public class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;
    static {
        System.out.println("");
    }
    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (patientId == null || patientId.trim().isEmpty() || medicationCodes == null) {
            throw new IllegalArgumentException("Invalid data");
        }
        for (String code : medicationCodes) {
            if (code == null || !code.matches("MED-[A-Z]")) {
                throw new IllegalArgumentException("Invalid medication code");
            }
        }
        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }
    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }
    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (newCode == null || !newCode.matches("MED-[A-Z]")) {
            throw new IllegalArgumentException("Invalid medication code");
        }
        String[] newCodes = medicationCodes.clone();
        newCodes[index] = newCode;
        return new DischargeSummary(patientId, newCodes);
    }
    public static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;
        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (summary instanceof CriticalCareDischargeSummary) {
                critical++;
            } else {
                routine++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + critical + " critical-care | " + routine + " routine";
    }
    public static void main(String[] args) {
        try {
            new DischargeSummary("MT2026-0142", new String[]{"MED-A", "bad"});
            System.out.println("construction succeeded");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "MED-B"});
        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println(d.getMedicationCodes()[0]);
        DischargeSummary[] summaries = {
            new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary("MT002", new String[]{"MED-Y"})
        };
        System.out.println(processNightlyBatch(summaries));
    }
}
class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;
    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}