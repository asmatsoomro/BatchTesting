package questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    /* Extracting the constant value into a variable */
    private static final String COMMA_SPLIITER = ",";
    public List<BatchRecord> getBatchRecords(InputStream inputStream) {
        List<BatchRecord> batchRecordList = new ArrayList<>();
        /*
        Using try with resource so the resources are deallocated once they are no longer in use.
        In original code, reader.close() could remain open if the exception occurs resulting in potential memory leak.
        This may significantly impact the performance of the your algorithm/program/application.
        One way to resolve this is to introduce a finally block and ensure if the resource is still open then close it.
         */
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA_SPLIITER);

                BatchRecord batchRecord = new BatchRecord();
                batchRecord.setAccountNumber(getField(parts, 0));
                batchRecord.setInvoiceNumber(getField(parts, 1));
                batchRecord.setFirstName(getField(parts, 2));
                batchRecord.setLastName(getField(parts, 3));
                batchRecord.setEmail(getField(parts, 4));
                batchRecord.setHomePhone(getField(parts, 5));
                batchRecord.setAddress1(getField(parts, 6));
                batchRecord.setAddress2(getField(parts, 7));
                batchRecord.setCity(getField(parts, 8));
                batchRecord.setState(getField(parts, 9));
                batchRecord.setPostalCode(getField(parts, 10));

                batchRecordList.add(batchRecord);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return batchRecordList;
    }
/*
getField checks if you got enough tokens in the line after splitting with comma.
If the tokens are missing they are filled with empty String.
This prevents an ArrayOutOfBoundException if the field(s) are missing.
*/
    private String getField(String[] parts, int index) {
        return index < parts.length ? parts[index] : "";
    }
}
