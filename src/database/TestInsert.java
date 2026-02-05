package database;

import model.Owner;
import model.Treatment;

import java.time.LocalDate;
import java.util.List;

public class TestInsert {

    public static void main(String[] args) {

        Owner owner = new Owner(
                0,
                "Aruzhan S.",
                List.of(1, 2),
                "+7 701 123 45 67",
                "aru@example.com"
        );
        Treatment t = new Treatment(
                2, "name", "desc", 10,
                LocalDate.now(), 1) {
            @Override
            public void performTreatment() {
                System.out.println("Performing general treatment");
            }

            @Override
            public String getType() {
                return "General Treatment";
            }
        };
//        OwnerDAO dao = new OwnerDAO();
//        dao.insertOwner(owner);
        TreatmentDAO dao = new TreatmentDAO();
        dao.insertTreatment(t);
    }
}
