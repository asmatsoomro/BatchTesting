package questions;

/*
Individual imports usually adhere to the clean code principles.
Wildcard imports reduces readability. It becomes difficult to understand what classes from the package are used.
 */
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class BatchTesting {

    public static void main(String[] args) {
        InputStream inputStream = getImportFileInputStream();
        /*
        Externalized the functionality of each functionality to adhere to the single responsbility principle
        i.e. FileReader only reads from the inputstream, DBHandler handles the database transactions
        */
        FileReader fileReader = new FileReader();
        DBHandler dbHandler = new DBHandler();
        List<BatchRecord> batchRecordList = fileReader.getBatchRecords(inputStream);
        dbHandler.insertBatchRecordIntoDB(batchRecordList);

    }



    /*
    Please do not modify this method, this is just to have a dataset to work from.
     */
    private static InputStream getImportFileInputStream() {
        String data = "AccountNumber,InvoiceNumber,FirstName,LastName,Email,HomePhone,Address1,Address2,City,State,PostalCode\n" +
                "123555878900,ABC432855589,Henry,Wayne,Henry.Wayne@test.com,2815554389,123 Main St,,Houston,TX,77370\n" +
                "892555239827,ABC837284736,Mary,Wade,MWade@test.com,8325559872,4329 Cherry St,Apt 403,Austin,TX,77398\n" +
                "937555839482,BCD836728437,Bruce,Jenkins,Bruce@test.com,7135554398,3483 Pine Blvd,,Los Angeles,CA,90345\n" +
                "734555782674,BCD927468293,Chris,Smith,CSmith@test.com,8325553266,8822 North St,Apt 312,Orlando,FL,\n" +
                "912555832367,CDE849382734,John,Doe,John.Doe@test.com,3145559823,39487 Loddington St,,Woodlands,TX,77378\n";

        return new ByteArrayInputStream(data.getBytes());
    }
}
