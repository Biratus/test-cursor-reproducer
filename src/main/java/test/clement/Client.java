package test.clement;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.vertx.mutiny.sqlclient.Tuple;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
@RegisterForReflection
@EqualsAndHashCode(of = "accountNumber")
@ToString
public class Client implements SQLEntity {

//    @Id
    private String accountNumber;
    private String compName;
    private String brandName;
    private String custTypeCode;
    private String CVATNO;
    private String segmentGroupLPCode;
    private String segmentGroupCustCode;
    private String RNANumber;
    private String activityEndDate;
    private String active;
    private String cDateTime;
    private String siretCode;
    private String usualName;
    private String phone;
    private String validPhone;
    private String secondPhone;
    private String validSecondPhone;
    private String fax;
    private String validFax;
    private String email;
    private String validEmail;
    private String webSite1;
    private String webSite2;
    private String webSite3;
    private String webSite4;
    private String webSite5;
    private String add2;
    private String add3;
    private String add4;
    private String add5;
    private String add6;
    private String add7;
    private String name1;
    private String siren;
//    private Date updateDate;

    @Override
    public String getEntityName() {
        return "Client";
    }

    @Override
    public List<String> getDatabaseFields() {
        return Arrays.asList(
                "accountNumber",
                "compName",
                "brandName",
                "custTypeCode",
                "CVATNO",
                "segmentGroupLPCode",
                "segmentGroupCustCode",
                "RNANumber",
                "activityEndDate",
                "active",
                "cDateTime",
                "siretCode",
                "usualName",
                "phone",
                "validPhone",
                "secondPhone",
                "validSecondPhone",
                "fax",
                "validFax",
                "email",
                "validEmail",
                "webSite1",
                "webSite2",
                "webSite3",
//                "webSite4",
//                "webSite5",
//                "add2",
//                "add3",
//                "add4",
//                "add5",
//                "add6",
//                "add7",
//                "name1",
                "siren");
    }

    @Override
    public Tuple toTuple() {
        //test with wrap ?
        return Tuple.tuple(Arrays.asList(
                accountNumber,
                compName,
                brandName,
                custTypeCode,
                CVATNO,
                segmentGroupLPCode,
                segmentGroupCustCode,
                RNANumber,
                activityEndDate,
                active,
                cDateTime,
                siretCode,
                usualName,
                phone,
                validPhone,
                secondPhone,
                validSecondPhone,
                fax,
                validFax,
                email,
                validEmail,
                webSite1,
                webSite2,
                webSite3,
                webSite4,
                webSite5,
                add2,
                add3,
                add4,
                add5,
                add6,
                add7,
                name1,
                siren));
    }
}
