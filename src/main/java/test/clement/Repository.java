package test.clement;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.sqlclient.*;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Slf4j
public class Repository extends BasicRepository<Client> {

    @Inject
    MySQLPool client;

    // Neither of these methods work, both throw the NPE

    public Multi<List<Client>> findAllMultiList() {
        return client.getConnection() // Récupération de la connection
                .onItem().produceUni(connection -> connection.prepare("SELECT "+String.join(",",new Client().getDatabaseFields())+" From Client")) // Prepare query
                .onItem().apply(PreparedStatement::cursor) // get cursor from query/
                .onItem().produceMulti(cursor -> splitResult(cursor,20))
                .onFailure().invoke(failure -> log.error("ERROR multi",failure))
                .onItem().apply(this::transformRowSet)
                .onFailure().invoke(failure ->  log.error("ERROR transform",failure));
    }
    public Multi<Client> findAllMulti() {
        return client.getConnection() // Récupération de la connection
                .onItem().produceUni(connection -> connection.prepare("SELECT * From Client")) // Prepare query
                .onItem().produceMulti(prepared ->  prepared.createStream(20, Tuple.tuple()).toMulti())
                .onFailure().invoke(failure -> log.error("ERROR multi",failure))
                .onItem().apply(this::fromRow)
                .onFailure().invoke(failure -> log.error("ERROR transform",failure));
    }

    @Override
    protected Client fromRow(Row row) {
        Client client = new Client();
        client.setAccountNumber(row.getString("accountNumber"));
        client.setCompName(row.getString("compName"));
        client.setBrandName(row.getString("brandName"));
        client.setCustTypeCode(row.getString("custTypeCode"));
        client.setCVATNO(row.getString("CVATNO"));
        client.setSegmentGroupLPCode(row.getString("segmentGroupLPCode"));
        client.setSegmentGroupCustCode(row.getString("segmentGroupCustCode"));
        client.setRNANumber(row.getString("RNANumber"));
        client.setActivityEndDate(row.getString("activityEndDate"));
        client.setActive(row.getString("active"));
        client.setCDateTime(row.getString("cDateTime"));
        client.setSiretCode(row.getString("siretCode"));
        client.setUsualName(row.getString("usualName"));
        client.setPhone(row.getString("phone"));
        client.setValidPhone(row.getString("validPhone"));
        client.setSecondPhone(row.getString("secondPhone"));
        client.setValidSecondPhone(row.getString("validSecondPhone"));
        client.setFax(row.getString("fax"));
        client.setValidFax(row.getString("validFax"));
        client.setEmail(row.getString("email"));
        client.setValidEmail(row.getString("validEmail"));
        client.setWebSite1(row.getString("webSite1"));
        client.setWebSite2(row.getString("webSite2"));
        client.setWebSite3(row.getString("webSite3"));
//        client.setWebSite4(row.getString("webSite4"));
//        client.setWebSite5(row.getString("webSite5"));
//        client.setAdd2(row.getString("add2"));
//        client.setAdd3(row.getString("add3"));
//        client.setAdd4(row.getString("add4"));
//        client.setAdd5(row.getString("add5"));
//        client.setAdd6(row.getString("add6"));
//        client.setAdd7(row.getString("add7"));
//        client.setName1(row.getString("name1"));
        client.setSiren(row.getString("siren"));

        return client;

    }
}
